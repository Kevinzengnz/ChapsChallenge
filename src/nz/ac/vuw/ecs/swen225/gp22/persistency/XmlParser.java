package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

/**
 * @author Leon Zhou
 * 300578231
 */
public class XmlParser {

    private static int levelNumber;
    private static int timeLeft;

    /**
     * This method is used to parse the xml file and return the document
     *
     * @param url the xml file to be parsed
     * @return the document of the xml file
     */
    public static Document parse(File url) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(url);
    }

    public static Element getTilesElement(Model m){
        Element Tiles = DocumentHelper.createElement("Tiles");
        for (Entity e : m.entities()) {
            String name = e.getSpriteName();
            if (e instanceof InfoTile) {
                Tiles.addElement(name)
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("text", ((InfoTile) e).getText());
            } else if (e instanceof Player) {
                ArrayList<String> inventory = new ArrayList<>();
                for (Key item : ((Player) e).getKeys()) {
                    inventory.add(item.getSpriteName());
                }

                Tiles.addElement(name)
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("inventory", inventory.toString())
                        .addAttribute("treasure", String.valueOf(((Player) e).getTreasureCollected()));
            } else {
                Tiles.addElement(name)
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()));
            }
        }
        return Tiles;
    }

    /**
     * This function saves the current game to a xml file
     * @param m Current model
     */
    public static void saveGame(Model m, String levelName) throws IOException {
        //print the list of entities
        //add the list of entities to the Tiles element
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");
        root.add(getTilesElement(m));

        //add level number to the xml file
        root.addElement("LEVEL").addAttribute("level", String.valueOf(m.levelNumber()));

        //add time to the xml file
        Element Time = root.addElement("TIME");
        Time.addAttribute("time", String.valueOf(m.timeLeft()));

        // write to a file
        write(document, levelName, "src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/");
    }


    /**
     * This function saves the document to a xml file
     * @param document the document to be saved
     * @param fileName the name of the file
     * @param path the path of the file
     */
    public static void write(Document document, String fileName, String path) throws IOException {
        // write to a file
        FileWriter out = new FileWriter(new File(path, fileName + ".xml"));
        document.write(out);
        out.close();
    }

    public static List<Entity> loadTiles(Element Tiles){
        List<Entity> entities = new ArrayList<>();
        EntityFactory factory = new EntityFactory();
        for (Element e : Tiles.elements()) {
            if (e.getName().equals("INFO")) {
                Entity IT = factory.createEntity(e.getName(),
                        new Point(Integer.parseInt(e.attributeValue("x")), Integer.parseInt(e.attributeValue("y"))));
                ((InfoTile) IT).setText(e.attributeValue("text"));
                entities.add(IT);
            } else if(e.getName().equals("PLAYER_UP") ||e.getName().equals("PLAYER_DOWN") || e.getName().equals("PLAYER_RIGHT") ||e.getName().equals("PLAYER_LEFT") ){
                Entity player = factory.createEntity(e.getName(),
                        new Point(Integer.parseInt(e.attributeValue("x")), Integer.parseInt(e.attributeValue("y"))));
                if (e.attributeValue("inventory") != null) {
                    String[] keys = e.attributeValue("inventory").substring(1, e.attributeValue("inventory").length() - 1).split(", ");
                    ArrayList<Key> keyList = new ArrayList<>();

                    //check if there is any key in the inventory
                    if (!keys[0].equals("")) {
                        for (String key : keys) {
                            keyList.add((Key) factory.createEntity(key, new Point(0, 0)));
                        }
                    }
                    ((Player) player).setKeys(keyList);
                    ((Player) player).setTreasureCollected(Integer.parseInt(e.attributeValue("treasure")));
                }
                entities.add(player);
            }
            else {
                entities.add(factory.createEntity(e.getName(),
                        new Point(Integer.parseInt(e.attributeValue("x")), Integer.parseInt(e.attributeValue("y")))));
            }
        }
        return entities;
    }

    /**
     * Load game from xml file
     * @param path
     * @return the list of entities in the xml file
     */
    public static List<Entity> loadGame(String path) {
        //load file from path
        File file = new File(path);

        //entity list
        List<Entity> entities = new ArrayList<>();
        //Iterate through the xml file and add the entities to the list
        try {
            Document document = parse(file);
            Element root = document.getRootElement();
            Element Tiles = root.element("Tiles");

            entities = loadTiles(Tiles);

            //get the level number from the xml file
            levelNumber = Integer.parseInt(root.element("LEVEL").attributeValue("level"));

            //get the time from the xml file
            timeLeft = Integer.parseInt(root.element("TIME").attributeValue("time"));

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return entities;
    }

    /**
     * This function returns the level number
     * @return the level number
     */
    public static int getLevel() {
        return levelNumber;
    }

    /**
     * This function returns the time
     * @return the time
     */
    public static int getTime() {
        return timeLeft;
    }

    public static void main(String[] args){
        ServiceLoader<ExtraActor> loader = ServiceLoader.load(ExtraActor.class);

        Iterator<ExtraActor> iterator = loader.iterator();



        HashMap<String, ExtraActor> services = new HashMap<>();
        for (ExtraActor service : loader) {
            System.out.println(service.getClass().getName());
            services.put(service.getClass().getName(), service);
        }
        System.out.println("Services " + services.size());
    }
}

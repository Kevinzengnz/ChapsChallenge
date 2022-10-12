package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sprite;
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

    /**
     * This method is used to parse the xml file and return the document
     *
     * @param url the xml file to be parsed
     * @return the document of the xml file
     */
    public static Document parse(File url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }

    /**
     * This function saves the current game to a xml file
     * @param entities the list of entities in the current game
     */
    public static void saveGame(List<Entity> entities, String levelName) throws IOException {
        //print the list of entities
        //add the list of entities to the Tiles element
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");
        Element Tiles = root.addElement("Tiles");

        //iterate and add the entities to the Tiles element
        for (Entity e : entities) {
            String name = e.getSprite();
            if (e instanceof InfoTile) {
                Tiles.addElement(name)
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("text", ((InfoTile) e).getText());
            } else if (e instanceof Player) {
                ArrayList<String> inventory = new ArrayList<>();
                for (Key item : ((Player) e).getKeys()) {
                    inventory.add(item.getSprite());
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
                        for (String key : keys) {
                            Key key1 = (Key) factory.createEntity(key, new Point(0, 0));
                            // if (!key.equals("")) {((Player) player).addKey(key1);}
                            System.out.println(key);
                        }

                    }
                    entities.add(player);

                }
                else {
                    entities.add(factory.createEntity(e.getName(),
                            new Point(Integer.parseInt(e.attributeValue("x")), Integer.parseInt(e.attributeValue("y")))));
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return entities;
    }
}

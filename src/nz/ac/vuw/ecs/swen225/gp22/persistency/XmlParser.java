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
            if (e instanceof WallTile) {
                Tiles.addElement("WallTile")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("sprite", String.valueOf(e.getSprite()));
            } else if (e instanceof FloorTile) {
                Tiles.addElement("FloorTile")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("sprite", String.valueOf(e.getSprite()));
            } else if (e instanceof Key) {
                Tiles.addElement("Key")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("colour", String.valueOf(((Key) e).getColour()));
                       // .addAttribute("depth", String.valueOf(e.getDepth()));
            } else if (e instanceof LockedDoor) {
                Tiles.addElement("LockedDoor")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("colour", String.valueOf(((LockedDoor) e).getColour()));
            } else if (e instanceof InfoTile) {
                Tiles.addElement("InfoTile")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("sprite", String.valueOf(e.getSprite()));
            } else if (e instanceof Treasure) {
                Tiles.addElement("Treasure")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("sprite", String.valueOf(e.getSprite()))
                        .addAttribute("depth", String.valueOf(e.getDepth()));
            } else if (e instanceof ExitDoor) {
                Tiles.addElement("ExitDoor")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("sprite", String.valueOf(e.getSprite()));
            } else if (e instanceof Exit) {
                Tiles.addElement("Exit")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("sprite", String.valueOf(e.getSprite()))
                        .addAttribute("depth", String.valueOf(e.getDepth()));
            } else if (e instanceof Player) {
                Element player = Tiles.addElement("Player")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("direction", String.valueOf(((Player) e).getDirection()))
                        .addAttribute("treasure", String.valueOf(((Player) e).getTreasureCollected()));
                Element keys = player.addElement("Keys");
                //write arraylist of keys to extra element
                for (Key k : ((Player) e).getKeys()) {
                    keys.addElement("Key")
                            .addAttribute("sprite", String.valueOf(k.getSprite()));
                }
            }

            // write to a file
            write(document, levelName, "src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/");
        }
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
            List<Element> tiles = Tiles.elements();
            for (Element e : tiles) {
                if (e.getName().equals("Player")) {
                    int x = Integer.parseInt(e.attributeValue("x"));
                    int y = Integer.parseInt(e.attributeValue("y"));
                    Player player = new Player(new Point(x, y));
                    player.setDirection(Direction.valueOf(e.attributeValue("direction")));

                    //key list to player
                    List<Key> keys = new ArrayList<>();
                    List<Element> keyElements = e.element("Keys").elements();
                    for (Element key : keyElements){

                    }
                    //player.setKeys(keys);

                    entities.add(player);
                } else if (e.getName().equals("WallTile")) {
                    int x = Integer.parseInt(e.attributeValue("x"));
                    int y = Integer.parseInt(e.attributeValue("y"));
                    WallTile wallTile = new WallTile(new Point(x, y));
                    entities.add(wallTile);
                } else if (e.getName().equals("FloorTile")){
                    int x = Integer.parseInt(e.attributeValue("x"));
                    int y = Integer.parseInt(e.attributeValue("y"));
                    FloorTile floorTile = new FloorTile(new Point(x, y));
                    entities.add(floorTile);
                }
                else if (e.getName().equals("Key")){
                    int x = Integer.parseInt(e.attributeValue("x"));
                    int y = Integer.parseInt(e.attributeValue("y"));
                    String colour = e.attributeValue("colour");
                    Key key = new Key(colour, new Point(x, y));
                    entities.add(key);
                }
                else if (e.getName().equals("LockedDoor")){
                    int x = Integer.parseInt(e.attributeValue("x"));
                    int y = Integer.parseInt(e.attributeValue("y"));
                    String colour = e.attributeValue("colour");
                    LockedDoor lockedDoor = new LockedDoor(colour, new Point(x, y));
                    entities.add(lockedDoor);
                }
                else if (e.getName().equals("InfoTile")){
                    int x = Integer.parseInt(e.attributeValue("x"));
                    int y = Integer.parseInt(e.attributeValue("y"));
                    InfoTile infoTile = new InfoTile(new Point(x, y), "");
                    entities.add(infoTile);
                }
                else if (e.getName().equals("Treasure")){
                    int x = Integer.parseInt(e.attributeValue("x"));
                    int y = Integer.parseInt(e.attributeValue("y"));
                    Treasure treasure = new Treasure(new Point(x, y));
                    entities.add(treasure);
                }
                else if (e.getName().equals("ExitDoor")){
                    int x = Integer.parseInt(e.attributeValue("x"));
                    int y = Integer.parseInt(e.attributeValue("y"));
                    ExitDoor exitDoor = new ExitDoor(new Point(x, y));
                    entities.add(exitDoor);
                }
                else if (e.getName().equals("Exit")){
                    int x = Integer.parseInt(e.attributeValue("x"));
                    int y = Integer.parseInt(e.attributeValue("y"));
                    Exit exit = new Exit(new Point(x, y));
                    entities.add(exit);
                }


            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return entities;
    }

    /**
     * Test saving
     * @param args
     */
    public static void main(String[] args){
        List<Entity> entities = new ArrayList<>();
        for (int i=0;i<20;i++) {
            for (int j=0;j<20;j++) {
                if (i == 0 || j == 0 || i==19 || j == 19) {
                    entities.add(new Tile(Sprite.WALL, new Point(i, j)));
                }
                else {
                    entities.add(new Tile(Sprite.FLOOR, new Point(i, j)));
                }
            }
        }

        try {
            saveGame(entities, "test");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //load game
        loadGame("src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/test.xml");

    }
}

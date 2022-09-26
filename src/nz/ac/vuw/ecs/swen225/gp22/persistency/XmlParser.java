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
        for (Entity e : entities){
            if (e instanceof Player){
                Tiles.addElement("Player")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("direction", String.valueOf(((Player) e).getDirection()));
            }
            else if (e instanceof Tile){
                Tiles.addElement("Tile")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("sprite", String.valueOf(e.getSprite()));
            }
            else {
                Tiles.addElement("I dont know what this is")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("sprite", String.valueOf(e.getSprite()));
            }
        }

        // write to a file
        write(document,levelName,"src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/");
    }

    /**
     * This function saves the document to a xml file
     */
    public static void write(Document document, String fileName, String path) throws IOException {
        // write to a file
        FileWriter out = new FileWriter(new File(path, fileName));
        document.write(out);
        out.close();
    }

    /**
     * Load game from xml file
     *
     * @param path
     */
    public static void loadGame(String path) {
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
                    entities.add(player);
                } else if (e.getName().equals("Tile")) {
                    int x = Integer.parseInt(e.attributeValue("x"));
                    int y = Integer.parseInt(e.attributeValue("y"));
                    String sprite = e.attributeValue("sprite");
                    Tile tile = new Tile(Sprite.valueOf(sprite), new Point(x, y));
                    entities.add(tile);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        //print the list to check if it's correct
        for (Entity e : entities) {
            System.out.println(e.getSprite() + " " + e.getPoint().x() + " " + e.getPoint().y());
        }

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
        XmlParser parser = new XmlParser();

        try {
            parser.saveGame(entities, "test");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //load game
        parser.loadGame("src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/test.xml");

    }
}

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
    public Document parse(File url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }

    /**
     * This function saves the current game to a xml file
     * @param entities the list of entities in the current game
     */
    public void saveGame(List<Entity> entities, String levelName) throws IOException {
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
            if (e instanceof Tile){
                Tiles.addElement("Tile")
                        .addAttribute("x", String.valueOf(e.getPoint().x()))
                        .addAttribute("y", String.valueOf(e.getPoint().y()))
                        .addAttribute("sprite", String.valueOf(e.getSprite()));
            }
        }

        //write the document to a file
        try {
            FileWriter out = new FileWriter(new File("src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/", levelName+ ".xml"));
            document.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Load game from xml file
     *
     * @param path
     */
    public void loadGame(String path) {
        //load file from path
        File file = new File(path);

        //entity list /tiles
        List<Entity> entities = new ArrayList<>();



        try {
            Document document = parse(file);
            System.out.println(document.asXML());
        } catch (DocumentException e) {
            e.printStackTrace();
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

    }
}

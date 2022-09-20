package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.app.*;
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
     * @param entity the list of entities in the current game
     */
    public void saveGame(List<Entity> entity){
        //print the list of entities
        for (Entity e : entity){
            System.out.println(e);
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
}

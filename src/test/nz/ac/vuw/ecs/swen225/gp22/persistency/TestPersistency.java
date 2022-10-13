package test.nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import nz.ac.vuw.ecs.swen225.gp22.recorder.GameRecorder;
import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestPersistency {

    /**
     * Load a document
     */
    @Test public void LoadTest(){
        File file = new File("src/test/nz/ac/vuw/ecs/swen225/gp22/persistency/Level1Test.xml");
        try {
            Document doc = XmlParser.parse(file);
            assert doc != null;

        } catch (Exception e) {
            Assert.fail("Failed to load the document");
        }
    }

    /**
     * Loading a non-existent file
     */
    @Test public void LoadFailTest() {
        File file = new File("src/test/nz/ac/vuw/ecs/swen225/gp22/persistency/Level1TestFail.xml");
        try {
            XmlParser.parse(file);
            Assert.fail("Loaded a document that should not be loaded");
        } catch (Exception e) {

        }
    }

    /**
     * Check the entity is loaded correctly
     */
    @Test public void GetTilesTest() {
        //load the document
        List<Entity> list = XmlParser.loadGame("src/test/nz/ac/vuw/ecs/swen225/gp22/persistency/TestEntities.xml");

        List<String> match = Arrays.asList("PLAYER_DOWN", "INFO", "FLOOR");

        //assert the list is matched
        for (int i = 0; i < list.size(); i++) {
            assert list.get(i).getSpriteName().equals(match.get(i));
        }
    }

    /**
     * Get the time left
     */
    @Test public void GetTimeLeftTest() {
        //load the document
        XmlParser.loadGame("src/test/nz/ac/vuw/ecs/swen225/gp22/persistency/TestEntities.xml");

        //assert the time left is correct
        assert XmlParser.getTime() == 100;

        //check the level number is 1
        assert XmlParser.getLevel() == 1;
    }
}

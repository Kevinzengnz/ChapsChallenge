package test.nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XmlParser;
import nz.ac.vuw.ecs.swen225.gp22.recorder.GameRecorder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    /**
     * load a broken document
     * @throws Exception
     */
    @Test public void LoadBrokenTest() throws Exception {

        XmlParser.loadGame("src/test/nz/ac/vuw/ecs/swen225/gp22/persistency/Broken.xml");
    }

    /**
     * Save game
     */
    @Test public void SaveGameTest(){
        //create enitities
        List<Entity> entities = XmlParser.loadGame("src/test/nz/ac/vuw/ecs/swen225/gp22/persistency/Level1Test.xml");

        //create a model
        Model model = makeModel(entities);

        //save the game
        try {
            XmlParser.saveGame(model, "testSave");
        } catch (Exception e) {
            Assert.fail("Failed to save the game");
        }
    }

    /** load a game with 0 tiles
     *
     */
    @Test public void LoadEmptyTest() {
        //load the document
        List<Entity> list = XmlParser.loadGame("src/test/nz/ac/vuw/ecs/swen225/gp22/persistency/NoPlayer.xml");

        //assert the list is empty
        assert list.size() == 0;
    }

    /**
     * Load each player direction
     */
    @Test public void LoadPlayerTest() {
        //load the document
        List<Entity> list = XmlParser.loadGame("src/test/nz/ac/vuw/ecs/swen225/gp22/persistency/EveryPlayer.xml");

        //assert the list is matched
        assert list.get(0).getSpriteName().equals("PLAYER_DOWN");
    }

    /**
     * Makes model for testing.
     *
     * @param gameEntities entities of test game
     * @return model to test with
     */
    public Model makeModel(List<Entity> gameEntities) {
        var m = new Model() {
            List<Entity> entities = gameEntities;

            @Override
            public int timeLeft() {
                return 30;
            }

            @Override
            public void decrementTime() {}

            @Override
            public Player player() {
                return null;
            }

            @Override
            public List<Entity> entities() {
                return entities;
            }

            @Override
            public GameRecorder recorder() {
                return null;
            }

            @Override
            public void remove(Entity e) {
                entities = entities.stream()
                        .filter(ei -> !ei.equals(e))
                        .toList();
            }

            @Override
            public void onGameOver() {}

            @Override
            public void onNextLevel() {}

            @Override
            public long totalTreasures() {
                return 1;
            }

            @Override
            public int levelNumber() {
                return 0;
            }
        };
        return m;
    }
}

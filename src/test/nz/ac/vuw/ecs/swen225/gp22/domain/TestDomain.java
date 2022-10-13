package test.nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.recorder.GameRecorder;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Audio;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the Domain Package.
 *
 * @author Alicia Robinson - 300560663
 */
public class TestDomain {

    /**
     * Testing Invalid Arguments for Entity Factory.
     */

    @Test public void EntityFactoryInvalidType() {
        EntityFactory entityFactory = new EntityFactory();
        try {
            entityFactory.createEntity("WINDOW", new Point(2, 4));
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test public void EntityFactoryNullType() {
        EntityFactory entityFactory = new EntityFactory();
        try {
            entityFactory.createEntity(null, new Point(2, 4));
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test public void EntityFactoryEmptyType() {
        EntityFactory entityFactory = new EntityFactory();
        try {
            entityFactory.createEntity("", new Point(2, 4));
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test public void EntityFactoryNullPoint() {
        EntityFactory entityFactory = new EntityFactory();
        try {
            entityFactory.createEntity("EXIT", null);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * Testing Valid Arguments for Entity Factory.
     */

    @Test public void EntityFactoryCreatePlayerUp() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Player;
    }

    @Test public void EntityFactoryCreatePlayerDown() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("PLAYER_DOWN", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Player;
    }

    @Test public void EntityFactoryCreatePlayerLeft() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("PLAYER_LEFT", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Player;
    }

    @Test public void EntityFactoryCreatePlayerRight() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("PLAYER_RIGHT", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Player;
    }

    @Test public void EntityFactoryCreateRobotDown() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("ROBOT_DOWN", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Robot;
    }

    @Test public void EntityFactoryCreateRobotUp() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("ROBOT_UP", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Robot;
    }

    @Test public void EntityFactoryCreateRobotLeft() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("ROBOT_LEFT", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Robot;
    }

    @Test public void EntityFactoryCreateRobotRight() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("ROBOT_RIGHT", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Robot;
    }

    @Test public void EntityFactoryCreateSewage() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("SEWAGE", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof SewageTile;
    }

    @Test public void EntityFactoryCreateExit() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("EXIT", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Exit;
    }

    @Test public void EntityFactoryCreateExitDoor() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("DOOR_EXIT", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof ExitDoor;
    }

    @Test public void EntityFactoryCreateFloor() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("FLOOR", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof FloorTile;
    }

    @Test public void EntityFactoryCreateInfo() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("INFO", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof InfoTile;
    }

    @Test public void EntityFactoryCreateKeyYellow() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("KEY_YELLOW", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Key;
        Assert.assertEquals("YELLOW", ((Key) entity).getColour());
    }

    @Test public void EntityFactoryCreateKeyBlue() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("KEY_BLUE", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Key;
        Assert.assertEquals("BLUE", ((Key) entity).getColour());
    }

    @Test public void EntityFactoryCreateKeyGreen() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("KEY_GREEN", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Key;
        Assert.assertEquals("GREEN", ((Key) entity).getColour());
    }

    @Test public void EntityFactoryCreateKeyRed() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("KEY_RED", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Key;
        Assert.assertEquals("RED", ((Key) entity).getColour());
    }

    @Test public void EntityFactoryCreateDoorYellow() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("DOOR_YELLOW", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Door;
        Assert.assertEquals("YELLOW", ((LockedDoor) entity).getColour());
    }

    @Test public void EntityFactoryCreateDoorBlue() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("DOOR_BLUE", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Door;
        Assert.assertEquals("BLUE", ((LockedDoor) entity).getColour());
    }

    @Test public void EntityFactoryCreateDoorGreen() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("DOOR_GREEN", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Door;
        Assert.assertEquals("GREEN", ((LockedDoor) entity).getColour());
    }

    @Test public void EntityFactoryCreateDoorRed() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("DOOR_RED", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Door;
        Assert.assertEquals("RED", ((LockedDoor) entity).getColour());
    }

    @Test public void EntityFactoryCreateWall() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("WALL", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof WallTile;
    }

    @Test public void EntityFactoryCreateTreasure() {
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("TREASURE", new Point(1, 1));
        Assert.assertNotNull(entity);
        assert entity instanceof Treasure;
    }

    /**
     * Actor Method Tests with valid inputs
     */

    @Test public void ActorMethodSetMoving() {
        EntityFactory entityFactory = new EntityFactory();
        Actor entity = (Actor) entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        entity.setMoving(false);
        Assert.assertFalse(entity.isMoving());
        entity.setMoving(true);
        Assert.assertTrue(entity.isMoving());
    }

    @Test public void ActorMethodSetDirection() {
        EntityFactory entityFactory = new EntityFactory();
        Actor entity = (Actor) entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        entity.setDirection(Direction.Up);
        Assert.assertEquals(entity.getDirection(), Direction.Up);
        entity.setDirection(Direction.Down);
        Assert.assertEquals(entity.getDirection(), Direction.Down);
        entity.setDirection(Direction.Left);
        Assert.assertEquals(entity.getDirection(), Direction.Left);
        entity.setDirection(Direction.Right);
        Assert.assertEquals(entity.getDirection(), Direction.Right);
    }

    @Test public void ActorMethodGetSprite() {
        EntityFactory entityFactory = new EntityFactory();
        Actor entity = (Actor) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 1));
        Assert.assertEquals("PLAYER_DOWN", entity.getSpriteName());
    }

    @Test public void ActorMethodGetPoint() {
        EntityFactory entityFactory = new EntityFactory();
        Actor entity = (Actor) entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        Assert.assertEquals(entity.getPoint(), new Point(1, 1));
    }

    @Test public void ActorMethodGetDepth() {
        EntityFactory entityFactory = new EntityFactory();
        Actor entity = (Actor) entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        Assert.assertEquals(entity.getDepth(), 2);
    }

    /**
     * Actor Method Tests with invalid inputs.
     */

    @Test public void ActorMethodSetNullDirection() {
        EntityFactory entityFactory = new EntityFactory();
        Actor entity = (Actor) entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        try {
            entity.setDirection(null);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * Collectable Method Tests with valid inputs.
     */

    @Test public void CollectableMethodSetSoundEffect() {
        EntityFactory entityFactory = new EntityFactory();
        Collectable entity = (Collectable) entityFactory.createEntity("TREASURE", new Point(1, 1));
        entity.setSoundEffect(Audio.getSoundPlayer("TREASURE"));
        assert entity.soundEffect != null;
    }

    @Test public void CollectableMethodGetSprite() {
        EntityFactory entityFactory = new EntityFactory();
        Collectable entity = (Collectable) entityFactory.createEntity("TREASURE", new Point(1, 1));
        Assert.assertEquals("TREASURE", entity.getSpriteName());
    }

    @Test public void CollectableMethodGetPoint() {
        EntityFactory entityFactory = new EntityFactory();
        Collectable entity = (Collectable) entityFactory.createEntity("TREASURE", new Point(1, 1));
        Assert.assertEquals(new Point(1, 1), entity.getPoint());
    }

    @Test public void CollectableMethodGetDepth() {
        EntityFactory entityFactory = new EntityFactory();
        Collectable entity = (Collectable) entityFactory.createEntity("TREASURE", new Point(1, 1));
        Assert.assertEquals(1, entity.getDepth());
    }

    /**
     * Collectable Method Tests with invalid inputs.
     */

    @Test public void CollectableMethodSetSoundEffectNull() {
        EntityFactory entityFactory = new EntityFactory();
        Collectable entity = (Collectable) entityFactory.createEntity("TREASURE", new Point(1, 1));
        try {
            entity.setSoundEffect(null);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * Door Method Tests with valid inputs.
     */

    @Test public void DoorMethodSetSoundEffect() {
        EntityFactory entityFactory = new EntityFactory();
        Door entity = (Door) entityFactory.createEntity("DOOR_YELLOW", new Point(1, 1));
        entity.setSoundEffect(Audio.getSoundPlayer("DOOR"));
        assert entity.soundEffect != null;
    }

    @Test public void DoorMethodGetSprite() {
        EntityFactory entityFactory = new EntityFactory();
        Door entity = (Door) entityFactory.createEntity("DOOR_YELLOW", new Point(1, 1));
        Assert.assertEquals("DOOR_YELLOW", entity.getSpriteName());
    }

    @Test public void DoorMethodGetPoint() {
        EntityFactory entityFactory = new EntityFactory();
        Door entity = (Door) entityFactory.createEntity("DOOR_YELLOW", new Point(1, 1));
        Assert.assertEquals(new Point(1, 1), entity.getPoint());
    }

    @Test public void DoorMethodGetDepth() {
        EntityFactory entityFactory = new EntityFactory();
        Door entity = (Door) entityFactory.createEntity("DOOR_YELLOW", new Point(1, 1));
        Assert.assertEquals(1, entity.getDepth());
    }

    /**
     * Collectable Method Tests with invalid inputs.
     */

    @Test public void DoorMethodSetSoundEffectNull() {
        EntityFactory entityFactory = new EntityFactory();
        Door entity = (Door) entityFactory.createEntity("DOOR_YELLOW", new Point(1, 1));
        try {
            entity.setSoundEffect(null);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * Exit Method Tests with valid inputs.
     */

    @Test public void ExitMethodSetSoundEffect() {
        EntityFactory entityFactory = new EntityFactory();
        Exit entity = (Exit) entityFactory.createEntity("EXIT", new Point(1, 1));
        entity.setSoundEffect(Audio.getSoundPlayer("EXIT"));
        assert entity.soundEffect != null;
    }

    @Test public void ExitMethodGetSprite() {
        EntityFactory entityFactory = new EntityFactory();
        Exit entity = (Exit) entityFactory.createEntity("EXIT", new Point(1, 1));
        Assert.assertEquals("EXIT", entity.getSpriteName());
    }

    @Test public void ExitMethodGetPoint() {
        EntityFactory entityFactory = new EntityFactory();
        Exit entity = (Exit) entityFactory.createEntity("EXIT", new Point(1, 1));
        Assert.assertEquals(new Point(1, 1), entity.getPoint());
    }

    @Test public void ExitMethodGetDepth() {
        EntityFactory entityFactory = new EntityFactory();
        Exit entity = (Exit) entityFactory.createEntity("EXIT", new Point(1, 1));
        Assert.assertEquals(1, entity.getDepth());
    }

    /**
     * Collectable Method Tests with invalid inputs.
     */

    @Test public void ExitMethodSetSoundEffectNull() {
        EntityFactory entityFactory = new EntityFactory();
        Exit entity = (Exit) entityFactory.createEntity("EXIT", new Point(1, 1));
        try {
            entity.setSoundEffect(null);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * InfoTile Method Tests with valid inputs.
     */

    @Test public void InfoMethodSetSoundEffect() {
        EntityFactory entityFactory = new EntityFactory();
        InfoTile entity = (InfoTile) entityFactory.createEntity("INFO", new Point(1, 1));
        entity.setSoundEffect(Audio.getSoundPlayer("INFO"));
        assert entity.soundEffect != null;
    }

    @Test public void InfoMethodGetSprite() {
        EntityFactory entityFactory = new EntityFactory();
        InfoTile entity = (InfoTile) entityFactory.createEntity("INFO", new Point(1, 1));
        Assert.assertEquals("INFO", entity.getSpriteName());
    }

    @Test public void InfoMethodGetPoint() {
        EntityFactory entityFactory = new EntityFactory();
        InfoTile entity = (InfoTile) entityFactory.createEntity("INFO", new Point(1, 1));
        Assert.assertEquals(new Point(1, 1), entity.getPoint());
    }

    @Test public void InfoMethodGetDepth() {
        EntityFactory entityFactory = new EntityFactory();
        InfoTile entity = (InfoTile) entityFactory.createEntity("INFO", new Point(1, 1));
        Assert.assertEquals(1, entity.getDepth());
    }

    @Test public void InfoMethodGetText() {
        EntityFactory entityFactory = new EntityFactory();
        InfoTile entity = (InfoTile) entityFactory.createEntity("INFO", new Point(1, 1));
        entity.setText("Text");
        Assert.assertEquals("Text", entity.getText());
    }

    /**
     * Collectable Method Tests with invalid inputs.
     */

    @Test public void InfoMethodSetSoundEffectNull() {
        EntityFactory entityFactory = new EntityFactory();
        InfoTile entity = (InfoTile) entityFactory.createEntity("INFO", new Point(1, 1));
        try {
            entity.setSoundEffect(null);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test public void InfoMethodSetTextNull() {
        EntityFactory entityFactory = new EntityFactory();
        InfoTile entity = (InfoTile) entityFactory.createEntity("INFO", new Point(1, 1));
        try {
            entity.setText(null);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test public void InfoMethodSetTextEmpty() {
        EntityFactory entityFactory = new EntityFactory();
        InfoTile entity = (InfoTile) entityFactory.createEntity("INFO", new Point(1, 1));
        try {
            entity.setText("");
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * Key Method Tests with valid inputs.
     */

    @Test public void KeyMethodEquals() {
        EntityFactory entityFactory = new EntityFactory();
        Key entity = (Key) entityFactory.createEntity("KEY_YELLOW", new Point(1, 1));
        Key entity2 = (Key) entityFactory.createEntity("KEY_YELLOW", new Point(1, 1));
        Assert.assertTrue(entity.equals(entity2));
    }

    @Test public void KeyMethodNotEquals() {
        EntityFactory entityFactory = new EntityFactory();
        Key entity = (Key) entityFactory.createEntity("KEY_YELLOW", new Point(1, 1));
        Key entity2 = (Key) entityFactory.createEntity("KEY_BLUE", new Point(1, 1));
        Assert.assertFalse(entity.equals(entity2));
    }

    /**
     * Player Method Tests with valid inputs.
     */

    @Test public void PlayerMethodGetTreasure() {
        EntityFactory entityFactory = new EntityFactory();
        Player entity = (Player) entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        Assert.assertEquals(0, entity.getTreasureCollected());
    }

    @Test public void PlayerMethodKeys() {
        EntityFactory entityFactory = new EntityFactory();
        Player entity = (Player) entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        Key key1 = (Key) entityFactory.createEntity("KEY_YELLOW", new Point(1, 1));
        Key key2 = (Key) entityFactory.createEntity("KEY_BLUE", new Point(1, 1));
        List<Key> keys = Arrays.asList(key1, key2);
        entity.setKeys(keys);
        Assert.assertEquals(keys, entity.getKeys());
    }

    @Test public void PlayerMethodTreasures() {
        EntityFactory entityFactory = new EntityFactory();
        Player entity = (Player) entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        entity.setTreasureCollected(3);
        Assert.assertEquals(3, entity.getTreasureCollected());
    }

    @Test public void ActorMethodGetMultipleSprites() {
        EntityFactory entityFactory = new EntityFactory();
        Player entity = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 1));
        Assert.assertEquals("PLAYER_DOWN", entity.getSpriteName());
        entity.setDirection(Direction.Up);
        Assert.assertEquals("PLAYER_UP", entity.getSpriteName());
        entity.setDirection(Direction.Left);
        Assert.assertEquals("PLAYER_LEFT", entity.getSpriteName());
        entity.setDirection(Direction.Right);
        Assert.assertEquals("PLAYER_RIGHT", entity.getSpriteName());
        entity.setDirection(Direction.None);
        Assert.assertEquals("PLAYER_RIGHT", entity.getSpriteName());
    }

    /**
     * Player Method Tests with invalid inputs.
     */

    @Test public void PlayerMethodSetKeysNull(){
        EntityFactory entityFactory = new EntityFactory();
        Player entity = (Player) entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        try {
            entity.setKeys(null);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test public void PlayerMethodTreasuresNegative() {
        EntityFactory entityFactory = new EntityFactory();
        Player entity = (Player) entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        try {
            entity.setTreasureCollected(-1);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * Robot Method Tests with valid inputs
     */

    @Test public void RobotMethodSetSoundEffect() {
        EntityFactory entityFactory = new EntityFactory();
        Robot entity = (Robot) entityFactory.createEntity("ROBOT_LEFT", new Point(1, 1));
        entity.setSoundEffect(Audio.getSoundPlayer("ROBOT"));
        assert entity.soundEffect != null;
    }

    /**
     * Robot Method Tests with invalid inputs.
     */

    @Test public void RobotMethodSetSoundEffectNull() {
        EntityFactory entityFactory = new EntityFactory();
        Robot entity = (Robot) entityFactory.createEntity("ROBOT_LEFT", new Point(1, 1));
        try {
            entity.setSoundEffect(null);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * Sewage Method Tests with valid inputs.
     */

    @Test public void SewageMethodSetSoundEffect() {
        EntityFactory entityFactory = new EntityFactory();
        SewageTile entity = (SewageTile) entityFactory.createEntity("SEWAGE", new Point(1, 1));
        entity.setSoundEffect(Audio.getSoundPlayer("SEWAGE"));
        assert entity.soundEffect != null;
    }

    /**
     * Sewage Method Tests with invalid inputs.
     */

    @Test public void SewageMethodSetSoundEffectNull() {
        EntityFactory entityFactory = new EntityFactory();
        SewageTile entity = (SewageTile) entityFactory.createEntity("SEWAGE", new Point(1, 1));
        try {
            entity.setSoundEffect(null);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * Point Method Tests with valid inputs.
     */

    @Test public void PointMethodsTest() {
        Point point = new Point(1, 1);
        Point point2 = new Point(2, 2);
        Point point3 = point.add(point2);
        Assert.assertEquals(point3, new Point(3, 3));
    }

    /**
     * WallTile Method Tests with valid inputs.
     */

    @Test public void WallMethodSetSoundEffect() {
        EntityFactory entityFactory = new EntityFactory();
        WallTile entity = (WallTile) entityFactory.createEntity("WALL", new Point(1, 1));
        entity.setSoundEffect(Audio.getSoundPlayer("WALL"));
        assert entity.soundEffect != null;
    }

    @Test public void WallMethodGetDepth() {
        EntityFactory entityFactory = new EntityFactory();
        WallTile entity = (WallTile) entityFactory.createEntity("WALL", new Point(1, 1));
        entity.setSoundEffect(Audio.getSoundPlayer("WALL"));
    }

    /**
     * WallTile Method Tests with invalid inputs.
     */

    @Test public void WallMethodSetSoundEffectNull() {
        EntityFactory entityFactory = new EntityFactory();
        WallTile entity = (WallTile) entityFactory.createEntity("WALL", new Point(1, 1));
        try {
            entity.setSoundEffect(null);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * Player ping method valid
     */

    @Test public void PlayerPingMethodKey() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        Key key = (Key) entityFactory.createEntity("KEY_YELLOW", new Point(1, 3));
        key.setSoundEffect(Audio.getSoundPlayer("KEY"));
        player.setMoving(true);
        List<Entity> GameEntities = Arrays.asList(key, player);
        Model m = makeModel(GameEntities);
        player.ping(m);
        assert player.getPoint().equals(new Point(1, 3));
    }

    @Test public void PlayerPingMethodTreasure() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        Treasure treasure = (Treasure) entityFactory.createEntity("TREASURE", new Point(1, 3));
        treasure.setSoundEffect(Audio.getSoundPlayer("TREASURE"));
        player.setMoving(true);
        List<Entity> GameEntities = Arrays.asList(treasure, player);
        Model m = makeModel(GameEntities);
        player.ping(m);
        assert player.getPoint().equals(new Point(1, 3));
    }

    @Test public void PlayerPingMethodWall() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        WallTile wall = (WallTile) entityFactory.createEntity("WALL", new Point(1, 3));
        wall.setSoundEffect(Audio.getSoundPlayer("WALL"));
        player.setMoving(true);
        List<Entity> GameEntities = Arrays.asList(wall, player);
        Model m = makeModel(GameEntities);
        player.ping(m);
        assert player.getPoint().equals(new Point(1, 2));
    }

    @Test public void PlayerPingMethodLockedDoor() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        LockedDoor door = (LockedDoor) entityFactory.createEntity("DOOR_YELLOW", new Point(1, 3));
        door.setSoundEffect(Audio.getSoundPlayer("DOOR"));
        player.setMoving(true);
        List<Entity> GameEntities = Arrays.asList(door, player);
        Model m = makeModel(GameEntities);
        player.ping(m);
        assert player.getPoint().equals(new Point(1, 2));
    }

    @Test public void PlayerPingMethodLockedDoorOpen() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        LockedDoor door = (LockedDoor) entityFactory.createEntity("DOOR_YELLOW", new Point(1, 3));
        Key key = (Key) entityFactory.createEntity("KEY_YELLOW", new Point(0, 0));
        key.setSoundEffect(Audio.getSoundPlayer("KEY"));
        List<Key> keys = new ArrayList<>();
        keys.add(key);
        player.setKeys(keys);
        door.setSoundEffect(Audio.getSoundPlayer("DOOR"));
        player.setMoving(true);
        List<Entity> GameEntities = Arrays.asList(door, player);
        Model m = makeModel(GameEntities);
        player.ping(m);
        assert player.getPoint().equals(new Point(1, 3));
        assert !m.entities().contains(door);
        assert !player.getKeys().contains(key);
    }

    @Test public void PlayerPingMethodInfoTile() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        InfoTile info = (InfoTile) entityFactory.createEntity("INFO", new Point(1, 3));
        info.setSoundEffect(Audio.getSoundPlayer("INFO"));
        player.setMoving(true);
        info.setText("Text here");
        List<Entity> GameEntities = Arrays.asList(info, player);
        Model m = makeModel(GameEntities);
        player.ping(m);
        assert player.getPoint().equals(new Point(1, 3));
    }

    @Test public void PlayerPingMethodExit() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        Exit exit = (Exit) entityFactory.createEntity("EXIT", new Point(1, 3));
        exit.setSoundEffect(Audio.getSoundPlayer("EXIT"));
        player.setMoving(true);
        player.setTreasureCollected(10);
        List<Entity> GameEntities = Arrays.asList(exit, player);
        Model m = makeModel(GameEntities);
        player.ping(m);
        assert player.getPoint().equals(new Point(1, 3));
    }

    @Test public void PlayerPingMethodExitDoor() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        ExitDoor exit = (ExitDoor) entityFactory.createEntity("DOOR_EXIT", new Point(1, 3));
        exit.setSoundEffect(Audio.getSoundPlayer("DOOR_EXIT"));
        player.setMoving(true);
        player.setTreasureCollected(10);
        List<Entity> GameEntities = Arrays.asList(exit, player);
        Model m = makeModel(GameEntities);
        player.ping(m);
        assert player.getPoint().equals(new Point(1, 3));
    }

    /**
     * doAction methods invalid input.
     */

    @Test public void DoActionMethodExitDoor() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        ExitDoor exit = (ExitDoor) entityFactory.createEntity("DOOR_EXIT", new Point(1, 3));
        exit.setSoundEffect(Audio.getSoundPlayer("DOOR_EXIT"));
        player.setMoving(true);
        Treasure treasure = (Treasure) entityFactory.createEntity("TREASURE", new Point(1, 3));
        treasure.setSoundEffect(Audio.getSoundPlayer("TREASURE"));
        List<Entity> GameEntities = Arrays.asList(exit, player, treasure);
        Model m = makeModel(GameEntities);
        player.ping(m);
        assert player.getPoint().equals(new Point(1, 2));
    }

    @Test public void DoActionMethodExitDoorPoint() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        ExitDoor exit = (ExitDoor) entityFactory.createEntity("DOOR_EXIT", new Point(1, 3));
        exit.setSoundEffect(Audio.getSoundPlayer("DOOR_EXIT"));
        player.setMoving(true);
        List<Entity> GameEntities = Arrays.asList(exit, player);
        Model m = makeModel(GameEntities);
        try {
            exit.doAction(m, player, new Point(3, 7));
        }catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test public void DoActionMethodExit() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        Exit exit = (Exit) entityFactory.createEntity("EXIT", new Point(1, 3));
        exit.setSoundEffect(Audio.getSoundPlayer("EXIT"));
        player.setMoving(true);
        Treasure treasure = (Treasure) entityFactory.createEntity("TREASURE", new Point(1, 3));
        treasure.setSoundEffect(Audio.getSoundPlayer("TREASURE"));
        List<Entity> GameEntities = Arrays.asList(exit, player, treasure);
        Model m = makeModel(GameEntities);
        try {
            player.ping(m);
        } catch (IllegalStateException e) {
            assert true;
        }
    }

    @Test public void DoActionMethodExitPoint() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        Exit exit = (Exit) entityFactory.createEntity("EXIT", new Point(1, 3));
        exit.setSoundEffect(Audio.getSoundPlayer("EXIT"));
        player.setMoving(true);
        List<Entity> GameEntities = Arrays.asList(exit, player);
        Model m = makeModel(GameEntities);
        try {
            exit.doAction(m, player, new Point(3, 7));
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test public void DoActionMethodKeyPoint() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        Key key = (Key) entityFactory.createEntity("KEY_YELLOW", new Point(1, 3));
        key.setSoundEffect(Audio.getSoundPlayer("KEY"));
        player.setMoving(true);
        List<Entity> GameEntities = Arrays.asList(key, player);
        Model m = makeModel(GameEntities);
        try {
            key.doAction(m, player, new Point(3, 7));
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test public void DoActionMethodWallPoint() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        WallTile wall = (WallTile) entityFactory.createEntity("WALL", new Point(1, 3));
        wall.setSoundEffect(Audio.getSoundPlayer("WALL"));
        player.setMoving(true);
        List<Entity> GameEntities = Arrays.asList(wall, player);
        Model m = makeModel(GameEntities);
        try {
            wall.doAction(m, player, new Point(3, 7));
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test public void DoActionMethodTreasurePoint() {
        EntityFactory entityFactory = new EntityFactory();
        Player player = (Player) entityFactory.createEntity("PLAYER_DOWN", new Point(1, 2));
        Treasure treasure = (Treasure) entityFactory.createEntity("TREASURE", new Point(1, 3));
        treasure.setSoundEffect(Audio.getSoundPlayer("TREASURE"));
        player.setMoving(true);
        List<Entity> GameEntities = Arrays.asList(treasure, player);
        Model m = makeModel(GameEntities);
        try {
            treasure.doAction(m, player, new Point(3, 7));
        }catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * Robot ping method
     */
    @Test public void RobotPingMethodWall() {
        EntityFactory entityFactory = new EntityFactory();
        Robot robot = (Robot) entityFactory.createEntity("ROBOT_RIGHT", new Point(1, 2));
        WallTile wall = (WallTile) entityFactory.createEntity("WALL", new Point(2, 2));
        wall.setSoundEffect(Audio.getSoundPlayer("WALL"));
        robot.setMoving(true);
        List<Entity> GameEntities = Arrays.asList(wall, robot);
        Model m = makeModel(GameEntities);
        robot.ping(m);
        robot.ping(m);
        Assert.assertEquals(robot.getPoint(), new Point(1, 3));
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

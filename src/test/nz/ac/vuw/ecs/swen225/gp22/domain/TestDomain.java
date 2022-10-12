package test.nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestDomain {

    /**
     * Testing Invalid Arguments for Entity Factory
     */

    @Test public void EntityFactoryInvalidType(){
        EntityFactory entityFactory = new EntityFactory();
        try {
            entityFactory.createEntity("WINDOW", new Point(2, 4));
        } catch(IllegalArgumentException e){
            assert true;
        }
    }

    @Test public void EntityFactoryNullType(){
        EntityFactory entityFactory = new EntityFactory();
        try {
            entityFactory.createEntity(null, new Point(2, 4));
        } catch(IllegalArgumentException e){
            assert true;
        }
    }

    @Test public void EntityFactoryEmptyType(){
        EntityFactory entityFactory = new EntityFactory();
        try {
            entityFactory.createEntity("", new Point(2, 4));
        } catch(IllegalArgumentException e){
            assert true;
        }
    }

    @Test public void EntityFactoryNullPoint(){
        EntityFactory entityFactory = new EntityFactory();
        try {
            entityFactory.createEntity("EXIT", null);
        } catch(IllegalArgumentException e){
            assert true;
        }
    }

    /**
     * Testing Valid Arguments for Entity Factory
     */

    @Test public void EntityFactoryCreatePlayerUp(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Player);
    }

    @Test public void EntityFactoryCreatePlayerDown(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("PLAYER_DOWN", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Player);
    }

    @Test public void EntityFactoryCreatePlayerLeft(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("PLAYER_LEFT", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Player);
    }

    @Test public void EntityFactoryCreatePlayerRight(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("PLAYER_UP", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Player);
    }

    @Test public void EntityFactoryCreateExit(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("EXIT", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Exit);
    }

    @Test public void EntityFactoryCreateExitDoor(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("DOOR_EXIT", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof ExitDoor);
    }

    @Test public void EntityFactoryCreateFloor(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("FLOOR", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof FloorTile);
    }

    @Test public void EntityFactoryCreateInfo(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("INFO", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof InfoTile);
    }

    @Test public void EntityFactoryCreateKeyYellow(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("KEY_YELLOW", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Key);
        Assert.assertEquals("YELLOW", ((Key) entity).getColour());
    }

    @Test public void EntityFactoryCreateKeyBlue(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("KEY_BLUE", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Key);
        Assert.assertEquals("BLUE", ((Key) entity).getColour());
    }

    @Test public void EntityFactoryCreateKeyGreen(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("KEY_GREEN", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Key);
        Assert.assertEquals("GREEN", ((Key) entity).getColour());
    }

    @Test public void EntityFactoryCreateKeyRed(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("KEY_RED", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Key);
        Assert.assertEquals("RED", ((Key) entity).getColour());
    }

    @Test public void EntityFactoryCreateDoorYellow(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("DOOR_YELLOW", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Door);
        Assert.assertEquals("YELLOW", ((LockedDoor) entity).getColour());
    }

    @Test public void EntityFactoryCreateDoorBlue(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("DOOR_YELLOW", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Door);
        Assert.assertEquals("YELLOW", ((LockedDoor) entity).getColour());
    }

    @Test public void EntityFactoryCreateDoorGreen(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("DOOR_GREEN", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Door);
        Assert.assertEquals("GREEN", ((LockedDoor) entity).getColour());
    }

    @Test public void EntityFactoryCreateDoorRed(){
        EntityFactory entityFactory = new EntityFactory();
        Entity entity = entityFactory.createEntity("DOOR_RED", new Point(1, 1));
        Assert.assertNotNull(entity);
        Assert.assertTrue(entity instanceof Door);
        Assert.assertEquals("RED", ((LockedDoor) entity).getColour());
    }
}

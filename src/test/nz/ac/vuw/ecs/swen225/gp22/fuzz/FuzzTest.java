package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.awt.AWTException; 
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.SwingUtilities;
import nz.ac.vuw.ecs.swen225.gp22.app.Main;
import org.junit.jupiter.api.Test;

/**
 * FuzzTest class is as the name implies, where fuzz tests are taking place.
 *
 * @author Hayden Curtis
 *       ID:300586379
 */
class FuzzTest {

  /**
   * Robot that will execute the random key and mouse presses.
   */
  private static Robot robot = null;

  /**
   * Random which will be used to generate random keys, points etc.
   */
  private Random random = new Random();
  
  /**
   * Returns a list of possible key inputs.
   */
  private List<Integer> keyList = List.of(KeyEvent.VK_UP, KeyEvent.VK_W,
      KeyEvent.VK_DOWN, KeyEvent.VK_S, 
      KeyEvent.VK_LEFT, KeyEvent.VK_A,
      KeyEvent.VK_RIGHT, KeyEvent.VK_D);
  
  /**
   * List of random clicks that can be made.
   */
  private Pair startR = new Pair(1250, 235);
  private Pair endR = new Pair(1250, 260);
  private Pair pause = new Pair(1250, 285);
  private Pair resume = new Pair(1250, 310);
  private Pair help = new Pair(1250, 340);
  private Pair save = new Pair(1250, 365);
  private Pair load = new Pair(1250, 390);
  private Pair exit = new Pair(1250, 415);
  private Pair loadR = new Pair(1250, 440);
  private Pair autoR = new Pair(1250, 465);
  private Pair stopA = new Pair(1250, 490);
  private Pair nextT = new Pair(1250, 515);
  private List<Pair> clickList = List.of(startR, endR, pause, resume, help,
      save, load, loadR, autoR, stopA, nextT);

  /**
   * Clicks that can be made after loads.
   */
  private Pair open = new Pair(750, 500);
  private List<Pair> loadList = List.of(new Pair(450, 250));
  
  /**
   * Simple record which acts as a x and y point for clicks.
   */
  record Pair(int x, int y) {}

  /**
   * Generates a list of random integers each corresponding to key inputs.
   *
   * @param size of the list
   * @return List of pairs
   */
  private List<Integer> genEvents(int size) {
    int next = 0;
    int prev = 0;
    int times = 0;
    List<Integer> es = new ArrayList<Integer>();
    while (es.size() < size) {
      //Generating the direction, want to be different than previous
      next = random.nextInt(keyList.size());
      if (next == prev) { 
        next = random.nextInt(keyList.size());
      }
      //Run in one direction for random number of steps
      times = random.nextInt(10);
      for (int i = 0; i < times; i++) {
        es.add(keyList.get(next));
      }
      prev = next;
    }
    return es;
  }

  /**
   * Generates a list of Pairs which contain an x and y point.
   *
   * @param size of the generated list
   * @return List of pairs
   */
  private List<Pair> genPoints(int size) {
    Pair next = null;
    List<Pair> es = new ArrayList<Pair>();
    while (es.size() < size) {
      next = clickList.get(random.nextInt(clickList.size()));
      es.add(next);
      if (next == load || next == loadR) {
        next = loadList.get(random.nextInt(loadList.size()));
        es.add(next);
        es.add(open);
      }
    }
    return es;
  }

  /**
   * test1() : fuzz tests for level 1.
   */
  @Test
  public void test1() {
    //Call the creation of the game
    String[] s = {};
    Main.main(s);
    sleep(2000);
    
    //Create Robot to apply clicks and key presses
    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }
    //Load level 1
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_1);
    robot.keyRelease(KeyEvent.VK_1);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    
    sleep(2000);
    //Generating randoms
    var events = genEvents(400);
    var clicks = genPoints(30);
    //Excecute fuzzes
    assertTimeout(Duration.ofMinutes(1), () -> {
      checkMovement(events);
      mouseClicks(clicks);
    });
  }

  /**
   * test2() : fuzz tests for level 2.
   */
  @Test
  public void test2() {
    //Call the creation of the game
    String[] s = {};
    Main.main(s);
    sleep(2000);
    
    //Create Robot to apply clicks and key presses
    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }
    //Load level 2
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_2);
    robot.keyRelease(KeyEvent.VK_2);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    
    sleep(2000);    
    //Generating randoms
    var events = genEvents(400);
    var clicks = genPoints(30);
    //Excecute fuzzes
    assertTimeout(Duration.ofMinutes(1), () -> {
      checkMovement(events);
      mouseClicks(clicks);
    });
  }

  /**
   * Method used to ensure all Key inputs are working.
   *
   * @param events List of random keys which will be pressed
   */
  void checkMovement(List<Integer> events) {
    events.stream()
        .forEach(e -> {
          try {
            robot.keyPress(e);
            robot.delay(33);
            robot.keyRelease(e);
          } catch (Exception exc) {
            throw exc;
          }
        });
  }

  /**
   * Simulated the mouse clicks with a robot.
   *
   * @param clicks List of pairs which provide x and y coords.
   */
  private void mouseClicks(List<Pair> clicks) {
    clicks.stream()
        .forEach(p -> {
          robot.mouseMove(p.x(), p.y());
          robot.delay(1000);
          robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
          robot.delay(20);
          robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
          robot.delay(200);
          }
        );
  }
  
  /**
   * Helper method so that i don't have this chunk of code making things messy.
   *
   * @param i Number of milliseconds to sleep.
   */
  private void sleep(int i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
  }
}

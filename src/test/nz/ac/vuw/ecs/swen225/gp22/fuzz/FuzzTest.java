package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.awt.AWTException; 
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.SwingUtilities;
import nz.ac.vuw.ecs.swen225.gp22.app.ChapsChallenge;
import nz.ac.vuw.ecs.swen225.gp22.app.Main;
import org.junit.jupiter.api.Test;

/**
 * FuzzTest class is as the name implies, where fuzz tests are taking place.

 * @author Hayden Curtis
ID:300586379
 */
class FuzzTest {
	private static Robot robot = null;
	/**
	 * Returns a list of possible key inputs.
	 */
	private List<Integer> keyList = List.of(KeyEvent.VK_UP, KeyEvent.VK_W,
			KeyEvent.VK_DOWN, KeyEvent.VK_S, 
			KeyEvent.VK_LEFT, KeyEvent.VK_A,
			KeyEvent.VK_RIGHT, KeyEvent.VK_D);
	
 /**
 * Generates a list of random integers each corresponding to key inputs.

 * @param size
Size of the list
 * @return List<Integer>

	 */
	private List<Integer> genEvents(int size){
		List<Integer> es = new ArrayList<Integer>();
		Random r = new Random();
		for(int i = 0; i<size; i++) {
			int next = r.nextInt(keyList.size());
			es.add(keyList.get(next));
		}
		return es;
	}
	
	private List<Pair> genPoints(int size){
		List<Pair> es = new ArrayList<Pair>();
		Random r = new Random();
		for(int i = 0; i<size; i++) {
			int x = r.nextInt();
			int y = r.nextInt();
			es.add(new Pair(x, y));
		}
		return es;
	}
	
	/**
	 * @author Hayden Curtis
	 * test1() : fuzz tests for level 1
	 */
	@Test
	public void test1() {
		//Call the creation of the level
		Main.main(null);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//Create Robot to apply clicks and key presses
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		//Random Key presses
		var events = genEvents(500);
		checkMovement(events);
	}
	
	/**
	 * test2 is for the second level of the game
	 */
	@Test
	public void test2() {
		//Call the creation of the level
		Main.main(null);		
		//Create Robot to apply clicks and key presses
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		//Random Key presses
		var events = genEvents(500);
		checkMovement(events);
	}
	
	/**
	 * Method used to ensure all Key inputs are working
	 * @param events 
	 */
	void checkMovement(List<Integer> events) {
		events.stream()
			.forEach(e -> {
				try {
					robot.keyPress(e);
					robot.delay(33);
					robot.keyRelease(e);
				} catch (Exception exc) {
					System.out.println(exc);
				}
			});
	}
	
	
	
//Pair of integers which acts as a point
	record Pair(int x, int y) {}
	/**
	 * Simulated the mouse clicks with a robot
	 * 
	 * @param x
	 * @param y
	 */
	private void mouseClicks(List<Pair> clicks) {
		clicks.stream()
			.forEach(p -> {
				robot.mouseMove(p.x(), p.y());
				robot.delay(5);
				robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);}
			);
	}
}

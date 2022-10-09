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

import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp22.app.ChapsChallenge;
import nz.ac.vuw.ecs.swen225.gp22.app.Keys;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
import nz.ac.vuw.ecs.swen225.gp22.app.Phase;
import nz.ac.vuw.ecs.swen225.gp22.app.PlayerController;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;

/**
 * 
 * @author Hayden Curtis
 * ID:300586379
 */
class FuzzTest {
	private static Robot robot = null;
	/**
	 * Generates a list of random integers
	 * @param size
	 * 		Size of the list
	 * @return
	 */
	private List<Integer> genEvents(int size){
		List<Integer> es = new ArrayList<Integer>();
		Random r = new Random();
		for(int i = 0; i<size; i++) {
			int next = r.nextInt(50);
//			if(!listOfKeys.contains(next)) {continue;}
			es.add(next);
		}
		return es;
	}
	
	/**
	 * @author Hayden Curtis
	 * test1() : fuzz tests for level 1
	 */
	@Test
	public void test1() {
		
		SwingUtilities.invokeLater(()->new ChapsChallenge());
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		//Random Key presses
		var events = genEvents(100);
	}
	
	/**
	 * Method used to ensure all Key inputs are working
	 * @param events
	 * @param p
	 * @param k
	 * @param m
	 * @throws Exception 
	 */
	void checkMovement(List<Integer> events, Player p, Keys k, Model m) throws Exception {
		for(Integer e : events) {
			try{
				k.getActionsPressed().getOrDefault(e,()->{}).run();
			} catch(Exception exc) {throw new Exception();}
		}
	}
	
	private void mouseClicks(int x, int y) {
		robot.mouseMove(x, y);
		robot.delay(5);
		robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
	}
}

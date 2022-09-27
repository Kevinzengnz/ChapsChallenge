package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.SwingUtilities;
import org.junit.jupiter.api.Test;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;

/**
 * 
 * @author Hayden Curtis
 * ID:300586379
 */
class FuzzTest {
	/**
	 * @author Hayden Curtis
	 * test1() : fuzz tests for level 1
	 */
	@Test
	public void test1() {
		//Player Movements
		Player p = new Player(new Point(3, 4));
		List<Runnable> moves = List.of(() -> p.setDirection(Direction.Up));
	}
	
	void checkMovement(List<KeyEvent> events, Player p, Point point) {
		Keys k = new Keys();
		for(KeyEvent e : events) {
			k.keyPressed(e);
			k.keyReleased(e);
		}
		assertTrue("Expected: " + point + ", Actual: " + p.getPoint(), p.getPoint().equals(point));
	}
	
}

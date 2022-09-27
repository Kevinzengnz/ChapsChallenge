package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import static org.junit.Assert.assertTrue;

import java.util.List; 
import javax.swing.SwingUtilities;
import org.junit.jupiter.api.Test;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;

/**
 * 
 * @author Hayden Curtis
 * ID:300586379
 */
public class FuzzTest {
	/**
	 * @author Hayden Curtis
	 * test1() : fuzz tests for level 1
	 */
	@Test
	public void test1() {
		Player p = new Player(new Point(3, 4));
		List<Runnable> moves = List.of(() -> p.setDirection(Direction.Up));
		checkMovement(moves, p, new Point(3, 3));
	}
	
//	/**
//	 * @author Hayden Curtis
//	 * test2() : fuzz tests for level 2
//	 */
//	@Test
//	public void test2() {
//		
//	}
	
	void checkMovement(List<Runnable> moves, Player p, Point point) {
		for(Runnable r : moves) {r.run();}
		assertTrue("Expected: " + point + ", Actual: " + p.getPoint(), p.getPoint().equals(point));
	}
	
}

package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import javax.swing.SwingUtilities;
import org.junit.jupiter.api.Test;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.recorder.GameRecorder;

/**
 * 
 * @author Hayden Curtis
 * ID:300586379
 */
class FuzzTest {
	
	List<Integer> genEvents(int size){
		List<Integer> es = new ArrayList<Integer>();
		Random r = new Random();
		for(int i = 0; i<size; i++) {
			es.add(r.nextInt(50));
		}
		return es;
	}
	/**
	 * @author Hayden Curtis
	 * test1() : fuzz tests for level 1
	 */
	@Test
	public void test1() {
		//Player Movements
		Random r = new Random();
		Player p = new Player(new Point(3, 4));
		var m = new Model() {
			@Override
			public Player player() {return p;}
			@Override
			public List<Entity> entities() {return null;}
			@Override
			public GameRecorder recorder() {return null;}
			@Override
			public void remove(Entity e) {}
			@Override
			public void onGameOver() {}
			@Override
			public void onNextLevel() {}
		};
		List<Integer> events = List.of(KeyEvent.VK_UP);
//		checkMovement(events, p, new Point(3, 3), m);
		System.out.println(genEvents(5) + " Helloooo");
	}
	
	void checkMovement(List<Integer> events, Player p, Point point, Model m) {
		Keys k = new Keys();
		for(Integer e : events) {
			k.getActionsPressed().getOrDefault(e,()->{}).run();
			p.ping(m);
		}
		assertTrue("Expected: " + point + ", Actual: " + p.getPoint(), p.getPoint().equals(point));
	}
	
}

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
			int next = r.nextInt(50);
//			if(!listOfKeys.contains(next)) {continue}
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
		//Player Movements
		List<Integer> events = genEvents(10);
		//Find where player should end up
		Player p = new Player(new Point(3,4));
		Model m = new Model(){
			@Override
			public Player player() {return p;}
			@Override
			public List<Entity> entities() {return List.of();}
			@Override
			public GameRecorder recorder() {return null;}
			@Override
			public void remove(Entity e) {}
			@Override
			public void onGameOver() {}
			@Override
			public void onNextLevel() {}};
		Keys k = new Keys();
		//UP, DOWN, LEFT, RIGHT ARROWS -- move Chap within the maze
        k.setAction(KeyEvent.VK_UP,() -> { p.setMoving(true); p.setDirection(Direction.Up);},() -> p.setMoving(false));
        k.setAction(KeyEvent.VK_DOWN,() -> { p.setMoving(true); p.setDirection(Direction.Down);},() ->p.setMoving(false));
        k.setAction(KeyEvent.VK_LEFT,() -> { p.setMoving(true); p.setDirection(Direction.Left);},() -> p.setMoving(false));
        k.setAction(KeyEvent.VK_RIGHT,() -> { p.setMoving(true); p.setDirection(Direction.Right);},() ->p.setMoving(false));

        //WASD also moves Chap within the maze
        k.setAction(KeyEvent.VK_W,() -> { p.setMoving(true); p.setDirection(Direction.Up);},() -> p.setMoving(false));
        k.setAction(KeyEvent.VK_S,() -> { p.setMoving(true); p.setDirection(Direction.Down);},() -> p.setMoving(false));
        k.setAction(KeyEvent.VK_A,() -> { p.setMoving(true); p.setDirection(Direction.Left);},() -> p.setMoving(false));
        k.setAction(KeyEvent.VK_D,() -> { p.setMoving(true); p.setDirection(Direction.Right);},() -> p.setMoving(false));
        
		checkMovement(events, p, new Point(3, 4), k, m);
	}
	
	
	void checkMovement(List<Integer> events, Player p, Point point, Keys k, Model m) {
		for(Integer e : events) {
			k.getActionsPressed().getOrDefault(e,()->{}).run();
			p.ping(m);
		}
		assertTrue("Expected: " + point + ", Actual: " + p.getPoint(), p.getPoint().equals(point));
	}
	
}

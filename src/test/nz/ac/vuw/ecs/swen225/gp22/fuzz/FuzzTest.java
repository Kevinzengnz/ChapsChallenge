package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.awt.event.KeyEvent;  
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.recorder.*;

/**
 * 
 * @author Hayden Curtis
 * ID:300586379
 */
class FuzzTest {
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
		
		ChapsChallenge cc = new ChapsChallenge();
		Phase phase = cc.getPhase();
		Model m = phase.model();
		PlayerController controller = phase.controller();
		
		//Random Key presses
		List<Integer> events = genEvents(100);
	}
	
	/**
	 * Method used to ensure all Key inputs are working
	 * @param events
	 * @param p
	 * @param k
	 * @param m
	 */
	void checkMovement(List<Integer> events, Player p, Keys k, Model m) {
		for(Integer e : events) {
			try{
				k.getActionsPressed().getOrDefault(e,()->{}).run();
			} catch(Exception exc) {}
		}
	}
	
}

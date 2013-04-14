package pl.edu.agh.to1.dice.logic;

import java.util.Random;

public class Dice {
	private int score = 1;
	private boolean active = true;
	private static Random rnd = new Random();
	
	public int roll(){
		if (active){
			score = rnd.nextInt(6) + 1;
		}
		
		return score;
	}
	
	public int getScore(){
		return score;
	}
	
	public void activate(){
		active = true;
	}
	
	public void deactivate(){
		active = false;
	}
}

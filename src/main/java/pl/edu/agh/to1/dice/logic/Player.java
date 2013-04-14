package pl.edu.agh.to1.dice.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
	private Map<Figure, Integer> score = new HashMap<Figure, Integer>();
	private String name;
	
	public Player(String name) {
		this.name = name;
	}

	public void chooseDices(DiceBox db){
		List<Boolean> mask = new ArrayList<Boolean>();
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		
		try {
			do {
				System.out.println(db + " - Choose dices to reroll:");
				mask.clear();
				String choiceStr = br.readLine();
				String choices[] = choiceStr.split(" ");
				for (String bitStr : choices) {
					mask.add( "1".equals(bitStr) );
				}
			} while ( mask.size() != db.getScore().size() );
			db.setMask(mask);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void chooseFigure(DiceBox db){
		Figure choice;
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		String input;
		
		System.out.println("Finally: " + db);
		try {
			do {
				System.out.print("Choose figure: ");
				input = br.readLine();
				choice = Figure.fromString( input );
			} while (choice == null || score.containsKey(choice));
			
			int gained = choice.getScore(db);
			score.put(choice, gained);
			System.out.println("Gained: " + gained);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getScore(Figure figure) {
		if ( score.containsKey(figure) ){
			return score.get(figure).toString();
		}
		
		return "-";
	}
	
	public int getBonus(){
		int sum = 0;
		for (int i = 0; i < 6; i++) {
			if ( score.containsKey( Figure.values()[i] ) ){
				sum += score.get( Figure.values()[i] );
			}
		}
		
		if (sum > 62){
			return sum;
		}
		
		return 0;
	}
	
	public int getTotalScore(){
		int sum = 0;
		
		for (Figure figure : Figure.values()) {
			if ( score.containsKey(figure) ){
				sum += score.get(figure);
			}
		}
		
		return sum + getBonus();
	}
	
	public String toString(){
		return name;
	}
}

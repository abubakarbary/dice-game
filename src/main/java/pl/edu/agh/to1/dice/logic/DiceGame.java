package pl.edu.agh.to1.dice.logic;

import java.util.ArrayList;
import java.util.List;

public class DiceGame {
	private List<Player> players = new ArrayList<Player>();
	private DiceBox diceBox = new DiceBox(5);
	private final int ROUND_COUNT = Figure.values().length; 
	
    public void play() {
        System.out.println("Playing Dice");
        
        players.add( new Player("Mietek") );
        players.add( new Player("Zbyszek") );
        
        for (int i = 0; i < ROUND_COUNT; i++) {			
        	for (Player player : players) {
        		System.out.println( String.format("\nIt is %s's turn:\n", player) );
        		printScore();
        		diceBox.reset();
        		diceBox.roll();
        		player.chooseDices(diceBox);
        		diceBox.roll();
        		player.chooseDices(diceBox);
        		diceBox.roll();
        		player.chooseFigure(diceBox);
        	}
		}
        System.out.println("\nWINNER: " + getWinner());
        System.out.println("FINAL SCORE:");
        printScore();
    }
    
    public void printScore(){
    	StringBuilder sb = new StringBuilder("\t");
    	for (Player player : players) {
			sb.append(player);
			sb.append("\t");
		}
    	System.out.println( sb.toString() );
    	for (Figure figure : Figure.values()) {
			sb = new StringBuilder( figure.getLabel() );
			sb.append("\t");
			for (Player player : players) {
				sb.append( player.getScore(figure) );
				sb.append("\t");
			}
			System.out.println( sb.toString() );
		}
    	sb = new StringBuilder( "Bonus\t" );
		for (Player player : players) {
			sb.append( player.getBonus() );
			sb.append("\t");
		}
		System.out.println( sb.toString() );
		sb = new StringBuilder( "------------\nTotal\t" );
		for (Player player : players) {
			sb.append( player.getTotalScore() );
			sb.append("\t");
		}
		System.out.println( sb.toString() );
    }
    
    public Player getWinner(){
    	Player winner = players.get(0);
    	
    	for (Player player : players) {
			if ( player.getTotalScore() > winner.getTotalScore() ){
				winner = player;
			}
		}
    	
    	return winner;
    }
}

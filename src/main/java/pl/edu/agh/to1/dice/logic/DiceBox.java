package pl.edu.agh.to1.dice.logic;

import java.util.ArrayList;
import java.util.List;

public class DiceBox {
	private List<Dice> dices;
	
	DiceBox(int size){
		dices = new ArrayList<Dice>();
		
		for (int i = 0; i < size; i++) {
			dices.add( new Dice() );
		}
	}
	
	public void roll(){
		for (Dice dice : dices) {
			dice.roll();
		}
	}
	
	public List<Integer> getScore(){
		List<Integer> scoreList = new ArrayList<Integer>();
		for (Dice dice : dices) {
			scoreList.add( dice.getScore() );
		}
		
		return scoreList;
	}
	
	public void setMask(List<Boolean> mask){
		for (int i = 0; i < dices.size(); i++) {
			if ( mask.get(i) ){
				dices.get(i).activate();
			}else{
				dices.get(i).deactivate();
			}
		}
	}
	
	public void reset(){
		for (Dice dice : dices) {
			dice.activate();
		}
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (Dice dice : dices) {
			sb.append( String.valueOf( dice.getScore() ) );
			sb.append(" ");
		}
		
		return sb.toString();
	}
}

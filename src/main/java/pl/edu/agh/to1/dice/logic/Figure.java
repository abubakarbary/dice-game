package pl.edu.agh.to1.dice.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Figure {
	ONES(1), TWOS(2), THREES(3), FOURS(4), FIVES(5), SIXES(6),
//	TRIO,
//	QUARTET,
//	FULL_HOUSE,
//	SMALL_STRAIGHT{
//		int getScore(DiceBox db){
//			int result = 0;
//			Set<Integer> ss = new HashSet<Integer>( db.getScore() );
//			return result;
//		}
//	},
	BIG_STRAIGHT("ds"){
		public int getScore(DiceBox db){
			int result = 0;
			List<Integer> score = db.getScore();
			Set<Integer> ss = new HashSet<Integer>(score);
			
			if (ss.size() == score.size()){
				result = 40;
			}
			
			return result;
		}
	},
	GENERAL("g"){
		public int getScore(DiceBox db){
			int result = 0;
			int i;
			List<Integer> scores = db.getScore();
			for (i = 1; i < scores.size(); ++i){
				if ( scores.get(i) != scores.get(i) ){
					break;
				}
			}
			if (i == scores.size()){
				result = 50;
			}
			
			return result;
		}
	},
	CHANCE("sz"){
		public int getScore(DiceBox db){
			int result = 0;
			for (int score : db.getScore()) {
				result += score;
			}
			
			return result;
		}
	};
	
	private int dominatingScore;
	private String label;
	
	Figure(String label){
		dominatingScore = 0;
		this.label = label;
	}
	
	Figure(int val){
		dominatingScore = val;
		this.label = String.valueOf( val );
	}
	
	public int getScore(DiceBox db){
		int result = 0;
		for (int score : db.getScore()) {
			if (score == dominatingScore){
				result += dominatingScore;
			}
		}
		
		return result;
	}
	
	public static Figure fromString(String name){
		if (name != null){
			for (Figure figure : Figure.values()) {
				if ( figure.label.equals(name) ){
					return figure;
				}
			}
		}
		
		return null;
	}
	
	public String getLabel(){
		return label;
	}
}

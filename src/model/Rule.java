package model;

public class Rule {
	int[] survive;
	int[] birth;
	int[] death;
	
	public Rule(String s, String b){
		survive = new int[s.length()];
		birth = new int[b.length()];
		for(int i=0; i < s.length(); i++){
			survive[i] = Character.getNumericValue(s.charAt(i));
		}
		for(int i=0; i < b.length(); i++){
			birth[i] = Character.getNumericValue(b.charAt(i));
		}
	}
	
	public boolean getLiveNextState(int neighbours){
		for(int i=0; i<survive.length; i++){
			if(survive[i] == neighbours){
				return true;
			} 
		}
		return false;
	}
	public boolean getDeadNextState(int neighbours){
		for(int i = 0; i < birth.length; i++){
			if(birth[i] == neighbours){
				return true;
			}
		}
		return false;
	}
	
}

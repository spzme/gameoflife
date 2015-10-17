package game;


public class Game {
	Boolean[][] field;
	int m,n;
	int freq; //how many generations should be computed every second?
	long previousGenerationTime;
	
	public static void main(String[] args){
		Game myGame = new Game();
		myGame.setup();
	}
	
	//Setup the game of life.
	public void setup(){
		m = 3;
		n = 3;
		freq = 2; // Two generations per second.
		field = new Boolean[m][n];
	}
	
	public void runSimulation(){
		boolean running = true;
		while(running){
			while(previousGenerationTime + 1000/freq < System.currentTimeMillis()){
			//Do nothing, so that the generation is halted.
			}
			nextGeneration();
			printField();
		}
		
		
	}
	
	public void nextGeneration(){
		Boolean[][] newField = new Boolean[m][n];
		for(int i =0; i<m; i++){
			for(int j=0; j<n; j++){
				int neighbours = 0;
				//top row
				if(i > 0){
					//top mid
					if(field[i-1][j]){
						neighbours++;
					}
					//top left
					if(j > 0 && field[i-1][j-1]){
						neighbours++;
					}
					//top right
					if(j < n-1 && field[i-1][j+1]){
						neighbours++;
					}
				}
				//mid row
				//mid left
				if(j > 0 && field [i][j-1]){
					neighbours++;
				}
				//mid right
				if (j < n-1 && field[i][j+1]){
					neighbours++;
				}
				//bottom row
				if(i < n-1){
					//bottom mid
					if(field[i+1][j]){
						neighbours++;
					}
					//bottom left
					if(j > 0 && field[i-1][j-1]){
						neighbours++;
					}
					//bottom right
					if(j < n - 1 && field[i-1][j+1]){
						neighbours++;
					}
					
				}
				//The amount of alive neighbours is determined now, determine alive state of cell.
				
				if (neighbours <= 2){
					//Die because of underpopulation.
					newField[i][j] = false;
				}
				if (neighbours == 2 || neighbours == 3){
					//Live on to the next generation.
					//Or, become alive!
					newField[i][j]  = true;
				}
				if (neighbours > 3){
					//Die because of overpopulation
					newField[i][j] = false;
				}
				
			}
		}
		
		
	}
	
	public void printField(){
		for(int i = 0; i<m; i++){
			System.out.println("");
			for(int j = 0; j<n; j++){
				System.out.println(field[i][j]);
			}
		}
	}
	
	
}



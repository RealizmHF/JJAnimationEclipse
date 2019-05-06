import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Slider;
import javafx.scene.shape.Rectangle;

public class Computer {

	private Random rand = new Random();
	
	private int pcCollected = 0;
	
	public Computer(Slider totalPogos, Slider totalDist, ArrayList<PogoStick<Integer>> pogos, ArrayList<Rectangle> steps, ArrayList<ArrayList<PogoStick<Integer>>> computerCombo, int dist) {
		
		
		PogoStick<Integer> tempStick = new PogoStick<Integer>(0);
		
		//Creates between 2 and 13 random pogo sticks
		for(int k = (int) (rand.nextInt(3) + totalPogos.getValue()); k > -1; k--) {
			//Random distance from 1 to 10
			int temp = rand.nextInt(9)+1;
			if(temp == 1) {
				if(rand.nextBoolean())
					temp++;
			}
			if(!tempStick.pogoContain(pogos, temp)) {
				pogos.add(new PogoStick<Integer>(temp));
			}
		}
		
		pogos.get(0).reOrder(pogos);
		
		
		//Random distance required to travel between 5 and 30
		for(int k = (int) (rand.nextInt(10) + totalDist.getValue()); k > -1; k--) {
			
			if(k > dist)
				dist = k;
			
			steps.add(new Rectangle());
			
		}
		
		//Initialize computerCombo's
		for(int m = 0; m < pogos.size(); m++) {
			ArrayList<PogoStick<Integer>> tempList = new ArrayList<PogoStick<Integer>>();
			tempList.add(pogos.get(m));
			computerCombo.add(tempList);
		}
		
		do {
			computerCombo = possible(computerCombo, pogos, steps.size());
			
			if(computerCombo.size() == 0) {
				for(int k = (int) (rand.nextInt(3) + totalPogos.getValue()); k > -1; k--) {
					//Random distance from 1 to 10
					int temp = rand.nextInt(9)+1;
					if(temp == 1) {
						if(rand.nextBoolean())
							temp++;
					}
					if(!tempStick.pogoContain(pogos, temp)) {
						pogos.add(new PogoStick<Integer>(temp));
					}
					
					computerCombo = new ArrayList<ArrayList<PogoStick<Integer>>>();

					for(int m = 0; m < pogos.size(); m++) {
						ArrayList<PogoStick<Integer>> tempList = new ArrayList<PogoStick<Integer>>();
						tempList.add(pogos.get(m));
						computerCombo.add(tempList);
					}
				}
				
				pogos.get(0).reOrder(pogos);
			}
		}while(computerCombo.size() == 0);
	}
	
	public ArrayList<ArrayList<PogoStick<Integer>>> possible(ArrayList<ArrayList<PogoStick<Integer>>> combos, ArrayList<PogoStick<Integer>> p, int goal){
		//Combos = all possible combinations
		//p = pogo sticks available
		//goal = final distance required
		
		//Temp combination being edited to add final list of combinations
		ArrayList<PogoStick<Integer>> tempPogo = new ArrayList<PogoStick<Integer>>();
		
		//Go through final list of combinations
		for(int k = 0; k < combos.size(); k++) {
			
				//Loop through useable pogo sticks, adding every possible combination to the final combo list
				for(int z = 0; z < p.size(); z++) {
					
					//TempPogo is equal to the combination at index k of the final combo list
					tempPogo = new ArrayList<PogoStick<Integer>>();
					tempPogo.addAll(combos.get(k));

					int tempDistance = tempPogo.get(0).getDistTotal(tempPogo);
					
					
					//If the temp combo distance + pogo stick at index z's distance is less than or equal to goal
					//Add it to the final combo list
					if(tempDistance + p.get(z).getDist() <= goal) {
						tempPogo.add(p.get(z));
						combos.add(tempPogo);
					}
				}

			//If the total distance of the combination at index k in the final combo list is less than goal, remove it
			if(p.get(0).getDistTotal(combos.get(k)) < goal) {
				combos.remove(k);
				k--;
			}
		}
		
		System.out.println("Combos: " + combos.size());
		return combos;
	}
	
	public void bestCombo(ArrayList<ArrayList<PogoStick<Integer>>> computerCombo, ArrayList<CoinPile<Integer>> coins, ArrayList<Rectangle> steps) {
		//Checks for the best combination
		for(int a = 0; a < computerCombo.size(); a++) {
			for(int c = 0; c < coins.size(); c++) {
				int tempCost = totalJumpCost(computerCombo.get(a), coins);
				if(tempCost > pcCollected && steps.size()-1 == computerCombo.get(a).size()) {
					pcCollected = tempCost;
				}
			}
		}
	}
	
	public static int totalJumpCost(ArrayList<PogoStick<Integer>> combo, ArrayList<CoinPile<Integer>> coins) {
		//Adds up the total cost of a jump
		
		int total = 0;
		
		int distance = 0;
		
		for(int x = 0; x < combo.size(); x++) {
			for(int y = 0; y < coins.size(); y++) {
				distance += combo.get(x).getDist();
				
				if(distance == coins.get(y).getLocation()) {
					total++;
				}
			}
		}
		return total;
	}
}

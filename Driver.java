
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Driver<T> extends Application{

	private double pbCount = 0;
	private ProgressBar pb = new ProgressBar(pbCount);
	
	private int totalCoins = 0;
	private int pcCollected = 0;
	
	private int src = 0;
	
	private Circle head = new Circle();
	private Line body = new Line();
	private Line armL = new Line();
	private Line armR = new Line();
	private Line legL = new Line();
	private Line legR = new Line();
	
	private Circle tireL = new Circle();
	private Circle tireR = new Circle();
	private Rectangle board = new Rectangle();
	
	private Label error = new Label();
	
	private Random rand = new Random();
	
	private ArrayList<Rectangle> steps = new ArrayList<Rectangle>();
	
	private ArrayList<Button> pogoButtons = new ArrayList<Button>();
	private ArrayList<PogoStick<Integer>> pogos = new ArrayList<PogoStick<Integer>>();
	
	private ArrayList<Label> userCombo = new ArrayList<Label>();
	
	private ArrayList<ArrayList<PogoStick<Integer>>> computerCombo = new ArrayList<ArrayList<PogoStick<Integer>>>();
	private ArrayList<CoinPile<Integer>> coins = new ArrayList<CoinPile<Integer>>();
	private ArrayList<Circle> coinPiles = new ArrayList<Circle>();
	
	private Line topDoor = new Line();
	private Line leftDoor = new Line();
	private Line rightDoor = new Line();
	private Circle knob = new Circle();

	
	private Label goal = new Label();
	
	@Override
	public void start(Stage primary) {
		
		pb.setLayoutX(400);
		pb.setLayoutY(20);
		
		pb.setMinWidth(200);
        
		
		
		error.setVisible(false);
		error.setText("This pogo stick cannot be used.");
		error.setLayoutX(400);
		error.setLayoutY(600);
		System.out.println("Welcome to JJ's Joly Jumping Journey!");
		System.out.println("\n\nThe object of the game is to use the pogo sticks in the correct order to collect as many coins as possible!");
		System.out.println("Good Luck!");
		
		goal.setVisible(false);
		goal.setText("Goal!");
		goal.setFont(new Font(50));
		goal.setLayoutX(120);
		goal.setLayoutY(50);
		
		
		PogoStick<Integer> tempStick = new PogoStick<Integer>(0);
		
		//Creates between 4 and 7 random pogo sticks
		for(int k = rand.nextInt(3)+4; k > -1; k--) {
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
		
		
		Pane pane = new Pane();
		
		
		buttonSize(pogoButtons, pogos, 70, 500);
		buttonLayout(pogoButtons, 250, 500);
		
		int dist = 0;
		//Random distance required to travel between 5 and 15
		for(int k = rand.nextInt(10)+5; k > -1; k--) {
			
			if(k > dist)
				dist = k;
			
			steps.add(new Rectangle());
			
		}
		
		for(int m = 0; m < pogos.size(); m++) {
			ArrayList<PogoStick<Integer>> tempList = new ArrayList<PogoStick<Integer>>();
			tempList.add(pogos.get(m));
			computerCombo.add(tempList);
		}
		
		do {
			computerCombo = possible(computerCombo, pogos, steps.size());
			
			if(computerCombo.size() == 0) {
				for(int k = rand.nextInt(3)+4; k > -1; k--) {
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
		
		//ArrayList<ArrayList<PogoStick<Integer>>> finalized = new ArrayList<ArrayList<PogoStick<Integer>>>();
		
		
		for(int b = 0; b < computerCombo.get(computerCombo.size()-1).size(); b++) {
			userCombo.add(new Label());
			userCombo.get(b).setVisible(false);
			userCombo.get(b).setFont(new Font(20));
		}
		
		stepSize(steps, 700);
		stepLayout(steps, 50, 400);
		
		int totalDist = dist;
		
		for(; dist > -1; dist--) {
			
			//Random coin piles generated to fit the distance
			if(dist % 2 != 0) {
				int placement = rand.nextInt(totalDist);
				if(!coins.contains(new CoinPile<Integer>(placement, 1))) {
					coins.add(new CoinPile<Integer>(placement, 1));
					coinPiles.add(new Circle());
					coinPiles.get(coinPiles.size()-1).setFill(Color.GOLD);
					coinPiles.get(coinPiles.size()-1).setRadius(steps.get(0).getWidth()/5);
					coinPiles.get(coinPiles.size()-1).setCenterX((steps.get(0).getWidth() * (placement + 1)) + (10 * placement) + steps.get(0).getWidth()/2);
					coinPiles.get(coinPiles.size()-1).setCenterX(50 + (steps.get(0).getWidth()*placement) + (10 * placement) + steps.get(0).getWidth()/2);
					coinPiles.get(coinPiles.size()-1).setCenterY(400 - steps.get(0).getWidth());
					if(placement == 0) {
						totalCoins++;
						coinPiles.get(coinPiles.size()-1).setVisible(false);
					}
				}
			}
		}
		
		
		for(int a = 0; a < computerCombo.size(); a++) {
			for(int c = 0; c < coins.size(); c++) {
				int tempCost = totalJumpCost(computerCombo.get(a), coins);
				if(tempCost > pcCollected && steps.size()-1 == computerCombo.get(a).size()) {
					pcCollected = tempCost;
				}
			}
		}
		
		doorSize(topDoor, leftDoor, rightDoor, knob, 900/steps.size() - 10, steps);
		
		scaleBody(head, body, armL, armR, legL, legR, steps.get(0).getWidth(), tireL, tireR, board);
		

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                // set progress to different level of progressbar 
                 
                
                String source = e.getSource().toString().substring(e.getSource().toString().length()-2, e.getSource().toString().length()-1);
    			
    			
    			if(totalUserDistance(userCombo) + Integer.parseInt(source) < steps.size()) {
    				for(int k = 0; k < userCombo.size(); k++) {
    					if(!userCombo.get(k).isVisible()) {
    						error.setVisible(false);
    						userCombo.get(k).setText(source);
    						refreshComboView(userCombo);
    						userCombo.get(k).setVisible(true);
    						k += userCombo.size();
    					}
    				}

        			src += Integer.parseInt(source);
    				
        			for(int k = 0; k < coins.size(); k++) {
        				if(src == coins.get(k).getLocation()) {
        					coinPiles.get(k).setVisible(false);
        					totalCoins++;
        				}
        			}
    				
    				
    				pbCount += Double.parseDouble(source)/steps.size();
    				pb.setProgress(pbCount);
    				Animation move = new Animation(head, body, armL, armR, legL, legR, Integer.parseInt(source), steps.get(0).getWidth(), tireL, tireR, board);
    				move.play();
    			}
    			else {
    				error.setVisible(true);
    			}
    			
    			if(totalUserDistance(userCombo) == steps.size()-1) {
    				goal.setText("                     Goal!" + "\n      You Collected " + totalCoins + " coins!");
    				goal.setVisible(true);
    				pb.setProgress(1);
    				
    			}
    			else if(totalUserDistance(userCombo) + pogos.get(pogos.size()-1).getDist() > steps.size()-1) {
    				goal.setText("You Lose.");
    				goal.setVisible(true);
    			}
            } 
        };
		
		pane.getChildren().addAll(userCombo);
		pane.getChildren().addAll(pogoButtons);
		pane.getChildren().addAll(steps);
		pane.getChildren().addAll(head, body, armL, armR, legL, legR, error, goal);
		pane.getChildren().addAll(topDoor, leftDoor, rightDoor, knob, pb);
		pane.getChildren().addAll(coinPiles);
		//pane.getChildren().addAll(tireL, tireR, board);
		
		
		for(int k = 0; k < pogoButtons.size(); k++) {
			//pogoButtons.get(k).setOnAction(press);
			pogoButtons.get(k).setOnAction(event);
		}

		
		Scene scene = new Scene(pane, 1000, 1000);
		primary.setTitle("JJ Graphical Adventure   Alpha v1.0");
		primary.setScene(scene);
		primary.show();
		
		
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
	public static String getComboText(ArrayList<ArrayList<PogoStick<Integer>>> combos) {
		
		String test = "";
		
		int index = combos.size()-1;
		
		for(int k = 0; k < combos.get(index).size(); k++) {
			test += combos.get(index).get(k).getDist() + " ";
		}
		return "";
	}
	

	
	public static void scaleBody(Circle head, Line body, Line armL, Line armR, Line legL, Line legR, double size, Circle tireL, Circle tireR, Rectangle board) {
		//Give all pieces for JJ Body and a steps width
		//Scales based on the size of a step
		
		tireL.setRadius(size/8);
		tireR.setRadius(size/8);
		board.setHeight(size/4);
		board.setWidth(size);
		board.setFill(new Color(Math.random(),Math.random(),Math.random(),1.0));

		tireL.setCenterX(55);
		tireL.setCenterY(400-size/12);
		tireR.setCenterX(50 + size - 5);
		tireR.setCenterY(400 - size/12);
		
		board.setLayoutX(50);
		board.setLayoutY(400-size/3);
		
		
		head.setRadius(size/2);
		body.minHeight(size);
		body.minWidth(3);
		armL.minHeight(size-2);
		armL.minWidth(2);
		armR.minHeight(size-2);
		armL.minWidth(2);
		legL.minHeight(size-1);
		legL.minWidth(2);
		legR.minHeight(size-1);
		legR.minWidth(2);
		body.maxHeight(size);
		body.maxWidth(3);
		armL.maxHeight(size-2);
		armL.maxWidth(2);
		armR.maxHeight(size-2);
		armL.maxWidth(2);
		legL.maxHeight(size-1);
		legL.maxWidth(2);
		legR.maxHeight(size-1);
		legR.maxWidth(2);
		
		
		legR.setStartX(50 + size);
		legR.setStartY(400);
		legR.setEndX(50 + (size/2));
		legR.setEndY(400 - size);
		
		legL.setStartX(50);
		legL.setStartY(400);
		legL.setEndX(50 + (size/2));
		legL.setEndY(400 - size);
		
		body.setStartX(50 + (size/2));
		body.setStartY(400 - size);
		body.setEndX(50 + (size/2));
		body.setEndY(400 - (size * 2));

		armR.setStartX(50 + (size/2));
		armR.setStartY(400 - (size * 1.5));
		armR.setEndX(50 + size/2);
		armR.setEndY(400 - size);
		
		armL.setStartX(50 + (size/2));
		armL.setStartY(400 - (size * 1.5));
		armL.setEndX(50 + size/2);
		armL.setEndY(400 - size);
		
		head.setCenterX(50 + (size/2));
		head.setCenterY(400 - (size * 2.5));
		
	}
	
	public static void doorSize(Line top, Line left, Line right, Circle knob, int size, ArrayList<Rectangle> steps) {
		
		steps.add(new Rectangle());
		steps.get(steps.size()-1).setFill(Color.BLACK);
		steps.get(steps.size()-1).setHeight(5);
		steps.get(steps.size()-1).setWidth(size);
		steps.get(steps.size()-1).setLayoutX(steps.get(steps.size()-2).getX() + size);
		steps.get(steps.size()-1).setLayoutY(400);
		
		top.setStartX(steps.get(steps.size()-2).getX() + size);
		top.setStartY(400 - (size * 3));
		top.setEndX((steps.get(steps.size()-2).getX() + (size * 2)));
		top.setEndY(400 - (size * 3));
		
		left.setStartX(steps.get(steps.size()-2).getX() + size);
		left.setStartY(400 - (size * 3));
		left.setEndX((steps.get(steps.size()-2).getX() + size));
		left.setEndY(400);
		
		right.setStartX(steps.get(steps.size()-2).getX() + (size * 2));
		right.setStartY(400 - (size * 3));
		right.setEndX((steps.get(steps.size()-2).getX() + (size * 2)));
		right.setEndY(400);
		
		knob.setRadius(size/10);
		knob.setCenterX((steps.get(steps.size()-2).getX() + size) + size/5);
		knob.setCenterY(400 - (size * 1.5));
	}
	
	public static <T> void buttonLayout(ArrayList<Button> buttons, int x, int y) {
		//Input list of buttons, and coords for top left corner (x, y)
		//Sets the buttons starting from the (x, y) coords and to the right
		

		buttons.get(0).setLayoutX(x);
		buttons.get(0).setLayoutY(y);
		
		for(int k = 1; k < buttons.size(); k++) {
			buttons.get(k).setLayoutX(x + (buttons.get(k).getMinWidth()*k));
			buttons.get(k).setLayoutY(y);
		}
	}
	
	public static <T> void buttonSize(ArrayList<Button> buttons, ArrayList<PogoStick<T>> pogos, int height, int width) {
		//Input the list of buttons, the area they need to fit in, and the pogo sticks to label
		

		//The total width allowed per buttons
		int size = width/pogos.size();
		
		for(int k = 0; k < pogos.size(); k++) {
			buttons.add(new Button(Integer.toString(pogos.get(k).getDist())));
			
			buttons.get(k).setMinSize(size, height);
			buttons.get(k).setMaxSize(width, height);
		}	
	}
	
	public static void stepSize(ArrayList<Rectangle> steps, int width) {
		//input the list of steps and the total screen space available
		//scales steps to fit space provided
		
		int size = width/steps.size();
		
		for(int k = 0; k < steps.size(); k++) {
			steps.get(k).setFill(Color.BLACK);
			steps.get(k).setWidth(size);
			steps.get(k).setHeight(5);
		}
	}
	
	public static void stepLayout(ArrayList<Rectangle> steps, int x, int y) {
		//Input list of steps and initial position
		//places the steps starting at (x, y) along the X-axis

		steps.get(0).setX(x);
		steps.get(0).setY(y);
		
		for(int k = 1; k < steps.size(); k++) {
			steps.get(k).setX(x + (steps.get(k).getWidth()*k) + (10 * k));
			steps.get(k).setY(y);
		}
	}
	
	public static ArrayList<ArrayList<PogoStick<Integer>>> possible(ArrayList<ArrayList<PogoStick<Integer>>> combos, ArrayList<PogoStick<Integer>> p, int goal){
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
	
	public static int totalUserDistance(ArrayList<Label> combo) {
		
		int total = 0;
		
		for(int k = 0; k < combo.size(); k++) {
			if(!combo.get(k).getText().equals(""))
				total += Integer.parseInt(combo.get(k).getText());
		}
		
		return total;
	}
	
	public static void refreshComboView(ArrayList<Label> combo) {
		
		combo.get(0).setLayoutX(350);
		combo.get(0).setLayoutY(700);
		
		for(int k = 1; k < combo.size(); k++) {
			combo.get(k).setLayoutX(350 + (k * 30));
			combo.get(k).setLayoutY(700);
		}
	}
//	
//	class Move implements EventHandler<ActionEvent> {
//		@Override
//		public void handle(ActionEvent e) {
//			
//			
//			
//		}
//	}
}

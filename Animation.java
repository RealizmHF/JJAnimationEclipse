import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Animation extends Pane{
	
	private Timeline animation;
	
	private boolean test = false;
	
	private int distance = 0;
	
	private int counter = 0;
	
	public Animation(Circle head, Line body, Line armL, Line armR, Line legL, Line legR, int count, double size, Circle tireL, Circle tireR, Rectangle board) {
		//JJ Jolly
		//Count = Pogo Stick Being Used
		//Size = Width of each step
		//Skateboard
		
		animation = new Timeline(new KeyFrame(Duration.millis(10), e->walk(head, body, armL, armR, legL, legR, count, size, tireL, tireR, board)));
		animation.setCycleCount(Timeline.INDEFINITE);
	}

	public void walk(Circle head, Line body, Line armL, Line armR, Line legL, Line legR, int count, double size, Circle tireL, Circle tireR, Rectangle board) {
	
		//Use the body to move, if body is at X do this
		
		//If the body is currently less than the step we are trying to get to
		if(body.getLayoutX() < body.getEndX() + (10 * count - 1) + (size * count - 1)) {
			
			//If the body layout is less than the body's start X + our step size and the white gap
			//Then we want to move the left leg, body, and arms moving outwards
			if(body.getLayoutX() < body.getEndX()-size/2) {
				
				legL.setStartX(legL.getStartX() + 1);
				body.setLayoutX(body.getLayoutX() + 1);
				head.setLayoutX(head.getLayoutX() + 1);
			}
			
		}
		else {
			System.out.println("Stopping");
			stop();
		}
		
		//System.out.println(distance++);
	}
//	public void walk(Circle head, Line body, Line armL, Line armR, Line legL, Line legR, int count, double size, Circle tireL, Circle tireR, Rectangle board) {
//	
//		//Walk using left leg until it passes the right leg
//		//If left leg is less than (behind) the right leg
//		//Else if right leg is less than left leg + size, we are mid way through a full step, and lead with right leg now
//		if(legL.getLayoutX() < legR.getLayoutX() + 10 && legL.getLayoutX() != (size * count) + (10 * count - 1) + 50) {
//			
//			
//			legL.setLayoutX(legL.getLayoutX() + 1);
//			
//			//If left leg has reached X point in animation, move arms and body
//			if(legL.getLayoutX() >= (legR.getLayoutX() - size/2)){
//				
//				legL.setLayoutX(legL.getLayoutX() + .5);
//				legR.setLayoutX(legR.getLayoutX() + .5);
//				body.setLayoutX(body.getLayoutX() + .5);
//				body.setLayoutX(body.getLayoutX() + .5);
//				head.setCenterX(head.getCenterX() + .5);
//				armL.setLayoutX(armL.getLayoutX() + .5);
//				armR.setLayoutX(armR.getLayoutX() + .5);
//				
//			}
//			
//			//If the left arm is greater than the body's position - size/20
//			if(armL.getLayoutX() > body.getLayoutX() - size/4)
//				armL.setLayoutX(armL.getLayoutX() + .1);
//			
//			//If the right arm is less than the body position + size/20
//			if(armR.getLayoutX() < body.getLayoutX() + size/4)
//				armR.setEndX(armR.getEndX() + .9);
//			
//		}
//		else if(legR.getLayoutX() < legL.getLayoutX() + size ){
//			
//			legR.setLayoutX(legR.getLayoutX() + 1);
//			
//			legL.setEndX(legL.getEndX() + .5);
//			legR.setEndX(legR.getEndX() + .5);
//			body.setLayoutX(body.getLayoutX() + .5);
//			body.setEndX(body.getEndX() + .5);
//			head.setCenterX(head.getCenterX() + .5);
//			armL.setLayoutX(armL.getLayoutX() + .5);
//			armR.setLayoutX(armR.getLayoutX() + .5);
//			
//			if(armL.getEndX() != body.getEndX())
//				armL.setEndX(armL.getEndX() + 1);
//			
//			if(armR.getEndX() != body.getEndX())
//				armR.setEndX(armR.getEndX() + .2);
//			
//		}
//		
//		
//		
//	}	
	
//	
//	public void walk(Circle head, Line body, Line armL, Line armR, Line legL, Line legR, int count, double size, Circle tireL, Circle tireR, Rectangle board) {
//		
//		
//		if(distance < (size + 9) * count) {
//			legL.setStartX(legL.getStartX() + 1);
//			legL.setEndX(legL.getEndX() + 1);
//			legR.setStartX(legR.getStartX() + 1);
//			legR.setEndX(legR.getEndX() + 1);
//			body.setStartX(body.getStartX() + 1);
//			body.setEndX(body.getEndX() + 1);
//			head.setCenterX(head.getCenterX() + 1);
//			armL.setEndX(armL.getEndX() + 1);
//			armL.setStartX(armL.getStartX() + 1);
//			armR.setStartX(armR.getStartX() + 1);
//			armR.setEndX(armR.getEndX() + 1);
//			tireL.setCenterX(tireL.getCenterX() + 1);
//			tireR.setCenterX(tireR.getCenterX() + 1);
//			board.setLayoutX(board.getLayoutX() + 1);
//			
//			
//			
//			if(!test && legL.getStartY() > 400 - size/2) {
//				
//				legL.setStartY(legL.getStartY() - 1);
//				legL.setEndY(legL.getEndY() -1);
//				legR.setStartY(legR.getStartY() - 1);
//				legR.setEndY(legR.getEndY() - 1);
//				body.setEndY(body.getEndY() - 1);
//				body.setStartY(body.getStartY() - 1);
//				head.setCenterY(head.getCenterY() - 1);
//				armL.setEndY(armL.getEndY() - 1);
//				armL.setStartY(armL.getStartY() - 1);
//				armR.setStartY(armR.getStartY() - 1);
//				armR.setEndY(armR.getEndY() - 1);
//				tireR.setCenterY(tireR.getCenterY() - 1);
//				tireL.setCenterY(tireL.getCenterY() - 1);
//				board.setLayoutY(board.getLayoutY() - 1);
//				
//				if(legL.getStartY() - 1 == 400 - size/2) {
//					test = true;
//				}
//			}
//			else if(legL.getStartY()-1 <= 400){
//				legL.setStartY(legL.getStartY() + 1);
//				legL.setEndY(legL.getEndY() + 1);
//				legR.setStartY(legR.getStartY() + 1);
//				legR.setEndY(legR.getEndY() + 1);
//				body.setEndY(body.getEndY() + 1);
//				body.setStartY(body.getStartY() + 1);
//				head.setCenterY(head.getCenterY() + 1);
//				armL.setEndY(armL.getEndY() + 1);
//				armL.setStartY(armL.getStartY() + 1);
//				armR.setStartY(armR.getStartY() + 1);
//				armR.setEndY(armR.getEndY() + 1);
//				tireR.setCenterY(tireR.getCenterY() + 1);
//				tireL.setCenterY(tireL.getCenterY() + 1);
//				board.setLayoutY(board.getLayoutY() + 1);
//			}
//			distance++;
//		}
//		
//		
//		if(counter <= count) {
//			test = false;
//			distance = 0;
//			counter++;
//		}
//	}

	
	public void unparkCar(Rectangle car, Circle tire1, Circle tire2) {
		//PRE: Input a valid Rectangle, and two Circles
		//POST: Moves the Rectangle and Circles
		
		if(tire1.getCenterY()+tire1.getRadius() < 800 && tire1.getCenterX()-tire1.getRadius() > 200 && tire2.getCenterY()+tire2.getRadius() != 200){
			car.setY(car.getY()+1);
			tire1.setCenterY(tire1.getCenterY()+1);
			tire2.setCenterY(tire2.getCenterY()+1);
		}
		else if(tire1.getCenterY()+tire1.getRadius() == 800 && tire1.getCenterX()-tire1.getRadius() > 200) {
			car.setX(car.getX()-1);
			tire1.setCenterX(tire1.getCenterX()-1);
			tire2.setCenterX(tire2.getCenterX()-1);
		}
		else if(tire1.getCenterX()-tire1.getRadius() == 200 && tire1.getCenterY()+tire1.getRadius() > 200) {
			car.setY(car.getY()-1);
			tire1.setCenterY(tire1.getCenterY()-1);
			tire2.setCenterY(tire2.getCenterY()-1);
		}
		else if(tire1.getCenterY()+tire1.getRadius() == 200 && tire2.getCenterX()+tire2.getRadius() < 1200) {
			car.setX(car.getX()+1);
			tire1.setCenterX(tire1.getCenterX()+1);
			tire2.setCenterX(tire2.getCenterX()+1);
		}
		else if(tire2.getCenterX()+tire2.getRadius() == 1200)
			stop();
			
			
	}
			
	
	public void play() { animation.play(); }
	public void stop() { animation.stop(); }

}
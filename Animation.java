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
	
	public Animation(Circle head, Line body, Line armL, Line armR, Line legL, Line legR, double size, double start) {
		//JJ Jolly
		//Count = Pogo Stick Being Used
		//Size = Width of each step
		//Start = LegL's starting X position before moving
		//Skateboard
		
		animation = new Timeline(new KeyFrame(Duration.millis(10), e->walk(head, body, armL, armR, legL, legR, size, start)));
		animation.setCycleCount(Timeline.INDEFINITE);
	}

	public void walk(Circle head, Line body, Line armL, Line armR, Line legL, Line legR, double size, double start) {
	
		//Walk using left leg until it passes the right leg
		//If left leg is less than (behind) the right leg
		//Else if right leg is less than left leg + size, we are mid way through a full step, and lead with right leg now
		if(legL.getStartX() < start + (size + 10)) {
			
			legL.setStartX(legL.getStartX() + size/50);
			
			if(body.getEndX() < (start + size/2) + size + 10) {
				body.setStartX(body.getStartX() + size/100);
				body.setEndX(body.getEndX() + size/100);
				head.setCenterX(head.getCenterX() + size/100);

				if(legL.getEndX() <= start + size + 10) {
					legL.setEndX(legL.getEndX() + size/100);
				}
				if(legR.getEndX() <= start + size + size + 10)
					legR.setEndX(legR.getEndX() + size/100);
			}
			
			
		}
		else if(legR.getStartX() < legL.getStartX() + size){
			
			legR.setStartX(legR.getStartX() + size/50);
			
			
			if(body.getEndX() < (start + size/2) + size + 10) {
				body.setStartX(body.getStartX() + size/100);
				body.setEndX(body.getEndX() + size/100);
				head.setCenterX(head.getCenterX() + size/100);
				armL.setLayoutX(armL.getLayoutX() + size/100);
				armR.setLayoutX(armR.getLayoutX() + size/100);
			}
			if(legL.getEndX() <= start + size + 10 + size/2)
				legL.setEndX(legL.getEndX() + (size)/100);
			if(legR.getEndX() <= start + size + size/2 + 10)
				legR.setEndX(legR.getEndX() + (size)/100);
			
		}
		else {
			stop();
		}
		
		
		
	}	
	
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

	public void play() { animation.play(); }
	public void stop() { animation.stop(); }

}
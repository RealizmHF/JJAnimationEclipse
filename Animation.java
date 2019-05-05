import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Animation extends Pane{
	
	private Timeline animation;
	
	private Timeline anim;
	
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
		anim = new Timeline(new KeyFrame(Duration.millis(10), e->arms(armL, armR, size, start)));
		animation.setCycleCount(Timeline.INDEFINITE);
	}
	
	public void arms(Line armL, Line armR, double size, double start) {
		
		boolean max = false;
		
		if(!max && armL.getEndX() > armL.getStartX() - size) {
			armL.setEndX(armL.getEndX() + size/200);
		}
		else {
			armL.setEndX(armL.getEndX() + size);
		}
	}

	public void walk(Circle head, Line body, Line armL, Line armR, Line legL, Line legR, double size, double start) {
	
		//Walk using left leg until it passes the right leg
		//If left leg is less than (behind) the right leg
		//Else if right leg is less than left leg + size, we are mid way through a full step, and lead with right leg now
		
		//If left leg's start is less than the initial position plus the step and white space
		//Move the left leg and not the right
		//Else, move the right leg
		if(legL.getStartX() < start + (size + 10)) {
			
			//Left leg's start X moves faster than the rest
			legL.setStartX(legL.getStartX() + size/50);
			
			//If the body is less than the initial position + a step and white space
			//Move body, head, arms and legs
			if(body.getEndX() < (start + size/2) + size + 10) {
				body.setStartX(body.getStartX() + size/100);
				body.setEndX(body.getEndX() + size/100);
				head.setCenterX(head.getCenterX() + size/100);
				armL.setStartX(armL.getStartX() + size/100);
				armR.setStartX(armR.getStartX() + size/100);
				
				//If left leg's end x is less than the initial position and a step and white space
				//Move left leg's end x with the body
				if(legL.getEndX() <= start + size + 10) {
					legL.setEndX(legL.getEndX() + size/100);
				}
				//If right leg's end x is less than the initial position and a step and white space
				//Move right leg's end x with the body
				if(legR.getEndX() <= start + size + size + 10)
					legR.setEndX(legR.getEndX() + size/100);
			}
			
			
		}
		//Else if right leg start is less than the left legs start + a step
		else if(legR.getStartX() < legL.getStartX() + size){
			
			//Right leg's start X moves faster than the rest
			legR.setStartX(legR.getStartX() + size/50);
			
			//If body isn't at the middle of the next step
			//Move the body, head, arms, and legs
			if(body.getEndX() < (start + size/2) + size + 10) {
				body.setStartX(body.getStartX() + size/100);
				body.setEndX(body.getEndX() + size/100);
				head.setCenterX(head.getCenterX() + size/100);
				armL.setStartX(armL.getStartX() + size/100);
				armR.setStartX(armR.getStartX() + size/100);
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

	public void play() { animation.play(); }
	public void stop() { animation.stop(); }

}
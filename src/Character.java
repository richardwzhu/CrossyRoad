import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Character extends Actor{

	public Character(String img) {
		ImageView imageView = new ImageView(img);
		imageView.setPreserveRatio(true);
		imageView.setEffect(new Glow());
        setImage(new Image(img));
	}

	public void movement() {
		if(!((CrossyWorld)getWorld()).isGameOver()) {
			boolean cont = true;
			ScaleTransition st = new ScaleTransition(Duration.millis(50), this);
		    st.setByX(0.5f);
		    st.setByY(0.5f);
		    st.setCycleCount((int) 2f);
		    st.setAutoReverse(true);
			if(getWorld().isKeyDown(KeyCode.A)) {
			    if (this.getX() > 10) {
			    	this.setX(this.getX() - 15);
			    	st.play();
	                
	                if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
	                	this.setX(this.getX() + 15);
	                }
	            }
			}
			if(getWorld().isKeyDown(KeyCode.D)) {
	            if (this.getX() + this.getWidth() < 290) {
	            	this.setX(this.getX() + 15);
	            	st.play();
	            	setImage(new Image(getClass().getClassLoader().getResource("resources/chickenright.png").toString()));
	            	if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
	                	this.setX(this.getX() - 15);
	                } 
	            }
			}
			if(getWorld().isKeyDown(KeyCode.S)) {
	            if (this.getY() + this.getHeight() < 590) {
	            	this.setY(this.getY() + 15);
	            	st.play();
	                if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
	                	this.setY(this.getY() - 15);
	                }
	            }
			}
			if(getWorld().isKeyDown(KeyCode.W)) {
	            if (this.getY() > 10) {
	            	this.setY(this.getY() - 15);
	            	st.play();
	            	if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
	                	this.setY(this.getY() + 15);
	                } 
	            }
			}
		}
	}
	
	@Override
	public void act(long now) {
		try {
			if(this.getOneIntersectingObject(MovingObstacle.class).getType().equals("car")) {
				((CrossyWorld)getWorld()).setGameOver(true);
			}
			//if(this.getOneIntersectingObject(Road.class).getTerrain().equals("river") && !this.getOneIntersectingObject(MovingObstacle.class).getType().equals("log")) {
				//((CrossyWorld)getWorld()).setGameOver(true);
			//}
		}catch(Exception e){
			
		}
	}
}

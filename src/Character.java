import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
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
			ScaleTransition st = new ScaleTransition(Duration.millis(25), this);
		    st.setByX(0.5f);
		    st.setByY(0.5f);
		    st.setCycleCount((int) 2f);
		    st.setAutoReverse(true);
		    
		    if(getWorld().isKeyDown(KeyCode.A)) {
			    if (this.getX() > 15) {
			    	this.setX(this.getX() - 15);
			    	st.play();
			    	setImage(new Image(getClass().getClassLoader().getResource("resources/chickenleft.png").toString()));
			    	if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
 	                	this.setX(this.getX() + 15);
 	                	int i = 14;
 	                	boolean cont = true;
 	                	while(i > 0 && cont == true) {
 	                		this.setX(this.getX() - i);
 	                		if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
 	    	                	this.setX(this.getX() + i);
 	    	                	i--;
 	                		}else {
 	                			cont = false;
 	                		}
 	                	}
 	                }
			    }
		    }   
		    if(getWorld().isKeyDown(KeyCode.D)) {
			    if (this.getX() < 285) {
			    	this.setX(this.getX() + 15);
			    	st.play();
			    	setImage(new Image(getClass().getClassLoader().getResource("resources/chickenright.png").toString()));
			    	if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
 	                	this.setX(this.getX() - 15);
 	                	int i = 14;
 	                	boolean cont = true;
 	                	while(i > 0 && cont == true) {
 	                		this.setX(this.getX() + i);
 	                		if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
 	    	                	this.setX(this.getX() - i);
 	    	                	i--;
 	                		}else {
 	                			cont = false;
 	                		}
 	                	}
 	                }
			    }
		    }
		    if(getWorld().isKeyDown(KeyCode.S)) {
			    if (this.getY() < 585) {
			    	this.setY(this.getY() + 15);
			    	st.play();
			    	Score cur = (((CrossyWorld)getWorld()).getScore());
			    	cur.setScore(cur.getScore() - 1);
			    	if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
 	                	this.setY(this.getY() - 15);
 	                	int i = 14;
 	                	boolean cont = true;
 	                	while(i > 0 && cont == true) {
 	                		this.setY(this.getY() + i);
 	                		if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
 	    	                	this.setY(this.getY() - i);
 	    	                	i--;
 	                		}else {
 	                			cont = false;
 	                		}
 	                	}
 	                	if(i == 0) {
 	                		cur.setScore(cur.getScore() + 1);
 	                	}
 	                }
			    }
		    }
		    if(getWorld().isKeyDown(KeyCode.W)) {
			    if (this.getY() > 15) {
			    	this.setY(this.getY() - 15);
			    	st.play();
			    	Score cur = (((CrossyWorld)getWorld()).getScore());
			    	cur.setScore(cur.getScore() + 1);
			    	if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
 	                	this.setY(this.getY() + 15);
 	                	int i = 14;
 	                	boolean cont = true;
 	                	while(i > 0 && cont == true) {
 	                		this.setY(this.getY() - i);
 	                		if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
 	    	                	this.setY(this.getY() + i);
 	    	                	i--;
 	                		}else {
 	                			cont = false;
 	                		}
 	                	}
 	                	if(i == 0) {
 	                		cur.setScore(cur.getScore() - 1);
 	                	}
 	                }
			    }
		    }
		}
	}
	
	@Override
	public void act(long now) {		
		try {
			for (Node actor: getWorld().getChildren()) {
                if(actor instanceof MovingObstacle && ((MovingObstacle)actor).getType().equals("car")) {
                	boolean x = false;
                	boolean y = false;
            		if(this.getX() + this.getWidth() <= ((MovingObstacle)actor).getX() + ((MovingObstacle)actor).getWidth() && this.getX() >= ((MovingObstacle)actor).getX()) {
            			x = true;
            		} 
            		if(this.getY() + this.getHeight() >= ((MovingObstacle)actor).getY() && this.getY() + this.getHeight() <= ((MovingObstacle)actor).getY() + 1.5 * ((MovingObstacle)actor).getHeight()) {
            			y = true;
            		}
            		if(x && y) {
                		((CrossyWorld)getWorld()).setGameOver(true);
                	}
                }
                try {
                	if(getOneIntersectingObject(Road.class).getTerrain().equals("river")) {
                		boolean onLog = false;
                		for(int i = 0; i < getIntersectingObjects(MovingObstacle.class).size(); i++) {
                			if(getIntersectingObjects(MovingObstacle.class).get(i).getType().equals("log")) {
                				onLog = true;
                			}
                		}
                		if(!onLog) {
                			((CrossyWorld)getWorld()).setGameOver(true);
                		}
                	}
                }catch(Error e) {
                	
                }
        	}
		}catch(Exception e){
			
		}
	}
}

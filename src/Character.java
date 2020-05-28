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
			    if (this.getX() > 10) {
			    	this.setX(this.getX() - 15);
			    	st.play();
			    	setImage(new Image(getClass().getClassLoader().getResource("resources/chickenleft.png").toString()));
			    	for (Node actor: getWorld().getChildren()) {
			    		boolean b = false;
                        if(actor instanceof StationaryObstacle) {
                        	boolean x = false;
                        	boolean y = false;
                        	for(int i = -5; i <= 5; i++) {
                        		if(this.getX() + this.getWidth()/2 == ((StationaryObstacle)actor).getX() + ((StationaryObstacle)actor).getWidth()/2 + i) {
                        			x = true;
                        		} 
                        		if(this.getY() + this.getHeight() == ((StationaryObstacle)actor).getY() + ((StationaryObstacle)actor).getHeight() + i) {
                        			y = true;
                        		}
                        	}
                        	if(x && y) {
                        		this.setX(this.getX() + 15);
                        		System.out.println("a");
                        	}
                        }
                        if(b) {
                        	break;
                        }
                    }
	            }
			}
			if(getWorld().isKeyDown(KeyCode.D)) {
	            if (this.getX() + this.getWidth() < 290) {
	            	this.setX(this.getX() + 15);
	            	st.play();
	            	setImage(new Image(getClass().getClassLoader().getResource("resources/chickenright.png").toString()));
	            	for (Node actor: getWorld().getChildren()) {
	            		boolean b = false;
                        if(actor instanceof StationaryObstacle) {
                        	boolean x = false;
                        	boolean y = false;
                        	for(int i = -5; i <= 5; i++) {
                        		if(this.getX() + this.getWidth()/2 == ((StationaryObstacle)actor).getX() + ((StationaryObstacle)actor).getWidth()/2 + i) {
                        			x = true;
                        		} 
                        		if(this.getY() + this.getHeight() == ((StationaryObstacle)actor).getY() + ((StationaryObstacle)actor).getHeight() + i) {
                        			y = true;
                        		}
                        	}
                        	if(x && y) {
                        		this.setX(this.getX() - 15);
                        		System.out.println("a");
                        	}
                        }
                        if(b) {
                        	break;
                        }
                    }
	            }
			}
			if(getWorld().isKeyDown(KeyCode.S)) {
	            if (this.getY() + this.getHeight() < 590) {
	            	this.setY(this.getY() + 15);
	            	st.play();
	            	for (Node actor: getWorld().getChildren()) {
	            		boolean b = false;
                        if(actor instanceof StationaryObstacle) {
                        	boolean x = false;
                        	boolean y = false;
                        	for(int i = -5; i <= 5; i++) {
                        		if(this.getX() + this.getWidth()/2 == ((StationaryObstacle)actor).getX() + ((StationaryObstacle)actor).getWidth()/2 + i) {
                        			x = true;
                        		} 
                        		if(this.getY() + this.getHeight() == ((StationaryObstacle)actor).getY() + ((StationaryObstacle)actor).getHeight() + i) {
                        			y = true;
                        		}
                        	}
                        	if(x && y) {
                        		this.setY(this.getY() - 15);
                        		System.out.println("a");
                        	}
                        }
                        if(b) {
                        	break;
                        }
                    }
	            }
			}
			if(getWorld().isKeyDown(KeyCode.W)) {
	            if (this.getY() > 10) {
	            	this.setY(this.getY() - 15);
	            	st.play();
	            	for (Node actor: getWorld().getChildren()) {
	            		boolean b = false;
                        if(actor instanceof StationaryObstacle) {
                        	boolean x = false;
                        	boolean y = false;
                        	for(int i = -5; i <= 5; i++) {
                        		if(this.getX() + this.getWidth()/2 == ((StationaryObstacle)actor).getX() + ((StationaryObstacle)actor).getWidth()/2 + i) {
                        			x = true;
                        		} 
                        		if(this.getY() + this.getHeight() == ((StationaryObstacle)actor).getY() + ((StationaryObstacle)actor).getHeight() + i) {
                        			y = true;
                        		}
                        	}
                        	if(x && y) {
                        		this.setY(this.getY() + 15);
                        		System.out.println("a");
                        	}
                        }
                        if(b) {
                        	break;
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
                	for(int i = -5; i <= 5; i++) {
                		if(this.getX() + this.getWidth()/2 == ((MovingObstacle)actor).getX() + ((MovingObstacle)actor).getWidth()/2 + i) {
                			x = true;
                		} 
                		if(this.getY() + this.getHeight() == ((MovingObstacle)actor).getY() + ((MovingObstacle)actor).getHeight() + i) {
                			y = true;
                		}
                	}
                	if(x && y) {
                		((CrossyWorld)getWorld()).setGameOver(true);
                		System.out.println("a");
                	}
                }
        	}
		}catch(Exception e){
			
		}
		
		/*try {
			if(this.getOneIntersectingObject(MovingObstacle.class).getType().equals("car")) {
				((CrossyWorld)getWorld()).setGameOver(true);
			}
			//if(this.getOneIntersectingObject(Road.class).getTerrain().equals("river") && !this.getOneIntersectingObject(MovingObstacle.class).getType().equals("log")) {
				//((CrossyWorld)getWorld()).setGameOver(true);
			//}
		}catch(Exception e){
			
		}*/
	}
}

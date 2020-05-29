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
			    if (this.getX() < 290) {
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
			    if (this.getY() < 590) {
			    	this.setY(this.getY() + 15);
			    	st.play();
			    	Score cur = (((CrossyWorld)getWorld()).getScore());
			    	if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
 	                	this.setY(this.getY() - 15);
 	                	cur.setScore(cur.getScore() - 1);
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
 	                	if(i == 1) {
 	                		cur.setScore(cur.getScore() + 1);
 	                	}
 	                }
			    }
		    }
		    if(getWorld().isKeyDown(KeyCode.W)) {
			    if (this.getY() > 10) {
			    	this.setY(this.getY() - 15);
			    	st.play();
			    	Score cur = (((CrossyWorld)getWorld()).getScore());
			    	if(this.getIntersectingObjects(StationaryObstacle.class).size() > 0) {
 	                	this.setY(this.getY() + 15);
 	                	cur.setScore(cur.getScore() + 1);
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
 	                	if(i == 1) {
 	                		cur.setScore(cur.getScore() - 1);
 	                	}
 	                }
			    }
		    }
		    
		    
		    //Attempt at better collision detecting
			/*if(getWorld().isKeyDown(KeyCode.A)) {
			    if (this.getX() > 10) {
			    	this.setX(this.getX() - 15);
			    	st.play();
			    	setImage(new Image(getClass().getClassLoader().getResource("resources/chickenleft.png").toString()));
			    	for (Node actor: getWorld().getChildren()) {
			    		boolean b = false;
                        if(actor instanceof StationaryObstacle) {
                        	boolean x = false;
                        	boolean y = false;
                        	if(this.getX() + 2 * this.getWidth() <= ((StationaryObstacle)actor).getX() + 2 * ((StationaryObstacle)actor).getWidth() && this.getX() - this.getWidth() >= ((StationaryObstacle)actor).getX() - ((StationaryObstacle)actor).getWidth()) {
                    			x = true;
                    			System.out.println("aax");
                    		} 
                    		if(this.getY() + 2 * this.getHeight() <= ((StationaryObstacle)actor).getY() + 2 * ((StationaryObstacle)actor).getHeight() && this.getY() >= ((StationaryObstacle)actor).getY()) {
                    			y = true;
                    			System.out.println("aay");
                    		}
                    		if(x && y) {
                        		this.setX(this.getX() + 15);
                        		b = true;
                        		System.out.println("aa");
                        		break;
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
                    		if(this.getX() + 2 * this.getWidth() <= ((StationaryObstacle)actor).getX() + 2 * ((StationaryObstacle)actor).getWidth() && this.getX() - this.getWidth() >= ((StationaryObstacle)actor).getX() - ((StationaryObstacle)actor).getWidth()) {
                    			x = true;
                    			System.out.println("dax");
                    		} 
                    		if(this.getY() + 2 * this.getHeight() <= ((StationaryObstacle)actor).getY() + 2 * ((StationaryObstacle)actor).getHeight() && this.getY() >= ((StationaryObstacle)actor).getY()) {
                    			y = true;
                    			System.out.println("day");
                    		}
                    		if(x && y) {
                        		this.setX(this.getX() - 15);
                        		b = true;
                        		System.out.println("da");
                        		break;
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
                        	if(this.getX() + 2 * this.getWidth() <= ((StationaryObstacle)actor).getX() + 2 * ((StationaryObstacle)actor).getWidth() && this.getX() - this.getWidth() >= ((StationaryObstacle)actor).getX() - ((StationaryObstacle)actor).getWidth()) {
                    			x = true;
                    			System.out.println("sax");
                    		} 
                    		if(this.getY() + 2 * this.getHeight() <= ((StationaryObstacle)actor).getY() + 2 * ((StationaryObstacle)actor).getHeight() && this.getY() >= ((StationaryObstacle)actor).getY()) {
                    			y = true;
                    			System.out.println("say");
                    		}
                    		if(x && y) {
                        		this.setY(this.getY() - 15);
                        		b = true;
                        		System.out.println("sa");
                        		break;
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
                        	if(this.getX() + 2 * this.getWidth() <= ((StationaryObstacle)actor).getX() + 2 * ((StationaryObstacle)actor).getWidth() && this.getX() - this.getWidth() >= ((StationaryObstacle)actor).getX() - ((StationaryObstacle)actor).getWidth()) {
                    			x = true;
                    			System.out.println("wax");
                    		} 
                    		if(this.getY() + 2 * this.getHeight() <= ((StationaryObstacle)actor).getY() + 2 * ((StationaryObstacle)actor).getHeight() && this.getY() >= ((StationaryObstacle)actor).getY()) {
                    			y = true;
                    			System.out.println("way");
                    		}
                    		if(x && y) {
                        		this.setY(this.getY() + 15);
                        		b = true;
                        		System.out.println("wa");
                        		break;
                        	}
                        }
                        if(b) {
                        	break;
                        }
	            	}
	            }
			}*/
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

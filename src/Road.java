import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Road extends Actor{
	private String terrain;
	private StationaryObstacle[] stationaryObstacles;
	private MovingObstacle[] movingObstacles;
	public boolean direction;
	private double dx;
	
	public Road() {
		int n = (int)(Math.random() * 3);
	
		//Grass with trees
		if(n == 0) {
			terrain = "grass";
			setImage(new Image(getClass().getClassLoader().getResource("resources/grass.png").toString()));
		}
		
		//Road with cars
		if(n == 1) {
			terrain = "road";
			setImage(new Image(getClass().getClassLoader().getResource("resources/road.png").toString()));
			if((int)(Math.random() * 2) == 1) {
				direction = true;
			}else {
				direction = false;
			}
		}
		
		//Rivers with logs
		if(n == 2) {
			terrain = "river";
			setImage(new Image(getClass().getClassLoader().getResource("resources/river.png").toString()));
			if((int)(Math.random() * 2) == 1) {
				direction = true;
			}else {
				direction = false;
			}
		}
		
		if(direction) {
			if(getTerrain() == "road") {
				dx = 1 + Math.random() * 2;
			}else {
				dx = 1 + Math.random() * 1;
			}
		}else{
			if(getTerrain() == "road") {
				dx = -(1 + Math.random() * 2);
			}else {
				dx = -(1 + Math.random() * 1);
			}
		}
	}
	
	public Road(int n) {
		//Grass with trees
		if(n == 0) {
			terrain = "grass";
			setImage(new Image(getClass().getClassLoader().getResource("resources/grass.png").toString()));
		}
		
		//Road with cars
		if(n == 1) {
			terrain = "road";
			setImage(new Image(getClass().getClassLoader().getResource("resources/road.png").toString()));
			if((int)(Math.random() * 2) == 1) {
				direction = true;
			}else {
				direction = false;
			}
		}
		
		//Rivers with logs
		if(n == 2) {
			terrain = "river";
			setImage(new Image(getClass().getClassLoader().getResource("resources/river.png").toString()));
			if((int)(Math.random() * 2) == 1) {
				direction = true;
			}else {
				direction = false;
			}
		}
		
		if(direction) {
			if(getTerrain() == "road") {
				dx = 1 + Math.random() * 2;
			}else {
				dx = 1 + Math.random() * 1;
			}
		}else{
			if(getTerrain() == "road") {
				dx = -(1 + Math.random() * 2);
			}else {
				dx = -(1 + Math.random() * 1);
			}
		}
	}
	
	//to the right is true, to the left is false
	public boolean getDirection() {
		return direction;
	}
	
	public String getTerrain() {
		return terrain;
	}
	
	public double getdX() {
		return dx;
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}

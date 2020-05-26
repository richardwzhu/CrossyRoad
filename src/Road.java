import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Road extends Actor{
	private String terrain;
	private StationaryObstacle[] stationaryObstacles;
	private MovingObstacle[] movingObstacles;
	public boolean direction;
	
	public Road(double width, double height) {
		//this.setWidth(width);
		//this.setHeight(height/15);
		int n = (int)(Math.random() * 3);
	
		//Grass with trees
		if(n == 0) {
			terrain = "grass";
			setImage(new Image(getClass().getClassLoader().getResource("resources/grass.png").toString()));
			int nunObstacles = (int)(Math.random() * 3) + 1;
			for(int i = 0; i < nunObstacles; i++) {
				//stationaryObstacles[i] = new StationaryObstacle((int)(Math.random() * 3));
			}
			
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
			//movingObstacles = "car";
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
			//movingObstacles = "log";
		}
	}
	
	//to the right is true, to the left is false
	public boolean getDirection() {
		return direction;
	}
	
	public String getTerrain() {
		return terrain;
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}

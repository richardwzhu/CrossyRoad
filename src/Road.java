import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Road extends Rectangle{
	private String terrain;
	private StationaryObstacle[] stationaryObstacles;
	private String movingObstacles;
	public boolean direction;
	
	public Road(double width, double height) {
		this.setWidth(width);
		this.setHeight(height/15);
		int n = (int)(Math.random() * 3);
	
		//Grass with trees
		if(n == 0) {
			terrain = "grass";
			this.setFill(Color.FORESTGREEN);
			int nunObstacles = (int)(Math.random() * 3) + 1;
			for(int i = 0; i < nunObstacles; i++) {
				stationaryObstacles[i] = new StationaryObstacle((int)(Math.random() * 3));
			}
			
		}
		
		//Road with cars
		if(n == 1) {
			terrain = "road";
			this.setFill(Color.DIMGRAY);
			if((int)(Math.random() * 2) == 1) {
				direction = true;
			}else {
				direction = false;
			}
			movingObstacles = "car";
			
		}
		
		//Rivers with logs
		if(n == 2) {
			terrain = "river";
			this.setFill(Color.LIGHTSKYBLUE);
			if((int)(Math.random() * 2) == 1) {
				direction = true;
			}else {
				direction = false;
			}
			movingObstacles = "log";
		}
	}
	
	//to the right is true, to the left is false
	public boolean getDirection() {
		return direction;
	}
	
	public String getTerrain() {
		return terrain;
	}

}

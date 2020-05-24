import javafx.scene.image.Image;

public class StationaryObstacle extends Actor{
	private double xPos;
	private double yPos;

	public StationaryObstacle(int num) {
		
		//tree
		if(num == 0) {

		}
		
		//bush
		if(num == 1) {
			
		}
		
		//rock
		if(num == 2) {
			
		}
	}
	
	@Override
	public void act(long now) {
		move(0, ((CrossyWorld)getWorld()).getRate());
	}

}

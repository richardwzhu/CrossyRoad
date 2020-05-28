import javafx.scene.image.Image;

public class StationaryObstacle extends Actor{
	private double xPos;
	private double yPos;

	public StationaryObstacle(int num) {
		
		//tree
		if(num == 0) {
			setImage(new Image(getClass().getClassLoader().getResource("resources/tree.png").toString()));
		}
		
		//bush
		if(num == 1) {
			setImage(new Image(getClass().getClassLoader().getResource("resources/bush.png").toString()));
		}
		
		//rock
		if(num == 2) {
			setImage(new Image(getClass().getClassLoader().getResource("resources/rock.png").toString()));
		}
	}
	
	@Override
	public void act(long now) {

	}

}

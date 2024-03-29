import javafx.scene.image.Image;

public class MovingObstacle extends Actor{
	private double dx;
	private int num;
	private String type;

	//direction true means going to the right
	public MovingObstacle(String type, Boolean direction, double dx) {
		this.type = type;
		this.dx = dx;
		
		if(type.equals("car")) {
			num = (int)(Math.random() * 3);
			if(direction) {
				if(num == 0) {
					setImage(new Image(getClass().getClassLoader().getResource("resources/car1right.png").toString()));
				}
				if(num == 1) {
					setImage(new Image(getClass().getClassLoader().getResource("resources/car2right.png").toString()));
				}
				if(num == 2) {
					setImage(new Image(getClass().getClassLoader().getResource("resources/car3right.png").toString()));
				}
			}else {
				if(num == 0) {
					setImage(new Image(getClass().getClassLoader().getResource("resources/car1left.png").toString()));
				}
				if(num == 1) {
					setImage(new Image(getClass().getClassLoader().getResource("resources/car2left.png").toString()));
				}
				if(num == 2) {
					setImage(new Image(getClass().getClassLoader().getResource("resources/car3left.png").toString()));
				}
			}
			
		}
		
		if(type.equals("log")) {
			if(direction) {
				setImage(new Image(getClass().getClassLoader().getResource("resources/log2.png").toString()));
			}else {
				setImage(new Image(getClass().getClassLoader().getResource("resources/log1.png").toString()));
			}
		}
		
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public void act(long now) {
		move(dx, 0);
	}

}

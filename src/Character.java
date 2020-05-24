import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Character extends Actor{

	public Character(String img) {
		ImageView imageView = new ImageView(img);
		imageView.setPreserveRatio(true);
		imageView.setEffect(new Glow());
        setImage(new Image(img));
	}

	@Override
	public void act(long now) {
		if(getWorld().isKeyDown(KeyCode.LEFT)) {
		    if (this.getX()>0) {
                move(-5, 0);
            }

		}
		if(getWorld().isKeyDown(KeyCode.RIGHT)) {
            if (this.getX()+this.getWidth()<655) {
                move(5, 0);
            }

		}
		if(getWorld().isKeyDown(KeyCode.DOWN)) {
            if (this.getX()+this.getWidth()<655) {
                move(0, 5);
            }

		}
		if(getWorld().isKeyDown(KeyCode.UP)) {
            if (this.getX()+this.getWidth()<655) {
                move(0, -5);
            }

		}
	}
}

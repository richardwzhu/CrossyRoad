import javafx.scene.text.Text;

public class Lives extends Text{
	private int lives;

	public Lives() {
		lives = 1;
	}
	
	public int getLives() {
		return lives;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
}

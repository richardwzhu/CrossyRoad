import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CrossyWorld extends World{
	private Score score;
	private Lives lives;
	private Score highScore;
	private Stage stage;
	private double rate;
	Scene titleScene;
	Scene gameOverScene;
	private ArrayList<Road> map;
	
	public CrossyWorld(Stage stage, Scene titleScene, Scene gameOverScene) {
		this.titleScene = titleScene;
		this.gameOverScene = gameOverScene;
		this.stage = stage;
		
		score = new Score();
		score.setX(10);
    	score.setY(20);
		highScore = new Score();
		highScore.setX(10);
    	highScore.setY(40);
    	
    	lives = new Lives();
    	
    	rate = 1;
    	
    	getChildren().add(score);
    	getChildren().add(lives);
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	public Score getScore() {
    	return score;
    }

	public Score getHighScore() {
		return highScore;
	}
	
	public boolean isGameOver() {
		if (lives.getLives() == 0) {
			return true;
		}
		return false;
	}

	public void reset() {
		if(score.getScore() > highScore.getScore()) {
			highScore.setScore(score.getScore());
		}
		score.setScore(0);
		score.updateDisplay();
		highScore.updateDisplay();
		
		lives.setLives(1);
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public void add() {
		map = new ArrayList<Road>();
		for(int i = 0; i < 15; i++) {
			map.add(new Road(getWidth(), getHeight()/15));
			map.get(i).setX(0);
			map.get(i).setY(i * getHeight()/15);
			this.add(map.get(i));
		}
	}

}

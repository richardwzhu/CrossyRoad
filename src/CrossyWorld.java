import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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
	private boolean gameOver;
	
	public CrossyWorld(Stage stage, Scene titleScene) {
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
    	gameOver = false;
    	
    	getChildren().add(score);
    	getChildren().add(lives);
	}
	
	public Score getScore() {
    	return score;
    }

	public Score getHighScore() {
		return highScore;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public void setGameOver(boolean o) {
		gameOver = o;
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
	
	public void addMap() {
		map = new ArrayList<Road>();
		for(int i = 0; i < 13; i++) {
			Road r = new Road();
			map.add(r);
			r.setX(0);
			r.setY(40 * i);
			this.add(r);
			if(r.getTerrain().equals("grass")){
				int numObstacles = (int)(Math.random() * 4) + 1;
				for(int j = 0; j < numObstacles; j++) {
					StationaryObstacle s = new StationaryObstacle((int)(Math.random() * 3));
					s.setX(Math.random() * 240 + 30);
					s.setY(r.getY() + 3);
					this.add(s);
				}
			}
			if(r.getTerrain().equals("road")){
				MovingObstacle m;
				if(r.getDirection()) {
					m = new MovingObstacle("car", true, r.getdX());
					m.setX(-20);
					m.setY(r.getY() + 3);
				}else {
					m = new MovingObstacle("car", false, r.getdX());
					m.setX(300);
					m.setY(r.getY() + 3);
				}
				this.add(m);
			}
			if(r.getTerrain().equals("river")){
				MovingObstacle m;
				if(r.getDirection()) {
					m = new MovingObstacle("log", true, r.getdX());
					m.setX(-20);
					m.setY(r.getY() + 5);
				}else {
					m = new MovingObstacle("log", false, r.getdX());
					m.setX(300);
					m.setY(r.getY() + 5);
				}
				this.add(m);
			}
		}
		for(int i = 13; i < 15; i++) {
			Road r = new Road(0);
			map.add(r);
			r.setX(0);
			r.setY(40 * i);
			this.add(r);
		}
	}
	
	@Override
	public void act(long now) {
		for(Road r : map) {
			if(r.getTerrain().equals("road") && r.getIntersectingObjects(MovingObstacle.class).size() == 0) {
				MovingObstacle m;
				if(r.getDirection()) {
					m = new MovingObstacle("car", true, r.getdX());
					m.setX(-20);
					m.setY(r.getY() + 3);
				}else {
					m = new MovingObstacle("car", false, r.getdX());
					m.setX(300);
					m.setY(r.getY() + 3);
				}
				this.add(m);
			}
			if(r.getTerrain().equals("river") && r.getIntersectingObjects(MovingObstacle.class).size() == 0) {
				MovingObstacle m;
				if(r.getDirection()) {
					m = new MovingObstacle("log", true, r.getdX());
					m.setX(-20);
					m.setY(r.getY() + 5);
				}else {
					m = new MovingObstacle("log", false, r.getdX());
					m.setX(300);
					m.setY(r.getY() + 5);
				}
				this.add(m);
			}
		}
	}
	

}

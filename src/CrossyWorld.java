import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CrossyWorld extends World{
	private Score score;
	private Stage stage;
	private double rate;
	Scene titleScene;
	Scene gameOverScene;
	private ArrayList<Road> map;
	private boolean gameOver;
	private boolean clear;
	
	public CrossyWorld(Stage stage, Scene titleScene, Scene gameOverScene) {
		this.titleScene = titleScene;
		this.stage = stage;
		this.gameOverScene = gameOverScene;
		
		addMap();
		
		score = new Score();
		score.setX(5);
		score.setY(20);
    	
    	rate = 1;
    	gameOver = false;
    	clear = false;
    	
    	getChildren().add(score);
	}
	
	public Score getScore() {
    	return score;
    }
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public void setGameOver(boolean o) {
		gameOver = o;
	}
	
	public void setLevelCleared(boolean clear) {
		this.clear = clear;
	}
	
	public boolean isLevelCleared() {
		return clear;
	}
	
	public ArrayList<Road> getMap(){
		return map;
	}

	public void reset() {
		map.clear();
		addMap();
	}
	
	public void cont() {
		map.clear();
		for(Node actor : this.getChildren()) {
			if(actor instanceof StationaryObstacle || actor instanceof MovingObstacle) {
				this.remove((Actor)actor);
			}
		}
		addMap();
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public void addMap() {
		map = new ArrayList<Road>();
		for(int i = 0; i < 12; i++) {
			Road r = new Road();
			map.add(r);
			r.setX(0);
			r.setY(40 * i);
			this.add(r);
			if(r.getTerrain().equals("grass")){
				int numObstacles = (int)(Math.random() * 3) + 1;
				for(int j = 0; j < numObstacles; j++) {
					StationaryObstacle s = new StationaryObstacle((int)(Math.random() * 3));
					s.setX(Math.random() * 240 + 30);
					s.setY(r.getY() + 3);
					this.add(s);
				}
			}
		}
		for(int i = 12; i < 15; i++) {
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

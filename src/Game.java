import java.util.ConcurrentModificationException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {
	private CrossyWorld crossyWorld;
	private Scene titleScene;
	private Scene gameScene;
	private Character player;
	private int score;
	
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
    	DropShadow dropShadow = new DropShadow(10, 10, 10, Color.GRAY);
        Light.Distant light = new Light.Distant();
		light.setAzimuth(-135.0);
		Lighting lighting = new Lighting();
		lighting.setLight(light);
		lighting.setSurfaceScale(5.0);
		dropShadow.setInput(lighting);
    	score = 0;
    	
    	stage.setTitle("Crossy Road");
        stage.setResizable(false);
        stage.setWidth(300);
        stage.setHeight(600);

        

        crossyWorld = new CrossyWorld(stage, titleScene);


        
        //Intro screen
        Group titleScreen = new Group();
        
        ImageView background = new ImageView();
        background.setImage(new Image(getClass().getClassLoader().getResource("resources/introback.png").toString()));
        background.setX(-200);
        background.setY(-100);
        
        Text breakoutText = new Text("Crossy Road");
		breakoutText.setEffect(dropShadow);
		breakoutText.setFill(Color.WHITE);
        breakoutText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 30));

        Button playButton = new Button("Play");
        playButton.setEffect(dropShadow);
        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
            	stage.setScene(gameScene);
            	crossyWorld.start();
                crossyWorld.requestFocus();
                stage.show();
            }
        });
        

        //Set positions
        breakoutText.setLayoutX(70);
        breakoutText.setLayoutY(100);
        playButton.setLayoutX(120);
        playButton.setLayoutY(160);

        titleScreen.getChildren().add(background);
        titleScreen.getChildren().add(breakoutText);
        titleScreen.getChildren().add(playButton);

        titleScene = new Scene(titleScreen);
        stage.setScene(titleScene);
        stage.show();
        
        
        
        //Game scene
        BorderPane crossyWorldScene = new BorderPane();

        //Create map
        crossyWorld.addMap();

        MyKeyPressedListener p = new MyKeyPressedListener();
        crossyWorld.setOnKeyPressed(p);
        crossyWorldScene.setCenter(crossyWorld);
        
        player = new Character(getClass().getClassLoader().getResource("resources/chickenleft.png").toString());
        player.setX(stage.getWidth()/2 - player.getWidth()/2);
        player.setY(stage.getHeight() - 80);
        crossyWorld.add(player);
        
        crossyWorld.start();
        
        gameScene = new Scene(crossyWorldScene);
        stage.show();
        crossyWorld.requestFocus();
        
        
        
        //Game Over Scene
        //BorderPane gameOverScreen = new BorderPane();
        //gameOverScene = new Scene(gameOverScreen);

        
        AnimationTimer mainLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (stage.getScene() == (gameScene)) {
                    if (crossyWorld.isGameOver()) {
                    	ColorAdjust adj = new ColorAdjust(0, 0, 0, 0);
                        GaussianBlur blur = new GaussianBlur(10);
                        adj.setInput(blur);
                        crossyWorld.setEffect(adj);
                        
                        try {
                            for (Node actor: player.getWorld().getChildren()) {
                                if(actor instanceof MovingObstacle) {
                                    ((MovingObstacle)actor).setOver();
                                }
                            }
                        } catch (ConcurrentModificationException e) {

                        }
                        
                        Text scoreText = new Text("Your score was: " + score + "!");
                        scoreText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                        scoreText.setFill(Color.WHITE);
                        scoreText.setX(45);
                        scoreText.setY(75);
                        crossyWorldScene.getChildren().add(scoreText);
                        
                        Button replayButton = new Button("Replay");
                        //replayButton.setEffect(dropShadow);
                        replayButton.setLayoutX(60);
                        replayButton.setLayoutY(200);
                        crossyWorldScene.getChildren().add(replayButton);
                        replayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent t) {
                            	crossyWorldScene.getChildren().remove(scoreText);
                            	crossyWorldScene.getChildren().remove(replayButton);
                                crossyWorld.reset();
                                stage.show();

                            }
                        });
                    }
                }
            }

        };
        mainLoop.start();
    }
    
    class MyKeyPressedListener implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			crossyWorld.addKeyCode(event.getCode());
			player.movement();
			crossyWorld.removeKeyCode(event.getCode());
		} 	
    }
}



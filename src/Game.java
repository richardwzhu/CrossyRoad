import java.util.ConcurrentModificationException;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
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
import javafx.util.Duration;

public class Game extends Application {
	private CrossyWorld crossyWorld;
	private Scene titleScene;
	private Scene gameScene;
	private Scene instructionScene;
	private Character player;
	private int score;
	private RotateTransition rt;
	
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
    	rt = new RotateTransition();
        rt.setDuration(Duration.seconds(2));
        rt.setByAngle(360);
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
        
        Text crossyText = new Text("Crossy Road");
        crossyText.setEffect(dropShadow);
        crossyText.setFill(Color.BLACK);
        crossyText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 50));

        //Create Buttons
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
        
        Button instructionButton = new Button("Instructions");
        instructionButton.setEffect(dropShadow);
        instructionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
            	stage.setScene(instructionScene);
            	rt.playFromStart();
            	stage.show();
            }
        });
        
        //Position elements and add them
        background.setLayoutX(-200);
        background.setLayoutY(-100);
        crossyText.setLayoutX(22.5);
        crossyText.setLayoutY(100);
        playButton.setLayoutX(120);
        playButton.setLayoutY(160);
        instructionButton.setLayoutX(101);
        instructionButton.setLayoutY(200);

        titleScreen.getChildren().add(background);
        titleScreen.getChildren().addAll(crossyText, playButton, instructionButton);

        titleScene = new Scene(titleScreen);
        stage.setScene(titleScene);
        stage.show();
        
        
        
        //Instruction screen 
        Group instructionScreen = new Group();

        Text instructionText = new Text("Instructions!");
        instructionText.setEffect(dropShadow);
        instructionText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 40));

        //Create Buttons
        Button instructionReturnButton = new Button("Return to Main Menu");
        instructionReturnButton.setPrefSize(200, 50);
        instructionReturnButton.setEffect(dropShadow);
        instructionReturnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                stage.setScene(titleScene);
                stage.show();
                rt.playFromStart();
            }
        });

        //Create instruction message
        Text instructionMessage = new Text();
        instructionMessage.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 7));
        instructionMessage.setText("             Use WASD to move your character around the screen.\n" +
                "Every step forward, you gain 1 point and every step back, you lose 1.\n" +
                "You cannot run through stationary obstacles (trees, bushes, rocks)\n " +
                "      and if you are hit by a car or fall into the river, you lose.");

        //Position elements and add them
        instructionText.setLayoutX(45);
        instructionText.setLayoutY(100);
        instructionMessage.setLayoutX(20);
        instructionMessage.setLayoutY(160);
        instructionReturnButton.setLayoutX(50);
        instructionReturnButton.setLayoutY(200);

        instructionScreen.getChildren().addAll(background, instructionText, instructionMessage, instructionReturnButton);

        instructionScene = new Scene(instructionScreen);

        rt.setNode(instructionText);
        
        
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
                        
                        Button replayButton = new Button("Replay");
                        replayButton.setEffect(dropShadow);
                        replayButton.setLayoutX(60);
                        replayButton.setLayoutY(200);
                        replayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent t) {
                            	crossyWorldScene.getChildren().remove(scoreText);
                            	crossyWorldScene.getChildren().remove(replayButton);
                                crossyWorld.reset();
                                stage.show();

                            }
                        });
                        
                        crossyWorldScene.getChildren().addAll(scoreText, replayButton);
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



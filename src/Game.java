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
	private Scene gameOverScene;
	private Character player;
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
    	
    	stage.setTitle("Crossy Road");
        stage.setResizable(false);
        stage.setWidth(300);
        stage.setHeight(600);

        crossyWorld = new CrossyWorld(stage, titleScene, gameOverScene);


        
        //Intro screen
        Group titleScreen = new Group();
        
        ImageView background = new ImageView();
        background.setImage(new Image(getClass().getClassLoader().getResource("resources/introback.png").toString()));
        
        Text crossyText = new Text("Crossy Road");
        crossyText.setEffect(dropShadow);
        crossyText.setFill(Color.BLACK);
        crossyText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 50));
        
        //Create visuals
        ImageView chicken = new ImageView();
        chicken.setImage(new Image(getClass().getClassLoader().getResource("resources/titlechicken.png").toString()));

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
        background.setX(-200);
        background.setY(-100);
        chicken.setLayoutX(150);
        chicken.setLayoutY(320);
        crossyText.setLayoutX(23);
        crossyText.setLayoutY(100);
        playButton.setLayoutX(125);
        playButton.setLayoutY(160);
        instructionButton.setLayoutX(105);
        instructionButton.setLayoutY(200);

        titleScreen.getChildren().addAll(background, chicken, crossyText, playButton, instructionButton);

        titleScene = new Scene(titleScreen);
        stage.setScene(titleScene);
        stage.show();
        
        
        
        //Instruction screen 
        Group instructionScreen = new Group();
        
        ImageView back = new ImageView();
        back.setImage(new Image(getClass().getClassLoader().getResource("resources/introback.png").toString()));

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
        back.setX(-200);
        back.setY(-100);
        instructionText.setLayoutX(45);
        instructionText.setLayoutY(100);
        instructionMessage.setLayoutX(20);
        instructionMessage.setLayoutY(160);
        instructionReturnButton.setLayoutX(50);
        instructionReturnButton.setLayoutY(200);

        instructionScreen.getChildren().addAll(back, instructionText, instructionMessage, instructionReturnButton);

        instructionScene = new Scene(instructionScreen);

        rt.setNode(instructionText);
        
        
        
        //Game scene
        BorderPane crossyWorldScene = new BorderPane();

        //Create map

        MyKeyPressedListener p = new MyKeyPressedListener();
        crossyWorld.setOnKeyPressed(p);
        crossyWorldScene.setCenter(crossyWorld);

        player = new Character(getClass().getClassLoader().getResource("resources/chickenleft.png").toString());
        player.setX(stage.getWidth()/2 - player.getWidth()/2);
        player.setY(stage.getHeight() - 80);
        
        crossyWorld.getChildren().addAll(player);
        
        crossyWorld.start();
        
        gameScene = new Scene(crossyWorldScene);
        stage.show();
        crossyWorld.requestFocus();
        
        
        
        //Game Over scene
        //Set up scene
        Group gameOverScreen = new Group();
        
        ImageView b = new ImageView();
        b.setImage(new Image(getClass().getClassLoader().getResource("resources/introback.png").toString()));

        //Create UI elements
        Text gameOverText = new Text("Game Over!");
		dropShadow.setInput(lighting);
        gameOverText.setEffect(dropShadow);
        gameOverText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 50));

        Text scoreText = new Text("Your Score Was " + crossyWorld.getScore().getScore() + "!");
        scoreText.setEffect(dropShadow);
        scoreText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 30));

        Button replayButton = new Button("Replay");
        replayButton.setEffect(dropShadow);
        replayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                stage.setScene(gameScene);
                player.setX(stage.getWidth()/2 - player.getWidth()/2);
                player.setY(stage.getHeight() - 80);
                crossyWorld.reset();
            	crossyWorld.addMap();
                stage.show();
            }
        });
        
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setEffect(dropShadow);
        mainMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                stage.setScene(titleScene);
                player.setX(stage.getWidth()/2 - player.getWidth()/2);
                player.setY(stage.getHeight() - 80);
                //crossyWorld.reset();
            	crossyWorld.addMap();
                stage.show();
                rt.playFromStart();
            }
        });

        b.setX(-200);
        b.setY(-100);
        gameOverText.setLayoutX(35);
        gameOverText.setLayoutY(100);
        scoreText.setLayoutX(40);
        scoreText.setLayoutY(160);
        replayButton.setLayoutX(120);
        replayButton.setLayoutY(200);
        mainMenuButton.setLayoutX(108);
        mainMenuButton.setLayoutY(250);
        
        gameOverScreen.getChildren().addAll(b, gameOverText, scoreText, replayButton, mainMenuButton);

        gameOverScene = new Scene(gameOverScreen);

        rt.setNode(gameOverText);

        
        
        
        AnimationTimer mainLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (stage.getScene() == (gameScene)) {
                    if (crossyWorld.isGameOver()) {
                    	scoreText.setText("Your Score Was " + crossyWorld.getScore().getScore() + "!");
                    	stage.setScene(gameOverScene);
                        stage.show();
                        rt.playFromStart();
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



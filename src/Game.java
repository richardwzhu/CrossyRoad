import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {
	private CrossyWorld crossyWorld;
	private Scene titleScene;
	private Scene gameScene;
	private Scene gameOverScene;
	private Character player;
	
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
    	
    	stage.setTitle("Crossy Road");
        stage.setResizable(false);
        stage.setWidth(300);
        stage.setHeight(600);

        

        crossyWorld = new CrossyWorld(stage, titleScene, gameOverScene);


        
        //Intro screen
        Group titleScreen = new Group();
        titleScene = new Scene(titleScreen);
        stage.setScene(titleScene);
        stage.show();
        
        
        
        //Game scene
        BorderPane crossyWorldScene = new BorderPane();

        //Create map
        crossyWorld.add();


        player = new Character(getClass().getClassLoader().getResource("resources/chicken.png").toString());
        player.setX(200);
        player.setY(500);
        crossyWorld.add(player);
        
        gameScene = new Scene(crossyWorldScene);
        stage.setScene(gameScene);
        stage.show();
        
        
    }
}



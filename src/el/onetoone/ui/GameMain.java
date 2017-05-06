package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameMain {

	Stage primaryStage;
	Scene lastScene;
	Scene scene;
	String mode;
	Group root;
	GamePanel gamePanel;
	
	ImageView backgroud, gameFrame;

	public GameMain(Stage primaryStage, Scene lastScene, String mode) {
		
		this.primaryStage = primaryStage;
		this.mode = mode;
		this.lastScene = lastScene;
		
		gamePanel = new GamePanel();
		root = new Group();
		scene = new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		scene.setFill(Color.BLACK);
		gamePanel.setLayoutX(250);
		primaryStage.setScene(scene);
		
		createControls();
		addControls();
	}

	private void addControls() {

		root.getChildren().add(backgroud);
		root.getChildren().add(gamePanel);
//		root.getChildren().add(gameFrame);
	}

	private void createControls() {
		
		backgroud = new ImageView(gamePanel.getTheme().getBG_GAME());
		gameFrame = new ImageView(gamePanel.getTheme().getFRAME_GAME());
	}
}

package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameMain {

	Stage primaryStage;
	Scene lastScene;
	Scene scene;
	String mode;
	Group root;
	GamePanel gamePanel;
	
	ImageView backgroud, gameFrame, moneyFrame, timeFrame, stepFrame, scoreFrame;
	SystemButton exitButton;

	public GameMain(Stage primaryStage, Scene lastScene, String mode) {
		
		this.primaryStage = primaryStage;
		this.mode = mode;
		this.lastScene = lastScene;
		
		gamePanel = new GamePanel();
		root = new Group();
		
		scene = new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		scene.setFill(Color.BLACK);
		gamePanel.setLayoutX(500);
		gamePanel.setLayoutY(100);
		primaryStage.setScene(scene);
		
		createControls();
		addControls();
	}

	private void addControls() {

		root.getChildren().add(backgroud);
		root.getChildren().add(gameFrame);
		
		gameFrame.setLayoutX(260);
		gameFrame.setLayoutY(-150);
		
		root.getChildren().addAll(moneyFrame, stepFrame, timeFrame, scoreFrame);
		root.getChildren().add(gamePanel);
		
		moneyFrame.setX(50);
		moneyFrame.setLayoutY(0);
		
		root.getChildren().add(exitButton);
		exitButton.setLayoutX(Config.SCREEN_WIDTH - 55);
		exitButton.setLayoutY(10);
		exitButton.setOnAction(e -> {
			Theme.setBlur(root);
			root.getChildren().add(new ExitPane());
		});
	}

	private void createControls() {
		
		backgroud = new ImageView(gamePanel.getTheme().getBG_GAME());
		gameFrame = new ImageView(gamePanel.getTheme().getFRAME_GAME());
		moneyFrame = new ImageView(gamePanel.getTheme().getFRAME_MONEY());
		stepFrame = new ImageView(gamePanel.getTheme().getFRAME_STEP());
		timeFrame = new ImageView(gamePanel.getTheme().getFRAME_TIME());
		scoreFrame = new ImageView(gamePanel.getTheme().getFRAME_HIGHEST_SCORE());
		
		exitButton = new SystemButton(0);
	}	
}

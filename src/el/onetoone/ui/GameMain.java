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

	private Stage primaryStage;
	private Scene lastScene;
	private Scene scene;
	private String mode;
	private Group root;
	private GamePanel gamePanel;

	// 各种框
	private ImageView backgroud, gameFrame, moneyFrame, timeFrame, stepFrame, scoreFrame;
	// 退出按钮
	private SystemButton exitButton;
	
	//积分板
	private FramePanel scorePanel;
	/**
	 * 设置按钮
	 */
	private SystemButton configButton;
	
	
	/**
	 * 
	 * @param primaryStage
	 * @param lastScene
	 * @param mode
	 */
	public GameMain(Stage primaryStage, Scene lastScene, String mode) {

		this.primaryStage = primaryStage;
		this.mode = mode;
		this.lastScene = lastScene;

		root = new Group();

		scene = new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		scene.setFill(Color.BLACK);
		
		primaryStage.setScene(scene);

		createNode();
		addNode();
	}

	/**
	 * 讲创建的组件进行设置，并添加到root中
	 */
	private void addNode() {

		root.getChildren().add(backgroud);
		root.getChildren().add(gameFrame);
		root.getChildren().add(gamePanel);

		gameFrame.setLayoutX(260);
		gameFrame.setLayoutY(-150);

//		root.getChildren().addAll(moneyFrame, stepFrame, timeFrame, scoreFrame);

//		moneyFrame.setX(50);
//		moneyFrame.setLayoutY(0);

		root.getChildren().add(exitButton);
		root.getChildren().add(configButton);
		root.getChildren().add(scorePanel);
		scorePanel.initScoreText();
	}

	/**
	 * 创建 组件
	 */
	private void createNode() {

		gamePanel = new GamePanel();
		gamePanel.setLayoutX(500);
		gamePanel.setLayoutY(100);
		
		backgroud = new ImageView(gamePanel.getTheme().getBG_GAME());
		gameFrame = new ImageView(gamePanel.getTheme().getFRAME_GAME());
//		moneyFrame = new ImageView(gamePanel.getTheme().getFRAME_MONEY());
//		stepFrame = new ImageView(gamePanel.getTheme().getFRAME_STEP());
//		timeFrame = new ImageView(gamePanel.getTheme().getFRAME_TIME());
//		scoreFrame = new ImageView(gamePanel.getTheme().getFRAME_HIGHEST_SCORE());

		exitButton = new SystemButton(0);
		exitButton.setLayoutX(Config.SCREEN_WIDTH - 55);
		exitButton.setLayoutY(10);
		exitButton.setOnAction(e -> {
			Theme.setBlur(root);
			root.getChildren().add(new ExitPane());
		});
		
		configButton = new SystemButton(2);
		configButton.setLayoutX(Config.SCREEN_WIDTH - 110);
		configButton.setLayoutY(10);
		configButton.setOnAction(e -> {
			Theme.setBlur(root);
			root.getChildren().add(new SettingPane());
		});
		
		scorePanel = new FramePanel(0);
		scorePanel.setLayoutX(50);
		scorePanel.setLayoutY(400);
	}
	
	private void goBackToLastScene() {
		
		primaryStage.setScene(lastScene);
	}
}

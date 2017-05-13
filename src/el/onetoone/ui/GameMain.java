package el.onetoone.ui;

import el.onetoone.back.BaseDiamondGrid;
import el.onetoone.back.Config;
import el.onetoone.back.ItemList;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameMain extends Pane{

	
	private static final int FRAMR_HEIGHT = 110;
	private static final int PROPS_WIDTH = 80;
	private String mode;
	private GamePanel gamePanel;
	private TilePane propPane;
	private BaseDiamondGrid diamondGrid;

	// 各种框
	private ImageView backgroud, gameFrame, moneyFrame, timeFrame, stepFrame, scoreFrame;
	private ImageView propsBoom, propsHammer, propsNewMap;
	private VBox frameBox;
	private String currentProps;
	public String getCurrentProps() {
		return currentProps;
	}

	public void setCurrentProps(String currentProps) {
		this.currentProps = currentProps;
	}

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
	public GameMain() {

		currentProps = null;
		createNode();
		addNode();
		createProps();
	}
	
	private void createProps() {
		
		diamondGrid = gamePanel.getDiamondGrid();
		propsBoom = new ImageView(Theme.PROPS_BOOM);
		propsBoom.setFitWidth(PROPS_WIDTH);
		propsBoom.setPreserveRatio(true);
		
		propsHammer = new ImageView(Theme.PROPS_HAMMER);
		propsHammer.setFitWidth(PROPS_WIDTH);
		propsHammer.setPreserveRatio(true);
		
		propsNewMap = new ImageView(Theme.PROPS_NEW_MAP);
		propsNewMap.setFitWidth(PROPS_WIDTH);
		propsNewMap.setPreserveRatio(true);
		
		propPane = new TilePane();
		propPane.setPrefColumns(2);
		propPane.getChildren().addAll(propsBoom, propsHammer, propsNewMap);
		propPane.setLayoutX(Config.SCREEN_WIDTH - 250);
		propPane.setLayoutY(80);
		propPane.setVgap(15);
		propPane.setHgap(15);
		
		getChildren().add(propPane);
		
		propsBoom.setOnMousePressed(e -> {
			setCursor(new ImageCursor(Theme.PROPS_BOOM));
			currentProps = ItemList.BOOM;
		});
		
		propsHammer.setOnMousePressed(e -> {
			setCursor(new ImageCursor(Theme.PROPS_HAMMER));
			currentProps = ItemList.HAMMER;
		});
		
		propsNewMap.setOnMousePressed(e -> {
			setCursor(new ImageCursor(Theme.PROPS_NEW_MAP));
			currentProps = ItemList.NEWMAP;
		});
	}

	public void setMode(String mode) {
		
		this.mode = mode;
	}

	/**
	 * 讲创建的组件进行设置，并添加到root中
	 */
	private void addNode() {

		this.getChildren().add(backgroud);
		this.getChildren().add(gameFrame);
		this.getChildren().add(gamePanel);

		this.getChildren().add(exitButton);
		this.getChildren().add(configButton);
//		scorePanel.initScoreText();
		
		frameBox = new VBox(timeFrame, stepFrame, scoreFrame, moneyFrame);
		frameBox.setLayoutX(170);
		frameBox.setLayoutY(260);
		getChildren().add(frameBox);
	}

	/**
	 * 创建 组件
	 */
	private void createNode() {

		gamePanel = new GamePanel(this);
		gamePanel.setLayoutX(460);
		gamePanel.setLayoutY(100);
		
		backgroud = new ImageView(MagicGirlTheme.BG_GAME_ING);
		
		gameFrame = new ImageView(gamePanel.getTheme().getFRAME_GAME());
		gameFrame.setFitWidth(600);
		gameFrame.setPreserveRatio(true);
		gameFrame.setLayoutX(400);
		gameFrame.setLayoutY(30);
		
		moneyFrame = new ImageView(gamePanel.getTheme().getFRAME_MONEY());
		moneyFrame.setFitHeight(FRAMR_HEIGHT);
		moneyFrame.setPreserveRatio(true);
		
		stepFrame = new ImageView(gamePanel.getTheme().getFRAME_STEP());
		stepFrame.setFitHeight(FRAMR_HEIGHT);
		stepFrame.setPreserveRatio(true);
		
		timeFrame = new ImageView(gamePanel.getTheme().getFRAME_TIME());
		timeFrame.setFitHeight(FRAMR_HEIGHT);
		timeFrame.setPreserveRatio(true);
		
		scoreFrame = new ImageView(gamePanel.getTheme().getFRAME_HIGHEST_SCORE());
		scoreFrame.setFitHeight(FRAMR_HEIGHT);
		scoreFrame.setPreserveRatio(true);

		exitButton = new SystemButton(0);
		exitButton.setLayoutX(Config.SCREEN_WIDTH - 55);
		exitButton.setLayoutY(10);
		exitButton.setOnAction(e -> {
			Theme.setBlur(this);
			this.getChildren().add(new ExitPane());
//			goBackToLastScene();
		});
		
		configButton = new SystemButton(2);
		configButton.setLayoutX(Config.SCREEN_WIDTH - 110);
		configButton.setLayoutY(10);
		configButton.setOnAction(e -> {
			Theme.setBlur(this);
			this.getChildren().add(new SettingPane());
		});
		
		scorePanel = new FramePanel(0);
		scorePanel.setLayoutX(50);
		scorePanel.setLayoutY(400);
	}
	
	private void goBackToLastScene() {
		
		((Pane)getParent()).getChildren().remove(this);
	}
	
}

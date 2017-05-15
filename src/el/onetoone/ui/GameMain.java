package el.onetoone.ui;

import el.onetoone.back.BaseDiamondGrid;
import el.onetoone.back.Config;
import el.onetoone.back.ItemList;
import el.onetoone.back.UserBox;
import el.onetoone.exceptions.NotLoginException;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class GameMain extends Pane {

	private static final int FRAMR_HEIGHT = 110;
	private static final int PROPS_WIDTH = 80;
	private static final int FRAME_CONTENT_LAYOUT_X = 200;
	private static final int FRAME_CONTENT_LAYOUT_Y = 290;
	private static final int BUTTON_WIDTH = 80;
	private String mode;
	private GamePanel gamePanel;
	private TilePane propPane;
	private BaseDiamondGrid diamondGrid;
	private ContentText scoreText, timeText, stepText;

	// 各种框
	private ImageView backgroud, gameFrame, moneyFrame, timeFrame, stepFrame, scoreFrame;
	Props propsBoom, propsHammer, propsNewMap, propsOneStep, propsThreeStep, propsFiveStep, propsOneSec, propsThreeSec, propsFiveSec;
	private ImageView timeIndefiniteImg, stepIndefiniteImg;
	private Button backBtn, restartBtn;
	private VBox frameBox;
	private HBox buttonBox;
	private String currentProps;

	public String getCurrentProps() {
		return currentProps;
	}

	public void setCurrentProps(String currentProps) {
		this.currentProps = currentProps;
	}

	// 退出按钮
	private SystemButton exitButton;

	// 积分板
	private FramePanel scorePanel;
	/**
	 * 设置按钮
	 */
	private SystemButton configButton;

	public GameMain(String mode) {
		this.mode = mode;
		currentProps = null;
		createNode();
		addNode();
		createProps();
		setFrameContent();
	}

	private void setFrameContent() {

		timeIndefiniteImg = new ImageView(Config.getTheme().getICON_INDEFINITE());
		timeIndefiniteImg.setFitHeight(FRAMR_HEIGHT - 50);
		timeIndefiniteImg.setPreserveRatio(true);
		timeIndefiniteImg.setLayoutX(FRAME_CONTENT_LAYOUT_X);
		timeIndefiniteImg.setLayoutY(FRAME_CONTENT_LAYOUT_Y);

		stepIndefiniteImg = new ImageView(Config.getTheme().getICON_INDEFINITE());
		stepIndefiniteImg.setFitHeight(FRAMR_HEIGHT - 50);
		stepIndefiniteImg.setPreserveRatio(true);
		stepIndefiniteImg.setLayoutX(FRAME_CONTENT_LAYOUT_X);
		stepIndefiniteImg.setLayoutY(FRAME_CONTENT_LAYOUT_Y + FRAMR_HEIGHT);

		if (mode == null) {
			getChildren().addAll(timeIndefiniteImg, stepIndefiniteImg);
			return;
		}
		switch (mode) {
		case SyntheticModel.TIMELIMITED:
			getChildren().add(stepIndefiniteImg);
			timeText = new ContentText(diamondGrid, gamePanel);
			timeText.bindTime();
			timeText.setLayoutX(FRAME_CONTENT_LAYOUT_X);
			timeText.setLayoutY(FRAME_CONTENT_LAYOUT_Y + 50);
			getChildren().add(timeText);
			break;

		case SyntheticModel.STEPLIMITED:
			getChildren().add(timeIndefiniteImg);
			stepText = new ContentText(diamondGrid, gamePanel);
			stepText.bindStep();
			stepText.setLayoutX(FRAME_CONTENT_LAYOUT_X);
			stepText.setLayoutY(FRAME_CONTENT_LAYOUT_Y + FRAMR_HEIGHT + 50);
			getChildren().add(stepText);
			break;

		case SyntheticModel.UNLIMITE:
			getChildren().addAll(timeIndefiniteImg, stepIndefiniteImg);
			break;
		}

		scoreText = new ContentText(diamondGrid, gamePanel);
		scoreText.setLayoutX(FRAME_CONTENT_LAYOUT_X);
		scoreText.setLayoutY(frameBox.getLayoutY() + FRAMR_HEIGHT * 3 + 30);
		scoreText.bindGrade();

		getChildren().add(scoreText);
	}

	private void createProps() {

		diamondGrid = gamePanel.getDiamondGrid();
		propsBoom = new Props(Theme.PROPS_BOOM, ItemList.BOOM);
		propsHammer = new Props(Theme.PROPS_HAMMER, ItemList.HAMMER);
		propsNewMap = new Props(Theme.PROPS_NEW_MAP, ItemList.NEWMAP);
		propsOneSec = new Props(Theme.PROPS_PLUS_ONE_S, ItemList.PLUSONESECOND);
		propsThreeSec = new Props(Theme.PROPS_PLUS_THREE_S, ItemList.PLUSTHREESECONDS);
		propsFiveSec = new Props(Theme.PROPS_PLUS_FIVE_S, ItemList.PLUSFIVESECONDS);
		propsOneStep = new Props(Theme.PROPS_ONE_STEP, ItemList.PLUSONESTEP);
		propsThreeStep = new Props(Theme.PROPS_THREE_STEP, ItemList.PLUSTHREESTEPS);
		propsFiveStep = new Props(Theme.PROPS_FIVE_STEP, ItemList.PLUSFIVESTEPS);

		propPane = new TilePane();
		propPane.setPrefColumns(2);
		propPane.getChildren().addAll(propsBoom, propsHammer, propsNewMap, propsOneSec, propsThreeSec, propsFiveSec, propsOneStep, propsThreeStep, propsFiveStep);
		propPane.setLayoutX(Config.SCREEN_WIDTH - 250);
		propPane.setLayoutY(80);
		propPane.setVgap(15);
		propPane.setHgap(15);

		getChildren().add(propPane);
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
		// scorePanel.initScoreText();

		frameBox = new VBox(timeFrame, stepFrame, scoreFrame, moneyFrame);
		frameBox.setLayoutX(170);
		frameBox.setLayoutY(260);
		getChildren().add(frameBox);

		buttonBox = new HBox(backBtn, restartBtn);
		buttonBox.setLayoutX(Config.SCREEN_WIDTH - 250);
		buttonBox.setLayoutY(Config.SCREEN_HEIGHT - 150);
		buttonBox.setSpacing(10);
		getChildren().add(buttonBox);
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
			// goBackToLastScene();
		});

		configButton = new SystemButton(2);
		configButton.setLayoutX(Config.SCREEN_WIDTH - 110);
		configButton.setLayoutY(10);
		configButton.setOnAction(e -> {
			Theme.setBlur(this);
			// this.getChildren().add(new SettingPane());
		});

		ImageView backImg = new ImageView(Config.getTheme().getBUTTON_BACK());
		backImg.setFitWidth(BUTTON_WIDTH);
		backImg.setPreserveRatio(true);

		backBtn = new Button();
		backBtn.setGraphic(backImg);
		backBtn.setOnAction(e -> {
			gamePanel.gameOver();
			Config.getMain().setScene(Config.getTheme().getSynScene());
		});

		ImageView restartImg = new ImageView(Config.getTheme().getBUTTON_GAME_RESTART());
		restartImg.setFitWidth(BUTTON_WIDTH);
		restartImg.setPreserveRatio(true);

		restartBtn = new Button();
		restartBtn.setGraphic(restartImg);
		restartBtn.setOnAction(e -> {
			diamondGrid.gradeProperty().set(0);
			diamondGrid.stepProperty().set(Config.START_STEP);
			diamondGrid.timeProperty().set(Config.START_TIME);
			gamePanel.restart();
		});
	}

	public String getMode() {
		return mode;
	}
	
	class Props extends StackPane {
		
		private ImageView propsImg;
		private Label quantityLabel;
		private String item;
		public Props(Image image, String item) {
			
			this.item = item;
			
			propsImg = new ImageView();
			propsImg.setImage(image);
			propsImg.setFitWidth(PROPS_WIDTH);
			propsImg.setPreserveRatio(true);
			propsImg.setOnMouseClicked(e -> {
				
				if (e.getClickCount() == 2) {
					
					try {
						switch (item) {
						case ItemList.NEWMAP:
							diamondGrid.useGenNewMap();
							gamePanel.repaintTheBoard();
							updateLabel();
							break;
							
						case ItemList.PLUSONESECOND:
							diamondGrid.usePlusOneSecond();
							updateLabel();
							break;
							
						case ItemList.PLUSTHREESECONDS:
							diamondGrid.usePlusThreeSeconds();
							updateLabel();
							break;
							
						case ItemList.PLUSFIVESECONDS:
							diamondGrid.usePlusFiveSeconds();
							updateLabel();
							break;
							
						case ItemList.PLUSONESTEP:
							diamondGrid.usePlusOneStep();
							updateLabel();
							break;
							
						case ItemList.PLUSTHREESTEPS:
							diamondGrid.usePlusThreeSteps();
							updateLabel();
							break;
							
						case ItemList.PLUSFIVESTEPS:
							diamondGrid.usePlusFiveSteps();
							updateLabel();
							break;
							
						case ItemList.BOOM:
						case ItemList.HAMMER:
							currentProps = item;
							GameMain.this.setCursor(new ImageCursor(image));
						}
					} catch (NotLoginException e2) {
						e2.printStackTrace();
					}
				}
			});
			
			getChildren().add(propsImg);
			
			quantityLabel = new Label(UserBox.getUser().getMyItems().get(item).toString());
			quantityLabel.setPrefSize(50, 30);
			StackPane.setAlignment(quantityLabel, Pos.BOTTOM_RIGHT);
			getChildren().add(quantityLabel);
		}
		
		public void updateLabel() {
			quantityLabel.setText(UserBox.getUser().getMyItems().get(item).toString());
		}
	}
}

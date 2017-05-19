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

public abstract class GameMain extends Pane {

	protected static final int FRAMR_HEIGHT = 110;
	protected static final int PROPS_WIDTH = 80;
	protected static final int FRAME_CONTENT_LAYOUT_X = 200;
	protected static final int FRAME_CONTENT_LAYOUT_Y = 290;
	protected static final int BUTTON_WIDTH = 80;
	protected String mode;
	protected GamePanel gamePanel;
	protected TilePane propPane;
	protected BaseDiamondGrid diamondGrid;
	protected ContentText scoreText, timeText, stepText;

	// 各种框
	protected ImageView backgroud, gameFrame, moneyFrame, timeFrame, stepFrame, scoreFrame;
	protected Props propsBoom, propsHammer, propsNewMap, propsOneStep, propsThreeStep, propsFiveStep, propsOneSec,
			propsThreeSec, propsFiveSec;
	protected ImageView timeIndefiniteImg, stepIndefiniteImg;
	protected Label money;
	protected Button backBtn, restartBtn;
	protected VBox frameBox;
	protected HBox buttonBox;
	protected String currentProps;

	public String getCurrentProps() {
		return currentProps;
	}

	public void setCurrentProps(String currentProps) {
		this.currentProps = currentProps;
	}

	protected SystemButton exitButton;
	protected SystemButton configButton;

	public GameMain(String mode) {

		this.mode = mode;
		currentProps = null;

		createFrameAndButton();
		addFrameAndButton();
		createProps();
		layoutProps();
		createFrameContent();
		layFrameContent();
	}

	protected void createFrameContent() {
		
		
		money = new Label(UserBox.getUser() == null ? "0" : UserBox.getUser().getCoinCount() + "");
		getChildren().add(money);
		
		timeIndefiniteImg = new ImageView(Config.getTheme().getICON_INDEFINITE());
		timeIndefiniteImg.setFitHeight(FRAMR_HEIGHT - 50);
		timeIndefiniteImg.setPreserveRatio(true);
		

		stepIndefiniteImg = new ImageView(Config.getTheme().getICON_INDEFINITE());
		stepIndefiniteImg.setFitHeight(FRAMR_HEIGHT - 50);
		stepIndefiniteImg.setPreserveRatio(true);
		

		if (mode == null) {
			getChildren().addAll(timeIndefiniteImg, stepIndefiniteImg);
			return;
		}
		switch (mode) {
		case SyntheticModel.TIMELIMITED:
			getChildren().add(stepIndefiniteImg);
			timeText = new ContentText(diamondGrid, gamePanel);
			timeText.bindTime();
			
			getChildren().add(timeText);
			break;

		case SyntheticModel.STEPLIMITED:
			getChildren().add(timeIndefiniteImg);
			stepText = new ContentText(diamondGrid, gamePanel);
			stepText.bindStep();
			
			getChildren().add(stepText);
			break;

		case SyntheticModel.UNLIMITE:
			getChildren().addAll(timeIndefiniteImg, stepIndefiniteImg);
			break;
		}

		scoreText = new ContentText(diamondGrid, gamePanel);
		scoreText.bindGrade();

		getChildren().add(scoreText);
	}

	protected abstract void layoutProps();
	protected abstract void layFrameContent();
	protected abstract void addFrameAndButton();

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
	}

	public void setMode(String mode) {

		this.mode = mode;
	}

	/**
	 * 创建 组件
	 */
	private void createFrameAndButton() {

		gamePanel = new GamePanel(this);
		
		gameFrame = new ImageView(gamePanel.getTheme().getFRAME_GAME());
		moneyFrame = new ImageView(gamePanel.getTheme().getFRAME_MONEY());
		stepFrame = new ImageView(gamePanel.getTheme().getFRAME_STEP());
		timeFrame = new ImageView(gamePanel.getTheme().getFRAME_TIME());
		scoreFrame = new ImageView(gamePanel.getTheme().getFRAME_HIGHEST_SCORE());

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
			Config.SOUND_CLICK.play();
			gamePanel.gameOver();
			Config.getMain().setScene(Config.getTheme().getSynScene());
			Config.getTheme().initBGM();
		});

		ImageView restartImg = new ImageView(Config.getTheme().getBUTTON_GAME_RESTART());
		restartImg.setFitWidth(BUTTON_WIDTH);
		restartImg.setPreserveRatio(true);

		restartBtn = new Button();
		restartBtn.setGraphic(restartImg);
		restartBtn.setOnAction(e -> {
			Config.SOUND_CLICK.play();
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
			getChildren().add(propsImg);

			if (UserBox.getUser() == null) {
				return;
			}
			propsImg.setOnMouseClicked(e -> {

				if (e.getClickCount() == 2) {

					try {
						switch (item) {
						case ItemList.NEWMAP:
							Config.SOUND_ITEMS.play();
							diamondGrid.useGenNewMap();
							gamePanel.repaintTheBoard();
							updateLabel();
							break;

						case ItemList.PLUSONESECOND:
							Config.SOUND_ITEMS.play();
							diamondGrid.usePlusOneSecond();
							updateLabel();
							break;

						case ItemList.PLUSTHREESECONDS:
							Config.SOUND_ITEMS.play();
							diamondGrid.usePlusThreeSeconds();
							updateLabel();
							break;

						case ItemList.PLUSFIVESECONDS:
							Config.SOUND_ITEMS.play();
							diamondGrid.usePlusFiveSeconds();
							updateLabel();
							break;

						case ItemList.PLUSONESTEP:
							Config.SOUND_ITEMS.play();
							diamondGrid.usePlusOneStep();
							updateLabel();
							break;

						case ItemList.PLUSTHREESTEPS:
							Config.SOUND_ITEMS.play();
							diamondGrid.usePlusThreeSteps();
							updateLabel();
							break;

						case ItemList.PLUSFIVESTEPS:
							Config.SOUND_ITEMS.play();
							diamondGrid.usePlusFiveSteps();
							updateLabel();
							break;

						case ItemList.BOOM:
							Config.SOUND_ITEMS.play();
							currentProps = item;
							GameMain.this.setCursor(new ImageCursor(image));
							break;
							
						case ItemList.HAMMER:
							Config.SOUND_ITEMS.play();
							currentProps = item;
							GameMain.this.setCursor(new ImageCursor(image));
						}
					} catch (NotLoginException e2) {
						e2.printStackTrace();
					}
				}
			});

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

package el.onetoone.ui.magicgirl;

import el.onetoone.back.Config;
import el.onetoone.ui.InitialView;
import el.onetoone.ui.SyntheticModel;
import el.onetoone.ui.SystemButton;
import el.onetoone.ui.SyntheticModel.SyntheticButton;
import el.onetoone.ui.shop.MarketPanel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MagicSyntheticPanel extends SyntheticModel {

	private static final int FUNC_BUTTON_HEIGHT = 60;
	private HBox modeButtonBox;
	private VBox funcButtonBox;

	public MagicSyntheticPanel() {
		// TODO Auto-generated constructor stub
		super();
		initBackgroud();
	}

	private void initBackgroud() {
		// TODO Auto-generated method stub

		backgroundImg = new ImageView(Config.getTheme().getBG_GAME());
		backgroundImg.setFitHeight(Config.SCREEN_HEIGHT);
		backgroundImg.setFitWidth(Config.SCREEN_WIDTH);
	}

	@Override
	protected void createModeButton() {
		// TODO Auto-generated method stub
		modeButtonBox = new HBox(50);
		modeButtonBox.setLayoutX(Config.SCREEN_WIDTH / 4);
		modeButtonBox.setLayoutY(Config.SCREEN_HEIGHT / 3 + 30);

		// 无尽模式
		ImageView unlimitedImg = new ImageView(Config.getTheme().getBUTTON_INDEFINITE_MODE());
		unlimitedImg.setFitWidth(MODE_BUTTON_WIDTH);
		unlimitedImg.setPreserveRatio(true);

		unlimitedMode = new SyntheticButton();
		unlimitedMode.setGraphic(unlimitedImg);

		// 限时模式
		ImageView timeImg = new ImageView(Config.getTheme().getBUTTON_TIME_MODE());
		timeImg.setFitWidth(MODE_BUTTON_WIDTH);
		timeImg.setPreserveRatio(true);

		timeLimitedMode = new SyntheticButton();
		timeLimitedMode.setGraphic(timeImg);

		// 步数模式
		ImageView stepImg = new ImageView(Config.getTheme().getBUTTON_STEP_MODE());
		stepImg.setFitWidth(MODE_BUTTON_WIDTH);
		stepImg.setPreserveRatio(true);

		stepLimitedMode = new SyntheticButton();
		stepLimitedMode.setGraphic(stepImg);

		modeButtonBox.getChildren().addAll(unlimitedMode, timeLimitedMode, stepLimitedMode);
		registerModeButtonListener();

		this.getChildren().add(modeButtonBox);
	}

	@Override
	protected void createFuncButton() {

		// 商城按钮
		ImageView shopImg = new ImageView(Config.getTheme().getBUTTON_SHOP());
		shopImg.setFitHeight(FUNC_BUTTON_HEIGHT);
		shopImg.setPreserveRatio(true);

		marketAndBackButton = new Button();
		marketAndBackButton.setGraphic(shopImg);

		// 最高分按钮
		ImageView maxMarkImg = new ImageView(Config.getTheme().getBUTTON_HEIGHEST_SCORE());
		maxMarkImg.setFitHeight(FUNC_BUTTON_HEIGHT);
		maxMarkImg.setPreserveRatio(true);

		maxMarkButton = new Button();
		maxMarkButton.setGraphic(maxMarkImg);

		// 氪金按钮
		ImageView kejinImg = new ImageView(Config.getTheme().getBUTTON_KEJIN());
		kejinImg.setFitHeight(FUNC_BUTTON_HEIGHT);
		kejinImg.setPreserveRatio(true);

		topUpButton = new Button();
		topUpButton.setGraphic(kejinImg);

		// 设置按钮
		ImageView settingImg = new ImageView(Config.getTheme().getBUTTON_CONFIG());
		settingImg.setFitHeight(FUNC_BUTTON_HEIGHT);
		settingImg.setPreserveRatio(true);

		settingButton = new Button();
		settingButton.setGraphic(settingImg);

		// 登出按钮
		ImageView logoutImg = new ImageView(Config.getTheme().getBUTTON_LOG_OUT());
		logoutImg.setFitHeight(FUNC_BUTTON_HEIGHT);
		logoutImg.setPreserveRatio(true);

		logOutButton = new Button();
		logOutButton.setGraphic(logoutImg);

		funcButtonBox = new VBox(settingButton, maxMarkButton, topUpButton, marketAndBackButton, logOutButton);
		funcButtonBox.setSpacing(-10);
		funcButtonBox.setAlignment(Pos.CENTER_LEFT);
		funcButtonBox.setLayoutX(50);
		funcButtonBox.setLayoutY(80);
		this.getChildren().add(funcButtonBox);
	}
}

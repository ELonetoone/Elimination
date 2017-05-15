package el.onetoone.ui.naruto;

import el.onetoone.back.Config;
import el.onetoone.ui.SyntheticModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class NarutoSynthetic extends SyntheticModel {

	private static final int BUTTON_HEIGHT = 100;

	private static final double FUNC_BUTTON_HEIGHT = 60;

	private VBox modeButtonBox;
	private VBox funcButtonBox;
	private ImageView modeChooseImg;

	public NarutoSynthetic() {
		super();
		backgroundImg.setImage(NarutoTheme.BG_SYN);

		modeChooseImg = new ImageView(new Image("/image/naruto/label_mode_choose.png"));
		modeChooseImg.setLayoutX(300);
		modeChooseImg.setLayoutY(100);
		modeChooseImg.setFitHeight(150);
		modeChooseImg.setPreserveRatio(true);
		getChildren().add(modeChooseImg);
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

		funcButtonBox = new VBox(logOutButton, settingButton, marketAndBackButton, topUpButton, maxMarkButton);
		funcButtonBox.setAlignment(Pos.CENTER);
		funcButtonBox.setLayoutX(Config.SCREEN_WIDTH - 200);
		funcButtonBox.setLayoutY(50);
		getChildren().add(funcButtonBox);
	}

	@Override
	protected void createModeButton() {
		// TODO Auto-generated method stub
		ImageView stepImg = new ImageView(Config.getTheme().getBUTTON_STEP_MODE());
		stepImg.setFitHeight(BUTTON_HEIGHT);
		stepImg.setPreserveRatio(true);

		stepLimitedMode = new SyntheticButton();
		stepLimitedMode.setGraphic(stepImg);

		ImageView indefiniteImg = new ImageView(Config.getTheme().getBUTTON_INDEFINITE_MODE());
		indefiniteImg.setFitHeight(BUTTON_HEIGHT);
		indefiniteImg.setPreserveRatio(true);

		unlimitedMode = new SyntheticButton();
		unlimitedMode.setGraphic(indefiniteImg);

		ImageView timeImg = new ImageView(Config.getTheme().getBUTTON_TIME_MODE());
		timeImg.setFitHeight(BUTTON_HEIGHT);
		timeImg.setPreserveRatio(true);

		timeLimitedMode = new SyntheticButton();
		timeLimitedMode.setGraphic(timeImg);

		modeButtonBox = new VBox(unlimitedMode, stepLimitedMode, timeLimitedMode);
		modeButtonBox.setAlignment(Pos.CENTER);
		modeButtonBox.setLayoutX(400);
		modeButtonBox.setLayoutY(250);
		getChildren().add(modeButtonBox);
		
		registerModeButtonListener();
	}

}

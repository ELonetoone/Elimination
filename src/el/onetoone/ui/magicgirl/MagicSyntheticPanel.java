package el.onetoone.ui.magicgirl;

import el.onetoone.back.Config;
import el.onetoone.ui.InitialView;
import el.onetoone.ui.SyntheticModel;
import el.onetoone.ui.SystemButton;
import el.onetoone.ui.SyntheticModel.ModeButton;
import el.onetoone.ui.shop.MarketPanel;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MagicSyntheticPanel extends SyntheticModel{

	private HBox modeButtonBox;
	
	public MagicSyntheticPanel() {
		// TODO Auto-generated constructor stub
		
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
		
		//无尽模式
		ImageView unlimitedImg = new ImageView(Config.getTheme().getBUTTON_INDEFINITE_MODE());
		unlimitedImg.setFitWidth(MODE_BUTTON_WIDTH);
		unlimitedImg.setPreserveRatio(true);
		
		unlimitedMode = new ModeButton();
		unlimitedMode.setGraphic(unlimitedImg);	
		
		//限时模式
		ImageView timeImg = new ImageView(Config.getTheme().getBUTTON_TIME_MODE());
		timeImg.setFitWidth(MODE_BUTTON_WIDTH);
		timeImg.setPreserveRatio(true);
		
		timeLimitedMode = new ModeButton();
		timeLimitedMode.setGraphic(timeImg);
		
		//步数模式
		ImageView stepImg = new ImageView(Config.getTheme().getBUTTON_STEP_MODE());
		stepImg.setFitWidth(MODE_BUTTON_WIDTH);
		stepImg.setPreserveRatio(true);
		
		stepLimitedMode = new ModeButton();
		stepLimitedMode.setGraphic(stepImg);
		
		modeButtonBox.getChildren().addAll(unlimitedMode, timeLimitedMode, stepLimitedMode);
		registerModeButtonListener();
		
		this.getChildren().add(modeButtonBox);
	}
}

package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * 设置面板
 * @author liao
 *
 */
public abstract class SettingPane extends Pane {

	private SystemButton closeBtn;
	
	protected Slider volumeBGM;
	protected Slider volumeSoundEffect;
	protected ImageView backgroudImg;
	
	public SettingPane() {

		setStyle(";-fx-background-radius: 10" + ";-fx-border-radius: 10"
				+ ";-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1)");
		
		closeBtn = new SystemButton(0);
		closeBtn.setOnAction(e -> {
			Theme.removeBlur((Pane)this.getParent());
			((Pane)this.getParent()).getChildren().remove(this);
		});
		closeBtn.setLayoutX(this.getPrefWidth() - 55);
		closeBtn.setLayoutY(10);
		
		createBackgroud();
		createSlider();
		addControlListener();
	}

	private void createBackgroud() {
		// TODO Auto-generated method stub
		backgroudImg = new ImageView(Config.getTheme().getBG_SETTING_PANE());
		backgroudImg.setFitHeight(500);
		backgroudImg.setPreserveRatio(true);
		backgroudImg.setLayoutX(200);
		backgroudImg.setLayoutY(100);
		
		getChildren().add(backgroudImg);
		
	}

	protected abstract void createSlider();

	private void addControlListener() {
		
		volumeBGM.valueProperty().bindBidirectional(Config.getTheme().getBGMPlayer().volumeProperty());
	}
}

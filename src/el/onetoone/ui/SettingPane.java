package el.onetoone.ui;

import javafx.scene.media.AudioClip;
import el.onetoone.back.Config;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * 设置面板
 * 
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

		setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				((Pane) this.getParent()).getChildren().remove(this);
			}
		});
		
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

		final DoubleProperty sound = volumeSoundEffect.valueProperty();
		volumeBGM.valueProperty().bindBidirectional(Config.getTheme().getBGMPlayer().volumeProperty());
		sound.bindBidirectional(Config.SOUND_BOOM.volumeProperty());
		sound.bindBidirectional(Config.SOUND_BUY_THING.volumeProperty());
		sound.bindBidirectional(Config.SOUND_CLICK.volumeProperty());
		sound.bindBidirectional(Config.SOUND_EL.volumeProperty());
		sound.bindBidirectional(Config.SOUND_ITEMS.volumeProperty());
		sound.bindBidirectional(Config.SOUND_MOVE.volumeProperty());
		sound.bindBidirectional(Config.SOUND_WARNING.volumeProperty());
	}
}

package el.onetoone.ui;

import javafx.scene.paint.Color;
import javafx.util.Duration;
import el.onetoone.back.Config;
import el.onetoone.ui.naruto.NarutoTheme;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
	protected EffectButton magicBtn, narutoBtn;

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
		createThemeChooseBtn();
	}

	private void createThemeChooseBtn() {

		HBox buttonBox = new HBox(80);
		
		ImageView magicImg = new ImageView(Theme.SETTING_MAGIC);
		magicImg.setFitHeight(50);
		magicImg.setPreserveRatio(true);
		
		magicBtn = new EffectButton();
		magicBtn.setGraphic(magicImg);
		magicBtn.setOnAction(e -> {
			if (Config.getTheme() instanceof NarutoTheme) {
				Config.getTheme().getBGMPlayer().stop();
				Config.setTheme(new MagicGirlTheme());
				Config.getTheme().initBGM();
				Config.getMain().setScene(Config.getTheme().getSynScene());
			}
		});
		
		ImageView narutoImg = new ImageView(Theme.SETTING_NARUTO);
		narutoImg.setFitHeight(40);
		narutoImg.setPreserveRatio(true);
		
		narutoBtn = new EffectButton();
		narutoBtn.setGraphic(narutoImg);
		narutoBtn.setOnAction(e -> {
			if (Config.getTheme() instanceof MagicGirlTheme) {
				Config.getTheme().getBGMPlayer().stop();
				Config.setTheme(new NarutoTheme());
				Config.getTheme().initBGM();
				Config.getMain().setScene(Config.getTheme().getSynScene());
			}
		});
		
		buttonBox.getChildren().addAll(narutoBtn, magicBtn);
		buttonBox.setLayoutX(350);
		buttonBox.setLayoutY(500);
		getChildren().add(buttonBox);
		buttonBox.toFront();
	}

	private void createBackgroud() {
		// TODO Auto-generated method stub
		backgroudImg = new ImageView(Theme.SETTING_PANE);
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
	
	public SystemButton getCloseBtn() {
		return closeBtn;
	}

	public void setCloseBtn(SystemButton closeBtn) {
		this.closeBtn = closeBtn;
	}

	class EffectButton extends Button {
		
		private DropShadow shadow;
		private ColorAdjust colorAdjust;

		public EffectButton() {

			shadow = new DropShadow();
			shadow.setColor(Color.TRANSPARENT);
			colorAdjust = new ColorAdjust();
			shadow.setInput(colorAdjust);
			setEffect(shadow);

			setOnMouseEntered(e -> {
				setHighlight();
			});

			setOnMouseExited(e -> setNormal());
		}

		public void setHighlight() {

			Timeline highlight = new Timeline(
					new KeyFrame(Duration.ZERO, new KeyValue(shadow.colorProperty(), Color.TRANSPARENT)),
					new KeyFrame(Duration.ZERO, new KeyValue(colorAdjust.brightnessProperty(), 0)),
					new KeyFrame(Duration.millis(200),
							new KeyValue(shadow.colorProperty(), Color.PINK)),
					new KeyFrame(Duration.millis(200),
							new KeyValue(colorAdjust.brightnessProperty(), 0.3)));

			highlight.play();
		}

		public void setNormal() {

			Timeline normal = new Timeline(
					new KeyFrame(Duration.millis(200),
							new KeyValue(shadow.colorProperty(), Color.TRANSPARENT)),
					new KeyFrame(Duration.millis(200),
							new KeyValue(colorAdjust.brightnessProperty(), 0)));

			normal.play();
		}
	}
}

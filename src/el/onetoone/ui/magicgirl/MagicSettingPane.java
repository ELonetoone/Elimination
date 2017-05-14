package el.onetoone.ui.magicgirl;

import el.onetoone.ui.SettingPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MagicSettingPane extends SettingPane {

	private static final int SLIDER_WIDTH = 350;
	private static final int SLIDER_HEIGHT = 30;

	@Override
	protected void createSlider() {
		// TODO Auto-generated method stub
		getStylesheets().add(MagicInitialScene.class.getResource("textField.css").toExternalForm());
		volumeSoundEffect = new Slider(0, 1, 1);
		volumeSoundEffect.setMinHeight(SLIDER_HEIGHT);
		volumeSoundEffect.setMinWidth(SLIDER_WIDTH);

		ProgressBar soundEffectBar = new ProgressBar(1.0);
		soundEffectBar.setMinHeight(SLIDER_HEIGHT);
		soundEffectBar.setMinWidth(SLIDER_WIDTH);

		volumeSoundEffect.valueProperty().addListener((o, oldValue, newValue) -> {

			soundEffectBar.setProgress(newValue.doubleValue());
		});

		StackPane soundStack = new StackPane(soundEffectBar, volumeSoundEffect);

		volumeBGM = new Slider(0, 1, 1);
		volumeBGM.setMinHeight(SLIDER_HEIGHT);
		volumeBGM.setMinWidth(SLIDER_WIDTH);

		ProgressBar bgmBar = new ProgressBar(1.0);
		bgmBar.setMinHeight(SLIDER_HEIGHT);
		bgmBar.setMinWidth(SLIDER_WIDTH);

		volumeBGM.valueProperty().addListener((o, oldValue, newValue) -> {

			bgmBar.setProgress(newValue.doubleValue());
		});

		StackPane bgmStack = new StackPane(bgmBar, volumeBGM);

		VBox sliderBox = new VBox(bgmStack, soundStack);
		sliderBox.setSpacing(40);
		sliderBox.setLayoutX(500);
		sliderBox.setLayoutY(270);
		getChildren().add(sliderBox);
	}

}

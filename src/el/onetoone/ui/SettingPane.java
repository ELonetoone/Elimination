package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

/**
 * 设置面板
 * @author liao
 *
 */
public abstract class SettingPane extends Pane {

	private SystemButton closeBtn;
	
	private Slider volume;
	
	public SettingPane() {

		setPrefSize(Config.SCREEN_WIDTH / 2 - 30, Config.SCREEN_HEIGHT - 30);
		setStyle("-fx-background-color: #f9f9f9" + ";-fx-background-radius: 10" + ";-fx-border-radius: 10"
				+ ";-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1)");
		
		createControl();
		addControlls();
		
		setLayoutX(15);
		setLayoutY(15);
	}

	private void addControlls() {
		
		getChildren().add(closeBtn);
		getChildren().add(volume);
	}

	private void createControl() {
		
		closeBtn = new SystemButton(0);
		closeBtn.setOnAction(e -> {
			Theme.removeBlur((Group)this.getParent());
			((Group)this.getParent()).getChildren().remove(this);
		});
		closeBtn.setLayoutX(this.getPrefWidth() - 55);
		closeBtn.setLayoutY(10);
		
		volume = new Slider(0, 1, 1);
		volume.setLayoutX(15);
		volume.setLayoutY(100);
		volume.setPrefWidth(200);
		volume.valueProperty().bindBidirectional(Config.getTheme().getBGMPlayer().volumeProperty());
	}

}

package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

/**
 * 设置面板
 * @author liao
 *
 */
public class SettingPane extends Pane {

	private SystemButton closeBtn;
	
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
	}

	private void createControl() {
		
		closeBtn = new SystemButton(0);
		closeBtn.setOnAction(e -> {
			Theme.removeBlur((Group)this.getParent());
			((Group)this.getParent()).getChildren().remove(this);
		});
		closeBtn.setLayoutX(this.getPrefWidth() - 55);
		closeBtn.setLayoutY(10);
	}

}

package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ExitPane extends Pane {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 300;

	private SystemButton exitBtn;
	private Button sureBtn;
	private Button cancelBtn;

	public ExitPane() {

		appearAnimation();

		setPrefSize(WIDTH, HEIGHT);
		setStyle("-fx-background-color: #f9f9f9" + ";-fx-background-radius: 10" + ";-fx-border-radius: 10"
				+ ";-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1)");

		exitBtn = new SystemButton(0);
		exitBtn.setLayoutX(475);
		exitBtn.setLayoutY(-25);
		exitBtn.setOnAction(e -> {

		});

		sureBtn = new Button("确定");
		sureBtn.setStyle("-fx-background-color: rgba(255,255,255,0.5);" + "-fx-background-radius: 30;"
				+ " -fx-border-radius: 30;" + "	-fx-font-family: \"FZZhengHeiS-EL-GB\";" + "	-fx-font-size: 24;"
				+ "-fx-font-weight: 600;" + "-fx-text-fill: #fff;" + "-fx-alignment: center;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1);");
		sureBtn.setLayoutX(WIDTH / 4);
		sureBtn.setLayoutY(HEIGHT / 2);
		sureBtn.setOnAction(e -> {
			System.exit(0);
		});

		cancelBtn = new Button("取消");
		cancelBtn.setStyle("-fx-background-color: rgba(255,255,255,0.5);" + "-fx-background-radius: 30;"
				+ " -fx-border-radius: 30;" + "	-fx-font-family: \"FZZhengHeiS-EL-GB\";" + "	-fx-font-size: 24;"
				+ "-fx-font-weight: 600;" + "-fx-text-fill: #fff;" + "-fx-alignment: center;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1);");
		cancelBtn.setLayoutX(WIDTH / 2);
		cancelBtn.setLayoutY(HEIGHT / 2);
		cancelBtn.setOnAction(e -> {
			Theme.removeBlur((Group) this.getParent());
			disappearAnimation();

		});

		this.getChildren().addAll(exitBtn, sureBtn, cancelBtn);

		setLayout();
	}

	private void appearAnimation() {
		// TODO Auto-generated method stub
		ScaleTransition appear = new ScaleTransition(Duration.seconds(0.3), this);
		appear.setInterpolator(Interpolator.EASE_BOTH);
		appear.setFromX(0);
		appear.setFromY(0);
		appear.setToX(1);
		appear.setToY(1);
		appear.play();
	}

	public void disappearAnimation() {

		ScaleTransition disappear = new ScaleTransition(Duration.seconds(0.3), this);
		disappear.setInterpolator(Interpolator.EASE_BOTH);
		disappear.setByX(-1);
		disappear.setByY(-1);

		disappear.play();
		disappear.setOnFinished(e -> {
			((Group) this.getParent()).getChildren().remove(this);
		});
	}

	private void setLayout() {
		// TODO Auto-generated method stub
		setLayoutX(Config.SCREEN_WIDTH / 4);
		setLayoutY(Config.SCREEN_HEIGHT / 4);
	}
}

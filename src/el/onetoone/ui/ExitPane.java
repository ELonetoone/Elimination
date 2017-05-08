package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ExitPane extends Pane{

	private static final int WIDTH = 500;
	private static final int HEIGHT = 300;
	
	private SystemButton exitBtn;
	private Button sureBtn;
	private Button cancelBtn;
	
	public ExitPane() {
		
		setPrefSize(WIDTH, HEIGHT);
		setStyle("-fx-background-color: #f9f9f9"
				+ ";-fx-background-radius: 10"
				+ ";-fx-border-radius: 10"
				+ ";-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1)");
		
		exitBtn = new SystemButton(0);
		exitBtn.setStyle("-fx-background-color: #ffffff;"
				+ "-fx-pref-width: 50;"
				+ "-fx-pref-height: 50;"
				+ "	-fx-background-radius: 25;"
				+ "	-fx-border-radius: 25;"
				+ "	-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1);");
		exitBtn.setLayoutX(475);
		exitBtn.setLayoutY(-25);
		exitBtn.setOnAction(e -> {
			
		});
		
		sureBtn = new Button("确定");
		sureBtn.setStyle("-fx-background-color: rgba(255,255,255,0.5);"
				+ "-fx-background-radius: 30;"
				+ " -fx-border-radius: 30;"
				+ "	-fx-font-family: \"FZZhengHeiS-EL-GB\";"
				+ "	-fx-font-size: 24;"
				+ "-fx-font-weight: 600;"
				+ "-fx-text-fill: #fff;"
				+ "-fx-alignment: center;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1);");
		sureBtn.setLayoutX(WIDTH / 4);
		sureBtn.setLayoutY(HEIGHT / 2);
		sureBtn.setOnAction(e -> {
			System.exit(0);
		});
		
		cancelBtn = new Button("取消");
		cancelBtn.setStyle("-fx-background-color: rgba(255,255,255,0.5);"
				+ "-fx-background-radius: 30;"
				+ " -fx-border-radius: 30;"
				+ "	-fx-font-family: \"FZZhengHeiS-EL-GB\";"
				+ "	-fx-font-size: 24;"
				+ "-fx-font-weight: 600;"
				+ "-fx-text-fill: #fff;"
				+ "-fx-alignment: center;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1);");
		cancelBtn.setLayoutX(WIDTH / 2);
		cancelBtn.setLayoutY(HEIGHT / 2);
		cancelBtn.setOnAction(e -> {
			Theme.removeBlur((Group)this.getParent());
			((Group)this.getParent()).getChildren().remove(this);
		});
		
		
		this.getChildren().addAll(exitBtn, sureBtn, cancelBtn);
		
		setLayout();
	}

	private void setLayout() {
		// TODO Auto-generated method stub
		setLayoutX(Config.SCREEN_WIDTH / 4);
		setLayoutY(Config.SCREEN_HEIGHT / 4);
	}
}

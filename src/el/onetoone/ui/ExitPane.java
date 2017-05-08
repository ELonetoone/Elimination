package el.onetoone.ui;

import javafx.scene.layout.Pane;

public class ExitPane extends Pane{

	private static final int WIDTH = 500;
	private static final int HEIGHT = 300;
	
	private SystemButton exitBtn;
	
	public ExitPane() {
		
		setPrefSize(WIDTH, HEIGHT);
		setStyle("-fx-background-color: #f9f9f9"
				+ ",-fx-background-radius: 10"
				+ ",-fx-border-radius: 10"
				+ ",-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1");
		
		exitBtn = new SystemButton(0);
		exitBtn.setLayoutX(475);
		exitBtn.setLayoutY(-25);
		exitBtn.setOnAction(e -> {
			
		});
		
		this.getChildren().add(exitBtn);
	}
}

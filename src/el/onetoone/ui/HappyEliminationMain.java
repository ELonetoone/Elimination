package el.onetoone.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HappyEliminationMain extends Application {

	Scene scene;
	StackPane root;
	VBox buttonContainer;
	Button signInBtn, signUpBtn, configureBtn, scoreBtn;
	Insets buttonContainerPadding;
	
	@Override
	public void start(Stage primaryStage) {
		
		createSplashScreenNodes();
		addStackPaneNodes();
		
		signInBtn.setOnAction(e -> {
			
			
		});
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void createSplashScreenNodes() {
		
		root = new StackPane();
		scene = new Scene(root, 640, 400);
		
		buttonContainer = new VBox(10);
		buttonContainer.setAlignment(Pos.CENTER_RIGHT);
		buttonContainerPadding = new Insets(10);
		buttonContainer.setPadding(buttonContainerPadding);
		
		signInBtn = new Button("登陆");
		signUpBtn = new Button("注册");
		configureBtn = new Button("设置");
		scoreBtn = new Button("记录");
		
		buttonContainer.getChildren().addAll(signInBtn, signUpBtn, configureBtn, scoreBtn);
	}
	
	private void addStackPaneNodes() {
		
		root.getChildren().add(buttonContainer);
	}
}

package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Config.setMain(this);
		stage = primaryStage;

		Scene initialPanel = new MagicGirlTheme().getInitialScene();

		stage.setScene(initialPanel);
		
		Config.getTheme().initBGM();

		primaryStage.setTitle("氪金消消乐");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(initialPanel);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void setScene(Scene scene) {
		this.stage.setScene(scene);
	}
}

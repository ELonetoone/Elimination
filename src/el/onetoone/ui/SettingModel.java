package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SettingModel implements FunctionModel{

	private Scene settingScene;
	
	/**
	 * 保留指向综合界面的引用，返回综合界面
	 */
	private Scene synScene;
	
	private Pane root;
	
	private Stage stage;
	
	private SettingPane settingPane;
	
	public SettingModel(Stage stage, Scene synScene) {
		this.synScene = synScene;
		this.stage = stage;
	}

	/**
	 * 初始化
	 */
	public void init() {
		
		settingPane = new SettingPane();
		root = new Pane();
		root.setStyle("-fx-backgroud-image: url(\"/image/bg_start\\.png\");"
				+ "-fx-background-size: stretch stretch;");
//		root.getStylesheets().add(InitialView.class.getResource("initialView.css").toExternalForm());
		root.getChildren().add(settingPane);
		settingScene = new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
	}
	
	/**
	 * 获取场景
	 * @return 对应的场景
	 */
	public Scene getScene() {
		return this.settingScene;
	}
	
	/**
	 * 返回综合界面
	 */
	public void returnToSyn() {
		stage.setScene(synScene);
	}
	
}

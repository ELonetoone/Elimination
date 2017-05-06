package el.onetoone.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MaxMarkModel implements FunctionModel {
	
	private Stage stage;

	private Scene maxMarkScene;
	
	//用于测试
	private Button returnButton;
	
	/**
	 * 保留指向综合界面的引用，返回综合界面
	 */
	private Scene synScene;

	public MaxMarkModel(Stage stage, Scene synScene) {
		this.synScene = synScene;
		this.stage = stage;
	}
	
	/**
	 * 初始化
	 */
	public void init() {
		/**
		 * 本方法暂时是测试成功的，可以删除内容
		 */
		returnButton = new Button("返回");
		returnButton.setOnAction(e -> {
			returnToSyn();
		});
		GridPane gridPane = new GridPane();
		gridPane.add(returnButton, 4, 0);
		maxMarkScene = new Scene(gridPane, 1000, 700);
	}
	
	/**
	 * 获取场景
	 * @return 对应的场景
	 */
	public Scene getScene() {
		return this.maxMarkScene;
	}
	
	/**
	 * 返回综合界面
	 */
	public void returnToSyn() {
		stage.setScene(synScene);
	}
	
}

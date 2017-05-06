package el.onetoone.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class TopUpModel implements FunctionModel{

	private Scene topUpScene;
	
	/**
	 * 保留指向综合界面的引用，返回综合界面
	 */
	private Scene synScene;
	
	private Stage stage;
	
	public TopUpModel(Stage stage, Scene synScene) {
		this.synScene = synScene;
		this.stage = stage;
	}

	/**
	 * 初始化
	 */
	public void init() {
		
	}
	
	/**
	 * 获取场景
	 * @return 对应的场景
	 */
	public Scene getScene() {
		return this.topUpScene;
	}
	
	/**
	 * 返回综合界面
	 */
	public void returnToSyn() {
		stage.setScene(synScene);
	}
}

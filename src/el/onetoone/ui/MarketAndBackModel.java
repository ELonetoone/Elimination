package el.onetoone.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MarketAndBackModel implements FunctionModel{
	
	private Scene marketAndBackScene;
	
	/**
	 * 保留指向综合界面的引用，返回综合界面
	 */
	private Scene synScene;
	
	/**
	 * 主界面stage
	 */
	private Stage stage;
	
	public MarketAndBackModel(Stage stage, Scene synScene) {
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
		return this.marketAndBackScene;
	}
	
	/**
	 * 返回综合界面
	 */
	public void returnToSyn() {
		stage.setScene(synScene);
	}
	
}



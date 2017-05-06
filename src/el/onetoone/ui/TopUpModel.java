package el.onetoone.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class TopUpModel implements FunctionModel{

	private Scene topUpScene;
	
	private Stage stage;
	
	public TopUpModel(Stage stage) {
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
		
	}
}

package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MarketAndBackModel implements FunctionModel{
	
	private Scene marketAndBackScene;
	
	private MarketPanel marketPanel;
	
	//返回按钮
	private SystemButton backButton;
	
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
	@Override
	public void init() {
		
		marketPanel = new MarketPanel();
		marketAndBackScene = new Scene(marketPanel, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		stage.setScene(marketAndBackScene);
		
		createNode();
		addNode();
		
	}
	
	private void addNode() {
		
		marketPanel.getChildren().add(backButton);
	}

	private void createNode() {
		
		backButton = new SystemButton(0);
		backButton.setLayoutX(Config.SCREEN_WIDTH - 55);
		backButton.setLayoutY(10);
		backButton.setOnAction(e -> {
			returnToSyn();
		});
	}

	/**
	 * 获取场景
	 * @return 对应的场景
	 */
	@Override
	public Scene getScene() {
		return this.marketAndBackScene;
	}
	
	/**
	 * 返回综合界面
	 */
	@Override
	public void returnToSyn() {
		stage.setScene(synScene);
	}
	
}



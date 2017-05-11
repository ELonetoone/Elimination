package el.onetoone.ui.shop;

import el.onetoone.back.Config;
import el.onetoone.ui.SystemButton;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MarketAndBackModel {

	private Scene marketAndBackScene;

	private MarketPanel marketPanel;

	// 返回按钮
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
		});
	}

}

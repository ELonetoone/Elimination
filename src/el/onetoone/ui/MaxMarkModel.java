package el.onetoone.ui;



import el.onetoone.back.Config;
import el.onetoone.back.UserBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MaxMarkModel implements FunctionModel {
	
	private Stage stage;

	private Scene maxMarkScene;
	
	//用于测试
	//返回按钮
	private Button returnButton;
	
	/**
	 * 无尽模式最高分
	 */
	private Label commonMaxMark;
	
	/**
	 * 有限步数模式最高分
	 */
	private Label limitedStepMaxMark;
	
	/**
	 * 有限时间模式最高分
	 */
	private Label limitedTimeMaxMark;
	
	
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
		commonMaxMark = new Label("无尽模式   ");
		limitedStepMaxMark = new Label("举步维艰   ");
		limitedTimeMaxMark = new Label("生死时速   ");
		
		commonMaxMark.setMinSize(200, 100);
		
		returnButton = new Button("返回");
		returnButton.setOnAction(e -> {
			stage.setScene(synScene);
		});
		
		Label commonMax = new Label(String.valueOf(UserBox.getUser().getendLessMaxMark()));
		Label limitedStepMax = new Label(String.valueOf(UserBox.getUser().getStepLimitedMaxMark()));
		Label limitedTimeMax = new Label(String.valueOf(UserBox.getUser().getTimeLimitedMaxMark()));
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(commonMaxMark, 1, 1);
		gridPane.add(returnButton, 3, 0);
		gridPane.add(commonMax, 2, 1);
		
		gridPane.add(limitedStepMaxMark, 1, 2);
		gridPane.add(limitedTimeMax, 2, 3);
		gridPane.add(limitedStepMax, 2, 2);
		gridPane.add(limitedTimeMaxMark, 1, 3);
		
		maxMarkScene = new Scene(gridPane, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		
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

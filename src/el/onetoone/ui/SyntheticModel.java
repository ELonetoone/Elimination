package el.onetoone.ui;

import java.awt.Desktop;
import java.net.URI;

import el.onetoone.back.Config;
import el.onetoone.back.UserBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SyntheticModel {
	
	/**
	 * 用于显示错误信息，比如尚未登录啊之类的
	 */
	private Text wrongMessage;
	
	private Stage primStage;
	
	/**
	 * 当前界面
	 */
	private Scene scene;
	
	private Button unlimitedMode;
	
	private Button timeLimitedMode;
	
	private Button stepLimitedMode;
	
	/**
	 * 背包和市场按钮
	 */
	private Button marketAndBackButton;
	
	/**
	 * 氪金按钮！
	 */
	private Button topUpButton;
	
	/**
	 * 设置按钮
	 */
	private Button settingButton;
	
	/**
	 * 登出按钮
	 */
	private Button logOutButton;
	
	/**
	 * 积分榜按钮
	 */
	private Button maxMarkButton;
	
	/**
	 * 关闭按钮
	 */
	private SystemButton exitButton;
	
	public static final String UNLIMITE = "ASABCK";
	
	public static final String TIMELIMITED = "AFKLJCZX";
	
	public static final String STEPLIMITED = "DASCBXZ,ME";
	
	private String mode = null;
	
	/**
	 * 切换出去的界面，比如主游戏界面啊，商城界面啊，设置界面啊之类的
	 */
	public Scene funcScene;
	
	public Scene getScene() {
		return this.scene;
	}
	
	private InitialView initialView;

	private GridPane gridPane;
	
	private Group root;
	
	/**
	 * 背景图片
	 */
	private ImageView backgroundImg;
	
	/**
	 * 获取stage
	 * @param stage
	 */
	public SyntheticModel(Stage stage, InitialView initialView) {
		this.primStage = stage;
		this.initialView = initialView;
	}
	
	public void init() {
		
		
		wrongMessage = new Text();
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(40);
		gridPane.setVgap(40);
		gridPane.setPadding(new Insets(40, 40, 40, 40));

		backgroundImg = new ImageView(Config.getTheme().BG_SIGN);
		backgroundImg.setFitHeight(Config.SCREEN_HEIGHT);
		backgroundImg.setFitWidth(Config.SCREEN_WIDTH);
		
		root = new Group();
		root.getChildren().add(backgroundImg);
		root.getChildren().add(gridPane);
		
//		gridPane.setAlignment(Pos.CENTER);
		/**
		 * 模式按钮
		 */
		unlimitedMode = new Button("无尽模式");
		timeLimitedMode = new Button("生死时速");
		stepLimitedMode = new Button("举步维艰");
		
		unlimitedMode.setMinSize(200, 400);
		timeLimitedMode.setMinSize(200, 400);
		stepLimitedMode.setMinSize(200, 400);
	
		registerModeButtonListener();
		
		/**
		 * 功能按钮
		 */
		marketAndBackButton = new Button("商城");
		maxMarkButton = new Button("积分榜");
		topUpButton = new Button("充值");
		settingButton = new Button("设置");
		
		marketAndBackButton.setMinSize(50, 50);
		maxMarkButton.setMinSize(50, 50);
		topUpButton.setMinSize(50, 50);
		settingButton.setMinSize(50, 50);
		
		registerFuncButtonListener();

		/**
		 * 登出按钮，之后会修改成自己的类
		 */
		logOutButton = new Button("登出");
		
		logOutButton.setMinSize(50, 50);
		
		registerLogoutListener();
		
		gridPane.add(unlimitedMode, 1, 1);
		gridPane.add(stepLimitedMode, 2, 1);
		gridPane.add(timeLimitedMode, 3, 1);
		
		gridPane.add(settingButton, 1, 2);
		gridPane.add(topUpButton, 2, 2);
		gridPane.add(marketAndBackButton, 3, 2);
		gridPane.add(maxMarkButton, 4, 2);
		
		//放在右上角
		gridPane.add(logOutButton, 4, 0);
		
		createExitButton();
		
		scene = new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		
	}
	
	private void createExitButton() {
		
		exitButton = new SystemButton(0);
		exitButton.setLayoutX(Config.SCREEN_WIDTH - 55);
		exitButton.setLayoutY(10);
		root.getChildren().add(exitButton);
		
		exitButton.setOnAction(e -> {
			System.exit(0);
		});
	}

	/**
	 * 注册登出按钮监听器
	 */
	public void registerLogoutListener() {
		
		logOutButton.setOnMouseEntered(e -> {
			logOutButton.setScaleX(1.3);
			logOutButton.setScaleY(1.3);
		});
		
		logOutButton.setOnMouseExited(e -> {
			logOutButton.setScaleX(1.0);
			logOutButton.setScaleY(1.0);
		});
		
		logOutButton.setOnAction(e -> {
			primStage.setScene(initialView.initialScene);
			UserBox.setUser(null);
		});
		
	}
	
	/**
	 * 注册功能性按钮的监听器
	 */
	public void registerFuncButtonListener() {
		
		marketAndBackButton.setOnMouseEntered(e -> {
			marketAndBackButton.setScaleX(1.3);
			marketAndBackButton.setScaleY(1.3);
		});
		
		marketAndBackButton.setOnMouseExited(e -> {
			marketAndBackButton.setScaleX(1.0);
			marketAndBackButton.setScaleY(1.0);
		});
		
		marketAndBackButton.setOnAction(e -> {
			if (UserBox.hasNotLogin()) {
				wrongMessage.setText("尚未登录！");
			} else {
				FunctionModel model =  new MarketAndBackModel(primStage, scene);
				model.init();
				funcScene = model.getScene();
				primStage.setScene(funcScene);
			}
		});
		
		maxMarkButton.setOnMouseEntered(e -> {
			maxMarkButton.setScaleX(1.3);
			maxMarkButton.setScaleY(1.3);
		});
		
		maxMarkButton.setOnMouseExited(e -> {
			maxMarkButton.setScaleX(1.0);
			maxMarkButton.setScaleY(1.0);
		});
		
		maxMarkButton.setOnAction(e -> {
			if (UserBox.hasNotLogin()) {
				wrongMessage.setText("尚未登录！");
			} else {
				FunctionModel model =  new MaxMarkModel(primStage, scene);
				model.init();
				funcScene = model.getScene();
				primStage.setScene(funcScene);
			}
		});
		
		topUpButton.setOnMouseEntered(e -> {
			topUpButton.setScaleX(1.3);
			topUpButton.setScaleY(1.3);
		});
		
		topUpButton.setOnMouseExited(e -> {
			topUpButton.setScaleX(1.0);
			topUpButton.setScaleY(1.0);
		});
		
		topUpButton.setOnAction(e -> {
//			FunctionModel model = new TopUpModel(primStage, scene);
//			model.init();
//			funcScene = model.getScene();
//			primStage.setScene(funcScene);
			String url = "http://115.159.29.36/wp-content/uploads/2017/05/9A450F09BC437A429703741650C1AE791.jpg";
			URI uri = URI.create(url);
			try {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
					desktop.browse(uri);
				}
			} catch (NullPointerException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		});
		
		settingButton.setOnMouseEntered(e -> {
			settingButton.setScaleX(1.3);
			settingButton.setScaleY(1.3);
		});
		
		settingButton.setOnMouseExited(e -> {
			settingButton.setScaleX(1.0);
			settingButton.setScaleY(1.0);
		});
		
		settingButton.setOnAction(e -> {
			if (UserBox.hasNotLogin()) {
				wrongMessage.setText("尚未登录！");
			} else {
				FunctionModel model =  new SettingModel(primStage, scene);
				model.init();
				funcScene = model.getScene();
				primStage.setScene(funcScene);
			}
		});
		
	}
	
	/**
	 * 注册模式按钮的监听器
	 */
	public void registerModeButtonListener() {
		
		unlimitedMode.setOnMouseEntered(e -> {
			unlimitedMode.setScaleX(1.3);
			unlimitedMode.setScaleY(1.3);
		});
		
		unlimitedMode.setOnMouseExited(e -> {
			unlimitedMode.setScaleX(1.0);
			unlimitedMode.setScaleY(1.0);
		});
		
		unlimitedMode.setOnAction(e -> {
			wrongMessage.setText("");
			mode = UNLIMITE;
			//然后传递mode到主游戏界面
			GameMain gamePanel = new GameMain(primStage, scene, mode);
		});
		
		timeLimitedMode.setOnMouseEntered(e -> {
			timeLimitedMode.setScaleX(1.3);
			timeLimitedMode.setScaleY(1.3);
		});
		
		timeLimitedMode.setOnMouseExited(e -> {
			timeLimitedMode.setScaleX(1.0);
			timeLimitedMode.setScaleY(1.0);
		});
		
		timeLimitedMode.setOnAction(e -> {
			wrongMessage.setText("");
			mode = TIMELIMITED;
			//然后传递mode到主游戏界面
			GameMain gamePanel = new GameMain(primStage, scene, mode);
		});
		
		stepLimitedMode.setOnMouseEntered(e -> {
			stepLimitedMode.setScaleX(1.3);
			stepLimitedMode.setScaleY(1.3);
		});
		
		stepLimitedMode.setOnMouseExited(e -> {
			stepLimitedMode.setScaleX(1.0);
			stepLimitedMode.setScaleY(1.0);
		});
		
		stepLimitedMode.setOnAction(e -> {
			wrongMessage.setText("");
			mode = STEPLIMITED;
			//然后传递mode到主游戏界面
			GameMain gamePanel = new GameMain(primStage, scene, mode);
		});
		
	}
 
}

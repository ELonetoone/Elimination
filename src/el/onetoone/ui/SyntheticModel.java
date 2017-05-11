package el.onetoone.ui;

import java.awt.Desktop;
import java.net.URI;

import el.onetoone.back.Config;
import el.onetoone.back.UserBox;
import el.onetoone.ui.shop.MarketAndBackModel;
import el.onetoone.ui.shop.MarketPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

abstract public class SyntheticModel extends Pane{
	
	protected static final int MODE_BUTTON_WIDTH = 150;
	
	/**
	 * 用于显示错误信息，比如尚未登录啊之类的
	 */
	private Text wrongMessage;
	
	private Stage primStage;
	private Scene scene;
	private GameMain gameMain;
	
	protected ModeButton unlimitedMode;
	protected ModeButton timeLimitedMode;
	protected ModeButton stepLimitedMode;
	
	protected Button marketAndBackButton;
	protected Button topUpButton;
	protected Button settingButton;
	protected Button logOutButton;
	protected Button maxMarkButton;
	protected SystemButton exitButton;
	
	public static final String UNLIMITE = "ASABCK";
	public static final String TIMELIMITED = "AFKLJCZX";
	public static final String STEPLIMITED = "DASCBXZ,ME";
	private String mode = null;
	
	private MarketPanel marketPanel;
	protected ImageView backgroundImg;
	
	
	
	/**
	 * 切换出去的界面，比如主游戏界面啊，商城界面啊，设置界面啊之类的
	 */
	public Scene funcScene;
	
	/**
	 * 获取stage
	 * @param stage
	 */
	public SyntheticModel() {
		
		init();
	}
	
	public void init() {
		
		gameMain = new GameMain();
		wrongMessage = new Text();
		

		backgroundImg = new ImageView(Config.getTheme().getBG_GAME());
		backgroundImg.setFitHeight(Config.SCREEN_HEIGHT);
		backgroundImg.setFitWidth(Config.SCREEN_WIDTH);
		
		this.getChildren().add(backgroundImg);
		
		/**
		 * 模式按钮
		 */
		createModeButton();
		
		
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
		
		createExitButton();
		
		
	}
	
	abstract protected void createModeButton();
	
	private void createExitButton() {
		
		exitButton = new SystemButton(0);
		exitButton.setLayoutX(Config.SCREEN_WIDTH - 55);
		exitButton.setLayoutY(10);
		this.getChildren().add(exitButton);
		
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
			((Pane)getParent()).getChildren().remove(this);
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
			gameMain.setMode(mode);
			this.getChildren().add(gameMain);
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
			gameMain.setMode(mode);
			this.getChildren().add(gameMain);
			
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
			gameMain.setMode(mode);
			this.getChildren().add(gameMain);
		});
		
	}
 
	public class ModeButton extends Button {
		
		//加一些特效
		public ModeButton() {
			// TODO Auto-generated constructor stub
			setStyle("-fx-background-color: transparent;"
					+ "-fx-border-color: transparent;"
					+ "-fx-content-display: center;");
		}
	}
}

package el.onetoone.ui;

import el.onetoone.back.Config;
import el.onetoone.ui.SyntheticModel.ModeButton;
import el.onetoone.ui.shop.MarketPanel;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

abstract public class SyntheticPanel {

	private static final int MODE_BUTTON_WIDTH = 150;
	
	private Text wrongMessage;
	
	private ModeButton unlimitedMode;
	private ModeButton timeLimitedMode;
	private ModeButton stepLimitedMode;
	
	private Button marketAndBackButton;
	private Button topUpButton;
	private Button settingButton;
	private Button logOutButton;
	private Button maxMarkButton;
	private SystemButton exitButton;
	
	public static final String UNLIMITE = "ASABCK";
	public static final String TIMELIMITED = "AFKLJCZX";
	public static final String STEPLIMITED = "DASCBXZ,ME";
	private String mode = null;
	
	private Pane root;
	private InitialView initialView;
	private MarketPanel marketPanel;
	private ImageView backgroundImg;
	
	private HBox modeButtonBox;
	
	public SyntheticPanel() {
		// TODO Auto-generated constructor stub
		
		initBackgroud();
		createModeButton();
	}

	private void initBackgroud() {
		// TODO Auto-generated method stub

		backgroundImg = new ImageView(Config.getTheme().getBG_GAME());
		backgroundImg.setFitHeight(Config.SCREEN_HEIGHT);
		backgroundImg.setFitWidth(Config.SCREEN_WIDTH);
	}
	
	public abstract void createModeButton();
	
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
		});
		
	}
}

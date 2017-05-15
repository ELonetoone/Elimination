package el.onetoone.ui;

import java.awt.Desktop;
import java.net.URI;
import java.util.Random;

import el.onetoone.back.Config;
import el.onetoone.back.UserBox;
import el.onetoone.ui.magicgirl.MagicGameMain;
import el.onetoone.ui.naruto.NarutoGameMain;
import el.onetoone.ui.shop.MarketPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;

abstract public class SyntheticModel extends Pane {

	protected static final int MODE_BUTTON_WIDTH = 150;
	private static final double BUTTON_CHAGE_SCALE = 1.1;
	/**
	 * 用于显示错误信息，比如尚未登录啊之类的
	 */

	private Scene scene;
	private Text wrongMessage;

	private GameMain gameMain;

	protected SyntheticButton unlimitedMode;
	protected SyntheticButton timeLimitedMode;
	protected SyntheticButton stepLimitedMode;

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

	abstract protected void createFuncButton();

	abstract protected void createModeButton();

	public SyntheticModel() {

		init();
	}

	public Scene getSyntheticScene() {

		scene = new Scene(this, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		scene.getStylesheets().add(InitialView.class.getResource("initialView.css").toExternalForm());
		return scene;
	}

	public void init() {

		wrongMessage = new Text();

		createBackgroud();
		createModeButton();
		createFuncButton();

		registerFuncButtonListener();
		registerLogoutListener();
		createExitButton();

	}

	private void createBackgroud() {
		// TODO Auto-generated method stub
		backgroundImg = new ImageView(Config.getTheme().getBG_GAME());
		backgroundImg.setFitHeight(Config.SCREEN_HEIGHT);
		backgroundImg.setFitWidth(Config.SCREEN_WIDTH);

		this.getChildren().add(backgroundImg);
	}

	private void createExitButton() {

		exitButton = new SystemButton(0);
		exitButton.setLayoutX(Config.SCREEN_WIDTH - 55);
		exitButton.setLayoutY(10);
		this.getChildren().add(exitButton);

		exitButton.setOnAction(e -> {
			AudioClip audioClip = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
			// System.out.println("OK");
			audioClip.play();
			System.exit(0);
		});
	}

	/**
	 * 注册登出按钮监听器
	 */
	public void registerLogoutListener() {

		logOutButton.setOnMouseEntered(e -> {
			logOutButton.setScaleX(BUTTON_CHAGE_SCALE);
			logOutButton.setScaleY(BUTTON_CHAGE_SCALE);
		});

		logOutButton.setOnMouseExited(e -> {
			logOutButton.setScaleX(1.0);
			logOutButton.setScaleY(1.0);
		});

		logOutButton.setOnAction(e -> {
			AudioClip audioClip = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
			// System.out.println("OK");
			audioClip.play();
			Config.getMain().setScene(Config.getTheme().getInitialScene());
			UserBox.setUser(null);
		});

	}

	/**
	 * 注册功能性按钮的监听器
	 */
	public void registerFuncButtonListener() {

		marketAndBackButton.setOnMouseEntered(e -> {
			marketAndBackButton.setScaleX(BUTTON_CHAGE_SCALE);
			marketAndBackButton.setScaleY(BUTTON_CHAGE_SCALE);
		});

		marketAndBackButton.setOnMouseExited(e -> {
			marketAndBackButton.setScaleX(1.0);
			marketAndBackButton.setScaleY(1.0);
		});

		marketAndBackButton.setOnAction(e -> {
			if (UserBox.hasNotLogin()) {
				AudioClip audioClip = new AudioClip(Main.class.getResource("sound/warning.wav").toExternalForm());
				audioClip.play();
				wrongMessage.setText("尚未登录！");
			} else {
				AudioClip audioClip = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
				audioClip.play();
				Config.getMain().setScene(Config.getTheme().getMarketScene());
			}
		});

		maxMarkButton.setOnMouseEntered(e -> {
			maxMarkButton.setScaleX(BUTTON_CHAGE_SCALE);
			maxMarkButton.setScaleY(BUTTON_CHAGE_SCALE);
		});

		maxMarkButton.setOnMouseExited(e -> {
			maxMarkButton.setScaleX(1.0);
			maxMarkButton.setScaleY(1.0);
		});

		maxMarkButton.setOnAction(e -> {
			if (UserBox.hasNotLogin()) {
				AudioClip audioClip = new AudioClip(Main.class.getResource("sound/warning.wav").toExternalForm());
				audioClip.play();
				wrongMessage.setText("尚未登录！");
			} else {
				AudioClip audioClip = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
				audioClip.play();
				Config.getMain().setScene(Config.getTheme().getMaxMarkScene());
			}
		});

		topUpButton.setOnMouseEntered(e -> {
			topUpButton.setScaleX(BUTTON_CHAGE_SCALE);
			topUpButton.setScaleY(BUTTON_CHAGE_SCALE);
		});

		topUpButton.setOnMouseExited(e -> {
			topUpButton.setScaleX(1.0);
			topUpButton.setScaleY(1.0);
		});

		topUpButton.setOnAction(e -> {
			// FunctionModel model = new TopUpModel(primStage, scene);
			// model.init();
			// funcScene = model.getScene();
			// primStage.setScene(funcScene);
			AudioClip audioClip = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
			audioClip.play();
			String url1 = "http://115.159.29.36/wp-content/uploads/2017/05/E860D85BEE735FCD143E0E97F7ADDDD2.jpg";
			String url2 = "http://115.159.29.36/wp-content/uploads/2017/05/9A450F09BC437A429703741650C1AE7911.jpg";
			String url3 = "http://115.159.29.36/wp-content/uploads/2017/05/06645FECD842644F3D60B1D2CE7EAE80.jpg";
			String url4 = "http://115.159.29.36/wp-content/uploads/2017/05/82A46EF09286C3AFA15947CE58C964F0.jpg";
			String url = null;
			int people = new Random().nextInt(4);
			switch (people) {
			case 0:
				url = url1;
				break;
			case 1:
				url = url2;
				break;
			case 2:
				url = url3;
				break;
			case 3:
				url = url4;
				break;
			default:
				url = url2;
			}
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
			settingButton.setScaleX(BUTTON_CHAGE_SCALE);
			settingButton.setScaleY(BUTTON_CHAGE_SCALE);
		});

		settingButton.setOnMouseExited(e -> {
			settingButton.setScaleX(1.0);
			settingButton.setScaleY(1.0);
		});

		settingButton.setOnAction(e -> {
			getChildren().add(Config.getTheme().getSettingPane());
			AudioClip audioClip = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
			audioClip.play();
		});

	}

	/**
	 * 注册模式按钮的监听器
	 */
	public void registerModeButtonListener() {

		unlimitedMode.setOnAction(e -> {
			AudioClip audioClip = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
			audioClip.play();
			wrongMessage.setText("");
			mode = UNLIMITE;
			// 然后传递mode到主游戏界面
			if (Config.getTheme() instanceof MagicGirlTheme) {
				gameMain = new MagicGameMain(mode);
			} else {
				gameMain = new NarutoGameMain(mode);
			}
			Config.getTheme().playGamingBGM();
			this.getChildren().add(gameMain);
		});

		timeLimitedMode.setOnAction(e -> {
			AudioClip audioClip = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
			audioClip.play();
			wrongMessage.setText("");
			mode = TIMELIMITED;
			// 然后传递mode到主游戏界面
			if (Config.getTheme() instanceof MagicGirlTheme) {
				gameMain = new MagicGameMain(mode);
			} else {
				gameMain = new NarutoGameMain(mode);
			}
			Config.getTheme().playGamingBGM();
			this.getChildren().add(gameMain);

		});

		stepLimitedMode.setOnAction(e -> {
			AudioClip audioClip = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
			audioClip.play();
			wrongMessage.setText("");
			mode = STEPLIMITED;
			// 然后传递mode到主游戏界面
			if (Config.getTheme() instanceof MagicGirlTheme) {
				gameMain = new MagicGameMain(mode);
			} else {
				gameMain = new NarutoGameMain(mode);
			}
			Config.getTheme().playGamingBGM();
			this.getChildren().add(gameMain);
		});

	}

	public class SyntheticButton extends Button {

		// 加一些特效
		public SyntheticButton() {

			setOnMouseEntered(e -> {
				setScaleX(BUTTON_CHAGE_SCALE);
				setScaleY(BUTTON_CHAGE_SCALE);
			});

			setOnMouseExited(e -> {
				setScaleX(1.0);
				setScaleY(1.0);
			});
		}
	}
}

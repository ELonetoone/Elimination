package el.onetoone.ui;

import el.onetoone.back.Config;
import el.onetoone.back.User;
import el.onetoone.back.UserBox;
import el.onetoone.ui.magicgirl.MagicSyntheticPanel;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class InitialView extends Application {

	private static final int BUTTON_HEIGHT = 60;
	private static final int BUTTON_HIGHLIGHT_MILLS = 300;

	private InitButton exitButton;
	private InitButton loginButton;
	private InitButton registerButton;
	private InitButton trialButton;

	private VBox buttonBox;

	/**
	 * 设置为public，全局可访问
	 */
	public User user = null;

	public Scene initialScene;
	private Stage primStage;
	private Scene regitserScene;
	private Scene loginScene;

	private SyntheticModel syntheticModel;

	private ImageView backgroud;

	private Pane root;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("氪金消消乐");
		primaryStage.initStyle(StageStyle.UNDECORATED);

		root = new Pane();

		createBackground();
		createButton();

		initialScene = new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		initialScene.getStylesheets().add(InitialView.class.getResource("initialView.css").toExternalForm());
		primaryStage.setScene(initialScene);

		// 方背景音乐
		Config.getTheme().playBGM();

		primStage = primaryStage;
		primaryStage.show();

	}

	private void createButton() {

		// 退出按钮
		ImageView exitImg = new ImageView(Config.getTheme().getINIT_BUTTON_QUIT());
		exitImg.setFitHeight(BUTTON_HEIGHT);
		exitImg.setPreserveRatio(true);

		exitButton = new InitButton();
		exitButton.setGraphic(exitImg);

		// 登陆按钮
		ImageView loginImg = new ImageView(Config.getTheme().getINIT_BUTTON_SIGN_IN());
		loginImg.setFitHeight(BUTTON_HEIGHT);
		loginImg.setPreserveRatio(true);

		loginButton = new InitButton();
		loginButton.setGraphic(loginImg);

		// 注册按钮
		ImageView signUpImg = new ImageView(Config.getTheme().getINIT_BUTTON_SIGN_UP());
		signUpImg.setFitHeight(BUTTON_HEIGHT);
		signUpImg.setPreserveRatio(true);

		registerButton = new InitButton();
		registerButton.setGraphic(signUpImg);

		// 尝试按钮
		ImageView trialImg = new ImageView(Config.getTheme().getINIT_BUTTON_TRY());
		trialImg.setFitHeight(BUTTON_HEIGHT);
		trialImg.setPreserveRatio(true);

		trialButton = new InitButton();
		trialButton.setGraphic(trialImg);

		// exitButton.setMinSize(150, 75);
		// loginButton.setMinSize(150, 75);
		// registerButton.setMinSize(150, 75);
		// trialButton.setMinSize(150, 75);

		buttonBox = new VBox();
		buttonBox.setSpacing(-5);
		buttonBox.setAlignment(Pos.BASELINE_RIGHT);
		buttonBox.getChildren().addAll(loginButton, registerButton, trialButton, exitButton);
		buttonBox.setLayoutX(750);
		buttonBox.setLayoutY(350);

		root.getChildren().add(buttonBox);

		registerButtonEventHandler();
	}

	private void createBackground() {
		// TODO Auto-generated method stub
		backgroud = new ImageView(Config.getTheme().getBG_START());
		backgroud.setFitWidth(Config.SCREEN_WIDTH);
		backgroud.setPreserveRatio(true);
		root.getChildren().add(backgroud);
	}

	private void registerButtonEventHandler() {

		trialButton.setOnAction(e -> {
			user = null;
			UserBox.setUser(user);
			// 转跳至综合界面
			syntheticModel = new MagicSyntheticPanel();
			root.getChildren().add(syntheticModel);
		});

		exitButton.setOnAction(e -> {
			System.exit(0);
		});

		loginButton.setOnAction(e -> {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(20);
			grid.setVgap(20);
			grid.setPadding(new Insets(30, 30, 30, 30));

			loginScene = new Scene(grid, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

			Text gameName = new Text("氪金消消乐");
			gameName.setSmooth(true);
			gameName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));

			grid.add(gameName, 0, 0, 3, 2);

			Label userName = new Label("用户名");
			grid.add(userName, 0, 2);

			Label password = new Label("密码");
			grid.add(password, 0, 3);

			TextField userTextField = new TextField();
			PasswordField passwordField = new PasswordField();

			grid.add(userTextField, 1, 2);
			grid.add(passwordField, 1, 3);

			Button sureButton = new Button("登录");
			Text promptText = new Text();

			sureButton.setOnAction(p -> {
				String uid = userTextField.getText().trim();
				String passwd = passwordField.getText().trim();
				if (uid == null || uid.equals("")) {
					promptText.setText("用户名不能为空");
				} else if (passwd == null || passwd.equals("")) {
					promptText.setText("密码不能为空");
				} else {
					boolean hasSpace = false;
					for (int i = 0; i < uid.length(); i++) {
						if (uid.charAt(i) == ' ') {
							hasSpace = true;
							break;
						}
					}
					if (hasSpace) {

						promptText.setText("用户名不能含有空格");
					} else {
						try {
							user = User.login(uid, passwd);

							UserBox.setUser(user);
							// 转换到综合界面
							syntheticModel = new MagicSyntheticPanel();
							root.getChildren().add(syntheticModel);
						} catch (Exception q) {
							String qErrorMessage = q.getMessage();

							if (qErrorMessage.equals(User.FAILTOCONNECTDATABASE)) {
								promptText.setText("网络连接失败");
							} else if (qErrorMessage.equals(User.HASNOTREGISTER)) {
								promptText.setText("尚未注册");
							} else if (qErrorMessage.equals(User.WRONGPASSWORD)) {
								promptText.setText("密码错误");
							} else {
								// 莫名其妙的问题
							}

						}
					}
				}
			});

			grid.add(sureButton, 3, 4);

			grid.add(promptText, 3, 5);

			Button returnMainButton = new Button("返回上层界面");

			returnMainButton.setOnAction(s -> {
				primStage.setScene(initialScene);
			});

			grid.add(returnMainButton, 4, 0);

			primStage.setScene(loginScene);
		});

		registerButton.setOnAction(e -> {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(20);
			grid.setVgap(20);
			grid.setPadding(new Insets(30, 30, 30, 30));

			regitserScene = new Scene(grid, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

			Text gameName = new Text("氪金消消乐");
			gameName.setSmooth(true);
			gameName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));

			grid.add(gameName, 0, 0, 3, 2);

			Label userName = new Label("用户名");
			grid.add(userName, 0, 2);

			Label password = new Label("密码");
			grid.add(password, 0, 3);

			Label confirmPassword = new Label("确认密码");
			grid.add(confirmPassword, 0, 4);

			TextField userTextField = new TextField();
			PasswordField passwordField = new PasswordField();
			PasswordField confirmPasswordField = new PasswordField();

			grid.add(userTextField, 1, 2);
			grid.add(passwordField, 1, 3);
			grid.add(confirmPasswordField, 1, 4);

			Button register = new Button("确认");
			Text promptText = new Text();

			register.setOnAction(p -> {
				String uid = userTextField.getText().trim();
				String passwd = passwordField.getText().trim();
				String confirmPasswd = confirmPasswordField.getText().trim();

				if (uid == null || uid.equals("")) {
					promptText.setText("用户名不得为空");
				} else if (passwd == null || passwd.equals("") || confirmPasswd == null || confirmPasswd.equals("")) {
					promptText.setText("密码不得为空");
				} else if (!passwd.equals(confirmPasswd)) {
					promptText.setText("两次输入的密码不一致");
				} else {
					boolean hasSpace = false;
					for (int i = 0; i < uid.length(); i++) {
						if (uid.charAt(i) == ' ') {
							hasSpace = true;
							break;
						}
					}
					if (hasSpace) {
						promptText.setText("用户名不能含有空格");
					} else {
						try {
							user = User.register(uid, passwd);
							UserBox.setUser(user);
							// 转跳到综合界面
							syntheticModel = new MagicSyntheticPanel();
							root.getChildren().add(syntheticModel);
						} catch (Exception q) {
							String qErrorMessage = q.getMessage();
							if (qErrorMessage.equals(User.FAILTOCONNECTDATABASE)) {
								promptText.setText("网络连接失败");
							} else if (qErrorMessage.equals(User.HASBEENREGISTERED)) {
								promptText.setText("用户名已被注册");
							} else {

							}
						}
					}
				}

			});

			grid.add(register, 3, 5);
			grid.add(promptText, 3, 6);

			Button returnMainButton = new Button("返回上层界面");

			returnMainButton.setOnAction(s -> {
				primStage.setScene(initialScene);
			});

			grid.add(returnMainButton, 4, 0);
			primStage.setScene(regitserScene);

		});
	}

	// private Timeline changeScreenAnimation() {
	//
	// final DoubleProperty opacity = initialScene.getRoot().opacityProperty();
	// Timeline fade = new Timeline(
	// new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
	// new KeyFrame(Duration.millis(500), new KeyValue(opacity, 0.0))
	// );
	//
	// fade.play();
	//
	// return fade;
	// }

	public static void main(String[] args) {
		launch(args);
	}

	public class InitButton extends Button {

		private DropShadow shadow;
		private ColorAdjust colorAdjust;
		
		public InitButton() {
			// TODO Auto-generated constructor stub
			setStyle("-fx-background-color: transparent;" + "-fx-border-color: transparent;"
					+ "-fx-content-display: center;");
			
			shadow = new DropShadow();
			shadow.setColor(Color.TRANSPARENT);
			colorAdjust = new ColorAdjust();
			shadow.setInput(colorAdjust);
			setEffect(shadow);

			setOnMouseEntered(e -> {
				setHighlight();
			});
			
			setOnMouseExited(e -> setNormal());
		}

		public void setHighlight() {

			Timeline highlight = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(shadow.colorProperty(), Color.TRANSPARENT)),
					new KeyFrame(Duration.ZERO, new KeyValue(colorAdjust.brightnessProperty(), 0)),
					new KeyFrame(Duration.millis(BUTTON_HIGHLIGHT_MILLS), new KeyValue(shadow.colorProperty(), Color.PINK)),
					new KeyFrame(Duration.millis(BUTTON_HIGHLIGHT_MILLS),new KeyValue(colorAdjust.brightnessProperty(), 0.3)));

			highlight.play();
		}
		
		public void setNormal() {
			
			Timeline normal = new Timeline(
					new KeyFrame(Duration.millis(BUTTON_HIGHLIGHT_MILLS), new KeyValue(shadow.colorProperty(), Color.TRANSPARENT)),
					new KeyFrame(Duration.millis(BUTTON_HIGHLIGHT_MILLS),new KeyValue(colorAdjust.brightnessProperty(), 0)));
			
			normal.play();
		}
	}

}

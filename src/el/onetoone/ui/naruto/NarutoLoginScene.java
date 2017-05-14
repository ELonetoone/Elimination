package el.onetoone.ui.naruto;


import el.onetoone.back.Config;
import el.onetoone.back.User;
import el.onetoone.back.UserBox;
import el.onetoone.ui.InitialView;
import el.onetoone.ui.LoginScene;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class NarutoLoginScene extends LoginScene {

	public static final double BUTTON_HIGHLIGHT_MILLS = 300;


	public NarutoLoginScene(Parent root, double width, double height, boolean depthBuffer,
			SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public NarutoLoginScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public NarutoLoginScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public NarutoLoginScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public NarutoLoginScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public NarutoLoginScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}
	
	private ImageView userNameImage;
	
	private ImageView passwordImage;
	
//	private ImageView frame;
//	
//	private ImageView frame2;
	
	private LoginAndRegisterButton loginButton;
	
	private NarutoReturnButton exitButton;
	
	private ImageView gameName;
	
	private Rectangle frame1;
	
	private Rectangle frame2;
	

	@Override
	public Scene getLoginScene() {
		// TODO Auto-generated method stub
		setBackground();
		putButton();
		getStylesheets().add(InitialView.class.getResource("initialView.css").toExternalForm());
		return this;
	}
	
	public void putButton() {
		
		super.createItems();
		
		userNameImage = new ImageView(new Image("/image/naruto/u_name.png", 120, 50, true, true));
		userNameImage.setLayoutX(50);
		userNameImage.setLayoutY(260);
		
		passwordImage = new ImageView(new Image("/image/naruto/password.png", 120, 50, true, true));
		passwordImage.setLayoutX(50);
		passwordImage.setLayoutY(320);
		
		frame1 = new Rectangle(290, 40);
		frame2 = new Rectangle(290, 40);
		
		frame1.setStroke(Color.BLACK);
		frame1.setStrokeWidth(2);
		frame1.setFill(null);
		frame1.setArcHeight(20);
		frame1.setArcWidth(20);
		
		frame2.setStroke(Color.BLACK);
		frame2.setStrokeWidth(2);
		frame2.setFill(null);
		frame2.setArcHeight(20);
		frame2.setArcWidth(20);
		
		frame1.setLayoutX(180);
		frame1.setLayoutY(265);
		
		frame2.setLayoutX(180);
		frame2.setLayoutY(325);
		
		gameName = new ImageView(new Image("/image/naruto/g_name.png", 400, 250, true, true));
		gameName.setLayoutX(50);
		gameName.setLayoutY(100);
		
		userNameField = new TextField();
		userNameField.setPrefHeight(40);
		userNameField.setPrefWidth(270);
		userNameField.setLayoutX(190);
		userNameField.setLayoutY(265);
		userNameField.setStyle("-fx-background-color: transparent;"
				+ "-fx-border-color: transparent;");
//		userName.setFont(value);
		
		passwordField = new PasswordField();
		passwordField.setPrefWidth(270);
		passwordField.setPrefHeight(40);
		passwordField.setLayoutX(190);
		passwordField.setLayoutY(325);
		passwordField.setStyle("-fx-background-color: transparent;"
				+ "-fx-border-color: transparent;");
//		passwordField.setFont(value);
		
		wrongMessage.setLayoutX(250);
		wrongMessage.setLayoutY(390);
//		wrongMessage.setFont(value);
		
//		Image frameImage = new Image("/image/naruto/frame.png", 290, 50, true, true);
//		frame = new ImageView(frameImage);
//		frame2 = new ImageView(frameImage);
//		
//		frame.setLayoutX(335);
//		frame.setLayoutY(160);
//		
//		frame2.setLayoutX(335);
//		frame2.setLayoutY(220);
		
		loginButton = new LoginAndRegisterButton();
		loginButton.setGraphic(new ImageView(new Image("/image/naruto/login.png", 150, 66, true, true)));
		loginButton.setPrefSize(70, 70);
		loginButton.setLayoutX(250);
		loginButton.setLayoutY(420);
		
		exitButton = new NarutoReturnButton();
		
		registerButton();
		
		addNodes();
		
	}
	
	
	public void registerButton() {
		
//		exitButton.setOnMouseEntered(e -> {
//			exitButton.setScaleX(1.3);
//			exitButton.setScaleY(1.3);
//		});
//		
//		exitButton.setOnMouseExited(e -> {
//			exitButton.setScaleX(1.0);
//			exitButton.setScaleY(1.0);
//		});
		
		exitButton.setOnAction(e -> {
			Config.getMain().setScene(Config.getTheme().getInitialScene());
		});
		
		loginButton.setOnAction(e -> {
			User user;
			String uid = userNameField.getText().trim();
			String passwd = passwordField.getText().trim();
			if (uid == null || uid.equals("")) {
				wrongMessage.setText("用户名不能为空");
			} else if (passwd == null || passwd.equals("")) {
				wrongMessage.setText("密码不能为空");
			} else {
				boolean hasSpace = false;
				for (int i = 0; i < uid.length(); i++) {
					if (uid.charAt(i) == ' ') {
						hasSpace = true;
						break;
					}
				}
				if (hasSpace) {
					wrongMessage.setText("用户名不能含有空格");
				} else {
					try {
						user = User.login(uid, passwd);

						UserBox.setUser(user);
						// 转换到综合界面
						Config.getMain().setScene(Config.getTheme().getSynScene());
					} catch (Exception q) {
						String qErrorMessage = q.getMessage();

						if (qErrorMessage.equals(User.FAILTOCONNECTDATABASE)) {
							wrongMessage.setText("网络连接失败");
						} else if (qErrorMessage.equals(User.HASNOTREGISTER)) {
							wrongMessage.setText("尚未注册");
						} else if (qErrorMessage.equals(User.WRONGPASSWORD)) {
							wrongMessage.setText("密码错误");
						} else {
							// 莫名其妙的问题
						}

					}
				}
			}
		});
		
		
	}
	
	public void addNodes() {
		((Pane) getRoot()).getChildren().add(frame1);
		((Pane) getRoot()).getChildren().add(frame2);
		((Pane) getRoot()).getChildren().add(gameName);
		((Pane) getRoot()).getChildren().add(passwordImage);
		((Pane) getRoot()).getChildren().add(userNameImage);
		((Pane) getRoot()).getChildren().add(wrongMessage);
		((Pane) getRoot()).getChildren().add(userNameField);
		((Pane) getRoot()).getChildren().add(passwordField);
		((Pane) getRoot()).getChildren().add(exitButton);
		((Pane) getRoot()).getChildren().add(loginButton);
	}
	
	
	
	public static class LoginAndRegisterButton extends Button {

		private DropShadow shadow;
		private ColorAdjust colorAdjust;

		public LoginAndRegisterButton() {

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

			Timeline highlight = new Timeline(
					new KeyFrame(Duration.ZERO, new KeyValue(shadow.colorProperty(), Color.TRANSPARENT)),
					new KeyFrame(Duration.ZERO, new KeyValue(colorAdjust.brightnessProperty(), 0)),
					new KeyFrame(Duration.millis(BUTTON_HIGHLIGHT_MILLS),
							new KeyValue(shadow.colorProperty(), Color.PINK)),
					new KeyFrame(Duration.millis(BUTTON_HIGHLIGHT_MILLS),
							new KeyValue(colorAdjust.brightnessProperty(), 0.3)));

			highlight.play();
		}

		public void setNormal() {

			Timeline normal = new Timeline(
					new KeyFrame(Duration.millis(BUTTON_HIGHLIGHT_MILLS),
							new KeyValue(shadow.colorProperty(), Color.TRANSPARENT)),
					new KeyFrame(Duration.millis(BUTTON_HIGHLIGHT_MILLS),
							new KeyValue(colorAdjust.brightnessProperty(), 0)));

			normal.play();
		}
	}

}

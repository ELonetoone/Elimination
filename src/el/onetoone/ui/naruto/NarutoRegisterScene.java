package el.onetoone.ui.naruto;

import el.onetoone.back.Config;
import el.onetoone.back.User;
import el.onetoone.back.UserBox;
import el.onetoone.ui.InitialView;
import el.onetoone.ui.Main;
import el.onetoone.ui.RegisterScene;
import el.onetoone.ui.naruto.NarutoLoginScene.LoginAndRegisterButton;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NarutoRegisterScene extends RegisterScene {

	public NarutoRegisterScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	public NarutoRegisterScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public NarutoRegisterScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public NarutoRegisterScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public NarutoRegisterScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public NarutoRegisterScene(Parent root, double width, double height, boolean depthBuffer,
			SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene getRegisterScene() {
		// TODO Auto-generated method stub
		setBackground();
		putButton();
		getStylesheets().add(InitialView.class.getResource("initialView.css").toExternalForm());
		return this;
	}
	
	private ImageView gameName;
	
	private ImageView userNameImage;
	
	private ImageView passwordImage;
	
	private ImageView confirmImage;
	
//	private ImageView frame;
//	
//	private ImageView frame2;
//	
//	private ImageView frame3;
	
	private Rectangle frame1;
	
	private Rectangle frame2;
	
	private Rectangle frame3;
	
	private LoginAndRegisterButton registerButton;
	
	private NarutoReturnButton exitButton;
	
	public void putButton() {
		
		super.createItems();
		
		userNameImage = new ImageView(new Image("/image/naruto/u_name.png", 120, 50, true, true));
		userNameImage.setLayoutX(50);
		userNameImage.setLayoutY(220);
		
		passwordImage = new ImageView(new Image("/image/naruto/password.png", 120, 50, true, true));
		passwordImage.setLayoutX(50);
		passwordImage.setLayoutY(280);
		
		confirmImage = new ImageView(new Image("/image/naruto/sure.png", 120, 40, true, true));
		confirmImage.setLayoutX(60);
		confirmImage.setLayoutY(345);
		
		frame1 = new Rectangle(290, 40);
		frame2 = new Rectangle(290, 40);
		frame3 = new Rectangle(290, 40);
		
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
		
		frame3.setStroke(Color.BLACK);
		frame3.setStrokeWidth(2);
		frame3.setFill(null);
		frame3.setArcHeight(20);
		frame3.setArcWidth(20);
		
		frame1.setLayoutX(180);
		frame1.setLayoutY(225);
		
		frame2.setLayoutX(180);
		frame2.setLayoutY(285);
		
		frame3.setLayoutX(180);
		frame3.setLayoutY(345);
		
		Font font = Font.loadFont(Main.class.getResource("font/message.TTF").toExternalForm(), 25);
		
		gameName = new ImageView(new Image("/image/naruto/g_name.png", 400, 250, true, true));
		gameName.setLayoutX(50);
		gameName.setLayoutY(100);
		
		wrongMessage = new Text();
		wrongMessage.setLayoutX(250);
		wrongMessage.setLayoutY(420);
		wrongMessage.setFont(font);
		
		registerButton = new LoginAndRegisterButton();
		registerButton.setGraphic(new ImageView(new Image("/image/naruto/reg.png", 150, 66, true, true)));
		registerButton.setPrefSize(70, 70);
		registerButton.setLayoutX(300);
		registerButton.setLayoutY(440);
		
		exitButton = new NarutoReturnButton();
		
		userName = new TextField();
		userName.setPrefHeight(40);
		userName.setPrefWidth(270);
		userName.setLayoutX(190);
		userName.setLayoutY(225);
		userName.setStyle("-fx-background-color: transparent;"
				+ "-fx-border-color: transparent;");
		userName.setFont(font);
		
		passwordField = new PasswordField();
		passwordField.setPrefWidth(270);
		passwordField.setPrefHeight(40);
		passwordField.setLayoutX(190);
		passwordField.setLayoutY(285);
		passwordField.setStyle("-fx-background-color: transparent;"
				+ "-fx-border-color: transparent;");
		
		confirmField = new PasswordField();
		confirmField.setPrefWidth(270);
		confirmField.setPrefHeight(40);
		confirmField.setLayoutX(190);
		confirmField.setLayoutY(345);
		confirmField.setStyle("-fx-background-color: transparent;"
				+ "-fx-border-color: transparent;");
		
		registerButton();
		
		addNodes();
		
	}
	
	public void registerButton() {
		
		
		exitButton.setOnAction(e -> {
			AudioClip audioClip = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
			audioClip.play();
			Config.getMain().setScene(Config.getTheme().getInitialScene());
		});
		
		registerButton.setOnAction(p -> {
			User user;
			String uid = userName.getText().trim();
			String passwd = passwordField.getText().trim();
			String confirmPasswd = confirmField.getText().trim();

			if (uid == null || uid.equals("")) {
				AudioClip audioClip = new AudioClip(Main.class.getResource("sound/warning.wav").toExternalForm());
				audioClip.play();
				wrongMessage.setText("用户名不得为空");
			} else if (passwd == null || passwd.equals("") || confirmPasswd == null || confirmPasswd.equals("")) {
				AudioClip audioClip = new AudioClip(Main.class.getResource("sound/warning.wav").toExternalForm());
				audioClip.play();
				wrongMessage.setText("密码不得为空");
			} else if (!passwd.equals(confirmPasswd)) {
				AudioClip audioClip = new AudioClip(Main.class.getResource("sound/warning.wav").toExternalForm());
				audioClip.play();
				wrongMessage.setText("两次输入的密码不一致");
			} else {
				boolean hasSpace = false;
				for (int i = 0; i < uid.length(); i++) {
					if (uid.charAt(i) == ' ') {
						hasSpace = true;
						break;
					}
				}
				if (hasSpace) {
					AudioClip audioClip = new AudioClip(Main.class.getResource("sound/warning.wav").toExternalForm());
					audioClip.play();
					wrongMessage.setText("用户名不能含有空格");
				} else {
					try {
						user = User.register(uid, passwd);
						UserBox.setUser(user);
						// 转跳到综合界面
						AudioClip audioClip = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
						audioClip.play();
						Config.getMain().setScene(Config.getTheme().getSynScene());
					} catch (Exception q) {
						String qErrorMessage = q.getMessage();
						if (qErrorMessage.equals(User.FAILTOCONNECTDATABASE)) {
							AudioClip audioClip = new AudioClip(Main.class.getResource("sound/warning.wav").toExternalForm());
							audioClip.play();
							wrongMessage.setText("网络连接失败");
						} else if (qErrorMessage.equals(User.HASBEENREGISTERED)) {
							AudioClip audioClip = new AudioClip(Main.class.getResource("sound/warning.wav").toExternalForm());
							audioClip.play();
							wrongMessage.setText("用户名已被注册");
						} else {

						}
					}
				}
			}

		});
	}
	
	public void addNodes() {
//		((Pane) getRoot()).getChildren().add(frame);
//		((Pane) getRoot()).getChildren().add(frame2);
//		((Pane) getRoot()).getChildren().add(frame3);
		((Pane) getRoot()).getChildren().add(gameName);
		((Pane) getRoot()).getChildren().add(passwordImage);
		((Pane) getRoot()).getChildren().add(userNameImage);
		((Pane) getRoot()).getChildren().add(wrongMessage);
		((Pane) getRoot()).getChildren().add(userName);
		((Pane) getRoot()).getChildren().add(passwordField);
		((Pane) getRoot()).getChildren().add(confirmField);
		((Pane) getRoot()).getChildren().add(exitButton);
		((Pane) getRoot()).getChildren().add(registerButton);
		((Pane) getRoot()).getChildren().add(confirmImage);
		((Pane) getRoot()).getChildren().add(frame1);
		((Pane) getRoot()).getChildren().add(frame2);
		((Pane) getRoot()).getChildren().add(frame3);
	}
 
}

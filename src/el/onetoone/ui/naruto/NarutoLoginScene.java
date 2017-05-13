package el.onetoone.ui.naruto;

import com.sun.media.jfxmedia.events.NewFrameEvent;

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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
	
	private ImageView frame;
	
	private ImageView frame2;
	
	private LoginAndRegisterButton loginButton;
	
	private Button exitButton;
	
	private ImageView gameName;
	

	@Override
	public Scene getLoginScene() {
		// TODO Auto-generated method stub
		setBackground();
		putButton();
	//	getStylesheets().add(InitialView.class.getResource("initialView.css").toExternalForm());
		return this;
	}
	
	public void putButton() {
		
		super.createItems();
		
		loginButton = new LoginAndRegisterButton();
		loginButton.setGraphic(new ImageView(new Image("/image/naruto/login.png", 70, 70, false, true)));
		loginButton.setPrefSize(70, 70);
		loginButton.setLayoutX(300);
		loginButton.setLayoutY(300);
		
		exitButton = new Button();
		exitButton.setGraphic(new ImageView(Config.getTheme().getBUTTON_BACK()));
		exitButton.setLayoutX(1150);
		exitButton.setLayoutY(30);
		
		registerButton();
		
		addNodes();
		
	}
	
	
	public void registerButton() {
		
		exitButton.setOnMouseEntered(e -> {
			exitButton.setScaleX(1.3);
			exitButton.setScaleY(1.3);
		});
		
		exitButton.setOnMouseExited(e -> {
			exitButton.setScaleX(1.0);
			exitButton.setScaleY(1.0);
		});
		
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
		((Pane) getRoot()).getChildren().add(frame);
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

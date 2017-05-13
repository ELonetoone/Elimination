package el.onetoone.ui.naruto;

import el.onetoone.back.Config;
import el.onetoone.back.User;
import el.onetoone.back.UserBox;
import el.onetoone.ui.InitialView;
import el.onetoone.ui.RegisterScene;
import el.onetoone.ui.naruto.NarutoLoginScene.LoginAndRegisterButton;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

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
	//	getStylesheets().add(InitialView.class.getResource("initialView.css").toExternalForm());
		return this;
	}
	
	private ImageView gameName;
	
	private ImageView userNameImage;
	
	private ImageView passwordImage;
	
	private ImageView confirmImage;
	
	private ImageView frame;
	
	private ImageView frame2;
	
	private ImageView frame3;
	
	private LoginAndRegisterButton registerButton;
	
	private Button exitButton;
	
	public void putButton() {
		
		super.createItems();
		
		registerButton = new LoginAndRegisterButton();
		registerButton.setGraphic(new ImageView(new Image("/image/naruto/login.png", 70, 70, false, true)));
		registerButton.setPrefSize(70, 70);
		registerButton.setLayoutX(300);
		registerButton.setLayoutY(300);
		
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
		
		registerButton.setOnAction(p -> {
			User user;
			String uid = userName.getText().trim();
			String passwd = passwordField.getText().trim();
			String confirmPasswd = confirmField.getText().trim();

			if (uid == null || uid.equals("")) {
				wrongMessage.setText("用户名不得为空");
			} else if (passwd == null || passwd.equals("") || confirmPasswd == null || confirmPasswd.equals("")) {
				wrongMessage.setText("密码不得为空");
			} else if (!passwd.equals(confirmPasswd)) {
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
					wrongMessage.setText("用户名不能含有空格");
				} else {
					try {
						user = User.register(uid, passwd);
						UserBox.setUser(user);
						// 转跳到综合界面
						Config.getMain().setScene(Config.getTheme().getSynScene());
					} catch (Exception q) {
						String qErrorMessage = q.getMessage();
						if (qErrorMessage.equals(User.FAILTOCONNECTDATABASE)) {
							wrongMessage.setText("网络连接失败");
						} else if (qErrorMessage.equals(User.HASBEENREGISTERED)) {
							wrongMessage.setText("用户名已被注册");
						} else {

						}
					}
				}
			}

		});
	}
	
	public void addNodes() {
		((Pane) getRoot()).getChildren().add(frame);
		((Pane) getRoot()).getChildren().add(frame2);
		((Pane) getRoot()).getChildren().add(frame3);
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
	}
 
}

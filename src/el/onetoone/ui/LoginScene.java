package el.onetoone.ui;

import el.onetoone.back.Config;
import el.onetoone.back.User;
import el.onetoone.back.UserBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public abstract class LoginScene extends Scene {

	public LoginScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public LoginScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public LoginScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public LoginScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public LoginScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public LoginScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	public abstract Scene getLoginScene();

	public Button returnButton;

	public Text wrongMessage;

	public Button sureButton;

	public TextField userNameField;

	public PasswordField passwordField;

	public ImageView background;

	public void createItems() {

		returnButton = new Button();

		sureButton = new Button();

		userNameField = new TextField();

		passwordField = new PasswordField();

		wrongMessage = new Text();
	}

	public void setBackground() {
		background = new ImageView(Config.getTheme().getBG_SIGN());
		background.setFitWidth(Config.SCREEN_WIDTH);
		background.setPreserveRatio(true);
		((Pane) getRoot()).getChildren().add(background);
	}

	public void registerListener() {
		returnButton.setOnAction(e -> {
			Config.SOUND_CLICK.play();
			Config.getMain().setScene(Config.getTheme().getInitialScene());
		});

		sureButton.setOnAction(p -> {
			User user;
			String uid = userNameField.getText().trim();
			String passwd = passwordField.getText().trim();
			if (uid == null || uid.equals("")) {
				Config.SOUND_WARNING.play();
				wrongMessage.setText("用户名不能为空");
			} else if (passwd == null || passwd.equals("")) {
				Config.SOUND_WARNING.play();
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
					Config.SOUND_WARNING.play();
					wrongMessage.setText("用户名不能含有空格");
				} else {
					try {
						user = User.login(uid, passwd);

						UserBox.setUser(user);
						// 转换到综合界面
						Config.SOUND_CLICK.play();
						Config.getMain().setScene(Config.getTheme().getSynScene());
					} catch (Exception q) {
						String qErrorMessage = q.getMessage();

						if (qErrorMessage.equals(User.FAILTOCONNECTDATABASE)) {
							Config.SOUND_WARNING.play();
							wrongMessage.setText("网络连接失败");
						} else if (qErrorMessage.equals(User.HASNOTREGISTER)) {
							Config.SOUND_WARNING.play();
							wrongMessage.setText("尚未注册");
						} else if (qErrorMessage.equals(User.WRONGPASSWORD)) {
							Config.SOUND_WARNING.play();
							wrongMessage.setText("密码错误");
						} else {
							// 莫名其妙的问题
						}

					}
				}
			}
		});
	}

}

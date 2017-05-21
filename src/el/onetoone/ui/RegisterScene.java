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

public abstract class RegisterScene extends Scene {

	public RegisterScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	public RegisterScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public RegisterScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public RegisterScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public RegisterScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public RegisterScene(Parent root, double width, double height, boolean depthBuffer,
			SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public abstract Scene getRegisterScene();

	public Button sureButton;

	public Button returnButton;

	public Text wrongMessage;

	public TextField userName;

	public PasswordField passwordField;

	public PasswordField confirmField;

	public ImageView background;

	public void createItems() {

		sureButton = new Button();

		returnButton = new Button();

		wrongMessage = new Text();

		userName = new TextField();

		passwordField = new PasswordField();

		confirmField = new PasswordField();

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
			String uid = userName.getText().trim();
			String passwd = passwordField.getText().trim();
			String confirmPasswd = confirmField.getText().trim();

			if (uid == null || uid.equals("")) {
				Config.SOUND_WARNING.play();
				wrongMessage.setText("用户名不得为空");
			} else if (passwd == null || passwd.equals("") || confirmPasswd == null || confirmPasswd.equals("")) {
				Config.SOUND_WARNING.play();
				wrongMessage.setText("密码不得为空");
			} else if (!passwd.equals(confirmPasswd)) {
				Config.SOUND_WARNING.play();
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
					Config.SOUND_WARNING.play();
					wrongMessage.setText("用户名不能含有空格");
				} else {
					try {
						user = User.register(uid, passwd);
						UserBox.setUser(user);
						// 转跳到综合界面
						Config.SOUND_CLICK.play();
						Config.getMain().setScene(Config.getTheme().getSynScene());
					} catch (Exception q) {
						String qErrorMessage = q.getMessage();
						if (qErrorMessage.equals(User.FAILTOCONNECTDATABASE)) {
							Config.SOUND_WARNING.play();
							wrongMessage.setText("网络连接失败");

						} else if (qErrorMessage.equals(User.HASBEENREGISTERED)) {
							Config.SOUND_WARNING.play();
							wrongMessage.setText("用户名已被注册");
						} else {
							Config.SOUND_WARNING.play();
						}
					}
				}
			}

		});
	}

}

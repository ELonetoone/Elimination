package el.onetoone.ui;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
	
	public Button registerButton;
	
	public Button returnButton;
	
	public abstract Scene getRegisterScene();
	
	public Button userName;
	
	public TextField userTextField;
	
	public PasswordField passwordField;
	
	public Button password;
	
}

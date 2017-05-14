package el.onetoone.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class SignInPane extends Pane {

	protected ImageView backgroud;

	protected Button signInButton;
	protected Button backButton;

	protected Label userNameLabel;
	protected Label passWordLabel;

	protected TextField userName;
	protected PasswordField passwordField;

	abstract protected void createLabel();

	abstract protected void createButton();

	abstract protected void createBackgroud();

	abstract protected void createPasswordArea();

	public SignInPane() {

		createBackgroud();
		createButton();
		createLabel();
		createPasswordArea();
	}

	private void registerButtonListener() {

		signInButton.setOnAction(e -> {

		});

		backButton.setOnAction(e -> {

		});
	}
}

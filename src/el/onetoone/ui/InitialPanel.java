package el.onetoone.ui;

import el.onetoone.back.Config;
import el.onetoone.back.User;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public abstract class InitialPanel extends Scene {
	
	public InitialPanel(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public InitialPanel(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public InitialPanel(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public InitialPanel(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public InitialPanel(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public InitialPanel(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}
	
	public static int BUTTON_HEIGHT = 60;
	
	public static int BUTTON_HIGHLIGHT_MILLS = 300;
	
	public InitButton exitButton;
	
	public InitButton loginButton;
	
	public InitButton registerButton;
	
	public InitButton trialButton;
	
	public User user;
	
	public void createButton () {
		
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
		
	}
	
	public void registerButtonEventHandler() {
		
		trialButton.setOnAction(e -> {
			
		});
		
		exitButton.setOnAction(e -> {
			System.exit(0);
		});
		
		registerButton.setOnAction(e -> {
		
		});
		
	}
	
	public class InitButton extends Button {

		private DropShadow shadow;
		private ColorAdjust colorAdjust;

		public InitButton() {

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

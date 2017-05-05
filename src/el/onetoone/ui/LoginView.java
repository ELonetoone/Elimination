package el.onetoone.ui;

import el.onetoone.back.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView extends Application {

	private TextField userTextFiled;
	private PasswordField passwordField;
	private User user;
	private Text resultText;
	private Scene scene;

	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Happy Elimination");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		scene = new Scene(grid, 400, 400);
		
		Text text = new Text("Happy Elimination");
		text.setSmooth(true);
		text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(text, 0, 0, 2, 1);
		
		Label username = new Label("用户名");
		grid.add(username, 0, 1);
		
		Label password = new Label("密码");
		grid.add(password, 0, 2);
		
		userTextFiled = new TextField();
		grid.add(userTextFiled, 1, 1);
		
		passwordField = new PasswordField();
		grid.add(passwordField, 1, 2);
		
		Button signInBtn = new Button("登陆");
		signInBtn.setOnAction(new SignInBtnEventHandler());
		Button signUpBtn = new Button("注册");
		
		signUpBtn.setOnAction(e -> {
			scene.setRoot(initSignUpPane());
		});
		
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().add(signInBtn);
		hBox.getChildren().add(signUpBtn);
		grid.add(hBox, 1, 4);
		
		resultText = new Text();
		grid.add(resultText, 1, 6);
		
		//test code
//		GameScreen gameScreen = new GameScreen(scene);
//		GamePanel gamePanel = new GamePanel(scene);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * 登录按钮的事件处理
	 * @author liao
	 *
	 */
	class SignInBtnEventHandler implements EventHandler<ActionEvent> {

		
		@Override
		public void handle(ActionEvent event) {

			try {
				user = User.login(userTextFiled.getText(), passwordField.getText());
				initDegreeOfDiffcultyPane();
			} catch (Exception e) {
				String msg = e.getMessage();
				
				switch (msg) {
				case User.FAILTOCONNECTDATABASE:
					
					resultText.setText("Fail to connect data base");
					break;

				case User.WRONGPASSWORD:
					
					resultText.setText("密码错误");
					break;
					
				case User.HASNOTREGISTER:
					
					resultText.setText("用户不存在");
					
				default:
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 初始化注册界面
	 * @return
	 */
	public Parent initSignUpPane() {
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		TextField username = new TextField();
		username.setPromptText("用户名");
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("密码");
		
		grid.add(username, 0, 0);
		grid.add(passwordField, 0, 1);
		
		Text actionResult = new Text();
		grid.add(actionResult, 0, 3);
		
		Button sureButton = new Button("确定");
		sureButton.setOnAction(e -> {
			try {
				String usernameStr = username.getText().trim();
				String passwordStr = passwordField.getText();
				if (usernameStr.equals("") || passwordStr.equals("")) {
					actionResult.setText("用户名与密码不能为空");
			//	} else if (User.hasRegister(usernameStr)) {
			//		actionResult.setText("用户名已存在");
				} else {
					user = User.register(username.getText().trim(), passwordField.getText());
					initDegreeOfDiffcultyPane();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				if (e1.getMessage().equals(User.FAILTOCONNECTDATABASE)) {
					actionResult.setText("网络连接失败");
				} else if (e1.getMessage().equals(User.HASNOTREGISTER)) {
					actionResult.setText("用户名已存在");
				} 
			}
		});
		HBox hBox = new HBox();
		hBox.getChildren().add(sureButton);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		grid.add(hBox, 0, 2);
		
		return grid;
	}
	
	/**
	 * 进入难度选择界面
	 */
	private void initDegreeOfDiffcultyPane() {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text easyText = new Text("Easy");
		Text mediumText = new Text("Medium");
		Text difficultText = new Text("Difficult");
		
		easyText.setFont(new Font(16));
		mediumText.setFont(new Font(16));
		difficultText.setFont(new Font(16));
		
		easyText.setOnMouseEntered(e -> {
			easyText.setScaleX(1.5);
			easyText.setScaleY(1.5);
		});
		
		easyText.setOnMouseExited(e -> {
			easyText.setScaleX(1.0);
			easyText.setScaleY(1.0);
		});
		
		easyText.setOnMousePressed(e -> {
			GameScreen gameScreen = new GameScreen(scene);
		});
		
		mediumText.setOnMouseEntered(e -> {
			mediumText.setScaleX(1.5);
			mediumText.setScaleY(1.5);
		});
		
		mediumText.setOnMouseExited(e -> {
			mediumText.setScaleX(1.0);
			mediumText.setScaleY(1.0);
		});
		
		mediumText.setOnMousePressed(e -> {
			GameScreen gameScreen = new GameScreen(scene);
		});
		
		difficultText.setOnMouseEntered(e -> {
			difficultText.setScaleX(1.5);
			difficultText.setScaleY(1.5);
		});
		
		difficultText.setOnMouseExited(e -> {
			difficultText.setScaleX(1.0);
			difficultText.setScaleY(1.0);
		});
		
		difficultText.setOnMousePressed(e -> {
			GameScreen gameScreen = new GameScreen(scene);
		});
		
		grid.add(easyText, 0, 0);
		grid.add(mediumText, 0, 1);
		grid.add(difficultText, 0, 2);
		
		scene.setRoot(grid);
	}

	public static void main(String[] args) {
		launch(args);
	}
}

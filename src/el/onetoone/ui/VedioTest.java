package el.onetoone.ui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class VedioTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Scene scene = new Scene(new Group(), 1000, 700);
		primaryStage.setScene(scene);
		
//		String source = getClass().getResource("/image/start_vedio.flv").toString();
//		System.out.println(source);
		Media media = new Media("file:/C:/Users/liao/workspace/Elimination/target/classes/image/1.flv");
		
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(1);
		mediaPlayer.setAutoPlay(true);
		
		MediaView mediaView = new MediaView(mediaPlayer);
		ImageView imageView = new ImageView(new Image("/image/diamond_1.png"));
		((Group) scene.getRoot()).getChildren().add(mediaView);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class FramePanel extends Pane{

	private ImageView backgourd;
	private ScoreText score;
	private Image bgImage;
	public FramePanel(int num) {
		// TODO Auto-generated constructor stub
		
		switch (num) {
		case 0:
			bgImage = Config.getTheme().FRAME_HIGHEST_SCORE;
			break;

		case 1:
			bgImage = Config.getTheme().FRAME_MONEY;
			break;
			
		case 2:
			bgImage = Config.getTheme().FRAME_STEP;
			break;
			
		case 3:
			bgImage = Config.getTheme().FRAME_TIME;
			break;
		}
		setPrefSize(300, 205);
		createNode();
		addNode();
	}
	private void addNode() {
		// TODO Auto-generated method stub
		
		getChildren().add(backgourd);
		
	}
	private void createNode() {
		// TODO Auto-generated method stub
		setPrefSize(300, 200);
		backgourd = new ImageView(bgImage);
		backgourd.setFitHeight(getPrefHeight());
		backgourd.setFitWidth(getPrefWidth());

	}
	public void initScoreText() {
		
		score = new ScoreText(((GamePanel)((Group)this.getParent()).getChildren().get(2)).getDiamondGrid());
		score.setLayoutX(backgourd.getFitWidth() / 2 - 10);
		score.setLayoutY(backgourd.getFitHeight() / 2 - 10);
		
		getChildren().add(score);
	}
}

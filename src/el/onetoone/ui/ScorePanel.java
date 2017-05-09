package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ScorePanel extends Pane{

	private ImageView backgourd;
	private ScoreText score;
	public ScorePanel() {
		// TODO Auto-generated constructor stub
		
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
		backgourd = new ImageView(Config.getTheme().FRAME_HIGHEST_SCORE);
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

package el.onetoone.ui.magicgirl;

import el.onetoone.back.Config;
import el.onetoone.ui.GameMain;
import el.onetoone.ui.MagicGirlTheme;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class MagicGameMain extends GameMain{

	public MagicGameMain(String mode) {
		
		super(mode);
	}

	@Override
	protected void layFrameContent() {
		// TODO Auto-generated method stub
		timeIndefiniteImg.setLayoutX(FRAME_CONTENT_LAYOUT_X);
		timeIndefiniteImg.setLayoutY(FRAME_CONTENT_LAYOUT_Y);
		
		stepIndefiniteImg.setLayoutX(FRAME_CONTENT_LAYOUT_X);
		stepIndefiniteImg.setLayoutY(FRAME_CONTENT_LAYOUT_Y + FRAMR_HEIGHT);
		
		if (timeText != null) {
			timeText.setLayoutX(FRAME_CONTENT_LAYOUT_X);
			timeText.setLayoutY(FRAME_CONTENT_LAYOUT_Y + 50);
		}
		if (stepText != null) {
			stepText.setLayoutX(FRAME_CONTENT_LAYOUT_X);
			stepText.setLayoutY(FRAME_CONTENT_LAYOUT_Y + FRAMR_HEIGHT + 50);
		}
		
		money.setLayoutX(FRAME_CONTENT_LAYOUT_X);
		money.setLayoutY(FRAME_CONTENT_LAYOUT_Y + FRAMR_HEIGHT * 2 + 30);
		
		scoreText.setLayoutX(FRAME_CONTENT_LAYOUT_X);
		scoreText.setLayoutY(FRAME_CONTENT_LAYOUT_Y + FRAMR_HEIGHT * 3 + 30);
	}

	@Override
	protected void layoutProps() {
		// TODO Auto-generated method stub
		propPane = new TilePane();
		propPane.setPrefColumns(2);
		propPane.getChildren().addAll(propsBoom, propsHammer, propsNewMap, propsOneSec, propsThreeSec, propsFiveSec, propsOneStep, propsThreeStep, propsFiveStep);
		propPane.setLayoutX(Config.SCREEN_WIDTH - 250);
		propPane.setLayoutY(80);
		propPane.setVgap(15);
		propPane.setHgap(15);

		getChildren().add(propPane);
	}

	@Override
	protected void addFrameAndButton() {
		
		backgroud = new ImageView(MagicGirlTheme.BG_GAME_ING);
		
		gamePanel.setLayoutX(460);
		gamePanel.setLayoutY(100);
		
		gameFrame.setFitWidth(600);
		gameFrame.setPreserveRatio(true);
		gameFrame.setLayoutX(400);
		gameFrame.setLayoutY(30);
		
		moneyFrame.setFitHeight(FRAMR_HEIGHT);
		moneyFrame.setPreserveRatio(true);
		
		stepFrame.setFitHeight(FRAMR_HEIGHT);
		stepFrame.setPreserveRatio(true);
		
		timeFrame.setFitHeight(FRAMR_HEIGHT);
		timeFrame.setPreserveRatio(true);
		
		scoreFrame.setFitHeight(FRAMR_HEIGHT);
		scoreFrame.setPreserveRatio(true);
		
		this.getChildren().add(backgroud);
		this.getChildren().add(gameFrame);
		this.getChildren().add(gamePanel);

		this.getChildren().add(exitButton);
		this.getChildren().add(configButton);
		// scorePanel.initScoreText();

		frameBox = new VBox(timeFrame, stepFrame, scoreFrame, moneyFrame);
		frameBox.setLayoutX(170);
		frameBox.setLayoutY(260);
		getChildren().add(frameBox);

		buttonBox = new HBox(backBtn, restartBtn);
		buttonBox.setLayoutX(Config.SCREEN_WIDTH - 250);
		buttonBox.setLayoutY(Config.SCREEN_HEIGHT - 150);
		buttonBox.setSpacing(10);
		getChildren().add(buttonBox);
	}

}

package el.onetoone.ui.naruto;

import el.onetoone.back.Config;
import el.onetoone.ui.GameMain;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class NarutoGameMain extends GameMain{

	public NarutoGameMain(String mode) {
		super(mode);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createFrameContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void layoutProps() {
		// TODO Auto-generated method stub
		propPane = new TilePane();
		propPane.setPrefColumns(1);
		propPane.setVgap(25);
		propPane.getChildren().addAll(propsBoom, propsHammer, propsNewMap, propsOneSec
				, propsThreeSec, propsFiveSec, propsOneStep, propsThreeStep, propsFiveStep);
		
		ScrollPane scrollPane = new ScrollPane(propPane);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setMaxSize(150, 400);
		scrollPane.setMinWidth(100);
		scrollPane.setLayoutX(60);
		scrollPane.setLayoutY(100);
		getChildren().add(scrollPane);
		
	}

	@Override
	protected void addFrameAndButton() {
		// TODO Auto-generated method stub
		backgroud = new ImageView(NarutoTheme.BG_GAME_ING);
		
		buttonBox = new HBox(restartBtn, backBtn);
		buttonBox.setLayoutX(Config.SCREEN_WIDTH - 200);
		buttonBox.setLayoutY(80);
		
		gamePanel.setLayoutX(330);
		gamePanel.setLayoutY(60);
		
		getChildren().addAll(backgroud, buttonBox, gamePanel);
	}

}

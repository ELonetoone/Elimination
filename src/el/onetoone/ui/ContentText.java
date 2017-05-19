package el.onetoone.ui;

import el.onetoone.back.BaseDiamondGrid;
import el.onetoone.back.Config;
import javafx.concurrent.Task;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ContentText extends Text {

	BaseDiamondGrid diamondGrid;
	GamePanel gamePanel;

	public ContentText(BaseDiamondGrid diamondGrid, GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.diamondGrid = diamondGrid;
		setFont(Font.font("Comic Sans MS"));
		setStyle("-fx-fill: #ff00ff;" + "-fx-text-alignment: center;" + "-fx-font-size: 24");
	}

	public void bindGrade() {
		textProperty().bind(diamondGrid.gradeProperty().asString());
	}

	public void bindStep() {
		diamondGrid.stepProperty().set(Config.START_STEP);
		textProperty().bind(diamondGrid.stepProperty().asString());
		textProperty().addListener((o, oldValue, newValue) -> {
			if (textProperty().get().equals("0")) {
				gamePanel.gameOver();
			}
		});
	}

	public void bindTime() {
		diamondGrid.setTime(Config.START_TIME);
		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
//				int currentTime = diamondGrid.timeProperty().get();
				while (diamondGrid.timeProperty().get() != 0) {

					Thread.sleep(1000);
					diamondGrid.timeProperty().set(diamondGrid.timeProperty().get() - 1);
				}
				gamePanel.gameOver();
				return null;
			}
		};

		textProperty().bind(diamondGrid.timeProperty().asString());
		new Thread(task).start();
	}
}

package el.onetoone.ui;

import el.onetoone.back.BaseDiamondGrid;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ContentText extends Text{

	BaseDiamondGrid diamondGrid;
	
	public ContentText(BaseDiamondGrid diamondGrid) {
		this.diamondGrid = diamondGrid;
		setFont(Font.font("Comic Sans MS"));
		setStyle("-fx-fill: #ff00ff;"
				+ "-fx-text-alignment: center;"
				+ "-fx-font-size: 24");
	}
	
	public void bindGrade() {
		textProperty().bind(diamondGrid.gradeProperty().asString());
	}
	
	public void bindStep() {
		textProperty().bind(diamondGrid.stepProperty().asString());
	}
	
	public void bindTime() {
		diamondGrid.setTime(60);
		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				int currentTime = diamondGrid.timeProperty().get();
				while (currentTime != 0) {
					
					Thread.sleep(1000);
					diamondGrid.timeProperty().set(--currentTime);
				}
				return null;
			}
		};
		
		textProperty().bind(diamondGrid.timeProperty().asString());
		new Thread(task).start();
	}
}

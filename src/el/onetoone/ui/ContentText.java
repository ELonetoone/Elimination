package el.onetoone.ui;

import el.onetoone.back.BaseDiamondGrid;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
		textProperty().bind(diamondGrid.timeProperty().asString());
	}
}

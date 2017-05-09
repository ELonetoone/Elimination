package el.onetoone.ui;

import el.onetoone.back.BaseDiamondGrid;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreText extends Text{

	
	public ScoreText(BaseDiamondGrid diamondGrid) {
		setFont(Font.font("Comic Sans MS"));
		textProperty().bind(diamondGrid.gradeProperty().asString());
	}
}

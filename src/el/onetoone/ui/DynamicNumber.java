package el.onetoone.ui;

import el.onetoone.back.Config;
import el.onetoone.back.UserBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DynamicNumber extends HBox{

	private int bits;
	private Image[] numImgs;
	private ImageView[] numImgViews;
	private String score;
	
	public DynamicNumber(int bits) {
		
		numImgs = Config.getTheme().getNums();
		this.bits = bits;
		numImgViews = new ImageView[bits];
		for (int i = 0; i < numImgViews.length; i++) {
			numImgViews[i] = new ImageView(numImgs[0]);
			numImgViews[i].setFitWidth(25);
			numImgViews[i].setPreserveRatio(true);
		}
		
		for (int i = 0; i < bits; i++) {
			getChildren().add(numImgViews[i]);
		}
	}
	
	public void initUnlimitedScore() {
		score = UserBox.getUser().getendLessMaxMark() + "";
		parseScore();
	}
	
	public void initStepScore() {
		score = UserBox.getUser().getStepLimitedMaxMark() + "";
		parseScore();
	}
	
	public void initTimeScore() {
		score = UserBox.getUser().getTimeLimitedMaxMark() + "";
		parseScore();
	}
	
	private void parseScore() {
		
		for (int i = 0; i < score.length(); i++) {
			numImgViews[bits - i - 1].setImage(numImgs[score.charAt(score.length() - 1 - i) - '0']);
		}
	}
}

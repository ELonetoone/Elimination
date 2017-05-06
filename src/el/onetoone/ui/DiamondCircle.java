package el.onetoone.ui;

import el.onetoone.back.Point;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class DiamondCircle extends ImageView{
	
	private static final double TRANSLATETRANSITIONTIME = 500;
	private static final double FADETRANSITIONTIME = 300;
	private Point point;
	private Bloom bloomEffect;
	
	public DiamondCircle() {
		
		bloomEffect = new Bloom();
		bloomEffect.setThreshold(0.9);
		setEffect(bloomEffect);		
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	
	/**
	 * 横向移动,同时改变相应的point的x，y
	 * @param byX 要移动的距离
	 */
	public TranslateTransition hrizontalTransition(double byX) {
		
		TranslateTransition hTransition = new TranslateTransition(Duration.millis(TRANSLATETRANSITIONTIME), this);
		hTransition.setCycleCount(1);
		hTransition.setByX(byX);
		
		return hTransition;
	}
	
	/**
	 * 竖直移动,同时会改变相应的point的x,y
	 * @param byY 要移动的距离
	 * @return 
	 */
	public TranslateTransition verticalTransition(double byY) {
		
		TranslateTransition vTransition = new TranslateTransition(Duration.millis(TRANSLATETRANSITIONTIME), this);
		vTransition.setCycleCount(1);
		vTransition.setByY(byY);
		
		return vTransition;
	}
	
	/**
	 * 被消除时执行一个fadetransition
	 */
	public ParallelTransition fade() {
		
		ParallelTransition result = new ParallelTransition();
		FadeTransition fade = new FadeTransition(Duration.millis(FADETRANSITIONTIME), this);
		fade.setFromValue(1.0);
		fade.setToValue(0.0);
		fade.setCycleCount(1);
		RotateTransition rotateTransition = new RotateTransition(Duration.millis(FADETRANSITIONTIME), this);
		rotateTransition.setByAngle(720);
		rotateTransition.setCycleCount(1);
		Timeline reduce = new Timeline(
				new KeyFrame(Duration.millis(FADETRANSITIONTIME), new KeyValue(this.scaleXProperty(), 0.0)),
				new KeyFrame(Duration.millis(FADETRANSITIONTIME), new KeyValue(this.scaleYProperty(), 0.0)));
		result.getChildren().addAll(fade, rotateTransition, reduce);
		
		return result;
	}
	
	public TranslateTransition dropToTransition(double toY) {
		TranslateTransition dropTransition = new TranslateTransition(Duration.millis(TRANSLATETRANSITIONTIME), this);
		dropTransition.setByY(toY);;
		
		return dropTransition;
	}
	
	public FadeTransition appearTransition() {
		
		FadeTransition appear = new FadeTransition(Duration.millis(FADETRANSITIONTIME), this);
		appear.setFromValue(0.0);
		appear.setToValue(1.0);
		appear.setCycleCount(1);
		
		return appear;
	}
	
	public String loaction() {
		
		return getLayoutX() + ", " + getLayoutY();
	}
}

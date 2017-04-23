package el.onetoone.ui;

import el.onetoone.back.Point;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class DiamondCircle extends Circle{
	
	private static final double TRANSLATETRANSITIONTIME = 500;
	private static final double FADETRANSITIONTIME = 300;
	private Point point;

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
	public FadeTransition fade() {
		
		FadeTransition fade = new FadeTransition(Duration.millis(FADETRANSITIONTIME), this);
		fade.setFromValue(1.0);
		fade.setToValue(0.0);
		fade.setCycleCount(1);
		
		return fade;
	}
	
	public String loaction() {
		
		return getCenterX() + ", " + getCenterY();
	}
}

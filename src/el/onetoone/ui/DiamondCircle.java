package el.onetoone.ui;

import el.onetoone.back.Point;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class DiamondCircle extends ImageView {

	private static final double TRANSLATETRANSITIONTIME = 500;
	private static final double FADETRANSITIONTIME = 300;
	private Point point;

	public DiamondCircle() {

		createEnterEffect();
	}

	private void createEnterEffect() {

		DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.WHITE, 0, 1, 0, 0);
		ColorAdjust light = new ColorAdjust();

		shadow.setInput(light);
		setEffect(shadow);

		Timeline flashing = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(light.brightnessProperty(), 0)),
				new KeyFrame(Duration.ZERO, new KeyValue(shadow.radiusProperty(), 0)),
				new KeyFrame(Duration.millis(FADETRANSITIONTIME),
						new KeyValue(shadow.radiusProperty(), 3, Interpolator.EASE_IN)),
				new KeyFrame(Duration.millis(FADETRANSITIONTIME),
						new KeyValue(light.brightnessProperty(), 0.2, Interpolator.EASE_IN)));

		Timeline stopFlashing = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(shadow.radiusProperty(), 3, Interpolator.EASE_IN)),
				new KeyFrame(Duration.ZERO, new KeyValue(light.brightnessProperty(), 0.2, Interpolator.EASE_IN)),
				new KeyFrame(Duration.millis(FADETRANSITIONTIME), new KeyValue(light.brightnessProperty(), 0)),
				new KeyFrame(Duration.millis(FADETRANSITIONTIME), new KeyValue(shadow.radiusProperty(), 0)));

		setOnMouseEntered(e -> {
			flashing.play();
		});

		setOnMouseExited(e -> stopFlashing.play());
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	/**
	 * 横向移动,同时改变相应的point的x，y
	 * 
	 * @param byX
	 *            要移动的距离
	 */
	public TranslateTransition hrizontalTransition(double byX) {

		TranslateTransition hTransition = new TranslateTransition(Duration.millis(TRANSLATETRANSITIONTIME / 2), this);
		hTransition.setCycleCount(1);
		hTransition.setByX(byX);

		return hTransition;
	}

	/**
	 * 竖直移动,同时会改变相应的point的x,y
	 * 
	 * @param byY
	 *            要移动的距离
	 * @return
	 */
	public TranslateTransition verticalTransition(double byY) {

		TranslateTransition vTransition = new TranslateTransition(Duration.millis(TRANSLATETRANSITIONTIME / 2), this);
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
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(1);

		Timeline reduce = new Timeline(
				new KeyFrame(Duration.millis(FADETRANSITIONTIME), new KeyValue(this.opacityProperty(), 0.0)),
				new KeyFrame(Duration.millis(FADETRANSITIONTIME), new KeyValue(this.opacityProperty(), 0.0)),
				new KeyFrame(Duration.millis(FADETRANSITIONTIME / 2), new KeyValue(this.scaleXProperty(), 1.2)),
				new KeyFrame(Duration.millis(FADETRANSITIONTIME / 2), new KeyValue(this.scaleYProperty(), 1.2)));
		result.getChildren().addAll(fade, reduce);

		return result;
	}

	public TranslateTransition dropToTransition(double toY) {
		TranslateTransition dropTransition = new TranslateTransition(Duration.millis(TRANSLATETRANSITIONTIME), this);
		dropTransition.setByY(toY);
		;

		return dropTransition;
	}

	public ParallelTransition appearTransition() {

		ParallelTransition result = new ParallelTransition();

		FadeTransition appear = new FadeTransition(Duration.millis(FADETRANSITIONTIME * 2), this);
		appear.setFromValue(0.0);
		appear.setToValue(1.0);
		appear.setCycleCount(1);

		// RotateTransition rotateTransition = new
		// RotateTransition(Duration.millis(FADETRANSITIONTIME), this);
		// rotateTransition.setByAngle(-360);

		result.getChildren().addAll(appear);

		return result;
	}

	public String loaction() {

		return getLayoutX() + ", " + getLayoutY();
	}
}

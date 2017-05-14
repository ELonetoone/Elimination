package el.onetoone.ui.naruto;

import el.onetoone.back.Config;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * 火影返回按钮，默认放置在1150 30位置
 * 鼠标放上去自动变大，移开自动变小
 * 应该会在多个界面用到
 * @author iznauy
 *
 */
public class NarutoReturnButton extends Button {

	public NarutoReturnButton() {
		// TODO Auto-generated constructor stub
		this.init();
	}

	public NarutoReturnButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public NarutoReturnButton(String text, Node graphic) {
		super(text, graphic);
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		this.setGraphic(new ImageView(Config.getTheme().getBUTTON_BACK()));
		this.setLayoutX(1150);
		this.setLayoutY(30);
		this.setOnMouseEntered(e -> {
			this.setScaleX(1.3);
			this.setScaleY(1.3);
		});
		
		this.setOnMouseExited(e -> {
			this.setScaleX(1.0);
//			this.setScaleY(1.0);
		});
	}
	
	

}

package el.onetoone.ui;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SystemButton extends Button{
	
	
	// 0 exit
	// 1 pause
	public SystemButton(int num) {
		
		this.setId("system-btn");
		ImageView imageView = null;
		switch (num) {
		case 0:
			
			imageView = new ImageView(Theme.SYSTEM_BUTTON_EXIT);
			imageView.setFitWidth(25);
			break;

//		case 1:
//			
//			imageView = new ImageView(Theme.SYSTEM_BUTTON_PAUSE);
//			imageView.setFitWidth(25);
//			break;
//			
		case 2:
			
			imageView = new ImageView(Theme.SYSTEM_BUTTON_CONFIG);
			imageView.setFitWidth(25);
			break;
		}
		
		imageView.setPreserveRatio(true);
		if (imageView != null) {
			this.setGraphic(imageView);
		}
		this.setStyle("-fx-background-color: #ffffff;"
				+ "-fx-pref-width: 50;"
				+ "-fx-pref-height: 50;"
				+ "	-fx-background-radius: 25;"
				+ "	-fx-border-radius: 100;"
				+ "	-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1);");
	}
}

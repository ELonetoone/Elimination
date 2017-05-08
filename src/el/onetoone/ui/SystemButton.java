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

		case 1:
			
			imageView = new ImageView(Theme.SYSTEM_BUTTON_PAUSE);
			imageView.setFitWidth(25);
			break;
			
		case 2:
			
			imageView = new ImageView(Theme.SYSTEM_BUTTON_CONGIG);
			imageView.setFitWidth(25);
			break;
		}
		
		imageView.setPreserveRatio(true);
		if (imageView != null) {
			this.setGraphic(imageView);
		}
	}
}

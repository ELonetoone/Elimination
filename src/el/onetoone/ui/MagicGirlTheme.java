package el.onetoone.ui;

import javafx.scene.image.Image;

public class MagicGirlTheme extends Theme {

	public MagicGirlTheme() {
		
		//set background
		setBG_START(new Image("/image/bg_start.png", true));
		setBG_GAME(new Image("/image/bg_game.jpg", true));
		setBG_SIGN(new Image("/image/bg_sign.png", true));
		
		//set diamond
		setDIAMOND_ONE(new Image("/image/diamond_1.png"));
		setDIAMOND_TWO(new Image("/image/diamond_2.png"));
		setDIAMOND_THREE(new Image("/image/diamond_3.png"));
		setDIAMOND_FOUR(new Image("/image/diamond_4.png"));
		setDIAMOND_FIVE(new Image("/imaeg/diamond_5"));
		setDIAMOND_SIX(new Image("/image/diamond_6"));
		
		//set button
		setBUTTON_SIGN_UP(new Image("/image/button_sign_up"));
		setBUTTON_BACK(new Image("/image/button_back"));
		setBUTTON_GAME_EXIT(new Image("/image/button_game_exit"));
		
	}
}

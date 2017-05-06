package el.onetoone.ui;

import javafx.scene.image.Image;

public class NarutoTheme extends Theme{

	public NarutoTheme() {
		
		// set background
		setBG_START(new Image("/image/narotu/bg_start.png", true));
		setBG_GAME(new Image("/image/narotu/bg_game.jpg", true));
		setBG_SIGN(new Image("/image/narotu/bg_sign.png", true));

		// set diamond
		setDIAMOND_ONE(new Image("/image/narotu/diamond_1.png"));
		setDIAMOND_TWO(new Image("/image/narotu/diamond_2.png"));
		setDIAMOND_THREE(new Image("/image/narotu/diamond_3.png"));
		setDIAMOND_FOUR(new Image("/image/narotu/diamond_4.png"));
		setDIAMOND_FIVE(new Image("/image/narotu/diamond_5.png"));
		setDIAMOND_SIX(new Image("/image/narotu/diamond_6.png"));

		// set button
		setBUTTON_BACK(new Image("/image/narotu/button_back.jpg"));
		setBUTTON_GAME_EXIT(new Image("/image/narotu/button_game_exit.png"));
		setBUTTON_GAME_RESTART(new Image("/image/narotu/button_restart.png"));
		setBUTTON_INDEFINITE_MODE(new Image("/image/narotu/button_indefinite_mode.png"));
		setBUTTON_PROPS(new Image("/image/narotu/button_props.png"));
		setBUTTON_SIGN_IN(new Image("/image/narotu/button_signIn.png"));
		setBUTTON_SIGN_UP(new Image("/image/narotu/button_signUp.png"));
		setBUTTON_STEP_MODE(new Image("/image/narotu/button_step_mode.png"));
		setBUTTON_TIME_MODE(new Image("/image/narotu/button_time_mode.png"));

		// set frame
		setFRAME_GAME(new Image("/image/narotu/frame_game.png"));
		setFRAME_HIGHEST_SCORE(new Image("/image/narotu/frame_highest_score.png"));
		setFRAME_MONEY(new Image("/image/narotu/frame_money.png"));
		setFRAME_SIGN_IN(new Image("/image/narotu/frame_signIn.png"));
		setFRAME_STEP(new Image("/image/narotu/frame_step.png"));
		setFRAME_TIME(new Image("/image/narotu/frame_time.png"));

	}
}

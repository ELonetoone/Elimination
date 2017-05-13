package el.onetoone.ui.naruto;

import el.onetoone.back.Config;
import el.onetoone.ui.Theme;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class NarutoTheme extends Theme{

	public static final Image BG_SYN = new Image("/image/naruto/bg_syn.png");
	public NarutoTheme() {
		
		// set background
		setBG_START(new Image("/image/naruto/init_bg.jpg", true));
		setBG_GAME(new Image("/image/naruto/bg_game.jpg", true));
		setBG_SIGN(new Image("/image/naruto/bg_sign.jpg", true));

		// set diamond
		setDIAMOND_ONE(new Image("/image/naruto/diamond_1.png"));
		setDIAMOND_TWO(new Image("/image/naruto/diamond_2.png"));
		setDIAMOND_THREE(new Image("/image/naruto/diamond_3.png"));
		setDIAMOND_FOUR(new Image("/image/naruto/diamond_4.png"));
		setDIAMOND_FIVE(new Image("/image/naruto/diamond_5.png"));
		setDIAMOND_SIX(new Image("/image/naruto/diamond_6.png"));

		// set button
		setBUTTON_BACK(new Image("/image/naruto/button_back.png"));
		setBUTTON_GAME_EXIT(new Image("/image/naruto/button_game_exit.png"));
		setBUTTON_GAME_RESTART(new Image("/image/naruto/button_restart.png"));
		setBUTTON_INDEFINITE_MODE(new Image("/image/naruto/button_indefinite_mode.png"));
//		setBUTTON_PROPS(new Image("/image/naruto/button_props.png"));
		setBUTTON_SIGN_IN(new Image("/image/naruto/button_signIn.png"));
		setBUTTON_SIGN_UP(new Image("/image/naruto/button_signUp.png"));
		setBUTTON_STEP_MODE(new Image("/image/naruto/button_step_mode.png"));
		setBUTTON_TIME_MODE(new Image("/image/naruto/button_time_mode.png"));
		
		setBUTTON_SHOP(new Image("/image/naruto/button_shop.png"));
		setBUTTON_KEJIN(new Image("/image/naruto/button_kejin.png"));
		setBUTTON_CONFIG(new Image("/image/naruto/button_config.png"));
		setBUTTON_LOG_OUT(new Image("/image/naruto/button_log_out.png"));
		setBUTTON_HEIGHEST_SCORE(new Image("/image/naruto/button_heighest_score.png"));
		
		setINIT_BUTTON_QUIT(new Image("/image/naruto/init_game_quit.png"));
		setINIT_BUTTON_SIGN_IN(new Image("/image/naruto/init_game_start.png"));
		setINIT_BUTTON_SIGN_UP(new Image("/image/naruto/init_game_signUp.png"));
		setINIT_BUTTON_TRY(new Image("/image/naruto/init_game_try.png"));
		
		// set frame
//		setFRAME_GAME(new Image("/image/naruto/frame_game.png"));
//		setFRAME_HIGHEST_SCORE(new Image("/image/naruto/frame_highest_score.png"));
//		setFRAME_MONEY(new Image("/image/naruto/frame_money.png"));
//		setFRAME_SIGN_IN(new Image("/image/naruto/frame_signIn.png"));
//		setFRAME_STEP(new Image("/image/naruto/frame_step.png"));
//		setFRAME_TIME(new Image("/image/naruto/frame_time.png"));

	}

	@Override
	public void playBGM() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Scene getInitialScene() {
		// TODO Auto-generated method stub
		return new NarutoInitialScene(new Pane(), Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT).getInitialScene();
	}

	@Override
	public Scene getLoginScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scene getRegisterScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scene getSettingScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scene getMaxMarkScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scene getMarketScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scene getGameScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scene getSynScene() {
		
		return new NarutoSynthetic().getSyntheticScene();
	}

}
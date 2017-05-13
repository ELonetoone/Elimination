package el.onetoone.ui;

import el.onetoone.back.Config;
import el.onetoone.ui.magicgirl.MagicInitialScene;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MagicGirlTheme extends Theme {

	private static final int FRAME_LITTLE_WIDTH = 300;
	private static final int FRAME_LITTLE_HEIGHT = 400;
	

	public MagicGirlTheme() {
		
		//set background
		setBG_START(new Image("/image/bg_start.png", Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, true, true, true));
		setBG_GAME(new Image("/image/bg_game.png", Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, false, false, true));
		setBG_SIGN(new Image("/image/bg_sign.png", Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, true, false, true));
		
		//set diamond
		setDIAMOND_ONE(new Image("/image/diamond_1.png"));
		setDIAMOND_TWO(new Image("/image/diamond_2.png"));
		setDIAMOND_THREE(new Image("/image/diamond_3.png"));
		setDIAMOND_FOUR(new Image("/image/diamond_4.png"));
		setDIAMOND_FIVE(new Image("/image/diamond_5.png"));
		setDIAMOND_SIX(new Image("/image/diamond_6.png"));
		
		//set button
		setBUTTON_BACK(new Image("/image/button_back.jpg"));
		setBUTTON_GAME_EXIT(new Image("/image/button_game_exit.png"));
		setBUTTON_GAME_RESTART(new Image("/image/button_restart.png"));
		setBUTTON_INDEFINITE_MODE(new Image("/image/button_indefinite_mode.png"));
		setBUTTON_PROPS(new Image("/image/button_props.png"));
		setBUTTON_SIGN_IN(new Image("/image/button_signIn.png"));
		setBUTTON_SIGN_UP(new Image("/image/button_signUp.png"));
		setBUTTON_STEP_MODE(new Image("/image/button_step_mode.png"));
		setBUTTON_TIME_MODE(new Image("/image/button_time_mode.png"));
		setBUTTON_CONFIG(new Image("/image/button_config.png"));
		setBUTTON_LOG_OUT(new Image("/image/button_log_out.png"));
		setBUTTON_KEJIN(new Image("/image/button_kejin.png"));
		setBUTTON_HEIGHEST_SCORE(new Image("/image/button_heighest_score.png"));
		setBUTTON_SHOP(new Image("/image/button_shop.png"));
		
		//设置初始界面的按钮
		setINIT_BUTTON_QUIT(new Image("/image/init_button_quit.png"));
		setINIT_BUTTON_SIGN_IN(new Image("/image/init_button_signIn.png"));
		setINIT_BUTTON_SIGN_UP(new Image("/image/init_button_signUp.png"));
		setINIT_BUTTON_TRY(new Image("/image/init_button_try.png"));
		
		//set frame
		setFRAME_GAME(new Image("/image/frame_game.png", 1000, 1200, true, false, true));
		setFRAME_HIGHEST_SCORE(new Image("/image/frame_highest_score.png", FRAME_LITTLE_WIDTH, FRAME_LITTLE_HEIGHT, true, false));
		setFRAME_MONEY(new Image("/image/frame_money.png", FRAME_LITTLE_WIDTH, FRAME_LITTLE_HEIGHT, true, false));
		setFRAME_SIGN_IN(new Image("/image/frame_signIn.png", FRAME_LITTLE_WIDTH, FRAME_LITTLE_HEIGHT, true, false));
		setFRAME_STEP(new Image("/image/frame_step.png", FRAME_LITTLE_WIDTH, FRAME_LITTLE_HEIGHT, true, false));
		setFRAME_TIME(new Image("/image/frame_time.png", FRAME_LITTLE_WIDTH, FRAME_LITTLE_HEIGHT, true, false));
	}

	@Override
	public void playBGM() {
		// TODO Auto-generated method stub
		String source = getClass().getResource("/image/bgm_magic.mp3").toExternalForm();
		Media bgm = new Media(source);
		bgmPlayer = new MediaPlayer(bgm);
		bgmPlayer.setAutoPlay(true);
		bgmPlayer.play();
	}

	@Override
	public Scene getInitialScene() {
		// TODO Auto-generated method stub
		return new MagicInitialScene(new Pane(), Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT).getInitialScene();
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
		// TODO Auto-generated method stub
		return null;
	}
}

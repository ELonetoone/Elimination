package el.onetoone.ui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

public abstract class Theme {

	public static final Image SYSTEM_BUTTON_EXIT = new Image("image/system_button_exit.png");
	// public static final Image SYSTEM_BUTTON_PAUSE = new
	// Image("/image/system_button_pause.png");
	public static final Image SYSTEM_BUTTON_CONFIG = new Image("image/system_button_config.png");
	public static final Image IMAGE_MONEY = new Image("/image/icon_money.png");
	public static final Image PROPS_HAMMER = new Image("/image/props_hammer.png");
	public static final Image PROPS_NEW_MAP = new Image("/image/props_new_map.png");
	public static final Image PROPS_BOOM = new Image("/image/props_boom.png");

	
	protected Image ICON_INDEFINITE;

	// backgroud image
	protected Image BG_START;
	protected Image BG_GAME;
	protected Image BG_SIGN;
	protected Image BG_HEIGHEST_SCORE;
	protected Image BG_SETTING_PANE;
	protected Image BG_SHOP;

	// diamond
	protected Image DIAMOND_ONE;
	protected Image DIAMOND_TWO;
	protected Image DIAMOND_THREE;
	protected Image DIAMOND_FOUR;
	protected Image DIAMOND_FIVE;
	protected Image DIAMOND_SIX;

	// button
	protected Image BUTTON_GAME_START;
	protected Image BUTTON_GAME_RESTART;
	protected Image BUTTON_GAME_EXIT;
	protected Image BUTTON_SIGN_UP;
	protected Image BUTTON_SIGN_IN;
	protected Image BUTTON_BACK;
	protected Image BUTTON_TIME_MODE;
	protected Image BUTTON_INDEFINITE_MODE;
	protected Image BUTTON_STEP_MODE;

	// 主界面的几个按钮
	protected Image BUTTON_CONFIG;
	protected Image BUTTON_SHOP;
	protected Image BUTTON_KEJIN;
	protected Image BUTTON_HEIGHEST_SCORE;
	protected Image BUTTON_LOG_OUT;

	// 初始化界面的按钮
	protected Image INIT_BUTTON_SIGN_IN;
	protected Image INIT_BUTTON_SIGN_UP;
	protected Image INIT_BUTTON_QUIT;
	protected Image INIT_BUTTON_TRY;

	// frame(框)
	protected Image FRAME_STEP;
	protected Image FRAME_SIGN_IN;
	protected Image FRAME_MONEY;
	protected Image FRAME_TIME;
	protected Image FRAME_GAME;
	protected Image FRAME_HIGHEST_SCORE;
	
	protected Image[] nums = new Image[10];

	protected MediaPlayer bgmPlayer;

	// 播放背景音乐
	abstract public void playBGM();

	public MediaPlayer getBGMPlayer() {
		return bgmPlayer;
	}

	public static void setBlur(Group root) {

		for (Node node : root.getChildren()) {
			node.setEffect(new GaussianBlur());
		}
	}

	public static void setBlur(Pane pane) {

		for (Node node : pane.getChildren()) {
			node.setEffect(new GaussianBlur());
		}
	}

	public static void removeBlur(Group group) {

		for (Node node : group.getChildren()) {
			node.setEffect(null);
		}
	}

	public static void removeBlur(Pane pane) {

		for (Node node : pane.getChildren()) {
			node.setEffect(null);
		}
	}

	public Image getBG_START() {
		return BG_START;
	}

	public void setBG_START(Image bG_START) {
		BG_START = bG_START;
	}

	public Image getBG_GAME() {
		return BG_GAME;
	}

	public void setBG_GAME(Image bG_GAME) {
		BG_GAME = bG_GAME;
	}

	public Image getBG_SIGN() {
		return BG_SIGN;
	}

	public void setBG_SIGN(Image bG_SIGN) {
		BG_SIGN = bG_SIGN;
	}

	public Image getDIAMOND_ONE() {
		return DIAMOND_ONE;
	}

	public void setDIAMOND_ONE(Image dIAMOND_ONE) {
		DIAMOND_ONE = dIAMOND_ONE;
	}

	public Image getDIAMOND_TWO() {
		return DIAMOND_TWO;
	}

	public void setDIAMOND_TWO(Image dIAMOND_TWO) {
		DIAMOND_TWO = dIAMOND_TWO;
	}

	public Image getDIAMOND_THREE() {
		return DIAMOND_THREE;
	}

	public void setDIAMOND_THREE(Image dIAMOND_THREE) {
		DIAMOND_THREE = dIAMOND_THREE;
	}

	public Image getDIAMOND_FOUR() {
		return DIAMOND_FOUR;
	}

	public void setDIAMOND_FOUR(Image dIAMOND_FOUR) {
		DIAMOND_FOUR = dIAMOND_FOUR;
	}

	public Image getDIAMOND_FIVE() {
		return DIAMOND_FIVE;
	}

	public void setDIAMOND_FIVE(Image dIAMOND_FIVE) {
		DIAMOND_FIVE = dIAMOND_FIVE;
	}

	public Image getDIAMOND_SIX() {
		return DIAMOND_SIX;
	}

	public void setDIAMOND_SIX(Image dIAMOND_SIX) {
		DIAMOND_SIX = dIAMOND_SIX;
	}

	public Image getBUTTON_GAME_START() {
		return BUTTON_GAME_START;
	}

	public void setBUTTON_GAME_START(Image bUTTON_GAME_START) {
		BUTTON_GAME_START = bUTTON_GAME_START;
	}

	public Image getBUTTON_GAME_RESTART() {
		return BUTTON_GAME_RESTART;
	}

	public void setBUTTON_GAME_RESTART(Image bUTTON_GAME_RESTART) {
		BUTTON_GAME_RESTART = bUTTON_GAME_RESTART;
	}

	public Image getBUTTON_GAME_EXIT() {
		return BUTTON_GAME_EXIT;
	}

	public void setBUTTON_GAME_EXIT(Image bUTTON_GAME_EXIT) {
		BUTTON_GAME_EXIT = bUTTON_GAME_EXIT;
	}

	public Image getBUTTON_SIGN_UP() {
		return BUTTON_SIGN_UP;
	}

	public void setBUTTON_SIGN_UP(Image bUTTON_SIGN_UP) {
		BUTTON_SIGN_UP = bUTTON_SIGN_UP;
	}

	public Image getBUTTON_SIGN_IN() {
		return BUTTON_SIGN_IN;
	}

	public void setBUTTON_SIGN_IN(Image bUTTON_SIGN_IN) {
		BUTTON_SIGN_IN = bUTTON_SIGN_IN;
	}

	public Image getBUTTON_BACK() {
		return BUTTON_BACK;
	}

	public void setBUTTON_BACK(Image bUTTON_BACK) {
		BUTTON_BACK = bUTTON_BACK;
	}

	public Image getBUTTON_PROPS() {
		return BUTTON_SHOP;
	}

	public void setBUTTON_PROPS(Image bUTTON_PROPS) {
		BUTTON_SHOP = bUTTON_PROPS;
	}

	public Image getBUTTON_TIME_MODE() {
		return BUTTON_TIME_MODE;
	}

	public void setBUTTON_TIME_MODE(Image bUTTON_TIME_MODE) {
		BUTTON_TIME_MODE = bUTTON_TIME_MODE;
	}

	public Image getBUTTON_INDEFINITE_MODE() {
		return BUTTON_INDEFINITE_MODE;
	}

	public void setBUTTON_INDEFINITE_MODE(Image bUTTON_INDEFINITE_MODE) {
		BUTTON_INDEFINITE_MODE = bUTTON_INDEFINITE_MODE;
	}

	public Image getBUTTON_STEP_MODE() {
		return BUTTON_STEP_MODE;
	}

	public void setBUTTON_STEP_MODE(Image bUTTON_STEP_MODE) {
		BUTTON_STEP_MODE = bUTTON_STEP_MODE;
	}

	public Image getFRAME_STEP() {
		return FRAME_STEP;
	}

	public void setFRAME_STEP(Image fRAME_STEP) {
		FRAME_STEP = fRAME_STEP;
	}

	public Image getFRAME_SIGN_IN() {
		return FRAME_SIGN_IN;
	}

	public void setFRAME_SIGN_IN(Image fRAME_SIGN_IN) {
		FRAME_SIGN_IN = fRAME_SIGN_IN;
	}

	public Image getFRAME_MONEY() {
		return FRAME_MONEY;
	}

	public void setFRAME_MONEY(Image fRAME_MONEY) {
		FRAME_MONEY = fRAME_MONEY;
	}

	public Image getFRAME_TIME() {
		return FRAME_TIME;
	}

	public void setFRAME_TIME(Image fRAME_TIME) {
		FRAME_TIME = fRAME_TIME;
	}

	public Image getFRAME_GAME() {
		return FRAME_GAME;
	}

	public void setFRAME_GAME(Image fRAME_GAME) {
		FRAME_GAME = fRAME_GAME;
	}

	public Image getFRAME_HIGHEST_SCORE() {
		return FRAME_HIGHEST_SCORE;
	}

	public void setFRAME_HIGHEST_SCORE(Image fRAME_HIGHEST_SCORE) {
		FRAME_HIGHEST_SCORE = fRAME_HIGHEST_SCORE;
	}

	public Image getBUTTON_CONFIG() {
		return BUTTON_CONFIG;
	}

	public void setBUTTON_CONFIG(Image bUTTON_CONFIG) {
		BUTTON_CONFIG = bUTTON_CONFIG;
	}

	public Image getBUTTON_SHOP() {
		return BUTTON_SHOP;
	}

	public void setBUTTON_SHOP(Image bUTTON_SHOP) {
		BUTTON_SHOP = bUTTON_SHOP;
	}

	public Image getBUTTON_KEJIN() {
		return BUTTON_KEJIN;
	}

	public void setBUTTON_KEJIN(Image bUTTON_KEJIN) {
		BUTTON_KEJIN = bUTTON_KEJIN;
	}

	public Image getBUTTON_HEIGHEST_SCORE() {
		return BUTTON_HEIGHEST_SCORE;
	}

	public void setBUTTON_HEIGHEST_SCORE(Image bUTTON_HEIGHEST_SCORE) {
		BUTTON_HEIGHEST_SCORE = bUTTON_HEIGHEST_SCORE;
	}

	public Image getBUTTON_LOG_OUT() {
		return BUTTON_LOG_OUT;
	}

	public void setBUTTON_LOG_OUT(Image bUTTON_LOG_OUT) {
		BUTTON_LOG_OUT = bUTTON_LOG_OUT;
	}

	public Image getINIT_BUTTON_SIGN_IN() {
		return INIT_BUTTON_SIGN_IN;
	}

	public void setINIT_BUTTON_SIGN_IN(Image iNIT_BUTTON_SIGN_IN) {
		INIT_BUTTON_SIGN_IN = iNIT_BUTTON_SIGN_IN;
	}

	public Image getINIT_BUTTON_SIGN_UP() {
		return INIT_BUTTON_SIGN_UP;
	}

	public void setINIT_BUTTON_SIGN_UP(Image iNIT_BUTTON_SIGH_UP) {
		INIT_BUTTON_SIGN_UP = iNIT_BUTTON_SIGH_UP;
	}

	public Image getINIT_BUTTON_QUIT() {
		return INIT_BUTTON_QUIT;
	}

	public void setINIT_BUTTON_QUIT(Image iNIT_BUTTON_QUIT) {
		INIT_BUTTON_QUIT = iNIT_BUTTON_QUIT;
	}

	public Image getINIT_BUTTON_TRY() {
		return INIT_BUTTON_TRY;
	}

	public void setINIT_BUTTON_TRY(Image iNIT_BUTTON_TRY) {
		INIT_BUTTON_TRY = iNIT_BUTTON_TRY;
	}
	
	public Image getICON_INDEFINITE() {
		return ICON_INDEFINITE;
	}

	public void setICON_INDEFINITE(Image iCON_INDEFINITE) {
		ICON_INDEFINITE = iCON_INDEFINITE;
	}

	public abstract Scene getInitialScene();

	public abstract Scene getLoginScene();

	public abstract Scene getRegisterScene();

	public abstract Scene getSettingScene();

	public abstract Scene getMaxMarkScene();

	public abstract Scene getMarketScene();

	public abstract Scene getGameScene();

	public abstract Scene getSynScene();

	public Image getBG_HEIGHEST_SCORE() {
		return BG_HEIGHEST_SCORE;
	}

	public void setBG_HEIGHEST_SCORE(Image bG_HEIGHEST_SCORE) {
		BG_HEIGHEST_SCORE = bG_HEIGHEST_SCORE;
	}

	public Image[] getNums() {
		return nums;
	}

	public void setNums(Image[] nums) {
		this.nums = nums;
	}

	public Image getBG_SETTING_PANE() {
		return BG_SETTING_PANE;
	}

	public void setBG_SETTING_PANE(Image bG_SETTING_PANE) {
		BG_SETTING_PANE = bG_SETTING_PANE;
	}

	public Image getBG_SHOP() {
		return BG_SHOP;
	}

	public void setBG_SHOP(Image bG_SHOP) {
		BG_SHOP = bG_SHOP;
	}

}

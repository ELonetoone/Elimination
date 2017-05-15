package el.onetoone.back;

import el.onetoone.ui.MagicGirlTheme;
import el.onetoone.ui.Main;
import el.onetoone.ui.Theme;

public class Config {

	public static int width = 8;
	public static int height = 10;

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int START_TIME = 60;
	public static final int START_STEP = 2;

	public static Theme theme = new MagicGirlTheme();

	public static Theme getTheme() {
		return Config.theme;
	}

	public static void setTheme(Theme theme) {
		Config.theme = theme;
	}

	public static Main main;

	public static void setMain(Main main) {
		Config.main = main;
	}

	public static Main getMain() {
		return Config.main;
	}

}

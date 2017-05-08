package el.onetoone.back;

import el.onetoone.ui.MagicGirlTheme;
import el.onetoone.ui.Theme;

public class Config {

	public static int width = 8;
	public static int height = 10;
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	public static Theme theme = new MagicGirlTheme();
	
	public static Theme getTheme() {
		return Config.theme;
	}
	
	public static void setTheme(Theme theme) {
		Config.theme = theme;
	}
	
}

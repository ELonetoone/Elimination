package el.onetoone.back;


import el.onetoone.ui.MagicGirlTheme;
import el.onetoone.ui.Main;
import el.onetoone.ui.Theme;
import el.onetoone.ui.naruto.NarutoTheme;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class Config {

	public static int width = 8;
	public static int height = 10;

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	//游戏开始的步数，时间
	public static final int START_TIME = 60;
	public static final int START_STEP = 2;
	
	//音效
	public static final AudioClip SOUND_CLICK = new AudioClip(Main.class.getResource("sound/click.wav").toExternalForm());
	public static final AudioClip SOUND_WARNING = new AudioClip(Main.class.getResource("sound/warning.wav").toExternalForm());
	public static final AudioClip SOUND_BOOM = new AudioClip(Main.class.getResource("sound/boom.wav").toExternalForm());
	public static final AudioClip SOUND_BUY_THING = new AudioClip(Main.class.getResource("sound/buything.wav").toExternalForm());
	public static final AudioClip SOUND_ITEMS = new AudioClip(Main.class.getResource("sound/items.wav").toExternalForm());
	public static final AudioClip SOUND_MOVE = new AudioClip(Main.class.getResource("sound/move.wav").toExternalForm());
	public static final AudioClip SOUND_EL = new AudioClip(Main.class.getResource("sound/el.wav").toExternalForm());
	
	//背景音乐
	public static final Media BGM_MAGIC = new Media(Config.class.getResource("/image/bgm_magic.mp3").toExternalForm());
	public static final Media BGM_NARUTO = new Media(Config.class.getResource("/image/naruto/bgm_naruto.mp3").toExternalForm());
	public static final Media BGM_MAGIC_GAMING = new Media(Config.class.getResource("/image/bgm_magic_gaming.mp3").toExternalForm());
	public static final Media BGM_NARUTO_GAMING = new Media(Config.class.getResource("/image/naruto/bgm_naruto_gaming.mp3").toExternalForm());

	public static Theme theme = new NarutoTheme();
	

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

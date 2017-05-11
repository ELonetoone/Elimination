package el.onetoone.ui.magicgirl;

import el.onetoone.back.Config;
import el.onetoone.ui.InitialView;
import el.onetoone.ui.SystemButton;
import el.onetoone.ui.SyntheticModel.ModeButton;
import el.onetoone.ui.shop.MarketPanel;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MagicSyntheticPanel extends Pane{

	private ModeButton unlimitedMode;
	private ModeButton timeLimitedMode;
	private ModeButton stepLimitedMode;
	
	private Button marketAndBackButton;
	private Button topUpButton;
	private Button settingButton;
	private Button logOutButton;
	private Button maxMarkButton;
	private SystemButton exitButton;
	
	public static final String UNLIMITE = "ASABCK";
	public static final String TIMELIMITED = "AFKLJCZX";
	public static final String STEPLIMITED = "DASCBXZ,ME";
	private String mode = null;
	
	private Pane root;
	private InitialView initialView;
	private MarketPanel marketPanel;
	private ImageView backgroundImg;
	
	private HBox modeButtonBox;
	
	public MagicSyntheticPanel() {
		// TODO Auto-generated constructor stub
		
		initBackgroud();
	}

	private void initBackgroud() {
		// TODO Auto-generated method stub

		backgroundImg = new ImageView(Config.getTheme().getBG_GAME());
		backgroundImg.setFitHeight(Config.SCREEN_HEIGHT);
		backgroundImg.setFitWidth(Config.SCREEN_WIDTH);
	}
}

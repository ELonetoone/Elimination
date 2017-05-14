package el.onetoone.ui;

import java.awt.Desktop;
import java.net.URI;

import el.onetoone.back.Config;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;

public abstract class MarketScene extends Scene {

	public MarketScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public MarketScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public MarketScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public MarketScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public MarketScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public MarketScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}
	
	private ImageView backgroundImg;
	protected Button kejinBtn;
	protected TilePane shopTile;
	protected TilePane myPropsTile;
	protected ScrollPane shopScroll;
	protected ScrollPane myPropsScroll;
	
	public abstract Scene getMarketScene();
	public abstract void createButton();
	
	protected void createBackground() {
		
		backgroundImg = new ImageView(Config.getTheme().getBG_SHOP());
		((Pane)getRoot()).getChildren().add(backgroundImg);
	}
	
	protected void addButtonListener() {
		kejinBtn.setOnAction(e -> {
			String url = "http://115.159.29.36/wp-content/uploads/2017/05/9A450F09BC437A429703741650C1AE7911.jpg";
			URI uri = URI.create(url);
			try {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
					desktop.browse(uri);
				}
			} catch (NullPointerException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		});
	}
	
	protected void createContentPane() {
		shopTile = new TilePane();
		shopTile.setPrefColumns(3);
		shopTile.setVgap(20);
		shopTile.setHgap(50);
		
		shopScroll = new ScrollPane(shopTile);
		shopScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		shopScroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		shopScroll.setLayoutX(250);
		shopScroll.setLayoutY(150);
		
		myPropsTile = new TilePane();
		myPropsTile.setPrefColumns(3);
		myPropsTile.setVgap(20);
		myPropsTile.setHgap(50);
		
		myPropsScroll = new ScrollPane(myPropsTile);
		myPropsScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		myPropsScroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		myPropsScroll.setLayoutX(350);
		myPropsScroll.setLayoutY(450);
		
		((Pane)getRoot()).getChildren().addAll(shopScroll, myPropsScroll);
	}
}

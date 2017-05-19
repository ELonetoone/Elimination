package el.onetoone.ui;

import java.awt.Desktop;
import java.net.URI;
import java.util.Map;
import java.util.Random;

import el.onetoone.back.Config;
import el.onetoone.back.ItemList;
import el.onetoone.back.UserBox;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

	private ImageView backgroundImg, shopPanelImg;
	private Map<String, Integer> itemMap = UserBox.getUser().getMyItems();
	protected PropsButton hammer, boom, genNewMap, oneStep, threeSteps, fiveSteps, oneSecond, threeSeconds, fiveSeconds;
	protected PropsButton myHammer, myBoom, myGenNewMap, myOneStep, myThreeSteps, myFiveSteps, myOneSecond,
			myThreeSeconds, myFiveSeconds;
	protected Button kejinBtn;
	protected TilePane shopTile;
	protected TilePane myPropsTile;
	protected ScrollPane shopScroll;
	protected ScrollPane myPropsScroll;
	private Label money;

	public abstract Scene getMarketScene();

	public abstract void createButton();

	protected void createMoney() {
		
		money = new Label();
		money.setLayoutX(400);
		money.setLayoutY(Config.SCREEN_HEIGHT - 93);
		money.setText(UserBox.getUser().getCoinCount() + "");
		((Pane)getRoot()).getChildren().add(money);
	}
	
	protected void updateMoney() {
		money.setText(UserBox.getUser().getCoinCount() + "");
	}
	
	protected void createBackground() {

		backgroundImg = new ImageView(Config.getTheme().getBG_SHOP());
		shopPanelImg = new ImageView(Theme.BG_SHOP_PANEL);
		shopPanelImg.setLayoutX(70);
		shopPanelImg.setLayoutY(50);
		((Pane) getRoot()).getChildren().addAll(backgroundImg, shopPanelImg);
	}

	protected void createProps() {

		hammer = new PropsButton(Theme.PROPS_HAMMER, Theme.TIP_HAMMER, ItemList.HAMMER);
		boom = new PropsButton(Theme.PROPS_BOOM, Theme.TIP_BOOM, ItemList.BOOM);
		genNewMap = new PropsButton(Theme.PROPS_NEW_MAP, Theme.TIP_NEW_MAP, ItemList.NEWMAP);
		oneSecond = new PropsButton(Theme.PROPS_PLUS_ONE_S, Theme.TIP_PLUS_ONE_S, ItemList.PLUSONESECOND);
		threeSeconds = new PropsButton(Theme.PROPS_PLUS_THREE_S, Theme.TIP_PLUS_THREE_S, ItemList.PLUSTHREESECONDS);
		fiveSeconds = new PropsButton(Theme.PROPS_PLUS_FIVE_S, Theme.TIP_PLUS_FIVE_S, ItemList.PLUSFIVESECONDS);
		oneStep = new PropsButton(Theme.PROPS_ONE_STEP, Theme.TIP_ONE_STEP, ItemList.PLUSONESTEP);
		threeSteps = new PropsButton(Theme.PROPS_THREE_STEP, Theme.TIP_THREE_STEP, ItemList.PLUSTHREESTEPS);
		fiveSteps = new PropsButton(Theme.PROPS_FIVE_STEP, Theme.TIP_FIVE_STEP, ItemList.PLUSFIVESTEPS);

		shopTile.getChildren().addAll(hammer, boom, genNewMap, oneSecond, threeSeconds, fiveSeconds, oneStep,
				threeSteps, fiveSteps);
		for (Node node : shopTile.getChildren()) {

			((PropsButton) node).addButtonListener();
		}

		myHammer = new PropsButton(Theme.PROPS_HAMMER, Theme.TIP_HAMMER, ItemList.HAMMER);
		myBoom = new PropsButton(Theme.PROPS_BOOM, Theme.TIP_BOOM, ItemList.BOOM);
		myGenNewMap = new PropsButton(Theme.PROPS_NEW_MAP, Theme.TIP_NEW_MAP, ItemList.NEWMAP);
		myOneSecond = new PropsButton(Theme.PROPS_PLUS_ONE_S, Theme.TIP_PLUS_ONE_S, ItemList.PLUSONESECOND);
		myThreeSeconds = new PropsButton(Theme.PROPS_PLUS_THREE_S, Theme.TIP_PLUS_THREE_S, ItemList.PLUSTHREESECONDS);
		myFiveSeconds = new PropsButton(Theme.PROPS_PLUS_FIVE_S, Theme.TIP_PLUS_FIVE_S, ItemList.PLUSFIVESECONDS);
		myOneStep = new PropsButton(Theme.PROPS_ONE_STEP, Theme.TIP_ONE_STEP, ItemList.PLUSONESTEP);
		myThreeSteps = new PropsButton(Theme.PROPS_THREE_STEP, Theme.TIP_THREE_STEP, ItemList.PLUSTHREESTEPS);
		myFiveSteps = new PropsButton(Theme.PROPS_FIVE_STEP, Theme.TIP_FIVE_STEP, ItemList.PLUSFIVESTEPS);

		myPropsTile.getChildren().addAll(myHammer, myBoom, myGenNewMap, myOneSecond, myThreeSeconds, myFiveSeconds,
				myOneStep, myThreeSteps, myFiveSteps);
		for (Node node : myPropsTile.getChildren()) {

			((PropsButton) node).setLabel();
		}
	}

	protected void createKejinButton() {

		ImageView kejinImg = new ImageView(Theme.SHOP_KEJIN_BUTTON);
		kejinImg.setFitHeight(60);
		kejinImg.setPreserveRatio(true);

		kejinBtn = new Button();
		kejinBtn.setGraphic(kejinImg);
		kejinBtn.setLayoutX(Config.SCREEN_WIDTH - 400);
		kejinBtn.setLayoutY(Config.SCREEN_HEIGHT - 130);

		kejinBtn.setOnAction(e -> {
			Config.SOUND_BUY_THING.play();
			String url1 = "http://115.159.29.36/wp-content/uploads/2017/05/E860D85BEE735FCD143E0E97F7ADDDD2.jpg";
			String url2 = "http://115.159.29.36/wp-content/uploads/2017/05/9A450F09BC437A429703741650C1AE7911.jpg";
			String url3 = "http://115.159.29.36/wp-content/uploads/2017/05/06645FECD842644F3D60B1D2CE7EAE80.jpg";
			String url4 = "http://115.159.29.36/wp-content/uploads/2017/05/82A46EF09286C3AFA15947CE58C964F0.jpg";
			String url = null;
			int people = new Random().nextInt(4);
			switch (people) {
			case 0:
				url = url1;
				break;
			case 1:
				url = url2;
				break;
			case 2:
				url = url3;
				break;
			case 3:
				url = url4;
				break;
			default:
				url = url2;
			}
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

		((Pane) getRoot()).getChildren().add(kejinBtn);
	}

	protected void createContentPane() {
		shopTile = new TilePane();
		shopTile.setPrefColumns(4);
		shopTile.setVgap(20);
		shopTile.setHgap(100);
		// shopTile.setStyle("-fx-background-color: transparent;");

		shopScroll = new ScrollPane(shopTile);
		shopScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		shopScroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		// shopScroll.setStyle("-fx-background-color: transparent");
		shopScroll.setMaxHeight(100);
		shopScroll.setMinWidth(700);

		shopScroll.setLayoutX(250);
		shopScroll.setLayoutY(150);

		myPropsTile = new TilePane();
		myPropsTile.setPrefColumns(4);
		myPropsTile.setVgap(20);
		myPropsTile.setHgap(100);
		myPropsTile.setId("propstile");

		myPropsScroll = new ScrollPane(myPropsTile);
		myPropsScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		myPropsScroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		myPropsScroll.setMaxHeight(120);
		myPropsScroll.setMinWidth(700);

		myPropsScroll.setLayoutX(350);
		myPropsScroll.setLayoutY(450);

		((Pane) getRoot()).getChildren().addAll(shopScroll, myPropsScroll);
	}

	public class PropsButton extends StackPane {

		private String item;
		private Button propsBtn;
		private int price;
		private Label quantityLabel;

		public PropsButton(Image image, Image tip, String item) {
			// TODO Auto-generated constructor stub
			switch (item) {
			case ItemList.HAMMER:
				price = 5000;
				break;

			case ItemList.BOOM:
				price = 50000;
				break;

			case ItemList.NEWMAP:
				price = 25000;
				break;

			case ItemList.PLUSTHREESECONDS:
				price = 25000;
				break;

			case ItemList.PLUSFIVESECONDS:
				price = 50000;
				break;

			case ItemList.PLUSONESTEP:
				price = 15000;
				break;

			case ItemList.PLUSTHREESTEPS:
				price = 30000;
				break;

			case ItemList.PLUSFIVESTEPS:
				price = 50000;
				break;

			case ItemList.PLUSONESECOND:
				price = 99999;
				break;
			}

			ImageView buttonImg = new ImageView(image);
			buttonImg.setFitWidth(100);
			buttonImg.setPreserveRatio(true);

			propsBtn = new Button();
			propsBtn.setGraphic(buttonImg);
			setStyle("-fx-backgroud-color: transparent");

			ImageView imageView = new ImageView(tip);
			imageView.setFitWidth(300);
			imageView.setPreserveRatio(true);
			Tooltip tooltip = new Tooltip();
			tooltip.setGraphic(imageView);
			propsBtn.setTooltip(tooltip);

			getChildren().add(propsBtn);
			this.item = item;
		}

		public void setLabel() {
			quantityLabel = new Label(itemMap.get(item).toString());
			quantityLabel.setPrefSize(50, 30);
			StackPane.setAlignment(quantityLabel, Pos.BOTTOM_RIGHT);
			getChildren().add(quantityLabel);
		}
		
		private void updateLabel() {
			quantityLabel.setText(itemMap.get(item).toString());
		}

		public void addButtonListener() {
			// TODO
			propsBtn.setOnMousePressed(e-> {
				System.out.println("pressed");
			});
			propsBtn.setOnMouseClicked(e -> {
				Config.SOUND_BUY_THING.play();
				System.out.println("clicked");
				if ((UserBox.getUser().getCoinCount() - price) >= 0 && e.getClickCount() == 2) {
					UserBox.getUser().setCoinCount(UserBox.getUser().getCoinCount() - price);
					itemMap.put(item, itemMap.get(item) + 1);
					for (Node node : myPropsTile.getChildren()) {

						((PropsButton) node).updateLabel();
					}
					updateMoney();
					UserBox.getUser().updateUserInfo();
				}
			});
		}
	}
}

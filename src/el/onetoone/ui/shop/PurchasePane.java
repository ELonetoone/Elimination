package el.onetoone.ui.shop;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Pane;

public class PurchasePane extends Pane{

	Button purchaseBtn;
	Label itemDescription;
	Label itemName;
	ScrollPane infoScroll;
	
	public PurchasePane() {
		
		setStyle("-fx-background-color: #ffffff" + ";-fx-background-radius: 10" + ";-fx-border-radius: 10"
				+ ";-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1)");
		setPrefSize(600, 200);
		createNode();
		addNode();
	}
	
	private void addNode() {

		getChildren().addAll(purchaseBtn, itemName, infoScroll);
	}

	private void createNode() {
		
		purchaseBtn = new Button("购买");
		purchaseBtn.setLayoutX(450);
		purchaseBtn.setLayoutY(160);
		
		itemName = new Label();
		itemName.setLayoutX(30);
		itemName.setLayoutY(30);
		
		itemDescription = new Label();
		itemDescription.setLayoutX(15);
		itemDescription.setLayoutY(15);
		
		infoScroll = new ScrollPane(itemDescription);
		infoScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		infoScroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		infoScroll.setPrefSize(400, 80);
		infoScroll.setLayoutX(40);
		infoScroll.setLayoutY(70);
		infoScroll.getStyleClass().add("edge-to-edge"); 
		infoScroll.setStyle("-fx-background: transparent;");
	}

	public void updateInfo(String name, String description) {
		
		itemName.setText(name);
		itemDescription.setText(description);
	}
}

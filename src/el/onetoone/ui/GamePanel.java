package el.onetoone.ui;

import java.util.ArrayList;

import el.onetoone.back.BaseDiamondGrid;
import el.onetoone.back.Config;
import el.onetoone.back.Diamond;
import el.onetoone.back.Point;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class GamePanel {

	private static final double CIRCLEDISTANCEE = 30;
	private static final double RADIUS = 10;
	
	ArrayList<DiamondCircle> diamondCircles;
	Diamond[][] diamondMap;
	BaseDiamondGrid diamondGrid;
	DiamondCircle choosedCircle;
	
	Group group;
	Scene scene;
	
	public GamePanel(Scene scene) {
		
		this.scene = scene;
		group = new Group();
		scene.setRoot(group);
		
		diamondGrid = new BaseDiamondGrid(Config.height, Config.width);
		diamondMap = diamondGrid.getDiamondMap();
		
		
		for (int i = 2; i < diamondMap.length - 2; i++) {
			for (int j = 2; j < diamondMap[i].length - 2; j++) {
				
				makeDiamondCircle(new Point(i, j));
			}
		}
	}
	
	private void makeDiamondCircle(Point point) {
		
		DiamondCircle diamondCircle = new DiamondCircle();
		diamondCircle.setPoint(point);
		diamondCircle.setRadius(RADIUS);
		diamondCircle.setCenterY(RADIUS + point.getX() * CIRCLEDISTANCEE);
		diamondCircle.setCenterX(RADIUS + point.getY() * CIRCLEDISTANCEE);
		
		Color color;
		switch (diamondMap[point.getX()][point.getY()].getColor().ordinal()) {
		case 0:
			
			color = Color.GREEN;
			break;

		case 1:
			
			color = Color.RED;
			break;
		case 2:
			
			color = Color.BLUE;
			break;
			
		case 3:
			
			color = Color.YELLOW;
			break;
			
		case 4:
			
			color = Color.ORANGE;
			break;
			
		case 5:
			
			color = Color.PURPLE;
			break;
			
	    default:
	    	color = Color.GRAY;
		}
		
		diamondCircle.setFill(color);
//		setAction(diamondCircle);
		
		group.getChildren().add(diamondCircle);
		
		testPrint();
	}
	
//	private void setAction(DiamondCircle currentCircle) {
//
//		
//		currentCircle.setOnMouseEntered(e -> {
//
//			currentCircle.setStroke(Color.BLACK);
//			currentCircle.setStrokeWidth(1);
//		});
//		
//		currentCircle.setOnMouseExited(e -> {
//			
//			currentCircle.setStroke(null);
//		});
//		
//		currentCircle.setOnMousePressed(e -> {
//			
//			if (choosedCircle == null) {
//				choosedCircle = currentCircle;
//			} else if ((choosedCircle.getPoint().getX() + 1 == currentCircle.getPoint().getX()
//					|| choosedCircle.getPoint().getX() - 1 ==  currentCircle.getPoint().getX())
//					&& choosedCircle.getPoint().getY() == currentCircle.getPoint().getY()) {
//				//上下交换
//				boolean exchangeValid = diamondGrid.executeExchangeElimination(currentCircle.getPoint(), choosedCircle.getPoint());
//				
//				TranslateTransition transition1 = choosedCircle.verticalTransition((currentCircle.getPoint().getX()
//						- choosedCircle.getPoint().getX()) * CIRCLEDISTANCEE);
//				TranslateTransition transition2 = currentCircle.verticalTransition((choosedCircle.getPoint().getX()
//						- currentCircle.getPoint().getX()) * CIRCLEDISTANCEE);
//				
//				
//				if (exchangeValid) {
//					exchangePoint(currentCircle);
//
//				} else {
//					transition1.setCycleCount(2);
//					transition2.setCycleCount(2);
//					transition1.setAutoReverse(true);
//					transition2.setAutoReverse(true);
//				}
//				
//				ParallelTransition parallelTransition = new ParallelTransition(transition1, transition2);
//				parallelTransition.setOnFinished(e -> {
//					
//				});
//				paintNullToNoColor(sequentialTransition);
//				sequentialTransition.play();
//				sequentialTransition.setOnFinished(event -> {
//					
//					sequentialTransition.getChildren().clear();
//					dropDiamonds();
////					repaintTheBoard();
//				});
////				testPrint();
////				printPoint();
////				printCenterLocation();
//				
//				
//				choosedCircle = null;
//			} else if ((choosedCircle.getPoint().getY() + 1 == currentCircle.getPoint().getY()
//					|| choosedCircle.getPoint().getY() - 1 == currentCircle.getPoint().getY())
//					&& choosedCircle.getPoint().getX() == currentCircle.getPoint().getX()) {
//				//左右交换
//				boolean exchangeValid = diamondGrid.executeExchangeElimination(currentCircle.getPoint(), choosedCircle.getPoint());
//				
//				TranslateTransition transition1 = currentCircle.hrizontalTransition((choosedCircle.getPoint().getY()
//						- currentCircle.getPoint().getY()) * CIRCLEDISTANCEE);
//				TranslateTransition transition2 = choosedCircle.hrizontalTransition((currentCircle.getPoint().getY()
//						- choosedCircle.getPoint().getY()) * CIRCLEDISTANCEE);
//				
//				if (exchangeValid) {
//					exchangePoint(currentCircle);
//
//				} else {
//					transition1.setCycleCount(2);
//					transition2.setCycleCount(2);
//					transition1.setAutoReverse(true);
//					transition2.setAutoReverse(true);
//				}
//				
//				ParallelTransition parallelTransition = new ParallelTransition(transition1, transition2);
//				sequentialTransition.getChildren().add(parallelTransition);
//				paintNullToNoColor(sequentialTransition);
//				sequentialTransition.play();
//				sequentialTransition.setOnFinished(event -> {
//					
//					sequentialTransition.getChildren().clear();
//					dropDiamonds();
////					repaintTheBoard();
//				});
//				
//				
//				
//				choosedCircle = null;
//			} else {
//				choosedCircle = currentCircle;
//			}
//		});
//	}

	private void exchangePoint(DiamondCircle currentCircle) {
		
		Point temp = currentCircle.getPoint();
		currentCircle.setPoint(choosedCircle.getPoint());
		choosedCircle.setPoint(temp);
	}
	
//	private void paintNullToNoColor(SequentialTransition sequentialTransition) {
//		// TODO Auto-generated method stub
//		ParallelTransition parallelTransition = new ParallelTransition();
//		for (int i = 0; i < diamondMap.length; i++) {
//			for (int j = 0; j < diamondMap[i].length; j++) {
//				
//				if (diamondMap[i][j] == null) {
//					parallelTransition.getChildren().add(diamondCircles[i - 2][j - 2].fade());
//				}
//			}
//		}
//		sequentialTransition.getChildren().add(parallelTransition);
//	}

	public void testPrint() {
		for (int i = 0; i < diamondMap.length; i++) {
			System.out.printf("%-3d ", i);
			for (int j = 0; j < diamondMap[i].length; j++) {
				if (diamondMap[i][j] != null) {
					System.out.printf("%-6s  ", diamondMap[i][j].getColor().toString());
				} else {
					System.out.printf("%-6s  ", diamondMap[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}

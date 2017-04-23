package el.onetoone.ui;



import java.util.Arrays;

import el.onetoone.back.BaseDiamondGrid;
import el.onetoone.back.Config;
import el.onetoone.back.Diamond;
import el.onetoone.back.Point;
import javafx.animation.Animation.Status;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class GameScreen {

	DiamondCircle[][] diamondCircles = new DiamondCircle[Config.height][Config.width];
	BaseDiamondGrid diamondGrid;
	DiamondCircle choosedCircle;
	Diamond[][] diamonds;
	
	Scene scene;
	Group group;
	BorderPane borderPane;
	
	double radius = 10;
	double circleDistance;
	double circleDistanceY;
	private SequentialTransition sequentialTransition;
	
	/**
	 * 执行初始化,并设置游戏屏幕的scene
	 */
	public GameScreen(Scene scene) {
		
		this.scene = scene;
		init();
	}
	
	/**
	 * 初始化
	 */
	private void init() {
		
		
		diamondGrid = new BaseDiamondGrid(Config.height, Config.width);
		diamonds = diamondGrid.getDiamondMap();
		borderPane = new BorderPane();
		group = new Group();
		sequentialTransition = new SequentialTransition();
		
		paintDiamonds();
		
		
		borderPane.setCenter(group);
		scene.setRoot(borderPane);
	}
	
	private void paintDiamonds() {
		//计算圆心坐标,注意,这里的v是指列，h指行
				double centerV = scene.getHeight() / (Config.height * 2);
				double centerH = scene.getWidth() / (Config.width * 2);
				circleDistance = 2 * centerV;
				Color color;
				for (int i = 0; i < diamondCircles.length; i++) {
					
					for (int j = 0; j < diamondCircles[i].length; j++) {
						
						//得到颜色，因为我们自己定义的Color与fx的Color重名，于是我才用了根据次序来获得颜色的方式
						switch (diamonds[i + 2][j + 2].getColor().ordinal()) {
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
						
						
						diamondCircles[i][j] = new DiamondCircle();
						diamondCircles[i][j].setPoint(new Point(i + 2, j + 2));
						diamondCircles[i][j].setFill(color);
						diamondCircles[i][j].setOpacity(1);
						diamondCircles[i][j].setRadius(radius);
						diamondCircles[i][j].setCenterX(centerH + circleDistance * j);
						diamondCircles[i][j].setCenterY(centerV + circleDistance * i);
						group.getChildren().add(diamondCircles[i][j]);
						
						DiamondCircle currentCircle = diamondCircles[i][j];
						
						currentCircle.setOnMouseEntered(e -> {

							currentCircle.setStroke(Color.BLACK);
							currentCircle.setStrokeWidth(1);
						});
						
						currentCircle.setOnMouseExited(e -> {
							
							currentCircle.setStroke(null);
						});
						
						currentCircle.setOnMousePressed(e -> {
							
							if (choosedCircle == null) {
								choosedCircle = currentCircle;
							} else if ((choosedCircle.getPoint().getX() + 1 == currentCircle.getPoint().getX()
									|| choosedCircle.getPoint().getX() - 1 ==  currentCircle.getPoint().getX())
									&& choosedCircle.getPoint().getY() == currentCircle.getPoint().getY()) {
								//上下交换
								boolean exchangeValid = diamondGrid.executeExchangeElimination(currentCircle.getPoint(), choosedCircle.getPoint());
								
								TranslateTransition transition1 = choosedCircle.verticalTransition((currentCircle.getPoint().getX()
										- choosedCircle.getPoint().getX()) * circleDistance);
								TranslateTransition transition2 = currentCircle.verticalTransition((choosedCircle.getPoint().getX()
										- currentCircle.getPoint().getX()) * circleDistance);
								
								
								if (exchangeValid) {
									exchangeDiamondCircle(currentCircle);
									exchangePoint(currentCircle);

								} else {
									transition1.setCycleCount(2);
									transition2.setCycleCount(2);
									transition1.setAutoReverse(true);
									transition2.setAutoReverse(true);
								}
								
								ParallelTransition parallelTransition = new ParallelTransition(transition1, transition2);
								sequentialTransition.getChildren().add(parallelTransition);
								paintNullToNoColor(sequentialTransition);
								sequentialTransition.play();
								sequentialTransition.setOnFinished(event -> {
									
									sequentialTransition.getChildren().clear();
									repaintTheBoard();
								});
//								testPrint();
//								printPoint();
//								printCenterLocation();
								
								
								choosedCircle = null;
							} else if ((choosedCircle.getPoint().getY() + 1 == currentCircle.getPoint().getY()
									|| choosedCircle.getPoint().getY() - 1 == currentCircle.getPoint().getY())
									&& choosedCircle.getPoint().getX() == currentCircle.getPoint().getX()) {
								//左右交换
								boolean exchangeValid = diamondGrid.executeExchangeElimination(currentCircle.getPoint(), choosedCircle.getPoint());
								
								TranslateTransition transition1 = currentCircle.hrizontalTransition((choosedCircle.getPoint().getY()
										- currentCircle.getPoint().getY()) * circleDistance);
								TranslateTransition transition2 = choosedCircle.hrizontalTransition((currentCircle.getPoint().getY()
										- choosedCircle.getPoint().getY()) * circleDistance);
								
								if (exchangeValid) {
									exchangeDiamondCircle(currentCircle);
									exchangePoint(currentCircle);

								} else {
									transition1.setCycleCount(2);
									transition2.setCycleCount(2);
									transition1.setAutoReverse(true);
									transition2.setAutoReverse(true);
								}
								
								ParallelTransition parallelTransition = new ParallelTransition(transition1, transition2);
								sequentialTransition.getChildren().add(parallelTransition);
								paintNullToNoColor(sequentialTransition);
								sequentialTransition.play();
								sequentialTransition.setOnFinished(event -> {
									
									sequentialTransition.getChildren().clear();
									repaintTheBoard();
								});
								
								
								
								choosedCircle = null;
							} else {
								choosedCircle = currentCircle;
							}
						});
					}
				}
	}

	/**
	 * 将被消除的宝石进行动画上的消除，同时会将保存宝石图形的数组里相应的宝石设置为null
	 */
	private void paintNullToNoColor(SequentialTransition sequentialTransition) {
		// TODO Auto-generated method stub
		ParallelTransition parallelTransition = new ParallelTransition();
		for (int i = 0; i < diamonds.length; i++) {
			for (int j = 0; j < diamonds[i].length; j++) {
				
				if (diamonds[i][j] == null) {
					parallelTransition.getChildren().add(diamondCircles[i - 2][j - 2].fade());
				}
			}
		}
		sequentialTransition.getChildren().add(parallelTransition);
	}
	
	/**
	 * 将选中的两个宝石在diamondCircles中交换位置，同时也会交换他们的Point
	 * @param currentCircle
	 * @param choosedCircle
	 */
	private void exchangeDiamondCircle(DiamondCircle currentCircle) {
		
		DiamondCircle tempCircle = currentCircle;
		diamondCircles[currentCircle.getPoint().getX() - 2][currentCircle.getPoint().getY() - 2] = choosedCircle;
		diamondCircles[choosedCircle.getPoint().getX() - 2][choosedCircle.getPoint().getY() - 2] = tempCircle;
	}
	
	private void exchangePoint(DiamondCircle currentCircle) {
		
		Point temp = currentCircle.getPoint();
		currentCircle.setPoint(choosedCircle.getPoint());
		choosedCircle.setPoint(temp);
	}
	
	/**
	 * 调试代码
	 */
	public void testPrint() {
		for (int i = 0; i < diamondCircles.length; i++) {
			System.out.printf("%-3d ", i);
			for (int j = 0; j < diamondCircles[i].length; j++) {
				if (diamondCircles[i][j] != null) {
					System.out.printf("%-6s  ", diamondCircles[i][j].getFill());
				} else {
					System.out.printf("%-6s  ", diamondCircles[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * 调试代码
	 */
	private void printPoint() {
		for (int i = 0; i < diamondCircles.length; i++) {
			System.out.printf("%-3d ", i);
			for (int j = 0; j < diamondCircles[i].length; j++) {
				if (diamondCircles[i][j] != null) {
					System.out.printf("%-6s  ", diamondCircles[i][j].getPoint());
				} else {
					System.out.printf("%-6s  ", diamondCircles[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * 调试代码，用于打印圆的位置
	 */
	private void printCenterLocation() {
		for (int i = 0; i < diamondCircles.length; i++) {
			System.out.printf("%-3d ", i);
			for (int j = 0; j < diamondCircles[i].length; j++) {
				if (diamondCircles[i][j] != null) {
					System.out.printf("%-6s  ", diamondCircles[i][j].loaction());
				} else {
					System.out.printf("%-6s  ", diamondCircles[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	private void repaintTheBoard() {
		group.getChildren().clear();
		diamondGrid.generateNewMap();
		paintDiamonds();
	}
}

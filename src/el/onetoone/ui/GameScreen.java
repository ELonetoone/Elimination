package el.onetoone.ui;




import java.util.List;

import el.onetoone.back.BaseDiamondGrid;
import el.onetoone.back.Config;
import el.onetoone.back.Diamond;
import el.onetoone.back.Point;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameScreen {

	private static final double CIRCLEDISTANCEE = 60;
	private static final double RADIUS = 10;
	
	DiamondCircle[][] diamondCircles = new DiamondCircle[Config.height][Config.width];
	BaseDiamondGrid diamondGrid;
	DiamondCircle choosedDiamond;
	Diamond[][] diamonds;
	
	Scene scene;
	ScoreBoard scoreBoard;
	Group group;
	BorderPane borderPane;
	Theme theme;
	
	private SequentialTransition sequentialTransition;
	private double startY;
	private double startX;
	
	/**
	 * 执行初始化,并设置游戏屏幕的scene
	 */
	public GameScreen(Scene scene) {
		
		theme = new MagicGirlTheme();
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
		scoreBoard = new ScoreBoard(diamondGrid);
		
		paintDiamonds();
		Theme theme = new MagicGirlTheme();
		ImageView backgroud = new ImageView(theme.getBG_GAME());
		backgroud.setFitWidth(scene.getWidth());
		backgroud.setFitHeight(scene.getHeight());
		borderPane.getChildren().add(backgroud);
		BorderPane.setAlignment(group, Pos.BASELINE_CENTER);
		borderPane.setBottom(group);
		
		borderPane.setTop(scoreBoard);
		scene.setRoot(borderPane);
	}
	
	/**
	 * 绘制宝石，同时也会给宝石添加事件监听器
	 */
	private void paintDiamonds() {
		
				startY = scene.getWidth() / (Config.width * 2);
				startX = scene.getHeight() / (Config.height * 2);
//				circleDistance = 2 * centerV;
				Image diamondImage = null;
				for (int i = 0; i < diamondCircles.length; i++) {
					
					for (int j = 0; j < diamondCircles[i].length; j++) {
						
						//得到颜色，因为我们自己定义的Color与fx的Color重名，于是我才用了根据次序来获得颜色的方式
						switch (diamonds[i + 2][j + 2].getColor().ordinal()) {
						case 0:
							
							diamondImage = theme.getDIAMOND_ONE();
							break;

						case 1:
							
							diamondImage = theme.getDIAMOND_TWO();
							break;
						case 2:
							
							diamondImage = theme.getDIAMOND_THREE();
							break;
							
						case 3:
							
							diamondImage = theme.getDIAMOND_FOUR();
							break;
							
						case 4:
							
							diamondImage = theme.getDIAMOND_FIVE();
							break;
							
						case 5:
							
							diamondImage = theme.getDIAMOND_SIX();
							break;
							
					    default:
					    	break;
						}
						
						
						diamondCircles[i][j] = new DiamondCircle();
						diamondCircles[i][j].setPoint(new Point(i + 2, j + 2));
						diamondCircles[i][j].setImage(diamondImage);
						diamondCircles[i][j].setFitWidth(100);
						diamondCircles[i][j].setFitHeight(80);
						diamondCircles[i][j].setLayoutX(startY + CIRCLEDISTANCEE * j);
						diamondCircles[i][j].setLayoutY(startX + CIRCLEDISTANCEE * i);
						group.getChildren().add(diamondCircles[i][j]);
						
						DiamondCircle currentDiamond = diamondCircles[i][j];
						
//						currentDiamond.setOnMouseEntered(e -> {
//
//							currentDiamond.setStroke(Color.BLACK);
//							currentDiamond.setStrokeWidth(1);
//						});
//						
//						currentDiamond.setOnMouseExited(e -> {
//							
//							currentDiamond.setStroke(null);
//						});
						
						currentDiamond.setOnMousePressed(e -> {
							
							if (choosedDiamond == null) {
								choosedDiamond = currentDiamond;
							} else if ((choosedDiamond.getPoint().getX() + 1 == currentDiamond.getPoint().getX()
									|| choosedDiamond.getPoint().getX() - 1 ==  currentDiamond.getPoint().getX())
									&& choosedDiamond.getPoint().getY() == currentDiamond.getPoint().getY()) {
								//上下交换
								boolean exchangeValid = diamondGrid.executeExchangeElimination(currentDiamond.getPoint(), choosedDiamond.getPoint());
								TranslateTransition transition1 = choosedDiamond.verticalTransition((currentDiamond.getPoint().getX()
										- choosedDiamond.getPoint().getX()) * CIRCLEDISTANCEE);
								TranslateTransition transition2 = currentDiamond.verticalTransition((choosedDiamond.getPoint().getX()
										- currentDiamond.getPoint().getX()) * CIRCLEDISTANCEE);
								
								
								if (exchangeValid) {
									exchangeDiamondCircle(currentDiamond);
									exchangePoint(currentDiamond);

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
									dropDiamonds();
//									repaintTheBoard();
								});
//								testPrint();
//								printPoint();
//								printCenterLocation();
								
								
								choosedDiamond = null;
							} else if ((choosedDiamond.getPoint().getY() + 1 == currentDiamond.getPoint().getY()
									|| choosedDiamond.getPoint().getY() - 1 == currentDiamond.getPoint().getY())
									&& choosedDiamond.getPoint().getX() == currentDiamond.getPoint().getX()) {
								//左右交换
								boolean exchangeValid = diamondGrid.executeExchangeElimination(currentDiamond.getPoint(), choosedDiamond.getPoint());
								TranslateTransition transition1 = currentDiamond.hrizontalTransition((choosedDiamond.getPoint().getY()
										- currentDiamond.getPoint().getY()) * CIRCLEDISTANCEE);
								TranslateTransition transition2 = choosedDiamond.hrizontalTransition((currentDiamond.getPoint().getY()
										- choosedDiamond.getPoint().getY()) * CIRCLEDISTANCEE);
								
								if (exchangeValid) {
									exchangeDiamondCircle(currentDiamond);
									exchangePoint(currentDiamond);

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
									dropDiamonds();
//									repaintTheBoard();
								});
								
								
								
								choosedDiamond = null;
							} else {
								choosedDiamond = currentDiamond;
							}
						});
					}
				}
	}
	
	private void dropDiamonds() {
		
		ParallelTransition parallelTransition = new ParallelTransition();
		
		for (int i = 0; i < diamonds[0].length; i++) {
			int numbersOfNull = 0;
			int bottom = -1;
			boolean haveSpecialDiamond = false;
			int specialDiamondPosition = -1;
			for (int j = diamonds.length - 1; j >= 0; j--) {
				
				if (diamonds[j][i] == null) {
					if (j > 1 && diamonds[j - 1][i] != null && diamonds[j - 2][i] == null) {
						haveSpecialDiamond = true;
						specialDiamondPosition = j - 1;
					}
					if (bottom == -1) {
						bottom = j;
					}
					numbersOfNull++;
				}
			}
			if (haveSpecialDiamond) {
				parallelTransition.getChildren().add(diamondCircles[specialDiamondPosition - 2][i - 2].dropToTransition((bottom - specialDiamondPosition) * CIRCLEDISTANCEE));
				while (bottom - 3- numbersOfNull >= 0) {
					parallelTransition.getChildren().add(diamondCircles[bottom - 3 - numbersOfNull][i - 2].dropToTransition(numbersOfNull * CIRCLEDISTANCEE));
					bottom--;
				}
			} else {
				while (bottom - 2- numbersOfNull >= 0) {
					parallelTransition.getChildren().add(diamondCircles[bottom - 2 - numbersOfNull][i - 2].dropToTransition(numbersOfNull * CIRCLEDISTANCEE));
					bottom--;
				}
			}
		}
		
		parallelTransition.play();
		parallelTransition.setOnFinished(e -> {
			appearDiamonds();
		});
		
		if (parallelTransition.getChildren().isEmpty()) {
			appearDiamonds();
		}
	}
	
	private void appearDiamonds() {
		ParallelTransition appear = new ParallelTransition();
		List<Point> list = diamondGrid.generateNewDiamonds();
		for (Point point : list) {
			DiamondCircle temp = new DiamondCircle();
			temp.setPoint(point);
			temp.setLayoutY(startX + ((point.getX() - 2) * CIRCLEDISTANCEE));
			temp.setLayoutX(startY + ((point.getY() - 2) * CIRCLEDISTANCEE));
			
			Image diamondImage = null;
			switch (diamonds[point.getX()][point.getY()].getColor().ordinal()) {
			case 0:
				
				diamondImage = theme.getDIAMOND_ONE();
				break;

			case 1:
				
				diamondImage = theme.getDIAMOND_TWO();
				break;
			case 2:
				
				diamondImage = theme.getDIAMOND_THREE();
				break;
				
			case 3:
				
				diamondImage = theme.getDIAMOND_FOUR();
				break;
				
			case 4:
				
				diamondImage = theme.getDIAMOND_FIVE();
				break;
				
			case 5:
				
				diamondImage = theme.getDIAMOND_SIX();
				break;
				
		    default:
		    	break;
			}
			
			temp.setImage(diamondImage);
			temp.setFitHeight(50);
			temp.setFitWidth(50);
			
			group.getChildren().add(temp);
			appear.getChildren().add(temp.appearTransition());
		}
		appear.play();
		appear.setOnFinished(e2 -> {
			repaintTheBoard();
			if (diamondGrid.executeFullScreenEliminationSucceed()) {
				
				ParallelTransition paintNullToTransparent = paintNullToNoColor();
				paintNullToTransparent.play();
				paintNullToTransparent.setOnFinished(e3 -> {
					dropDiamonds();
				});
			}
		});
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
	
	private ParallelTransition paintNullToNoColor() {
		ParallelTransition parallelTransition = new ParallelTransition();
		for (int i = 0; i < diamonds.length; i++) {
			for (int j = 0; j < diamonds[i].length; j++) {
				
				if (diamonds[i][j] == null) {
					parallelTransition.getChildren().add(diamondCircles[i - 2][j - 2].fade());
				}
			}
		}
		
		return parallelTransition;
	}
	
	/**
	 * 将选中的两个宝石在diamondCircles中交换位置，同时也会交换他们的Point
	 * @param currentCircle
	 */
	private void exchangeDiamondCircle(DiamondCircle currentCircle) {
		
		DiamondCircle tempCircle = currentCircle;
		diamondCircles[currentCircle.getPoint().getX() - 2][currentCircle.getPoint().getY() - 2] = choosedDiamond;
		diamondCircles[choosedDiamond.getPoint().getX() - 2][choosedDiamond.getPoint().getY() - 2] = tempCircle;
	}
	
	private void exchangePoint(DiamondCircle currentCircle) {
		
		Point temp = currentCircle.getPoint();
		currentCircle.setPoint(choosedDiamond.getPoint());
		choosedDiamond.setPoint(temp);
	}
	
	/**
	 * 调试代码
	 */
	public void testPrint() {
		for (int i = 0; i < diamondCircles.length; i++) {
			System.out.printf("%-3d ", i);
			for (int j = 0; j < diamondCircles[i].length; j++) {
				if (diamondCircles[i][j] != null) {
					System.out.printf("%-6s  ", diamondCircles[i][j].getImage());
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
		paintDiamonds();
	}
}

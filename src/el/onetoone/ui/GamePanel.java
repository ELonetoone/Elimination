package el.onetoone.ui;

import java.util.List;

import el.onetoone.back.BaseDiamondGrid;
import el.onetoone.back.Config;
import el.onetoone.back.Diamond;
import el.onetoone.back.ItemList;
import el.onetoone.back.Point;
import el.onetoone.back.Status;
import el.onetoone.back.UserBox;
import el.onetoone.exceptions.NotLoginException;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.image.Image;

public class GamePanel extends Group {

	private static final double DISTANCE = 60;
	private static final double START_Y = 100;
	private static final double START_X = 100;
	private static final double DIAMOND_WIDTH = 100;
	private static final double DIAMOND_HEIGHT = 80;

	private DiamondCircle[][] diamondCircles = new DiamondCircle[Config.height][Config.width];
	private BaseDiamondGrid diamondGrid;
	private boolean gameOver = false;

	public BaseDiamondGrid getDiamondGrid() {
		return diamondGrid;
	}

	private DiamondCircle choosedDiamond;
	private Diamond[][] diamonds;

	private Theme theme;
	private GameMain gameMain;
	
	public Theme getTheme() {
		return theme;
	}

	private SequentialTransition sequentialTransition;

	/**
	 * 执行初始化,并设置游戏屏幕的scene
	 */
	public GamePanel(GameMain gameMain) {

		this.gameMain = gameMain;
		theme = Config.getTheme();
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {

		diamondGrid = new BaseDiamondGrid(Config.height, Config.width);
		diamonds = diamondGrid.getDiamondMap();
		sequentialTransition = new SequentialTransition();

		paintDiamonds();
	}

	/**
	 * 绘制宝石，同时也会给宝石添加事件监听器
	 */
	private void paintDiamonds() {

		Image diamondImage = null;
		for (int i = 0; i < diamondCircles.length; i++) {

			for (int j = 0; j < diamondCircles[i].length; j++) {

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

				if (diamonds[i + 2][j + 2].getStatus() != Status.COMMON) {

				}

				diamondCircles[i][j] = new DiamondCircle();
				diamondCircles[i][j].setPoint(new Point(i + 2, j + 2));
				diamondCircles[i][j].setImage(diamondImage);
				diamondCircles[i][j].setFitWidth(DIAMOND_WIDTH);
				diamondCircles[i][j].setFitHeight(DIAMOND_HEIGHT);
				diamondCircles[i][j].setLayoutX(START_Y + DISTANCE * (j - 2));
				diamondCircles[i][j].setLayoutY(START_X + DISTANCE * (i - 2));
				this.getChildren().add(diamondCircles[i][j]);

				DiamondCircle currentDiamond = diamondCircles[i][j];

				currentDiamond.setOnMousePressed(e -> {

					if (gameOver) {
						return;
					}
					
					if (gameMain.getCurrentProps() != null) {
						gameMain.setCursor(Cursor.DEFAULT);
						try {
							if (UserBox.getUser().useItem(gameMain.getCurrentProps())) {
								
								switch (gameMain.getCurrentProps()) {
								case ItemList.BOOM:
									diamondGrid.useBoom(currentDiamond.getPoint());
									break;
									
								case ItemList.HAMMER:
									diamondGrid.useHammer(currentDiamond.getPoint());
									break;
									
								case ItemList.NEWMAP:
									diamondGrid.useGenNewMap();
									repaintTheBoard();
									break;
									
								}
							}
							gameMain.setCurrentProps(null);
							paintNullToNoColor(sequentialTransition);
							sequentialTransition.play();
							sequentialTransition.setOnFinished(e2 -> {
								sequentialTransition.getChildren().clear();
								dropDiamonds();
							});
						} catch (NotLoginException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return;
					} 
					if (choosedDiamond == null) {
						choosedDiamond = currentDiamond;
					} else if ((choosedDiamond.getPoint().getX() + 1 == currentDiamond.getPoint().getX()
							|| choosedDiamond.getPoint().getX() - 1 == currentDiamond.getPoint().getX())
							&& choosedDiamond.getPoint().getY() == currentDiamond.getPoint().getY()) {
						// 上下交换
						boolean exchangeValid = diamondGrid.executeExchangeElimination(currentDiamond.getPoint(),
								choosedDiamond.getPoint());
						TranslateTransition transition1 = choosedDiamond.verticalTransition(
								(currentDiamond.getPoint().getX() - choosedDiamond.getPoint().getX()) * DISTANCE);
						TranslateTransition transition2 = currentDiamond.verticalTransition(
								(choosedDiamond.getPoint().getX() - currentDiamond.getPoint().getX()) * DISTANCE);

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
						});

						choosedDiamond = null;
					} else if ((choosedDiamond.getPoint().getY() + 1 == currentDiamond.getPoint().getY()
							|| choosedDiamond.getPoint().getY() - 1 == currentDiamond.getPoint().getY())
							&& choosedDiamond.getPoint().getX() == currentDiamond.getPoint().getX()) {
						// 左右交换
						boolean exchangeValid = diamondGrid.executeExchangeElimination(currentDiamond.getPoint(),
								choosedDiamond.getPoint());
						TranslateTransition transition1 = currentDiamond.hrizontalTransition(
								(choosedDiamond.getPoint().getY() - currentDiamond.getPoint().getY()) * DISTANCE);
						TranslateTransition transition2 = choosedDiamond.hrizontalTransition(
								(currentDiamond.getPoint().getY() - choosedDiamond.getPoint().getY()) * DISTANCE);

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
				parallelTransition.getChildren().add(diamondCircles[specialDiamondPosition - 2][i - 2]
						.dropToTransition((bottom - specialDiamondPosition) * DISTANCE));
				while (bottom - 3 - numbersOfNull >= 0) {
					parallelTransition.getChildren().add(diamondCircles[bottom - 3 - numbersOfNull][i - 2]
							.dropToTransition(numbersOfNull * DISTANCE));
					bottom--;
				}
			} else {
				while (bottom - 2 - numbersOfNull >= 0) {
					parallelTransition.getChildren().add(diamondCircles[bottom - 2 - numbersOfNull][i - 2]
							.dropToTransition(numbersOfNull * DISTANCE));
					bottom--;
				}
			}
		}

		parallelTransition.play();
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (parallelTransition.getStatus() == javafx.animation.Animation.Status.RUNNING) {
					lockDiamonds();
					stop();
				}
			}

			private void lockDiamonds() {
				// TODO Auto-generated method stub
				for (int i = 0; i < diamondCircles.length; i++) {

					for (int j = 0; j < diamondCircles[i].length; j++) {

						diamondCircles[i][j].setOnMousePressed(null);
					}
				}
			}
		};

		timer.start();
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
			temp.setLayoutY(START_X + ((point.getX() - 4) * DISTANCE));
			temp.setLayoutX(START_Y + ((point.getY() - 4) * DISTANCE));

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
			temp.setFitHeight(DIAMOND_HEIGHT);
			temp.setFitWidth(DIAMOND_WIDTH);

			this.getChildren().add(temp);
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
	 * 
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

	private void repaintTheBoard() {
		this.getChildren().clear();
		paintDiamonds();
	}
	
	public void gameOver() {
		UserBox.getUser().updateUserInfo();
		gameOver = true;
	}
}

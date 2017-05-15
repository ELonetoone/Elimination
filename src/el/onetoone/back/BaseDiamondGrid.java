package el.onetoone.back;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import el.onetoone.exceptions.NotLoginException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * 感觉下一步还可以试下撤销某一步的逻辑 不过要等先做出来一个版本把所有可能的bug都de了
 * 
 * 啊啊啊，还要debug，烦烦烦！ QAQ
 * 
 * @author iznauy
 *
 */
public class BaseDiamondGrid {

	private IntegerProperty timeProperty;
	private IntegerProperty stepProperty;
	private IntegerProperty gradeProperty;

	private String mode = null;

	private Color[] colors = Color.values();

	/**
	 * 存储所有的宝石
	 */
	private Diamond[][] diamondMap;

	/**
	 * 游戏网格实际高度
	 */
	private int height;

	/**
	 * 游戏网格实际宽度
	 */
	private int width;

	/**
	 * 玩家获取的分数
	 */

	public int getGrade() {
		return gradeProperty.get();
	}

	public IntegerProperty gradeProperty() {
		return gradeProperty;
	}

	/**
	 * 初始化游戏地图
	 * 
	 * @param height
	 *            游戏网格实际高度
	 * @param width
	 *            游戏网格实际宽度
	 */
	public BaseDiamondGrid(int height, int width) {
		this.height = height;
		this.width = width;
		diamondMap = new Diamond[height + 4][width + 4];
		do {
			this.init();
		} while (isDie() || canDirectlyEliminated());

		// 用户分数设置为0
		gradeProperty = new SimpleIntegerProperty(0);
		timeProperty = new SimpleIntegerProperty();
		stepProperty = new SimpleIntegerProperty();

	}

	/**
	 * 另一个构造器，后端持有一个mode，这样就可以在一定模式在后端下限制用户道具使用
	 * 
	 * @param height
	 * @param width
	 * @param mode
	 */
	public BaseDiamondGrid(int height, int width, String mode) {
		this(height, width);
		this.mode = mode;
	}

	/**
	 * 初始化游戏界面
	 */
	public void init() {
		colors = Stream.of(colors).filter(c -> !c.toString().equals("GRAY")).toArray(Color[]::new);
		for (int i = 0; i < height + 4; i++) {
			for (int j = 0; j < width + 4; j++) {
				if (i < 2 || j < 2 || j > width + 1 || i > height + 1) {
					// 外围填灰色
					diamondMap[i][j] = new Diamond(Color.GRAY, Status.COMMON);
				} else {
					diamondMap[i][j] = new Diamond(colors[(int) ((Math.random() * 100) % colors.length)],
							Status.COMMON);
				}
			}
		}
	}

	/**
	 * 重置游戏 比如用户输了选择重来，或者用户选择重新开始游戏
	 */
	public void reset() {
		this.gradeProperty.set(0);
		do {
			this.init();
		} while (isDie() || canDirectlyEliminated());
	}

	/**
	 * 判定是否无路可走
	 * 
	 * @return 是否无路可走
	 */
	public boolean isDie() {
		for (int i = 2; i < this.height + 1; i++) {
			for (int j = 2; j < this.width + 1; j++) {
				Color currentColor = diamondMap[i][j].getColor();
				if (currentColor == diamondMap[i + 1][j].getColor()) {
					if (currentColor == diamondMap[i - 2][j].getColor()
							|| currentColor == diamondMap[i - 1][j - 1].getColor()
							|| currentColor == diamondMap[i - 1][j + 1].getColor()
							|| currentColor == diamondMap[i + 3][j].getColor()
							|| currentColor == diamondMap[i + 2][j - 1].getColor()
							|| currentColor == diamondMap[i + 2][j + 1].getColor()) {
						return false;
					}
				} else if (currentColor == diamondMap[i][j + 1].getColor()) {
					if (currentColor == diamondMap[i][j - 2].getColor()
							|| currentColor == diamondMap[i - 1][j - 1].getColor()
							|| currentColor == diamondMap[i + 1][j - 1].getColor()
							|| currentColor == diamondMap[i][j + 3].getColor()
							|| currentColor == diamondMap[i - 1][j + 2].getColor()
							|| currentColor == diamondMap[i + 1][j + 2].getColor()) {
						return false;
					}
				} else if (currentColor == diamondMap[i][j + 2].getColor()) {
					if (currentColor == diamondMap[i - 1][j + 1].getColor()
							|| currentColor == diamondMap[i + 1][j + 1].getColor()) {
						return false;
					}
				} else if (currentColor == diamondMap[i + 2][j].getColor()) {
					if (currentColor == diamondMap[i + 1][j - 1].getColor()
							|| currentColor == diamondMap[i + 1][j + 1].getColor()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 初始化地图时候调用，判断当前是否有可以直接消除的元素。 如果存在，地图将会被重置。
	 * 
	 * @return 是否可以直接消除
	 */
	private boolean canDirectlyEliminated() {
		for (int i = 2; i < height + 2; i++) {
			for (int j = 2; j < width + 2; j++) {
				Color currentColor = diamondMap[i][j].getColor();
				if (currentColor == diamondMap[i][j + 1].getColor()
						&& currentColor == diamondMap[i][j + 2].getColor()) {
					// 横着可以消除
					return true;
				} else if (currentColor == diamondMap[i + 1][j].getColor()
						&& currentColor == diamondMap[i + 2][j].getColor()) {
					// 竖着可以消除
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 交换两个宝石位置时候调用
	 * 
	 * @param x1
	 *            第一个宝石x坐标
	 * @param y1
	 *            第一个宝石y坐标
	 * @param x2
	 *            第二个宝石x坐标
	 * @param y2
	 *            第二个宝石y坐标
	 * @return 返回一个消除以及生成新宝石的队列
	 */
	public ArrayList<EliminationArrayList> exchangeTwoDiamond(int x1, int y1, int x2, int y2) {

		boolean changeSucceed = false;
		ArrayList<EliminationArrayList> eliminationArrayLists = new ArrayList<>();
		// 将两块宝石物理上交换
		Diamond tempDiamond = diamondMap[x1][y1];
		diamondMap[x1][y1] = diamondMap[x2][y2];
		diamondMap[x2][y2] = tempDiamond;

		// 如果存在五消特效宝石，五消最特殊，不需要判断列也可以消除
		if (diamondMap[x1][y1].getStatus() == Status.FIVE_EL || diamondMap[x2][y2].getStatus() == Status.FIVE_EL) {

			EliminationArrayList eliminationArrayList = new EliminationArrayList();
			eliminationArrayList.setToBeEliminatedPoints(concernSpecialDiamond(fiveSpecialElimination(x1, y1, x2, y2)));// modified
																														// by
																														// liao
			eliminationArrayLists.add(eliminationArrayList);
			return eliminationArrayLists;
		}
		// testPrint();
		// 不存在五消特效宝石被交换
		// 先搜索第一个点附近
		int[] point1row = rowSearch(x1, y1);
		int[] point1col = columnSearch(x1, y1);

		if (point1row[2] >= 2) {
			int beginX = x1;
			int beginY = y1 - point1row[0]; // 这下面的这个不为空

			TwoTuple<Status, ArrayList<Point>> result = searchSpecifyPoint(beginX, beginY); // 搜索s1所在位置的起点，由于是行搜索，所以一定包含这个交换的点
			EliminationArrayList eliminationArrayList1 = new EliminationArrayList();

			if (result.first != Status.COMMON) {
				eliminationArrayList1.setNewDiamond(new Point(x1, y1),
						new Diamond(diamondMap[x1][y1].getColor(), result.first));
			}

			eliminationArrayList1.setToBeEliminatedPoints(concernSpecialDiamond(result.second));
			eliminationArrayLists.add(eliminationArrayList1);
		} else if (point1col[2] >= 2) {
			int beginX = x1 - point1col[0];
			int beginY = y1;

			TwoTuple<Status, ArrayList<Point>> result = searchSpecifyPoint(beginX, beginY);
			EliminationArrayList eliminationArrayList1 = new EliminationArrayList();
			if (result.first != Status.COMMON) {
				eliminationArrayList1.setNewDiamond(new Point(x1, y1),
						new Diamond(diamondMap[x1][y1].getColor(), result.first));
			}
			eliminationArrayList1.setToBeEliminatedPoints(concernSpecialDiamond(result.second));
			eliminationArrayLists.add(eliminationArrayList1);
		} else {
			eliminationArrayLists.add(null);
		}

		// 搜索第二个点
		int[] point2row = rowSearch(x2, y2);
		int[] point2col = columnSearch(x2, y2);
		if (point2row[2] >= 2) {
			int beginX = x2;
			int beginY = y2 - point2row[0];
			TwoTuple<Status, ArrayList<Point>> result = searchSpecifyPoint(beginX, beginY);
			EliminationArrayList eliminationArrayList2 = new EliminationArrayList();
			if (result.first != Status.COMMON) {
				eliminationArrayList2.setNewDiamond(new Point(x2, y2),
						new Diamond(diamondMap[x2][y2].getColor(), result.first));
			}
			eliminationArrayList2.setToBeEliminatedPoints(concernSpecialDiamond(result.second));
			eliminationArrayLists.add(eliminationArrayList2);
		} else if (point2col[2] >= 2) {
			int beginX = x2 - point2col[0];
			int beginY = y2;
			TwoTuple<Status, ArrayList<Point>> result = searchSpecifyPoint(beginX, beginY);
			EliminationArrayList eliminationArrayList2 = new EliminationArrayList();
			if (result.first != Status.COMMON) {
				eliminationArrayList2.setNewDiamond(new Point(x2, y2),
						new Diamond(diamondMap[x2][y2].getColor(), result.first));
			}
			eliminationArrayList2.setToBeEliminatedPoints(concernSpecialDiamond(result.second));
			eliminationArrayLists.add(eliminationArrayList2);
		} else {
			eliminationArrayLists.add(null);
		}

		// 交换失败后的工作
		for (EliminationArrayList list : eliminationArrayLists) {
			if (list != null) {
				changeSucceed = true;
			}
		}

		if (!changeSucceed) {
			tempDiamond = diamondMap[x1][y1];
			diamondMap[x1][y1] = diamondMap[x2][y2];
			diamondMap[x2][y2] = tempDiamond;
		}
		return eliminationArrayLists;
	}

	/**
	 * 检查某个特定元素左右与之相连的同色宝石个数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] rowSearch(int x, int y) {
		int leftdeviate = 0;
		int rightdeviate = 0;
		for (int i = y - 1; i >= 0 && diamondMap[x][i] != null
				&& diamondMap[x][i].getColor() == diamondMap[x][y].getColor(); i--) {
			leftdeviate++;
		}
		for (int i = y + 1; i < width + 4 && diamondMap[x][i] != null
				&& diamondMap[x][i].getColor() == diamondMap[x][y].getColor(); i++) {
			rightdeviate++;
		}
		return new int[] { leftdeviate, rightdeviate, leftdeviate + rightdeviate };
	}

	/**
	 * 检查某个特定元素上下与之相连得同色宝石个数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] columnSearch(int x, int y) {
		int updeviate = 0;
		int downdeviate = 0;
		for (int i = x - 1; i >= 0 && diamondMap[i][y] != null
				&& diamondMap[i][y].getColor() == diamondMap[x][y].getColor(); i--) {
			updeviate++;
		}
		for (int i = x + 1; i < height + 4 && diamondMap[i][y] != null
				&& diamondMap[i][y].getColor() == diamondMap[x][y].getColor(); i++) {
			downdeviate++;
		}
		return new int[] { updeviate, downdeviate, updeviate + downdeviate };
	}

	/**
	 * 五消专属方法！
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public ArrayList<Point> fiveSpecialElimination(int x1, int y1, int x2, int y2) {
		ArrayList<Point> points = new ArrayList<>();
		if (diamondMap[x1][y1].getStatus() == Status.FIVE_EL && diamondMap[x2][y2].getStatus() != Status.FIVE_EL) {
			if (diamondMap[x1][y1].getColor() != diamondMap[x2][y2].getColor()) {
				points.add(new Point(x1, y1));
			}
			points.addAll(getSameColor(diamondMap[x2][y2].getColor()));
		} else if (diamondMap[x1][y1].getStatus() != Status.FIVE_EL
				&& diamondMap[x2][y2].getStatus() == Status.FIVE_EL) {
			if (diamondMap[x1][y1].getColor() != diamondMap[x2][y2].getColor()) {
				points.add(new Point(x2, y2));
			}
			points.addAll(getSameColor(diamondMap[x1][y1].getColor()));
		} else if (diamondMap[x1][y1].getStatus() == Status.FIVE_EL
				&& diamondMap[x2][y2].getStatus() == Status.FIVE_EL) {
			if (diamondMap[x1][y1].getColor() == diamondMap[x2][y2].getColor()) {
				points.addAll(getSameColor(diamondMap[x1][y1].getColor()));
			} else {
				points.addAll(getSameColor(diamondMap[x1][y1].getColor()));
				points.addAll(getSameColor(diamondMap[x2][y2].getColor()));
			}
		}
		return points;
	}

	/**
	 * 获取网格内某种颜色的所有宝石坐标
	 * 
	 * @param color
	 *            指定的颜色
	 * @return 一个装有Point的ArrayList
	 */
	private ArrayList<Point> getSameColor(Color color) {
		ArrayList<Point> sameColorArray = new ArrayList<>();
		for (int i = 2; i < height + 2; i++) {
			for (int j = 2; j < width + 2; j++) {
				if (diamondMap[i][j].getColor() == color) {
					sameColorArray.add(new Point(i, j));
				}
			}
		}
		return sameColorArray;
	}

	/**
	 * 交换两个宝石后调用
	 * 
	 * @param point1
	 *            第一个宝石的位置
	 * @param point2
	 *            第二个宝石的位置
	 * @return 返回一个消除以及生成新宝石队列 (考虑到交换的两个都有可能形成一个消除)
	 */
	public ArrayList<EliminationArrayList> exchangeTwoDiamond(Point point1, Point point2) {
		return exchangeTwoDiamond(point1.getX(), point1.getY(), point2.getX(), point2.getY());
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 经过特效处理后的点阵集合 修复了上次例会提到的bug
	 * 
	 * @param points
	 *            原点阵集合
	 * @return 新的点阵集合
	 */
	public ArrayList<Point> concernSpecialDiamond(ArrayList<Point> points) {
		HashSet<Point> pointSet = new HashSet<>();
		HashSet<Point> finalpointSet = new HashSet<>();
		for (Point point : points) {
			pointSet.add(point);
		}
		int originalCount = pointSet.size();
		int finalCount = 0;
		do {
			// 除了第一次之外，每次进入之后都会让original被final替换
			if (finalCount != 0) {
				originalCount = finalCount;
			}
			for (Point point : pointSet) {
				finalpointSet.add(point);
				Diamond diamond = diamondMap[point.getX()][point.getY()];
				if (diamond.getStatus() == Status.COMMON) {
					continue;
				} else if (diamond.getStatus() == Status.FOUR_EL_COL) {
					for (int i = 2; i < height + 2; i++) {
						finalpointSet.add(new Point(i, point.getY()));
					}
				} else if (diamond.getStatus() == Status.FOUR_EL_ROW) {
					for (int i = 2; i < width + 2; i++) {
						finalpointSet.add(new Point(point.getX(), i));
					}
				} else if (diamond.getStatus() == Status.BIG_EL) {
					for (int i = 2; i < height + 2; i++) {
						finalpointSet.add(new Point(i, point.getY()));
					}
					for (int i = 2; i < width + 2; i++) {
						finalpointSet.add(new Point(point.getX(), i));
					}
				} else if (diamond.getStatus() == Status.L_EL) {
					for (int i = point.getX() - 1; i <= point.getX() + 1; i++) {
						for (int j = point.getY() - 1; j <= point.getY() + 1; j++) {
							if (diamondMap[i][j].getColor() != Color.GRAY) {
								finalpointSet.add(new Point(i, j));
							}
						}
					}
				}
			}
			finalCount = finalpointSet.size();
			pointSet = finalpointSet;
		} while (finalCount != originalCount);
		ArrayList<Point> pointList = new ArrayList<>();
		for (Point finalPoint : pointSet) {
			pointList.add(finalPoint);
		}
		return pointList;
	}

	/**
	 * 对于某个特定的起始点，得到周围包含他的要消除的元素，不考虑特效。
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public TwoTuple<Status, ArrayList<Point>> searchSpecifyPoint(int i, int j) {
		ArrayList<Point> toBeEliminations = new ArrayList<>();
		Status status;
		// modified by liao
		if (diamondMap[i][j] == null) {
			return null;
		}
		Color currentColor = diamondMap[i][j].getColor();
		// modified a bug here
		if (diamondMap[i][j + 1] != null && diamondMap[i][j + 2] != null
				&& currentColor == diamondMap[i][j + 1].getColor() && currentColor == diamondMap[i][j + 2].getColor()) {
			// 保证横着可以消除
			toBeEliminations.add(new Point(i, j));
			toBeEliminations.add(new Point(i, j + 1));
			toBeEliminations.add(new Point(i, j + 2));
			// 假如可以四消，判断五消和水平4个的大L消除
			// modified a bug here
			if ((j + 3) < (width + 4) && diamondMap[i][j + 3] != null
					&& currentColor == diamondMap[i][j + 3].getColor()) {
				toBeEliminations.add(new Point(i, j + 3));
				// 判断是否可以五消
				if ((j + 4) < (width + 4) && diamondMap[i][j + 4] != null
						&& currentColor == diamondMap[i][j + 4].getColor()) {
					toBeEliminations.add(new Point(i, j + 4));
					status = Status.FIVE_EL;
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (diamondMap[i + 1][j + 3] != null && diamondMap[i + 2][j + 3] != null
						&& currentColor == diamondMap[i + 1][j + 3].getColor()
						&& currentColor == diamondMap[i + 2][j + 3].getColor()) {
					// 右下的大L形状消除
					toBeEliminations.add(new Point(i + 1, j + 3));
					toBeEliminations.add(new Point(i + 2, j + 3));
					status = Status.BIG_EL;
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (diamondMap[i - 1][j + 3] != null && diamondMap[i - 2][j + 3] != null
						&& currentColor == diamondMap[i - 1][j + 3].getColor()
						&& currentColor == diamondMap[i - 2][j + 3].getColor()) {
					// 右上的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i - 1, j + 3));
					toBeEliminations.add(new Point(i - 2, j + 3));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (diamondMap[i - 1][j] != null && diamondMap[i - 2][j] != null
						&& currentColor == diamondMap[i - 1][j].getColor()
						&& currentColor == diamondMap[i - 2][j].getColor()) {
					// 左上的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i - 1, j));
					toBeEliminations.add(new Point(i - 2, j));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (diamondMap[i + 1][j] != null && diamondMap[i + 2][j] != null
						&& currentColor == diamondMap[i + 1][j].getColor()
						&& currentColor == diamondMap[i + 2][j].getColor()) {
					// 右下的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i + 1, j));
					toBeEliminations.add(new Point(i + 2, j));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				}
				// 否则就返回四消结果
				status = Status.FOUR_EL_ROW;
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			}
			// 如果横着不可以四消,判断水平只有3个的大L消除和L消除
			if (diamondMap[i + 1][j + 2] != null && diamondMap[i + 2][j + 2] != null
					&& currentColor == diamondMap[i + 1][j + 2].getColor()
					&& currentColor == diamondMap[i + 2][j + 2].getColor()) {
				// 右下的L形状消除
				toBeEliminations.add(new Point(i + 1, j + 2));
				toBeEliminations.add(new Point(i + 2, j + 2));
				status = Status.L_EL;
				// 判断是否能大L
				if ((i + 3) < (height + 4) && diamondMap[i + 3][j + 2] != null
						&& currentColor == diamondMap[i + 3][j + 2].getColor()) {
					toBeEliminations.add(new Point(i + 3, j + 2));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (diamondMap[i - 1][j + 2] != null && diamondMap[i - 2][j + 2] != null
					&& currentColor == diamondMap[i - 1][j + 2].getColor()
					&& currentColor == diamondMap[i - 2][j + 2].getColor()) {
				// 右上的L形状消除
				toBeEliminations.add(new Point(i - 1, j + 2));
				toBeEliminations.add(new Point(i - 2, j + 2));
				status = Status.L_EL;
				if (i >= 3 && diamondMap[i - 3][j + 2] != null && currentColor == diamondMap[i - 3][j + 2].getColor()) {
					toBeEliminations.add(new Point(i - 3, j + 2));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (diamondMap[i - 1][j] != null && diamondMap[i - 2][j] != null
					&& currentColor == diamondMap[i - 1][j].getColor()
					&& currentColor == diamondMap[i - 2][j].getColor()) {
				// 左上的L形状消除
				toBeEliminations.add(new Point(i - 1, j));
				toBeEliminations.add(new Point(i - 2, j));
				status = Status.L_EL;
				if ((i >= 3) && currentColor == diamondMap[i - 3][j].getColor()) {
					toBeEliminations.add(new Point(i - 3, j));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (diamondMap[i + 1][j] != null && diamondMap[i + 2][j] != null
					&& currentColor == diamondMap[i + 1][j].getColor()
					&& currentColor == diamondMap[i + 2][j].getColor()) {
				// 右下的L形状消除
				status = Status.L_EL;
				toBeEliminations.add(new Point(i + 1, j));
				toBeEliminations.add(new Point(i + 2, j));
				if (currentColor == diamondMap[i + 3][j].getColor()) {
					toBeEliminations.add(new Point(i + 3, j));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			}
			// 否则只能普通的三消
			status = Status.COMMON;
			return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
		} else if (diamondMap[i + 1][j] != null && diamondMap[i + 2][j] != null
				&& currentColor == diamondMap[i + 1][j].getColor() && currentColor == diamondMap[i + 2][j].getColor()) {
			// 保证竖着可以消除
			toBeEliminations.add(new Point(i, j));
			toBeEliminations.add(new Point(i + 1, j));
			toBeEliminations.add(new Point(i + 2, j));
			// 假如可以四消，判断五消和竖着4个的大L消除
			if ((i + 3) < (height + 4) && diamondMap[i + 3][j] != null
					&& currentColor == diamondMap[i + 3][j].getColor()) {
				toBeEliminations.add(new Point(i + 3, j));
				// 判断是否可以五消
				if ((i + 4) < (height + 4) && diamondMap[i + 4][j] != null
						&& currentColor == diamondMap[i + 4][j].getColor()) {
					toBeEliminations.add(new Point(i + 4, j));
					status = Status.FIVE_EL;
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (diamondMap[i + 3][j + 1] != null && diamondMap[i + 3][j + 2] != null
						&& currentColor == diamondMap[i + 3][j + 1].getColor()
						&& currentColor == diamondMap[i + 3][j + 2].getColor()) {
					// 右下的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i + 3, j + 1));
					toBeEliminations.add(new Point(i + 3, j + 2));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (diamondMap[i + 3][j - 1] != null && diamondMap[i + 3][j - 2] != null
						&& currentColor == diamondMap[i + 3][j - 1].getColor()
						&& currentColor == diamondMap[i + 3][j - 2].getColor()) {
					// 左下的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i + 3, j - 1));
					toBeEliminations.add(new Point(i + 3, j - 2));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (diamondMap[i][j - 1] != null && diamondMap[i][j - 2] != null
						&& currentColor == diamondMap[i][j - 1].getColor()
						&& currentColor == diamondMap[i][j - 2].getColor()) {
					// 左上的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i, j - 1));
					toBeEliminations.add(new Point(i, j - 2));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (diamondMap[i][j + 1] != null && diamondMap[i][j + 2] != null
						&& currentColor == diamondMap[i][j + 1].getColor()
						&& currentColor == diamondMap[i][j + 2].getColor()) {
					// 右上的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i, j + 1));
					toBeEliminations.add(new Point(i, j + 2));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				}
				// 否则就返回四消结果
				status = Status.FOUR_EL_COL;
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			}
			// 如果横着不可以四消,判断竖着只有3个的大L消除和L消除
			if (diamondMap[i + 2][j + 1] != null && diamondMap[i + 2][j + 2] != null
					&& currentColor == diamondMap[i + 2][j + 1].getColor()
					&& currentColor == diamondMap[i + 2][j + 2].getColor()) {
				// 右下的L形状消除
				status = Status.L_EL;
				toBeEliminations.add(new Point(i + 2, j + 1));
				toBeEliminations.add(new Point(i + 2, j + 2));
				// 判断是否能大L
				if ((j + 3) < (width + 4) && currentColor == diamondMap[i + 2][j + 3].getColor()) {
					toBeEliminations.add(new Point(i + 2, j + 3));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (diamondMap[i + 2][j - 1] != null && diamondMap[i + 2][j - 2] != null
					&& currentColor == diamondMap[i + 2][j - 1].getColor()
					&& currentColor == diamondMap[i + 2][j - 2].getColor()) {
				// 左下的L形状消除
				status = Status.L_EL;
				toBeEliminations.add(new Point(i + 2, j - 1));
				toBeEliminations.add(new Point(i + 2, j - 2));
				// 判断能否大L
				if ((j >= 3) && currentColor == diamondMap[i + 2][j - 3].getColor()) {
					toBeEliminations.add(new Point(i + 2, j - 3));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (diamondMap[i][j - 1] != null && diamondMap[i][j - 2] != null
					&& currentColor == diamondMap[i][j - 1].getColor()
					&& currentColor == diamondMap[i][j - 2].getColor()) {
				// 左上的L形状消除
				status = Status.L_EL;
				toBeEliminations.add(new Point(i, j - 1));
				toBeEliminations.add(new Point(i, j - 2));
				// 判断能否大L
				if ((j >= 3) && diamondMap[i][j - 3] != null && currentColor == diamondMap[i][j - 3].getColor()) {
					toBeEliminations.add(new Point(i, j - 3));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (diamondMap[i][j + 1] != null && diamondMap[i][j + 2] != null
					&& currentColor == diamondMap[i][j + 1].getColor()
					&& currentColor == diamondMap[i][j + 2].getColor()) {
				// 右上的L形状消除
				status = Status.L_EL;
				toBeEliminations.add(new Point(i, j + 1));
				toBeEliminations.add(new Point(i, j + 2));
				if ((j + 3) < (width + 4) && diamondMap[i][j + 3] != null
						&& currentColor == diamondMap[i][j + 3].getColor()) {
					toBeEliminations.add(new Point(i, j + 3));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			}
			// 否则只能普通的三消
			status = Status.COMMON;
			return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
		}
		return null;
	}

	/**
	 * 不考虑特殊元素的全屏幕普通消除，一次返回一种消除
	 * 
	 * @return 返回一个必须会被消除的元素的坐标列表
	 */
	public TwoTuple<Status, ArrayList<Point>> eliminationMap() {
		TwoTuple<Status, ArrayList<Point>> result = null;
		for (int i = 2; i < Config.height; i++) {
			for (int j = 2; j < Config.width; j++) {
				result = searchSpecifyPoint(i, j);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}

	/**
	 * 生成新地图后（宝石掉落后），用于消除新地图中可消除的，一次只能消除一种。 默认特效元素是arraylist里面的第一个元素的位置。
	 * 不过特效元素不在这儿写.. 好像有些随机的成分？ 不过确实理论上是最左上角的元素(maybe) (逃) 没有消除就返回null
	 * 
	 * @return
	 */
	public EliminationArrayList fullScreenElimination() {
		TwoTuple<Status, ArrayList<Point>> result = eliminationMap();
		EliminationArrayList eliminationArrayList = new EliminationArrayList();
		if (result != null) {
			eliminationArrayList.setToBeEliminatedPoints(concernSpecialDiamond(result.second));
			if (result.first != Status.COMMON) {
				eliminationArrayList.setNewDiamond(new Point(result.second.get(0).getX(), result.second.get(0).getY()),
						new Diamond(diamondMap[result.second.get(0).getX()][result.second.get(0).getY()].getColor(),
								result.first));
			}
			return eliminationArrayList;
		}
		return null;
	}

	/**
	 * 执行用户的交换操作后的消除工作 一点小bug修复
	 * 
	 * @param point1
	 * @param point2
	 */
	public boolean executeExchangeElimination(Point point1, Point point2) {

		boolean exchangeValid = false;
		ArrayList<EliminationArrayList> toBeEliminatedLists = this.exchangeTwoDiamond(point1, point2);

		for (EliminationArrayList list : toBeEliminatedLists) {

			if (list != null) {

				exchangeValid = true;
				for (Point point : list.getToBeEliminatedPoints()) {

					// iznauy 修改 2017.4.16
					// 用于增加分数
					// gradeProperty.add(diamondMap[point.getX()][point.getY()].getGrade());

					if (diamondMap[point.getX()][point.getY()] != null) {
						gradeProperty.set(gradeProperty.get() + diamondMap[point.getX()][point.getY()].getGrade());
					}
					diamondMap[point.getX()][point.getY()] = null;
				}

				// 将普通宝石换为特效宝石
				if (list.getNewDiamond() != null) {
					Point temp = list.getPoint();
					diamondMap[temp.getX()][temp.getY()] = list.getNewDiamond();
				}
			}
		}
		System.out.println(getGrade());
		if (exchangeValid) {
			stepMinOne();
		}

		return exchangeValid;
	}

	public boolean executeFullScreenEliminationSucceed() {

		EliminationArrayList list;
		boolean flag = false;
		while ((list = fullScreenElimination()) != null) {

			if (list.getNewDiamond() != null) {
				Point temp = list.getPoint();
				diamondMap[temp.getX()][temp.getY()] = list.getNewDiamond();
			}

			for (Point point : list.getToBeEliminatedPoints()) {
				gradeProperty.set(gradeProperty.get() + diamondMap[point.getX()][point.getY()].getGrade());
				diamondMap[point.getX()][point.getY()] = null;
			}
			flag = true;
		}

		return flag;
	}

	/**
	 * 生成消除后的新地图，将地图内部的null移到最顶部，然后系那个null替换为新的方块
	 */
	public List<Point> generateNewDiamonds() {

		// boolean haschanged = false;
		// 把null移到最顶部
		moveNullToTheTop();

		List<Point> list = new ArrayList<>();
		// 将null换为新的宝石
		for (int i = 2; i < diamondMap.length - 2; i++) {

			for (int j = 2; j < diamondMap[i].length - 2; j++) {
				if (diamondMap[i][j] == null) {
					// haschanged = true;
					diamondMap[i][j] = new Diamond(colors[(int) ((Math.random() * 100) % colors.length)],
							Status.COMMON);
					list.add(new Point(i, j));
				}
			}
		}

		return list;
	}

	private void moveNullToTheTop() {

		for (int i = 2; i < diamondMap.length - 2; i++) {

			for (int j = 2; j < diamondMap[i].length - 2; j++) {

				if (i != 2) {
					// 最顶部不用移动
					if (diamondMap[i][j] == null) {
						int k = i;
						while (true) {
							// 上方也为null时则不动
							if (diamondMap[k - 1][j] == null) {
								break;
							}
							diamondMap[k][j] = diamondMap[k - 1][j];
							diamondMap[k - 1][j] = null;
							k--;
							if (k == 2) {
								break;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 返回宝石地图
	 * 
	 * @author liao
	 * @return 宝石地图
	 */
	public Diamond[][] getDiamondMap() {

		return diamondMap;
	}

	/**
	 * 道具消除的几个方法 等到debug结束后再处理
	 */

	/**
	 * 使用小锤子
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws NotLoginException
	 */
	public boolean useHammer(int x, int y) throws NotLoginException {
		if (UserBox.getUser() == null) {
			throw new NotLoginException(NotLoginException.NOTLOGIN);
		} else if (UserBox.getUser().useItem(ItemList.HAMMER)) {
			gradeProperty.set(gradeProperty.get() + diamondMap[x][y].getGrade());
			diamondMap[x][y] = null;
			return true;
		} else {
			return false;
		}
	}

	public boolean useHammer(Point point) throws NotLoginException {
		return useHammer(point.getX(), point.getY());
	}

	/**
	 * 使用炸弹
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws NotLoginException
	 */
	public boolean useBoom(int x, int y) throws NotLoginException {
		if (UserBox.getUser() == null) {
			throw new NotLoginException(NotLoginException.NOTLOGIN);
		} else if (UserBox.getUser().useItem(ItemList.BOOM)) {
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (i >= 2 && i <= height + 1 && j >= 2 && j <= width + 1) {
						gradeProperty.set(gradeProperty.get() + diamondMap[i][j].getGrade());
						diamondMap[i][j] = null;
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean useBoom(Point point) throws NotLoginException {
		return useBoom(point.getX(), point.getY());
	}

	/**
	 * 使用生成新地图道具
	 * 
	 * @return 是否使用成功
	 * @throws NotLoginException
	 */
	public boolean useGenNewMap() throws NotLoginException {
		if (UserBox.getUser() == null) {
			throw new NotLoginException(NotLoginException.NOTLOGIN);
		} else if (UserBox.getUser().useItem(ItemList.NEWMAP)) {
			do {
				this.init();
			} while (isDie() || canDirectlyEliminated());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 加五步道具
	 * 
	 * @return
	 * @throws NotLoginException
	 */
	public boolean usePlusFiveSteps() throws NotLoginException {
		if (UserBox.getUser() == null) {
			throw new NotLoginException(NotLoginException.NOTLOGIN);
		} else if (UserBox.getUser().useItem(ItemList.PLUSFIVESTEPS)) {
			stepPlus(5);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 加三步
	 * 
	 * @return
	 * @throws NotLoginException
	 */
	public boolean usePlusThreeSteps() throws NotLoginException {
		if (UserBox.getUser() == null) {
			throw new NotLoginException(NotLoginException.NOTLOGIN);
		} else if (UserBox.getUser().useItem(ItemList.PLUSTHREESTEPS)) {
			stepPlus(3);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 加一步
	 * 
	 * @return
	 * @throws NotLoginException
	 */
	public boolean usePlusOneStep() throws NotLoginException {
		if (UserBox.getUser() == null) {
			throw new NotLoginException(NotLoginException.NOTLOGIN);
		} else if (UserBox.getUser().useItem(ItemList.PLUSONESTEP)) {
			stepPlus(1);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * +1s
	 * 
	 * @return
	 * @throws NotLoginException
	 */
	public boolean usePlusOneSecond() throws NotLoginException {
		if (UserBox.getUser() == null) {
			throw new NotLoginException(NotLoginException.NOTLOGIN);
		} else if (UserBox.getUser().useItem(ItemList.PLUSONESECOND)) {
			addTime(1);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * +5s
	 * 
	 * @return
	 * @throws NotLoginException
	 */
	public boolean usePlusThreeSeconds() throws NotLoginException {
		if (UserBox.getUser() == null) {
			throw new NotLoginException(NotLoginException.NOTLOGIN);
		} else if (UserBox.getUser().useItem(ItemList.PLUSTHREESECONDS)) {
			addTime(3);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * +10s
	 * 
	 * @return
	 * @throws NotLoginException
	 */
	public boolean usePlusFiveSeconds() throws NotLoginException {
		if (UserBox.getUser() == null) {
			throw new NotLoginException(NotLoginException.NOTLOGIN);
		} else if (UserBox.getUser().useItem(ItemList.PLUSFIVESECONDS)) {
			addTime(5);
			return true;
		} else {
			return false;
		}
	}

	public void setTime(int seconds) {
		timeProperty.set(seconds);
	}

	public void addTime(int second) {
		timeProperty.set(timeProperty.get() + second);
	}

	public IntegerProperty stepProperty() {
		return stepProperty;
	}

	public IntegerProperty timeProperty() {
		return timeProperty;
	}

	public void stepMinOne() {
		stepProperty.set(stepProperty.get() - 1);
	}

	public void stepPlus(int time) {
		stepProperty.set(stepProperty.get() + time);
	}

	public void restart() {
		do {
			this.init();
		} while (isDie() || canDirectlyEliminated());
	}
}
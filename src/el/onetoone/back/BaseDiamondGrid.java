package el.onetoone.back;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Stream;


public class BaseDiamondGrid {

	private Diamond[][] diamondMap;
	
	/**
	 * 游戏网格实际高度
	 */
	private int height;
	
	/**
	 * 游戏网格实际宽度
	 */
	private int width;
	
	
	//测试代码
//	public static void main(String[] args) {
//		new BaseDiamondGrid(5, 5);
//	}
	
	/**
	 * 初始化游戏地图
	 * @param height 游戏网格实际高度
	 * @param width 游戏网格实际宽度
	 */
	public BaseDiamondGrid(int height, int width) {
		this.height = height;
		this.width = width;
		diamondMap = new Diamond[height+4][width+4];
		do {
			this.init();
		} while (isDie() || canDirectlyEliminated());
		
		//测试代码
		for (int i = 0; i < height + 4; i++) {
			for (int j = 0; j < width + 4; j++) {
				System.out.print(diamondMap[i][j].getColor().toString() + "    ");
			}
			System.out.println();
			
		}
	}
	
	/**
	 * 初始化游戏界面
	 */
	@SuppressWarnings("unused")
	public void init() {
		Random random = new Random();
		Color[] colors = Color.values();
		colors = Stream.of(colors).filter(c -> !c.toString().equals("GRAY")).toArray(Color[]::new);
		for (int i = 0; i < height + 4; i++) {
			for (int j = 0; j < width + 4; j++) {
				if (i < 2 || j < 2 || j > width + 1 || i > height + 1) {
					//外围填灰色
					diamondMap[i][j] = new Diamond(Color.GRAY, Status.COMMON);
				} else {
					diamondMap[i][j] = new Diamond(colors[(int) ((Math.random() * 100) % colors.length)], Status.COMMON);
				}
			}
		}
	}
	
	/**
	 * 判定是否无路可走
	 * @return 是否无路可走
	 */
	public boolean isDie() {
		for (int i = 2; i < this.height + 1; i++) {
			for (int j = 2; j < this.width + 1; j++) {
				Color currentColor = diamondMap[i][j].getColor();
				if (currentColor == diamondMap[i+1][j].getColor()) {
					if (currentColor == diamondMap[i-2][j].getColor()
							|| currentColor == diamondMap[i-1][j-1].getColor()
							|| currentColor == diamondMap[i-1][j+1].getColor()
							|| currentColor == diamondMap[i+3][j].getColor()
							|| currentColor == diamondMap[i+2][j-1].getColor()
							|| currentColor == diamondMap[i+2][j+1].getColor()) {
						return true;
					}
				} else if (currentColor == diamondMap[i][j+1].getColor()){
					if (currentColor == diamondMap[i][j-2].getColor()
							|| currentColor == diamondMap[i-1][j-1].getColor()
							|| currentColor == diamondMap[i+1][j-1].getColor()
							|| currentColor == diamondMap[i][j+3].getColor()
							|| currentColor == diamondMap[i-1][j+2].getColor()
							|| currentColor == diamondMap[i+1][j+2].getColor()) {
						return true;
					}
				} else if (currentColor == diamondMap[i][j+2].getColor()) {
					if (currentColor == diamondMap[i-1][j+1].getColor()
							|| currentColor == diamondMap[i+1][j+1].getColor()) {
						return true;
					}
				} else if (currentColor == diamondMap[i+2][j].getColor()) {
					if (currentColor == diamondMap[i+1][j-1].getColor()
							|| currentColor == diamondMap[i+1][j+1].getColor()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 初始化地图时候调用，判断当前是否有可以直接消除的元素。
	 * 如果存在，地图将会被重置。
	 * @return 是否可以直接消除
	 */
	private boolean canDirectlyEliminated() {
		for (int i = 2; i < height; i++) {
			for (int j = 2; j < width; j++) {
				Color currentColor = diamondMap[i][j].getColor();
				if (currentColor == diamondMap[i][j+1].getColor()
						&& currentColor == diamondMap[i][j+2].getColor()) {
					//横着可以消除
					return true;
				} else if (currentColor == diamondMap[i+1][j].getColor()
						&& currentColor == diamondMap[i+2][j].getColor()) {
					//竖着可以消除
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 交换两个宝石位置时候调用
	 * @param x1 第一个宝石x坐标
	 * @param y1 第一个宝石y坐标
	 * @param x2 第二个宝石x坐标
	 * @param y2 第二个宝石y坐标
	 * @return 返回一个消除以及生成新宝石的队列
	 */
	public ArrayList<EliminationArrayList> exchangeTwoDiamond(int x1, int y1, int x2, int y2) {
		ArrayList<EliminationArrayList> eliminationArrayLists = new ArrayList<>();
		//将两块宝石物理上交换
		Diamond tempDiamond = diamondMap[x1][y1];
		diamondMap[x1][y1] = diamondMap[x2][y2];
		diamondMap[x2][y2] = tempDiamond;
		
		//如果存在五消特效宝石，五消最特殊，不需要判断列也可以消除
		if (diamondMap[x1][y1].getStatus() == Status.FIVE_EL || diamondMap[x2][y2].getStatus() == Status.FIVE_EL) {
			EliminationArrayList eliminationArrayList = new EliminationArrayList();
			eliminationArrayList.setToBeEliminatedPoints(fiveSpecialElimination(x1, y1, x2, y2));
			eliminationArrayLists.add(eliminationArrayList);
			return eliminationArrayLists;
		} 
		//不存在五消特效宝石被交换
		//先搜索第一个点附近
		int[] point1row = rowSearch(x1, y1);
		int[] point1col = columnSearch(x1, y1);
		if (point1row[2] >= 3) {
			int beginX = x1 - point1row[0];
			int beginY = y1;  //这下面的这个不为空
			TwoTuple<Status, ArrayList<Point>> result = searchSpecifyPoint(beginX, beginY); //搜索s1所在位置的起点，由于是行搜索，所以一定包含这个交换的点
			EliminationArrayList eliminationArrayList1 = new EliminationArrayList();
			if (result.first != Status.COMMON) {
				eliminationArrayList1.setDiamond(new Diamond(diamondMap[x1][y1].getColor(), result.first));
			}
			eliminationArrayList1.setToBeEliminatedPoints(concernSpecialDiamond(result.second));
			eliminationArrayLists.add(eliminationArrayList1);
		} else if (point1col[2] >= 3) {
			int beginX = x1;
			int beginY = y1 - point1col[0];
			TwoTuple<Status, ArrayList<Point>> result = searchSpecifyPoint(beginX, beginY);
			EliminationArrayList eliminationArrayList1 = new EliminationArrayList();
			if (result.first != Status.COMMON) {
				eliminationArrayList1.setDiamond(new Diamond(diamondMap[x1][y1].getColor(), result.first));
			}
			eliminationArrayList1.setToBeEliminatedPoints(concernSpecialDiamond(result.second));
			eliminationArrayLists.add(eliminationArrayList1);
		} else {
			eliminationArrayLists.add(null);
		}
		
		//搜索第二个点
		int[] point2row = rowSearch(x2, y2);
		int[] point2col = columnSearch(x2, y2);
		if (point2row[2] >= 3) {
			int beginX = x2 - point2row[0];
			int beginY = y2;
			TwoTuple<Status, ArrayList<Point>> result = searchSpecifyPoint(beginX, beginY);
			EliminationArrayList eliminationArrayList2 = new EliminationArrayList();
			if (result.first != Status.COMMON) {
				eliminationArrayList2.setDiamond(new Diamond(diamondMap[x2][y2].getColor(), result.first));
			}
			eliminationArrayList2.setToBeEliminatedPoints(concernSpecialDiamond(result.second));
			eliminationArrayLists.add(eliminationArrayList2);
		} else if (point2col[2] >= 3) {
			int beginX = x2;
			int beginY = y2 - point2col[0];
			TwoTuple<Status, ArrayList<Point>> result = searchSpecifyPoint(beginX, beginY);
			EliminationArrayList eliminationArrayList2 = new EliminationArrayList();
			if (result.first != Status.COMMON) {
				eliminationArrayList2.setDiamond(new Diamond(diamondMap[x2][y2].getColor(), result.first));
			}
			eliminationArrayList2.setToBeEliminatedPoints(concernSpecialDiamond(result.second));
			eliminationArrayLists.add(eliminationArrayList2);
		} else {
			eliminationArrayLists.add(null);
		}
	
		return eliminationArrayLists;
	}
	
	
	/**
	 * 检查某个特定元素左右与之相连的同色宝石个数
	 * @param x
	 * @param y
	 * @return 
	 */
	public int[] rowSearch(int x, int y) {
		int leftdeviate = 0;
		int rightdeviate = 0;
		for (int i = y - 1; i >= 0 && diamondMap[x][i].getColor() == diamondMap[x][y].getColor(); i--) {
			leftdeviate++;
		}
		for (int i = y + 1; i < width + 4 && diamondMap[x][i].getColor() == diamondMap[x][y].getColor(); i++) {
			rightdeviate++;
		}
		return new int[] {leftdeviate, rightdeviate, leftdeviate + rightdeviate};
	}
	
	/**
	 * 检查某个特定元素上下与之相连得同色宝石个数
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] columnSearch(int x, int y) {
		int updeviate = 0;
		int downdeviate = 0;
		for (int i = x - 1; i>= 0 && diamondMap[i][y].getColor() == diamondMap[x][y].getColor(); i--) {
			updeviate++;
		}
		for (int i = x + 1; i < height + 4 && diamondMap[i][y].getColor() == diamondMap[x][y].getColor(); i++) {
			downdeviate++;
		}
		return new int[]{updeviate, downdeviate, updeviate + downdeviate};
	}
	
	/**
	 * 五消专属方法！
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
		} else if (diamondMap[x1][y1].getStatus() != Status.FIVE_EL && diamondMap[x2][y2].getStatus() == Status.FIVE_EL) {
			if (diamondMap[x1][y1].getColor() != diamondMap[x2][y2].getColor()) {
				points.add(new Point(x2, y2));
			}
			points.addAll(getSameColor(diamondMap[x1][y1].getColor()));
		} else if (diamondMap[x1][y1].getStatus() == Status.FIVE_EL && diamondMap[x2][y2].getStatus() == Status.FIVE_EL) {
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
	 * @param color 指定的颜色
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
	 * @param point1 第一个宝石的位置
	 * @param point2 第二个宝石的位置
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
	 * 经过特效处理后的点阵集合
	 * @param points 原点阵集合
	 * @return 新的点阵集合
	 */
	public ArrayList<Point> concernSpecialDiamond(ArrayList<Point> points) {
		HashSet<Point> pointSet = new HashSet<>();
		for (Point point: points) {
			pointSet.add(point);
			Diamond diamond = diamondMap[point.getX()][point.getY()];
			if (diamond.getStatus() == Status.COMMON) {
				continue;
			} else if (diamond.getStatus() == Status.FOUR_EL_COL) {
				for (int i = 2; i < height + 2; i++) {
					pointSet.add(new Point(i, point.getY()));
				}
			} else if (diamond.getStatus() == Status.FOUR_EL_ROW) {
				for (int i = 2; i < width + 2; i++) {
					pointSet.add(new Point(point.getX(), i));
				}
			} else if (diamond.getStatus() == Status.BIG_EL) {
				for (int i = 2; i < height + 2; i++) {
					pointSet.add(new Point(i, point.getY()));
				}
				for (int i = 2; i < width + 2; i++) {
					pointSet.add(new Point(point.getX(), i));
				}
			} else if (diamond.getStatus() == Status.L_EL) {
				for (int i = point.getX() - 1; i <= point.getX() + 1; i++) {
					for (int j = point.getY() - 1; j <= point.getY() + 1; j++) {
						if (diamondMap[i][j].getColor() != Color.GRAY) {
							pointSet.add(new Point(i, j));
						}
					}
				}
			}
		}
		ArrayList<Point> pointList = new ArrayList<>();
		for (Point finalPoint: pointSet) {
			pointList.add(finalPoint);
		}
		return pointList;
	}
	
	/**
	 * 对于某个特定的起始点，得到周围包含他的要消除的元素，不考虑特效。
	 * @param i
	 * @param j
	 * @return
	 */
	public TwoTuple<Status, ArrayList<Point>> searchSpecifyPoint(int i, int j) {
		ArrayList<Point> toBeEliminations = new ArrayList<>();
		Status status;
		Color currentColor = diamondMap[i][j].getColor();
		if (currentColor == diamondMap[i][j+1].getColor()
				&& currentColor == diamondMap[i][j+2].getColor()) {
			//保证横着可以消除
			toBeEliminations.add(new Point(i, j));
			toBeEliminations.add(new Point(i, j+1));
			toBeEliminations.add(new Point(i, j+2));
			//假如可以四消，判断五消和水平4个的大L消除
			if (currentColor == diamondMap[i][j+3].getColor()) {
				toBeEliminations.add(new Point(i, j+3));
				//判断是否可以五消
				if (currentColor == diamondMap[i][j+4].getColor()) {
					toBeEliminations.add(new Point(i, j+4));
					status = Status.FIVE_EL;
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (currentColor == diamondMap[i+1][j+3].getColor()
						&& currentColor == diamondMap[i+2][j+3].getColor()) {
					//右下的大L形状消除
					toBeEliminations.add(new Point(i+1, j+3));
					toBeEliminations.add(new Point(i+2, j+3));
					status = Status.BIG_EL;
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (currentColor == diamondMap[i-1][j+3].getColor()
						&& currentColor == diamondMap[i-2][j+3].getColor()) {
					//右上的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i-1, j+3));
					toBeEliminations.add(new Point(i-2,	j+3));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (currentColor == diamondMap[i-1][j].getColor()
						&& currentColor == diamondMap[i-2][j].getColor()) {
					//左上的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i-1, j));
					toBeEliminations.add(new Point(i-2, j));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (currentColor == diamondMap[i+1][j].getColor()
						&& currentColor == diamondMap[i+2][j].getColor()) {
					//右下的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i+1, j));
					toBeEliminations.add(new Point(i+2, j));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				}
				//否则就返回四消结果
				status = Status.FOUR_EL_ROW;
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} 
			//如果横着不可以四消,判断水平只有3个的大L消除和L消除
			if (currentColor == diamondMap[i+1][j+2].getColor()
					&& currentColor == diamondMap[i+2][j+2].getColor()) {
				//右下的L形状消除
				toBeEliminations.add(new Point(i+1, j+2));
				toBeEliminations.add(new Point(i+2, j+2));
				status = Status.L_EL;
				//判断是否能大L
				if (currentColor == diamondMap[i+3][j+2].getColor()) {
					toBeEliminations.add(new Point(i+3, j+2));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (currentColor == diamondMap[i-1][j+2].getColor()
					&& currentColor == diamondMap[i-2][j+2].getColor()) {
				//右上的L形状消除
				toBeEliminations.add(new Point(i-1, j+2));
				toBeEliminations.add(new Point(i-2,	j+2));
				status = Status.L_EL;
				if ((i >= 3) && currentColor == diamondMap[i-3][j+2].getColor()) {
					toBeEliminations.add(new Point(i-3, j+2));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (currentColor == diamondMap[i-1][j].getColor()
					&& currentColor == diamondMap[i-2][j].getColor()) {
				//左上的L形状消除
				toBeEliminations.add(new Point(i-1, j));
				toBeEliminations.add(new Point(i-2, j));
				status = Status.L_EL;
				if ((i >= 3) && currentColor == diamondMap[i-3][j].getColor()) {
					toBeEliminations.add(new Point(i-3, j));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (currentColor == diamondMap[i+1][j].getColor()
					&& currentColor == diamondMap[i+2][j].getColor()) {
				//右下的L形状消除
				status = Status.L_EL;
				toBeEliminations.add(new Point(i+1, j));
				toBeEliminations.add(new Point(i+2, j));
				if (currentColor == diamondMap[i+3][j].getColor()) {
					toBeEliminations.add(new Point(i+3, j));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} 
			//否则只能普通的三消
			status = Status.COMMON;
			return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
		} else if (currentColor == diamondMap[i+1][j].getColor()
				&& currentColor == diamondMap[i+2][j].getColor()) {
			//保证竖着可以消除
			toBeEliminations.add(new Point(i, j));
			toBeEliminations.add(new Point(i+1, j));
			toBeEliminations.add(new Point(i+2, j));
			//假如可以四消，判断五消和竖着4个的大L消除
			if (currentColor == diamondMap[i+3][j].getColor()) {
				toBeEliminations.add(new Point(i+3, j));
				//判断是否可以五消
				if (currentColor == diamondMap[i+4][j].getColor()) {
					toBeEliminations.add(new Point(i+4, j));
					status = Status.FIVE_EL;
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (currentColor == diamondMap[i+3][j+1].getColor()
						&& currentColor == diamondMap[i+3][j+2].getColor()) {
					//右下的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i+3, j+1));
					toBeEliminations.add(new Point(i+3, j+2));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (currentColor == diamondMap[i+3][j-1].getColor()
						&& currentColor == diamondMap[i+3][j-2].getColor()) {
					//左下的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i+3, j-1));
					toBeEliminations.add(new Point(i+3,	j-2));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (currentColor == diamondMap[i][j-1].getColor()
						&& currentColor == diamondMap[i][j-2].getColor()) {
					//左上的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i, j-1));
					toBeEliminations.add(new Point(i, j-2));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				} else if (currentColor == diamondMap[i][j+1].getColor()
						&& currentColor == diamondMap[i][j+2].getColor()) {
					//右上的大L形状消除
					status = Status.BIG_EL;
					toBeEliminations.add(new Point(i, j+1));
					toBeEliminations.add(new Point(i, j+2));
					return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
				}
				//否则就返回四消结果
				status = Status.FOUR_EL_COL;
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} 
			//如果横着不可以四消,判断竖着只有3个的大L消除和L消除
			if (currentColor == diamondMap[i+2][j+1].getColor()
					&& currentColor == diamondMap[i+2][j+2].getColor()) {
				//右下的L形状消除
				status = Status.L_EL;
				toBeEliminations.add(new Point(i+2, j+1));
				toBeEliminations.add(new Point(i+2, j+2));
				//判断是否能大L
				if (currentColor == diamondMap[i+2][j+3].getColor()) {
					toBeEliminations.add(new Point(i+2, j+3));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (currentColor == diamondMap[i+2][j-1].getColor()
					&& currentColor == diamondMap[i+2][j-2].getColor()) {
				//左下的L形状消除
				status = Status.L_EL;
				toBeEliminations.add(new Point(i+2, j-1));
				toBeEliminations.add(new Point(i+2,	j-2));
				//判断能否大L
				if ((j >= 3) && currentColor == diamondMap[i+2][j-3].getColor()) {
					toBeEliminations.add(new Point(i+2, j-3));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (currentColor == diamondMap[i][j-1].getColor()
					&& currentColor == diamondMap[i][j-2].getColor()) {
				//左上的L形状消除
				status = Status.L_EL;
				toBeEliminations.add(new Point(i, j-1));
				toBeEliminations.add(new Point(i, j-2));
				//判断能否大L
				if ((j >= 3) && currentColor == diamondMap[i][j-3].getColor()) {
					toBeEliminations.add(new Point(i, j-3));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} else if (currentColor == diamondMap[i][j+1].getColor()
					&& currentColor == diamondMap[i][j+2].getColor()) {
				//右上的L形状消除
				status = Status.L_EL;
				toBeEliminations.add(new Point(i, j+1));
				toBeEliminations.add(new Point(i, j+2));
				if (currentColor == diamondMap[i][j+3].getColor()) {
					toBeEliminations.add(new Point(i, j+3));
					status = Status.BIG_EL;
				}
				return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
			} 
			//否则只能普通的三消
			status = Status.COMMON;
			return new TwoTuple<Status, ArrayList<Point>>(status, toBeEliminations);
		}
		return null;
	}
	
	/**
	 * 不考虑特殊元素的全屏幕普通消除，一次返回一种消除
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
	 * 生成新地图后（宝石掉落后），用于消除新地图中可消除的，一次只能消除一种。
	 * 默认特效元素是arraylist里面的第一个元素的位置。
	 * 不过特效元素不在这儿写..
	 * 好像有些随机的成分？
	 * 不过确实理论上是最左上角的元素(maybe)
	 * (逃)
	 * 没有消除就返回null
	 * @return
	 */
	public EliminationArrayList fullScreenElimination() {
		TwoTuple<Status, ArrayList<Point>> result = eliminationMap();
		EliminationArrayList eliminationArrayList = new EliminationArrayList();
		if (result != null) {
			eliminationArrayList.setToBeEliminatedPoints(concernSpecialDiamond(result.second));
			if (result.first != Status.COMMON) {
				eliminationArrayList.setDiamond(new Diamond(diamondMap[result.second.get(0).getX()][result.second.get(0).getY()].getColor(), result.first));
			}
			return eliminationArrayList;
		}
		return null;
	}
	
}

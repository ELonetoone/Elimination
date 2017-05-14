package el.onetoone.back;

import java.util.ArrayList;

public class EliminationArrayList {

	/**
	 * 新生成的特效宝石。
	 */
	private Diamond newGeneratedDiamond = null;

	/**
	 * 新生成的宝石的坐标。
	 */
	private Point newDiamondLocation = null;

	/**
	 * 存储将要被消除的点集合。
	 */
	private ArrayList<Point> toBeEliminatedDiamond;

	public EliminationArrayList() {
		this.toBeEliminatedDiamond = new ArrayList<>();
	}

	/**
	 * 生成特效宝石。（最多生成一个特效宝石）
	 * 
	 * @param point
	 *            特效宝石位置
	 * @param diamond
	 *            特效宝石
	 */
	public void setNewDiamond(Point point, Diamond diamond) {
		this.newDiamondLocation = point;
		this.newGeneratedDiamond = diamond;
	}

	/**
	 * 设置特效宝石位置
	 * 
	 * @param point
	 */
	public void setSpecialPoint(Point point) {
		this.newDiamondLocation = point;
	}

	/**
	 * 设置特效宝石位置
	 * 
	 * @param x
	 * @param y
	 */
	public void setSpecialPoint(int x, int y) {
		this.newDiamondLocation = new Point(x, y);
	}

	/**
	 * 设置特效宝石
	 * 
	 * @param diamond
	 */
	public void setDiamond(Diamond diamond) {
		this.newGeneratedDiamond = diamond;
	}

	/**
	 * 获取特效宝石位置
	 * 
	 * @return
	 */
	public Point getPoint() {
		return this.newDiamondLocation;
	}

	/**
	 * 获取特效宝石
	 * 
	 * @return
	 */
	public Diamond getNewDiamond() {
		return this.newGeneratedDiamond;
	}

	/**
	 * 获取将要被消除的宝石的点集
	 * 
	 * @return 将要被消除的点的集合
	 */
	public ArrayList<Point> getToBeEliminatedPoints() {
		return this.toBeEliminatedDiamond;
	}

	/**
	 * 批量设置消除点
	 * 
	 * @param points
	 */
	public void setToBeEliminatedPoints(ArrayList<Point> points) {
		this.toBeEliminatedDiamond = points;
	}

	public void addCommonPoint(Point point) {
		this.toBeEliminatedDiamond.add(point);
	}
}

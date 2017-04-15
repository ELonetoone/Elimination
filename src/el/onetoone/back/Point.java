package el.onetoone.back;

public class Point {

	/**
	 * 点的x坐标
	 */
	private int x;
	
	/**
	 * 点的y坐标
	 */
	private int y;
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void getX(int y) {
		this.y = y;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object point) {
		Point anotherPoint = (Point) point;
		if (anotherPoint.x == x && anotherPoint.y == y) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return x * 100 + y;
	}
	
}

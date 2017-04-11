package el.onetoone.back;

public class Point {

	private int x;
	private int y;
	private Status status;
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

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
	
}

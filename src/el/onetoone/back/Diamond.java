package el.onetoone.back;

public class Diamond {

	/**
	 * 宝石的状态，默认为COMMON
	 */
	private Status status = Status.COMMON;
	
	/**
	 * 宝石的颜色
	 */
	private Color color;
	
	public Status getStatus() {
		
		return this.status;
	}
	
	public void setStatus(Status status) {
		
		this.status = status;
	}
	
	public Color getColor() {
		
		return this.color;
	}
	
	public void setColor(Color color) {
		
		this.color = color;
	}
	
	public Diamond(Color color, Status status) {
		this.color = color;
		this.status = status;
	}
}

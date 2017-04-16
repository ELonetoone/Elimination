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
	
	/**
	 * 根据宝石的状态来赋予分值，在 Diamond 里面并没有专门的字段
	 * （普通消除分数还是低点好）
	 * @return
	 */
	public int getGrade() {
		if (status == Status.FIVE_EL) {
			return 1000;
		} else if (status == Status.BIG_EL) {
			return 500;
		} else if (status == Status.FOUR_EL_COL) {
			return 200;
		} else if (status == Status.FOUR_EL_ROW) {
			return 200;
		} else if (status == Status.L_EL) {
			return 250;
		} else {
			return 50;
		}
		
	}
	
}

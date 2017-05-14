package el.onetoone.back;

public enum Status {

	/**
	 * 由于四消的宝石需要判断是行消除还是列消除，新增一个枚举成员以区分。
	 */
	COMMON, FOUR_EL_ROW, FOUR_EL_COL, FIVE_EL, L_EL, BIG_EL;
}

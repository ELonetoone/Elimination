package el.onetoone.ui;

import javafx.scene.Scene;

public interface FunctionModel {
	
	/**
	 * 初始化功能页
	 */
	public void init();
	
	/**
	 * 获取功能页Scene
	 * @return
	 */
	public Scene getScene();
	
	/**
	 * 返回综合界面
	 */
	public void returnToSyn();

}

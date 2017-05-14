package el.onetoone.back;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 用于操控数据库 主要是懒得再大规模改动了。。干脆改成互联网版本。。
 * 
 * @author iznauy
 *
 */
public class DataBase {

	private File dataBase = new File("db/database");

	/**
	 * 改变数据库源 （感觉不会用到QAQ）
	 * 
	 * @param dataBase
	 *            数据库文件的路径
	 */
	public void setDataBase(File dataBase) {
		this.dataBase = dataBase;
	}

	/**
	 * 如果第一次使用，初始化文件
	 * 
	 * @throws IOException
	 */
	public void init() throws IOException {
		if (!dataBase.exists()) {
			dataBase.createNewFile();
		}
	}

	/**
	 * 读取数据库中所有信息
	 * 
	 * @return 数据库中所有信息，每个条目都是一个字符串
	 */
	public ArrayList<String> read() {
		ArrayList<String> objectsInfo = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dataBase))) {
			String objectInfo = null;
			while ((objectInfo = bufferedReader.readLine()) != null) {
				objectsInfo.add(objectInfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objectsInfo;
	}

	/**
	 * 批量写入数据，覆盖原有数据
	 * 
	 * @param objects
	 * @return
	 */
	public boolean write(ArrayList<String> objects) {
		try {
			init();
			FileWriter fileWriter = new FileWriter(dataBase);
			for (String object : objects) {
				fileWriter.write(object);
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 以追加模式入单条数据
	 * 
	 * @param object
	 * @return
	 */
	public boolean write(String object) {
		try {
			init();
			FileWriter fileWriter = new FileWriter(dataBase, true);
			fileWriter.write(object);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

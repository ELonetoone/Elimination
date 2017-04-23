package el.onetoone.back;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		String[][] strings;
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("db/test"));) {
			
			strings = (String[][]) objectInputStream.readObject();
			BaseDiamondGrid grid = new BaseDiamondGrid(Config.height, Config.width);
			Diamond[][] diamonds = new Diamond[strings.length][strings[0].length];
			for (int i = 0; i < strings.length; i++) {
				for (int j = 0; j < strings[i].length; j++) {
					diamonds[i][j] = new Diamond(Color.valueOf(strings[i][j]), Status.COMMON);
				}
			}
			grid.setDiamondMap(diamonds);
			grid.testPrint();
//			ArrayList<EliminationArrayList> list = grid.exchangeTwoDiamond(2, 5, 2, 6);
			grid.executeExchangeElimination(new Point(2, 5), new Point(2, 6));
//			System.out.println(list.toString());
			
			grid.testPrint();
		} catch (IOException | ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}

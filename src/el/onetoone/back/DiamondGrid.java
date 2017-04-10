package el.onetoone.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class DiamondGrid {
	
	private Diamond[][] diamondMap = new Diamond[Config.height + 4][Config.width + 4];
	
	public void init() {
		
		//initialize the map
		Random random = new Random();
		Color[] colors = Color.values();
		colors = Stream.of(colors).filter(c -> !c.toString().equals("GRAY")).toArray(Color[]::new);
		System.out.println(Arrays.toString(colors));
		
		do {
			for (int i = 0; i < diamondMap.length; i++) {
				
				for (int j = 0; j < diamondMap[i].length; j++) {
					
					if (i < 2 || j < 2 || j > Config.width + 1 || i > Config.height + 1) {
						//外围填灰色
						diamondMap[i][j] = new Diamond();
						diamondMap[i][j].setColor(Color.GRAY);
					} else {
						diamondMap[i][j] = new Diamond();
						diamondMap[i][j].setColor(colors[(int) ((Math.random() * 100) % colors.length)]);
					}
				}
			}
		} while ((!hasDied()) || canElimination());


		for (int i = 0; i < diamondMap.length; i++) {
			
			for (int j = 0; j < diamondMap[i].length; j++) {
				
				System.out.printf("%-6s  ",diamondMap[i][j].getColor().toString());
			}
			System.out.println();
		}
	}
	
	public boolean hasDied() {
		for (int i = 2; i < Config.height + 1; i++) {
			for (int j = 2; j < Config.width + 1; j++) {
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
	
	public boolean canElimination() {
		for (int i = 2; i < Config.height; i++) {
			for (int j = 2; j < Config.width; j++) {
				Color currentColor = diamondMap[i][j].getColor();
				if (currentColor == diamondMap[i][j+1].getColor()
						&& currentColor == diamondMap[i][j+2].getColor()) {
					//保证横着一定可以消除
					return true;
				} else if (currentColor == diamondMap[i+1][j].getColor()
						&& currentColor == diamondMap[i+2][j].getColor()) {
					//保证竖着一定可以消除
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 不考虑特殊元素的普通消除
	 * @return 返回一个必须会被消除的元素的坐标列表
	 */
	public ArrayList<Point> eliminationMap() {
		ArrayList<Point> toBeEliminations = new ArrayList<>();
		for (int i = 2; i < Config.height; i++) {
			for (int j = 2; j < Config.width; j++) {
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
							return toBeEliminations;
						} else if (currentColor == diamondMap[i+1][j+3].getColor()
								&& currentColor == diamondMap[i+2][j+3].getColor()) {
							//右下的大L形状消除
							toBeEliminations.add(new Point(i+1, j+3));
							toBeEliminations.add(new Point(i+2, j+3));
							return toBeEliminations;
						} else if (currentColor == diamondMap[i-1][j+3].getColor()
								&& currentColor == diamondMap[i-2][j+3].getColor()) {
							//右上的大L形状消除
							toBeEliminations.add(new Point(i-1, j+3));
							toBeEliminations.add(new Point(i-2,	j+3));
							return toBeEliminations;
						} else if (currentColor == diamondMap[i-1][j].getColor()
								&& currentColor == diamondMap[i-2][j].getColor()) {
							//左上的大L形状消除
							toBeEliminations.add(new Point(i-1, j));
							toBeEliminations.add(new Point(i-2, j));
							return toBeEliminations;
						} else if (currentColor == diamondMap[i+1][j].getColor()
								&& currentColor == diamondMap[i+2][j].getColor()) {
							//右下的大L形状消除
							toBeEliminations.add(new Point(i+1, j));
							toBeEliminations.add(new Point(i+2, j));
							return toBeEliminations;
						}
						//否则就返回四消结果
						return toBeEliminations;
					} 
					//如果横着不可以四消,判断水平只有3个的大L消除和L消除
					if (currentColor == diamondMap[i+1][j+2].getColor()
							&& currentColor == diamondMap[i+2][j+2].getColor()) {
						//右下的L形状消除
						toBeEliminations.add(new Point(i+1, j+2));
						toBeEliminations.add(new Point(i+2, j+2));
						//判断是否能大L
						if (currentColor == diamondMap[i+3][j+2].getColor()) {
							toBeEliminations.add(new Point(i+3, j+2));
						}
						return toBeEliminations;
					} else if (currentColor == diamondMap[i-1][j+2].getColor()
							&& currentColor == diamondMap[i-2][j+2].getColor()) {
						//右上的L形状消除
						toBeEliminations.add(new Point(i-1, j+2));
						toBeEliminations.add(new Point(i-2,	j+2));
						if ((i >= 3) && currentColor == diamondMap[i-3][j+2].getColor()) {
							toBeEliminations.add(new Point(i-3, j+2));
						}
						return toBeEliminations;
					} else if (currentColor == diamondMap[i-1][j].getColor()
							&& currentColor == diamondMap[i-2][j].getColor()) {
						//左上的L形状消除
						toBeEliminations.add(new Point(i-1, j));
						toBeEliminations.add(new Point(i-2, j));
						if ((i >= 3) && currentColor == diamondMap[i-3][j].getColor()) {
							toBeEliminations.add(new Point(i-3, j));
						}
						return toBeEliminations;
					} else if (currentColor == diamondMap[i+1][j].getColor()
							&& currentColor == diamondMap[i+2][j].getColor()) {
						//右下的L形状消除
						toBeEliminations.add(new Point(i+1, j));
						toBeEliminations.add(new Point(i+2, j));
						if (currentColor == diamondMap[i+3][j].getColor()) {
							toBeEliminations.add(new Point(i+3, j));
						}
						return toBeEliminations;
					} 
					//否则只能普通的三消
					return toBeEliminations;
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
							return toBeEliminations;
						} else if (currentColor == diamondMap[i+3][j+1].getColor()
								&& currentColor == diamondMap[i+3][j+2].getColor()) {
							//右下的大L形状消除
							toBeEliminations.add(new Point(i+3, j+1));
							toBeEliminations.add(new Point(i+3, j+2));
							return toBeEliminations;
						} else if (currentColor == diamondMap[i+3][j-1].getColor()
								&& currentColor == diamondMap[i+3][j-2].getColor()) {
							//左下的大L形状消除
							toBeEliminations.add(new Point(i+3, j-1));
							toBeEliminations.add(new Point(i+3,	j-2));
							return toBeEliminations;
						} else if (currentColor == diamondMap[i][j-1].getColor()
								&& currentColor == diamondMap[i][j-2].getColor()) {
							//左上的大L形状消除
							toBeEliminations.add(new Point(i, j-1));
							toBeEliminations.add(new Point(i, j-2));
							return toBeEliminations;
						} else if (currentColor == diamondMap[i][j+1].getColor()
								&& currentColor == diamondMap[i][j+2].getColor()) {
							//右上的大L形状消除
							toBeEliminations.add(new Point(i, j+1));
							toBeEliminations.add(new Point(i, j+2));
							return toBeEliminations;
						}
						//否则就返回四消结果
						return toBeEliminations;
					} 
					//如果横着不可以四消,判断竖着只有3个的大L消除和L消除
					if (currentColor == diamondMap[i+2][j+1].getColor()
							&& currentColor == diamondMap[i+2][j+2].getColor()) {
						//右下的L形状消除
						toBeEliminations.add(new Point(i+2, j+1));
						toBeEliminations.add(new Point(i+2, j+2));
						//判断是否能大L
						if (currentColor == diamondMap[i+2][j+3].getColor()) {
							toBeEliminations.add(new Point(i+2, j+3));
						}
						return toBeEliminations;
					} else if (currentColor == diamondMap[i+2][j-1].getColor()
							&& currentColor == diamondMap[i+2][j-2].getColor()) {
						//左下的L形状消除
						toBeEliminations.add(new Point(i+2, j-1));
						toBeEliminations.add(new Point(i+2,	j-2));
						//判断能否大L
						if ((j >= 3) && currentColor == diamondMap[i+2][j-3].getColor()) {
							toBeEliminations.add(new Point(i+2, j-3));
						}
						return toBeEliminations;
					} else if (currentColor == diamondMap[i][j-1].getColor()
							&& currentColor == diamondMap[i][j-2].getColor()) {
						//左上的L形状消除
						toBeEliminations.add(new Point(i, j-1));
						toBeEliminations.add(new Point(i, j-2));
						//判断能否大L
						if ((j >= 3) && currentColor == diamondMap[i][j-3].getColor()) {
							toBeEliminations.add(new Point(i, j-3));
						}
						return toBeEliminations;
					} else if (currentColor == diamondMap[i][j+1].getColor()
							&& currentColor == diamondMap[i][j+2].getColor()) {
						//右上的L形状消除
						toBeEliminations.add(new Point(i, j+1));
						toBeEliminations.add(new Point(i, j+2));
						if (currentColor == diamondMap[i][j+3].getColor()) {
							toBeEliminations.add(new Point(i, j+3));
						}
						return toBeEliminations;
					} 
					//否则只能普通的三消
					return toBeEliminations;
				}
			}
		}
		return null;
	}
	
	public void generateNewMap() {
		
		
	}

	public static void main(String[] args) {
		
		DiamondGrid diamondGrid = new DiamondGrid();
		diamondGrid.init();
	}
}

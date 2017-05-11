# BaseDiamondGrid

标签（空格分隔）： 类

---
BaseBiamondGrid：

diamondMap 后端宝石对象二维数组，外部有灰色扩充

width 实际宽度

height 实际高度

gradeProperty 玩家获取的分数

构造器：调用init，直到isDie和canDirectlyEliminated判定失败，设置分数为0

init：初始化每个宝石

reset：重置游戏，然后积分版设置为0，（假设用户对象存在，还要增加金币数等）

isDie：判定是否无路可走

canDirectlyEliminated：判断地图是否直接可以消除，只在初始化和重置时候调用

rowSearch：检查某个元素左右与之同色的元素个数

columnSearch：检查某个元素上下与之同色的元素个数

getSameColor：取得地图上所有同色宝石

fiveSpecialElimination：五消调用，获取所有要被消除宝石位置列表，不考虑特殊消除，不会生成其他特效宝石

concernSpecialDiamond：将一个之前没有考虑特殊宝石位置序列扩充到考虑特殊宝石的位置序列

searchSpecifyPoint：查询以某个点为起点的消除种类，不考虑特殊消除，如果没有，返回null

eliminationMap：返回一种可能的消除，如果有生成特效宝石，那第一个就是，不考虑消除点内包含特殊宝石的情况

fullScreenElimination：生成一个特效元素or不生成，考虑特殊消除，返回一个EliminationArrayList，里面包含要被消除的所有的产物，理论上应该被循环调用直到返回null

executeExchangeElimination：用户交换后调用，判断是否交换是合法的，如何合法，返回TRUE，获取被消除的宝石的分数，将宝石的位置化成null，如果存在特殊宝石，生成特殊宝石

exchangeTwoDiamond：首先在物理上交换宝石，然后考虑交换的宝石是否存在五消，如果存在五消，返回的是没有考虑特殊消除的点阵集合，ArrayList长度为1，如果存在其他消除，返回ArrayList的长度为2，可能含有null，如果没有消除，物理上把宝石交换回去，返回的ArrayList为空

executeFullScreenEliminationSucceed：反复调用fullScreenElimination，把点设为null，返回是否直接成功进行消除

moveNullToTheTop：把下方没有支撑的宝石在物理上下落（个人认为应该保留一个移动的List，要不然下落的宝石闪现有些奇怪）

generateNewDiamonds：调用moveNullToTheTop，之后随机生成新的宝石，记录成宝石的点位，返回一个装有宝石点位的List



EliminationArrayList：

持有一个新生成的宝石以及它的坐标
要被消除的宝石位置列表

前端调用的后端的方法：

getDiamondMap 获取宝石地图的引用

executeExchangeElimination

generateNewDiamonds

executeFullScreenEliminationSucceed






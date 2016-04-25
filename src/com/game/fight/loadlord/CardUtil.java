package com.game.fight.loadlord;

import java.util.ArrayList;
import java.util.List;

/**
 * 扑克牌工具类
 * 
 * @author wangliang
 * 
 */
public class CardUtil {
	/**
	 * 初始化一副54张的扑克牌
	 */
	public static String[] pukepai = new String[] {

	"红桃A", "方块A", "梅花A", "黑桃A",

	"红桃2", "方块2", "梅花2", "黑桃2",

	"红桃3", "方块3", "梅花3", "黑桃3",

	"红桃4", "方块4", "梅花4", "黑桃4",

	"红桃5", "方块5", "梅花5", "黑桃5",

	"红桃6", "方块6", "梅花6", "黑桃6",

	"红桃7", "方块7", "梅花7", "黑桃7",

	"红桃8", "方块8", "梅花8", "黑桃8",

	"红桃9", "方块9", "梅花9", "黑桃9",

	"红桃10", "方块10", "梅花10", "黑桃10",

	"红桃J", "方块J", "梅花J", "黑桃J",

	"红桃Q", "方块Q", "梅花Q", "黑桃Q",

	"红桃K", "方块K", "梅花K", "黑桃K",

	"大鬼", "小鬼" };

	/**
	 * 制定游戏规则算法时用到的数组
	 * 
	 * 数组中的数值一一对应着扑克牌中的牌
	 */
	private int[] pukepaiInts = new int[] {

	11, 12, 13, 14,

	21, 22, 23, 24,

	31, 32, 33, 34,

	41, 42, 43, 44,

	51, 52, 53, 54,

	61, 62, 63, 64,

	71, 72, 73, 74,

	81, 82, 83, 84,

	91, 92, 93, 94,

	101, 102, 103, 104,

	121, 122, 123, 124,

	131, 132, 133, 134,

	0, 1 };

	/**
	 * 洗牌函数
	 * 
	 * @param pukepai
	 *            初始化的扑克牌对象
	 * @return 返回洗好后的扑克牌对象
	 */
	public static String[] xipai(String[] pukepai) {
		String[] xipaiEnd = new String[54];
		// 把初始化的扑克牌元素插入到新的空的扑克牌中
		for (int i = 0; i < pukepai.length; i++) {
			int index = zeroToFifty_four(xipaiEnd);
			xipaiEnd[index] = pukepai[i];
			System.out.println("第 " + i + " 张牌 " + pukepai[i] + " 位置下标："
					+ index);
		}
		return xipaiEnd;
	}

	/**
	 * 寻找元素为空的下标
	 * 
	 * @param pukepai
	 * @return
	 */
	private static int zeroToFifty_four(String[] pukepai) {
		// 0 <= Math.random < 1
		// 随机获取下标，范围0~53
		int temp = (int) (Math.random() * 54);
		if (pukepai[temp] != null) {
			// System.out.println("下标 " + temp + " 元素不为空");
			// 继续寻找元素为空的下标
			return zeroToFifty_four(pukepai);
		} else {
			return temp;
		}
	}

	/**
	 * 发牌函数
	 * 
	 * @param pukepai
	 *            传入洗完后的扑克牌对象
	 * @return 返回三位玩家对象列表
	 */
	public static List<Gamers> fapai(String[] pukepai, Gamers wanjiaA,
			Gamers wanjiaB, Gamers wanjiaC) {
		List<Gamers> wanjias = new ArrayList<Gamers>();
		// 预留最后三张底牌
		for (int i = 0; i < pukepai.length - 3; i++) {
			if (i % 3 == 0) {// 第一个
				wanjiaA.pais.add(pukepai[i]);
			} else if (i % 3 == 1) {// 第二个
				wanjiaB.pais.add(pukepai[i]);
			} else if (i % 3 == 2) {// 第三个
				wanjiaC.pais.add(pukepai[i]);
			}
		}
		wanjias.add(wanjiaA);
		wanjias.add(wanjiaB);
		wanjias.add(wanjiaC);
		return wanjias;
	}

	/**
	 * 整型数组 转换为 列表 List<String>
	 * 
	 * 展示需要
	 * 
	 * @param paramInts
	 * @return
	 */
	public static List<String> transitionToList(int[] paramInts) {
		List<String> listStr = new ArrayList<String>();
		for (int value : paramInts) {
			switch (value) {
			case 0:
				listStr.add("小鬼");
				break;
			case 1:
				listStr.add("大鬼");
				break;
			case 11:
				listStr.add("红桃A");
				break;
			case 12:
				listStr.add("方块A");
				break;
			case 13:
				listStr.add("梅花A");

				break;
			case 14:
				listStr.add("黑桃A");

				break;
			case 21:
				listStr.add("红桃2");
				break;
			case 22:
				listStr.add("方块2");
				break;
			case 23:
				listStr.add("梅花2");

				break;
			case 24:
				listStr.add("黑桃2");

				break;
			case 31:
				listStr.add("红桃3");
				break;
			case 32:
				listStr.add("方块3");
				break;
			case 33:
				listStr.add("梅花3");

				break;
			case 34:
				listStr.add("黑桃3");

				break;
			case 41:
				listStr.add("红桃4");
				break;
			case 42:
				listStr.add("方块4");
				break;
			case 43:
				listStr.add("梅花4");

				break;
			case 44:
				listStr.add("黑桃4");

				break;
			case 51:
				listStr.add("红桃5");
				break;
			case 52:
				listStr.add("方块5");
				break;
			case 53:
				listStr.add("梅花5");

				break;
			case 54:
				listStr.add("黑桃5");

				break;
			case 61:
				listStr.add("红桃6");
				break;
			case 62:
				listStr.add("方块6");
				break;
			case 63:
				listStr.add("梅花6");

				break;
			case 64:
				listStr.add("黑桃6");

				break;
			case 71:
				listStr.add("红桃7");
				break;
			case 72:
				listStr.add("方块7");
				break;
			case 73:
				listStr.add("梅花7");

				break;
			case 74:
				listStr.add("黑桃7");

				break;
			case 81:
				listStr.add("红桃8");
				break;
			case 82:
				listStr.add("方块8");
				break;
			case 83:
				listStr.add("梅花8");

				break;
			case 84:
				listStr.add("黑桃8");

				break;
			case 91:
				listStr.add("红桃9");
				break;
			case 92:
				listStr.add("方块9");
				break;
			case 93:
				listStr.add("梅花9");

				break;
			case 94:
				listStr.add("黑桃9");

				break;
			case 101:
				listStr.add("红桃10");
				break;
			case 102:
				listStr.add("方块10");
				break;
			case 103:
				listStr.add("梅花10");

				break;
			case 104:
				listStr.add("黑桃10");

				break;
			case 111:
				listStr.add("红桃J");
				break;
			case 112:
				listStr.add("方块J");
				break;
			case 113:
				listStr.add("梅花J");

				break;
			case 114:
				listStr.add("黑桃J");

				break;
			case 121:
				listStr.add("红桃Q");
				break;
			case 122:
				listStr.add("方块Q");
				break;
			case 123:
				listStr.add("梅花Q");

				break;
			case 124:
				listStr.add("黑桃Q");

				break;
			case 131:
				listStr.add("红桃K");
				break;
			case 132:
				listStr.add("方块K");
				break;
			case 133:
				listStr.add("梅花K");

				break;
			case 134:
				listStr.add("黑桃K");

				break;
			default:
				listStr.add("程序错误");
				break;
			}
		}
		return listStr;
	}

	/**
	 * 扑克牌列表转换为整型数组
	 * 
	 * 
	 * @param pais
	 *            扑克牌列表
	 * @return 返回由小到大排序的整形数据
	 */
	public static int[] transitionToInts(List<String> pais) {
		int[] ints = new int[pais.size()];
		for (int i = 0; i < pais.size(); i++) {
			String pai = pais.get(i);
			if (pai.compareTo("红桃A") == 0) {
				ints[i] = 11;
			} else if (pai.compareTo("红桃2") == 0) {
				ints[i] = 21;
			} else if (pai.compareTo("红桃3") == 0) {
				ints[i] = 31;
			} else if (pai.compareTo("红桃4") == 0) {
				ints[i] = 41;
			} else if (pai.compareTo("红桃5") == 0) {
				ints[i] = 51;
			} else if (pai.compareTo("红桃6") == 0) {
				ints[i] = 61;
			} else if (pai.compareTo("红桃7") == 0) {
				ints[i] = 71;
			} else if (pai.compareTo("红桃8") == 0) {
				ints[i] = 81;
			} else if (pai.compareTo("红桃9") == 0) {
				ints[i] = 91;
			} else if (pai.compareTo("红桃10") == 0) {
				ints[i] = 101;
			} else if (pai.compareTo("红桃J") == 0) {
				ints[i] = 111;
			} else if (pai.compareTo("红桃Q") == 0) {
				ints[i] = 121;
			} else if (pai.compareTo("红桃K") == 0) {
				ints[i] = 131;
			} else if (pai.compareTo("方块A") == 0) {
				ints[i] = 12;
			} else if (pai.compareTo("方块2") == 0) {
				ints[i] = 22;
			} else if (pai.compareTo("方块3") == 0) {
				ints[i] = 32;
			} else if (pai.compareTo("方块4") == 0) {
				ints[i] = 42;
			} else if (pai.compareTo("方块5") == 0) {
				ints[i] = 52;
			} else if (pai.compareTo("方块6") == 0) {
				ints[i] = 62;
			} else if (pai.compareTo("方块7") == 0) {
				ints[i] = 72;
			} else if (pai.compareTo("方块8") == 0) {
				ints[i] = 82;
			} else if (pai.compareTo("方块9") == 0) {
				ints[i] = 92;
			} else if (pai.compareTo("方块10") == 0) {
				ints[i] = 102;
			} else if (pai.compareTo("方块J") == 0) {
				ints[i] = 112;
			} else if (pai.compareTo("方块Q") == 0) {
				ints[i] = 122;
			} else if (pai.compareTo("方块K") == 0) {
				ints[i] = 132;
			} else if (pai.compareTo("梅花A") == 0) {
				ints[i] = 13;
			} else if (pai.compareTo("梅花2") == 0) {
				ints[i] = 23;
			} else if (pai.compareTo("梅花3") == 0) {
				ints[i] = 33;
			} else if (pai.compareTo("梅花4") == 0) {
				ints[i] = 43;
			} else if (pai.compareTo("梅花5") == 0) {
				ints[i] = 53;
			} else if (pai.compareTo("梅花6") == 0) {
				ints[i] = 63;
			} else if (pai.compareTo("梅花7") == 0) {
				ints[i] = 73;
			} else if (pai.compareTo("梅花8") == 0) {
				ints[i] = 83;
			} else if (pai.compareTo("梅花9") == 0) {
				ints[i] = 93;
			} else if (pai.compareTo("梅花10") == 0) {
				ints[i] = 103;
			} else if (pai.compareTo("梅花J") == 0) {
				ints[i] = 113;
			} else if (pai.compareTo("梅花Q") == 0) {
				ints[i] = 123;
			} else if (pai.compareTo("梅花K") == 0) {
				ints[i] = 133;
			} else if (pai.compareTo("黑桃A") == 0) {
				ints[i] = 14;
			} else if (pai.compareTo("黑桃2") == 0) {
				ints[i] = 24;
			} else if (pai.compareTo("黑桃3") == 0) {
				ints[i] = 34;
			} else if (pai.compareTo("黑桃4") == 0) {
				ints[i] = 44;
			} else if (pai.compareTo("黑桃5") == 0) {
				ints[i] = 54;
			} else if (pai.compareTo("黑桃6") == 0) {
				ints[i] = 64;
			} else if (pai.compareTo("黑桃7") == 0) {
				ints[i] = 74;
			} else if (pai.compareTo("黑桃8") == 0) {
				ints[i] = 84;
			} else if (pai.compareTo("黑桃9") == 0) {
				ints[i] = 94;
			} else if (pai.compareTo("黑桃10") == 0) {
				ints[i] = 104;
			} else if (pai.compareTo("黑桃J") == 0) {
				ints[i] = 114;
			} else if (pai.compareTo("黑桃Q") == 0) {
				ints[i] = 124;
			} else if (pai.compareTo("黑桃K") == 0) {
				ints[i] = 134;
			} else if (pai.compareTo("大鬼") == 0) {
				ints[i] = 1;
			} else if (pai.compareTo("小鬼") == 0) {
				ints[i] = 0;
			} else {
				ints[i] = -1;
			}
		}
		return sortBubblingFromMinToMax(ints);
	}

	/**
	 * 冒泡排序， 由小到大
	 * 
	 * @param paramInts
	 * @return
	 */
	private static int[] sortBubblingFromMinToMax(int[] paramInts) {
		return sortBubblingFromMinToMax(paramInts, 0, paramInts.length - 1);
	}

	/**
	 * 
	 * @param paramInts
	 * @param index
	 * @param moreIndex
	 * @return
	 */
	private static int[] sortBubblingFromMinToMax(int[] paramInts, int index,
			int moreIndex) {
		if (moreIndex == 0) {
			return paramInts;
		} else if (index == moreIndex) {
			return sortBubblingFromMinToMax(paramInts, 0, moreIndex - 1);
		} else if (paramInts[index + 1] < paramInts[index]) {// 后一元素小于于前一元素
			// 交换数值
			int temp = paramInts[index];
			paramInts[index] = paramInts[index + 1];
			paramInts[index + 1] = temp;
			// 比较一下对元素
			return sortBubblingFromMinToMax(paramInts, index + 1, moreIndex);
		} else {// 后一元素不于前一元素
			// 比较下一对元素
			return sortBubblingFromMinToMax(paramInts, index + 1, moreIndex);

		}
	}

}

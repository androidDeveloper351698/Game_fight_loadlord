package com.game.fight.loadlord;

public class GameRuleUtil {

	/**
	 * 移除"三带一"中的被带的牌
	 * 
	 * @param paramInts
	 *            有序数组（由小到大 or 由大到小）
	 * @return 新的数组
	 */
	public static int[] removeX(int[] paramInts) {
		int[] results = new int[3];
		if (paramInts[0] == paramInts[1]) {
			results[0] = paramInts[0];
			results[1] = paramInts[0];
			results[2] = paramInts[0];
		} else {
			results[0] = paramInts[paramInts.length - 1];
			results[1] = paramInts[paramInts.length - 1];
			results[2] = paramInts[paramInts.length - 1];

		}
		return results;
	}

	/**
	 * 得到三带一中三张相同的牌
	 * 
	 * @param paramInts
	 *            有序数组（由小到大 or 由大到小）
	 * @return 相同的牌
	 */
	public static int getEqualInt(int[] paramInts) {
		int result = -1;
		if (paramInts[0] / 10 == paramInts[1] / 10) {
			result = paramInts[0];
		} else {
			result = paramInts[paramInts.length - 1];

		}
		return result;
	}

	/**
	 * 判断两个"顺子"的大小
	 * 
	 * @param oneShunZi
	 *            玩家出的顺子 (由小到大排序)
	 * @param otherShunZi
	 *            牌桌上的顺子（由小到大排序）
	 * @return true:玩家的顺子大 false:玩家的顺子压不过牌桌上的顺子
	 */
	public static boolean commpareTwoShunZi(int[] oneShunZi, int[] otherShunZi) {
		boolean flag = false;
		if (otherShunZi.length != oneShunZi.length) {// 两个数组大长度不相等
			flag = false;
		} else if (otherShunZi[0] == 1) {// 牌桌上的"顺子"中有A
			flag = false;
		} else if (oneShunZi[0] == 1) {// 玩家出的"顺子"中有A
			flag = true;
		} else if (commpareTwoPai(oneShunZi[0], otherShunZi[0])) {
			// 牌桌上的牌与玩家出的牌都没有A
			// 玩家出的"顺子"大于牌桌上的"顺子"
			flag = true;
		} else {
			flag = false;
		}
		return flag;

	}

	/**
	 * 判断两张牌的大小
	 * 
	 * @param onePai
	 *            玩家出的牌
	 * @param otherPai
	 *            牌桌上的牌
	 * @return true:玩家出的牌压住牌桌上的牌 false:牌桌上的牌压不住牌桌上的牌
	 */
	public static boolean commpareTwoPai(int onePai, int otherPai) {
		boolean flag = false;
		if (otherPai / 10 > 2) {// 是3 ~ 13的牌
			if (onePai / 10 > otherPai || onePai / 10 <= 2) {
				flag = true;
			} else {
				flag = false;
			}
		} else if (otherPai == 0 && onePai == 1) {// "大鬼"压"小鬼"
			flag = true;
		} else if (otherPai / 10 == 1 && (onePai / 10 == 0 || onePai / 10 == 2)) {// 大小鬼及2压A
			flag = true;
		} else if (otherPai / 10 == 2 && onePai / 10 == 0) {// 大小鬼压2
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断两个"飞机"的大小
	 * 
	 * @param oneFeiJi
	 *            玩家出的"飞机"(由小到大排序)
	 * @param otherFeiJi
	 *            牌桌上的"飞机"（由小到大排序）
	 * @return true:玩家的"飞机"压过牌桌上的"飞机" false:玩家的"飞机"压不过牌桌上的"飞机"
	 */
	public static boolean commpareTwoFeiJi(int[] oneFeiJi, int[] otherFeiJi) {
		boolean flag = false;
		if (otherFeiJi[0] / 10 == 1) {// otherFeiJi牌"飞机"中含有牌AAA
			flag = false;
		} else if (oneFeiJi[0] / 10 == 1) {// oneFeiJi牌"飞机"中含有牌AAA
			flag = true;
		} else if (oneFeiJi[0] / 10 > otherFeiJi[0] / 10) {// oneFeiji压过otherFeiJi
			flag = true;
		} else {// oneFeiJi压不住otherFeiJi
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断两个"连对"的大小
	 * 
	 * @param oneFeiJi
	 *            玩家出的"连对"(由小到大排序)
	 * @param otherFeiJi
	 *            牌桌上的"连对"（由小到大排序）
	 * @return true:玩家的"连对"压过牌桌上的"连对" false:玩家的"连对"压不过牌桌上的"连对"
	 */
	public static boolean commpareTwoLianDui(int[] oneLianDui,
			int[] otherLianDui) {
		boolean flag = false;
		if (otherLianDui[0] / 10 == 1) {// otherLianDui"连对"中含有AA
			flag = false;
		} else if (oneLianDui[0] / 10 == 1) {// oneLianDui"连对"中含有AA
			flag = true;
		} else if (oneLianDui[0] / 10 > otherLianDui[0] / 10) {// oneLianDui"连对"压过otherLianDui"连对"
			flag = true;
		} else {// oneLianDui"连对"压不住otherLianDui"连对"
			flag = false;
		}
		return flag;
	}

	/**
	 * 移除"四带二"中被带的那两张牌
	 * 
	 * @param pais
	 * @return
	 */
	private static int[] removeFourBandTwoTwoPais(int[] pais) {

		return removeFourPaisTwoPais(pais, 0, new int[4], 0);

	}

	/**
	 * 得到"四带二"中的被带的那两张牌
	 * 
	 * @param pais
	 * @return
	 */
	private static int[] getFourBandTwoTwoPais(int[] pais) {
		int[] result = new int[2];
		return result;
	}

	/**
	 * 移除"四带二"中的被带的两张牌
	 * 
	 * @param pais
	 * @return
	 */
	private static int[] removeFourPaisTwoPais(int[] pais, int num,
			int[] newPais, int index) {
		if (num == 3) {
			return newPais;
		} else if (pais[index] == pais[index + 1]) {
			newPais[num] = pais[index];
			++num;
			return removeFourPaisTwoPais(pais, num, newPais, index + 1);
		} else {
			return removeFourPaisTwoPais(newPais, 0, newPais, index + 1);
		}
	}

	/**
	 * 判断两个"四带二"的大小
	 * 
	 * 
	 * @param handPais
	 *            玩家出的"四带二"的牌
	 * @param otherPais
	 *            牌桌上的"四带二"的牌
	 * @return true:玩家出的牌压住牌桌上的牌 false:玩家出的牌压不住牌桌上的牌
	 */

	public static boolean commpareFourPaisBandTwoPai(int[] handPais,
			int[] otherPais) {
		return commpareTwoPai(removeFourBandTwoTwoPais(handPais)[0],
				removeFourBandTwoTwoPais(otherPais)[0]);
	}

	/**
	 * 判断两个"飞机带二"的大小
	 * 
	 * @param handPais
	 *            玩家出的"飞机带二"(由小到大排序)
	 * @param otherPais
	 *            牌桌上的"飞机带二"(由小到大排序)
	 * @return true:玩家出的"飞机带二"压住牌桌上的"飞机" false:玩家出的"飞机带二"压不住牌桌上的"飞机带二"
	 */
	public static boolean commpareFeiJiBandTwoPai(int[] handPais,
			int[] otherPais) {
		return commpareTwoFeiJi(removeFeiJiBandTwoPais(handPais),
				removeFeiJiBandTwoPais(otherPais));
	}

	/**
	 * 移除"飞机带二"中的被带的那两张牌
	 * 
	 * @return
	 */
	private static int[] removeFeiJiBandTwoPais(int[] pais) {
		return removeFeiJiBandTwoPais(pais, new int[6], 0, 0);
	}

	private static int[] removeFeiJiBandTwoPais(int[] pais, int[] newPais,
			int index, int num) {
		if (num == 6) {
			return newPais;
		} else if (num == 3) {
			return removeFeiJiBandTwoPais(pais, newPais, index + 1, num + 1);
		} else if (pais[index] == pais[index + 1]) {
			newPais[num] = pais[index];
			return removeFeiJiBandTwoPais(pais, newPais, index + 1, num + 1);
		} else {
			return removeFeiJiBandTwoPais(pais, newPais, index + 1, num);
		}

	}
}

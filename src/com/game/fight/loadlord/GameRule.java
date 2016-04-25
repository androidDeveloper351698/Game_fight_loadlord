package com.game.fight.loadlord;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏规则
 * 
 * @author wangliang
 * 
 */
public class GameRule {

	/**
	 * 牌桌，存放玩家每次出的牌
	 */
	public List<int[]> paiZHuo = new ArrayList<int[]>();

	public boolean isCanChuPai(Gamers paramWanjia) {
		boolean flag = false;
		if (paramWanjia.operationStatus.compareTo("出牌") == 0) {
			// 玩家出的牌如符合游戏规则，可以出牌，不符合游戏规则，不可以出牌
			return guize(paramWanjia.chuPai);
		} else {
			// 玩家不可以出牌

		}
		return flag;
	}

	/**
	 * 判断玩家出的牌是否符合规则
	 * 
	 * @param handPai
	 *            玩家出的牌
	 * @return true:符合规则 false:不符合规则
	 */
	private boolean guize(int[] handPai) {
		// 对玩家出的牌由小到大排序
		handPai = sortBubblingFromMinToMax(handPai);
		boolean flag = false;
		int[] lastHandPais = null;
		List<String> lastHandPaisStr = null;
		if (!paiZHuo.isEmpty()) {
			lastHandPais = paiZHuo.get(paiZHuo.size() - 1);
			lastHandPaisStr = CardUtil.transitionToList(lastHandPais);
		}
		List<String> handPaiStr = CardUtil.transitionToList(handPai);
		switch (handPai.length) {
		case 1:// 玩家出一张牌
			if (paiZHuo.isEmpty()) {// 牌桌上没有牌
				System.out.println("一张 " + handPaiStr);
				flag = true;
			} else if (lastHandPais.length == 1) {// 牌桌上一张牌
				int lastHandPai = lastHandPais[0];
				flag = GameRuleUtil.commpareTwoPai(handPai[0], lastHandPai);
				if (flag == true) {
					System.out.println("一张 " + handPaiStr + " 压住 一张 "
							+ lastHandPaisStr);
				} else {
					System.out.println("一张 " + handPaiStr + " 压不住 一张 "
							+ lastHandPaisStr);
				}
			} else {// 牌桌上大于一张牌
				System.out.println("一张 " + handPaiStr + " 压不住 "
						+ lastHandPaisStr);
				flag = false;
			}
			break;
		case 2:// 玩家出两张牌
			if (isXEqual(handPai, 0, 1, 2)) {// 一对,例如：AA

				if (paiZHuo.isEmpty()) {// 牌桌上没有牌
					System.out.println("一对 " + handPaiStr);
					flag = true;
				} else if (handPai[0] / 10 == 0 && handPai[1] / 10 == 0) {// 玩家出的"大小鬼"
					System.out.println("俩鬼 压住 " + lastHandPaisStr);
					flag = true;
				} else if (lastHandPais.length == 2) {// 牌桌上两张牌
					int lastHandPai = lastHandPais[0];
					flag = GameRuleUtil.commpareTwoPai(handPai[0], lastHandPai);
					if (flag == true) {
						System.out.println("\"一对\"  " + handPaiStr + " 压住 "
								+ lastHandPaisStr);
					} else {
						System.out.println("\"一对\" " + handPaiStr
								+ " 压不住 \"一对\"" + lastHandPaisStr);
					}
				} else {// 牌桌上不是两张牌
					System.out.println("\"一对\"  " + handPaiStr + " 压不住 "
							+ lastHandPaisStr);
					flag = false;
				}
			} else {// 玩家出的两张牌:不符合规则
				System.out.println("两张牌不是一对，不符合游戏规则");
				flag = false;
			}

			break;
		case 3:// 玩家出3张牌
			if (isXEqual(handPai, 0, 1, 3)) {// 例如：AAA
				if (paiZHuo.isEmpty()) {// 牌桌上没有牌
					System.out.println("\"三不带\"  " + handPaiStr);
					flag = true;
				} else if (lastHandPais.length == 3) {// 牌桌上是三张牌
					int lastHandPai = lastHandPais[0];
					flag = GameRuleUtil.commpareTwoPai(handPai[0], lastHandPai);
					if (flag == true) {
						System.out.println("\"三不带\"  " + handPaiStr
								+ " 压住 \"三不带\"" + lastHandPaisStr);
					} else {
						System.out.println("\"三不带\"  " + handPaiStr
								+ " 压不住 \"三不带\"" + lastHandPaisStr);
					}
				} else {// 牌桌上不是三张牌
					System.out.println("\"三不带\"  " + handPaiStr + " 压不住 "
							+ lastHandPaisStr);
					flag = false;
				}
			} else {// 玩家出的三张牌:不符合规则
				System.out.println("三张牌 " + handPaiStr + " 不是\"三不带\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 4:// 玩家出4张牌
			if (isXEqual(handPai, 0, 1, 4)) {// 四张牌:"一炸"
				if (paiZHuo.isEmpty()) {// 牌桌为空
					System.out.println("\"一炸\" " + handPaiStr);
					flag = true;

				} else if (lastHandPais.length == 2
						&& lastHandPais[0] / 10 == 0
						&& lastHandPais[1] / 10 == 0) {// 桌牌上是"俩鬼"
					System.out.println("\"一炸\"  " + handPaiStr + " 压不住 \"两鬼\" "
							+ lastHandPaisStr);
				} else if (!isXEqual(lastHandPais, 0, 1, 4)) {// 牌桌上不是"一炸"
					System.out.println("\"一炸\" " + handPaiStr + " 压住 "
							+ lastHandPaisStr);
					flag = true;
				} else if (GameRuleUtil.commpareTwoPai(handPai[0],
						lastHandPais[0])) {// 玩家的炸大于牌桌上的"一炸"
					System.out.println("\"一炸\" " + handPaiStr + " 压住 \"一炸\" "
							+ lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"一炸\" " + handPaiStr + " 压不住 \"一炸\" "
							+ lastHandPaisStr);
					flag = false;
				}

			} else if (isXEqual(handPai, 0, 1, 3)) {// 四张牌："三带一"
				if (paiZHuo.isEmpty()) {// 牌桌为空
					System.out.println("\"三带一\"" + handPaiStr);
					flag = true;

				} else if (!isXEqual(lastHandPais, 0, 1, 3)) {// 牌桌上不是"三带一"
					System.out.println("\"三带一\"  " + handPaiStr + " 压不住 "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil.commpareTwoPai(
						GameRuleUtil.getEqualInt(handPai),
						GameRuleUtil.getEqualInt(lastHandPais))) {// 玩家出的"三带一"压住牌桌上的"三带一"
					System.out.println("\"三带一\"  " + handPaiStr
							+ " 压住 \"三带一\" " + lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"三带一\"  " + handPaiStr
							+ " 压不住 \"三带一\" " + lastHandPaisStr);
					flag = false;
				}
			} else if (handPai[0] == 0 && handPai[1] == 1) {// 四张牌:两鬼带两张
				if (paiZHuo.isEmpty()) {
					System.out.println("\"两鬼带二\" " + handPaiStr);
					flag = true;
				} else {
					System.out.println("\"两鬼带二\" " + handPaiStr + " 压不住 "
							+ lastHandPaisStr);
					flag = false;
				}
			} else {// 玩家出的四张牌:不符合规则
				System.out.println("四张牌 " + handPaiStr
						+ " 不是\"三带一\" or \"一炸\" or  \" 两鬼带二\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 5:// 玩家出5张牌
				// 五张牌按照规则，只能是顺子
			if (isShunZi(handPai)) {// 五张牌：顺子
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"顺子\" " + handPaiStr);
					flag = true;

				} else if (isShunZi(lastHandPais)) {// 牌桌上的不是顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil
						.commpareTwoShunZi(handPai, lastHandPais)) {// 判断玩家出的顺子是否压过牌桌上的顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压住 "
							+ "\"顺子\" " + lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 "
							+ "\"顺子\" " + lastHandPaisStr);
					flag = false;
				}
			} else {// 五张牌：不符合规则
				System.out.println("五张牌 " + handPaiStr + " 不是\"顺子\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 6:// 玩家出6张牌
			if (isShunZi(handPai)) {// 六张牌：顺子
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"顺子\" " + handPaiStr);
					flag = true;
				} else if (isShunZi(lastHandPais)) {// 牌桌上的不是顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil
						.commpareTwoShunZi(handPai, lastHandPais)) {// 玩家出的顺子压过牌桌上的顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压住 "
							+ "\"顺子\" " + lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 "
							+ "\"顺子\" " + lastHandPaisStr);
					flag = false;
				}
			} else if (isFeiji(handPai)) {// 六张牌：飞机
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"飞机\" " + handPaiStr);
					flag = true;
				} else if (isFeiji(lastHandPais)) {// 牌桌上的不是飞机
					System.out.println("\"飞机\" " + handPaiStr + " 压不住 "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil.commpareTwoFeiJi(handPai, lastHandPais)) {// 玩家出的飞机压过牌桌上的飞机
					System.out.println("\"飞机\" " + handPaiStr + " 压住 \"飞机\""
							+ lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"飞机\" " + handPaiStr + " 压不住 \"飞机\""
							+ lastHandPaisStr);
					flag = false;
				}
			} else if (isLiandui(handPai)) {// 六张牌：三连对
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"三连对\" " + handPaiStr);
					flag = true;
				} else if (isLiandui(lastHandPais)) {// 牌桌上的不是连对
					System.out.println("\"三连对\" " + handPaiStr + " 压不住 "
							+ handPaiStr);
					flag = false;
				} else if (GameRuleUtil.commpareTwoLianDui(handPai,
						lastHandPais)) {// 玩家出的连对压过牌桌上的连对
					System.out.println("\"三连对\" " + handPaiStr
							+ " 压住  \"三连对\" " + handPaiStr);
					flag = true;
				} else {
					System.out.println("\"三连对\" " + handPaiStr
							+ " 压不住  \"三连对\" " + handPaiStr);
					flag = false;
				}
			} else if (isXEqual(handPai, 0, 1, 4)) {// 六张牌：四带二
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"四带二\" " + handPaiStr);
					flag = true;
				} else if (!isXEqual(lastHandPais, 0, 1, 4)) {// 牌桌上不是四带二
					System.out.println("\"四带二\" " + handPaiStr + " 压不住 "
							+ lastHandPaisStr);
					flag = false;

				} else if (GameRuleUtil.commpareFourPaisBandTwoPai(handPai,
						lastHandPais)) {// 玩家出的四带二压过桌牌上的四带二
					System.out.println("\"四带二\" " + handPaiStr + " 压住 \"四带二\" "
							+ lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"四带二\" " + handPaiStr
							+ " 压不住 \"四带二\" " + lastHandPaisStr);
					flag = false;
				}
			} else {// 玩家出的六张牌：不符合规则
				System.out.println("六张牌 " + handPaiStr
						+ " 不是\"顺子\" or \"四带二\" or \"三连对\" or \"飞机\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 7:// 玩家出7张牌
			if (isShunZi(handPai)) {// 七张牌：顺子
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"顺子\" " + handPaiStr);
					flag = true;
				} else if (isShunZi(lastHandPais)) {// 牌桌上的不是顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压住 "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil
						.commpareTwoShunZi(handPai, lastHandPais)) {// 判断玩家出的顺子是否压过牌桌上的顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压住 \"顺子\" "
							+ lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 \"顺子\" "
							+ lastHandPaisStr);
					flag = false;
				}
			} else {// 七张牌：不符合规则
				System.out.println("七张牌 " + handPaiStr + " 不是\"顺子\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 8:// 玩家出8张牌
			if (isShunZi(handPai)) {// 八张牌:顺子
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"顺子\" " + handPaiStr);
					flag = true;
				} else if (isShunZi(lastHandPais)) {// 牌桌上的不是顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压不住  "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil
						.commpareTwoShunZi(handPai, lastHandPais)) {// 玩家出的顺子是否压过牌桌上的顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压住 \"顺子\" "
							+ lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 \"顺子\" "
							+ lastHandPaisStr);
					flag = false;
				}
			} else if (isFeijiBandTwo(handPai)) {// 八张牌：飞机带两张
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"飞机带二\" " + handPaiStr);
					flag = true;
				} else if (!isFeijiBandTwo(lastHandPais)) {// 牌桌上不是飞机带两张
					System.out.println("\"飞机带二\" " + handPaiStr + " 压不住  "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil.commpareFeiJiBandTwoPai(handPai,
						lastHandPais)) {// 玩家出的飞机带两张压过牌桌上的飞机带两张
					System.out.println("\"飞机带二\" " + handPaiStr
							+ " 压住  \"飞机带二\" " + lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"飞机带二\" " + handPaiStr
							+ " 压不住  \"飞机带二\" " + lastHandPaisStr);
					flag = false;
				}
			} else if (isLiandui(handPai)) {// 八张牌：四连对
				if (paiZHuo.isEmpty()) {
					System.out.println("\"四连对\" " + handPaiStr);
					flag = true;
				} else if (isLiandui(lastHandPais)) {// 牌桌上的不是连对
					System.out.println("\"四连对\" " + handPaiStr + " 压不住  "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil.commpareTwoLianDui(handPai,
						lastHandPais)) {// 玩家出的连对压过牌桌上的连对
					System.out.println("\"四连对\" " + handPaiStr
							+ " 压住  \"四连对\" " + lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"四连对\" " + handPaiStr
							+ " 压不住  \"四连对\" " + lastHandPaisStr);
					flag = false;
				}
			} else {// 玩家出的八张牌：不符合规则
				System.out.println("八张牌 " + handPaiStr
						+ " 不是\"顺子\" or \"四连对\" or \"飞机带二\" or \"\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 9:// 玩家出9张牌
			if (isShunZi(handPai)) {// 九张牌：顺子
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"顺子\" " + handPaiStr);
					flag = true;
				} else if (isShunZi(lastHandPais)) {// 牌桌上的不是顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil
						.commpareTwoShunZi(handPai, lastHandPais)) {// 判断玩家出的顺子是否压过牌桌上的顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压住 \"顺子\" "
							+ lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 \"顺子\" "
							+ lastHandPaisStr);
					flag = false;
				}
			} else {// 玩家出的九张牌：不符合规则
				System.out.println("九张牌 " + handPaiStr + " 不是\"顺子\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 10:// 玩家出10张牌
			if (isShunZi(handPai)) {// 十张牌：顺子
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"顺子\" " + handPaiStr);
					flag = true;
				} else if (isShunZi(lastHandPais)) {// 牌桌上的不是顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil
						.commpareTwoShunZi(handPai, lastHandPais)) {// 判断玩家出的顺子是否压过牌桌上的顺子
					flag = true;
					System.out.println("\"顺子\" " + handPaiStr + " 压住 \"顺子\" "
							+ lastHandPaisStr);
				} else {
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 \"顺子\" "
							+ lastHandPaisStr);
					flag = false;
				}
			} else if (isLiandui(handPai)) {// 十张牌： 五连对
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"五连对\" " + handPaiStr);
					flag = true;
				} else if (isLiandui(lastHandPais)) {// 牌桌上的不是连对
					System.out.println("\"五连对\" " + handPaiStr + " 压不住 "
							+ handPaiStr);
					flag = false;
				} else if (GameRuleUtil.commpareTwoLianDui(handPai,
						lastHandPais)) {// 玩家出的连对压过牌桌上的连对
					System.out.println("\"五连对\" " + handPaiStr
							+ " 压住 \"五连对\"  " + handPaiStr);
					flag = true;
				} else {
					System.out.println("\"五连对\" " + handPaiStr
							+ " 压不住 \"五连对\"  " + handPaiStr);
					flag = false;
				}
			} else {// 玩家出的十张牌：不符合规则
				System.out.println("十张牌 " + handPaiStr
						+ " 不是\"顺子\" or  \"五连对\" ，不符合游戏规则");
				flag = false;
			}
			break;
		case 11:// 玩家出11张牌
			if (isShunZi(handPai)) {// 十一张牌：顺子
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"顺子\" " + handPaiStr);
					flag = true;
				} else if (isShunZi(lastHandPais)) {// 牌桌上的不是顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压住 "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil
						.commpareTwoShunZi(handPai, lastHandPais)) {// 判断玩家出的顺子是否压过牌桌上的顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压住 \"顺子\" "
							+ lastHandPaisStr);
					flag = true;
				} else {
					flag = false;
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 \"顺子\" "
							+ lastHandPaisStr);
				}
			} else {// 玩家出的十一张牌：不符合规则
				System.out.println("十一张牌 " + handPaiStr + " 不是\"顺子\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 12:// 玩家出12张牌
			if (isShunZi(handPai)) {// 十二张牌：顺子
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"顺子\" " + handPaiStr);
					flag = true;
				} else if (isShunZi(lastHandPais)) {// 牌桌上的不是顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压不住  "
							+ lastHandPaisStr);
					flag = false;
				} else if (GameRuleUtil
						.commpareTwoShunZi(handPai, lastHandPais)) {// 判断玩家出的顺子是否压过牌桌上的顺子
					System.out.println("\"顺子\" " + handPaiStr + " 压住 \"顺子\" "
							+ lastHandPaisStr);
					flag = true;
				} else {
					System.out.println("\"顺子\" " + handPaiStr + " 压不住 \"顺子\" "
							+ lastHandPaisStr);
					flag = false;
				}
			} else if (isLiandui(handPai)) {// 十二张牌： 六连对
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"六连对\" " + handPaiStr
							+ " 压住 \"六连对\"  " + handPaiStr);
					flag = true;
				} else if (isLiandui(lastHandPais)) {// 牌桌上的不是连对
					System.out.println("\"六连对\" " + handPaiStr + " 压不住   "
							+ handPaiStr);
					flag = false;
				} else if (GameRuleUtil.commpareTwoLianDui(handPai,
						lastHandPais)) {// 玩家出的连对压过牌桌上的连对
					System.out.println("\"六连对\" " + handPaiStr
							+ " 压住 \"六连对\"  " + handPaiStr);
					flag = true;
				} else {
					System.out.println("\"六连对\" " + handPaiStr
							+ " 压不住 \"六连对\"  " + handPaiStr);
					flag = false;
				}
			} else {// 玩家出的十二张牌：不符合规则
				System.out.println("十二张牌 " + handPaiStr
						+ " 不是\"顺子\" or \"六连对\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 13:// 玩家出13张牌
			System.out.println("不能出十三张牌 " + handPaiStr + " ，不符合游戏规则");
			flag = false;
			break;
		case 14:// 玩家出14张牌
			if (isLiandui(handPai)) {// 十四张牌：七连对
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"七连对\" " + handPaiStr);
					flag = true;
				} else if (isLiandui(lastHandPais)) {// 牌桌上的不是连对
					System.out.println("\"七连对\" " + handPaiStr + " 压不住 "
							+ handPaiStr);
					flag = false;
				} else if (GameRuleUtil.commpareTwoLianDui(handPai,
						lastHandPais)) {// 玩家出的连对压过牌桌上的连对
					System.out.println("\"七连对\" " + handPaiStr
							+ " 压住 \"七连对\"   " + handPaiStr);
					flag = true;
				} else {
					System.out.println("\"七连对\" " + handPaiStr
							+ " 压不住 \"七连对\"   " + handPaiStr);
					flag = false;
				}
			} else {// 玩家出的十四张牌：不符合规则
				System.out.println("十四张牌 " + handPaiStr + " 不是\"七连对\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 15:// 玩家出15张牌
			System.out.println("不能出十五张牌 " + handPaiStr + " ，不符合游戏规则");
			flag = false;
			break;
		case 16:// 玩家出16张牌
			if (isLiandui(handPai)) {// 十六张牌：八连对
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"八连对\" " + handPaiStr);
					flag = true;
				} else if (isLiandui(lastHandPais)) {// 牌桌上的不是连对
					System.out.println("\"八连对\" " + handPaiStr + " 压不住 "
							+ handPaiStr);
					flag = false;
				} else if (GameRuleUtil.commpareTwoLianDui(handPai,
						lastHandPais)) {// 玩家出的连对压过牌桌上的连对
					System.out.println("\"八连对\" " + handPaiStr
							+ " 压住 \"八连对\"   " + handPaiStr);
					flag = true;
				} else {
					System.out.println("\"八连对\" " + handPaiStr
							+ " 压不住 \"八连对\"    " + handPaiStr);
					flag = false;
				}
			} else {// 玩家出的十六张牌：不符合规则
				System.out.println("十六张牌 " + handPaiStr + " 不是\"八连对\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 17:// 玩家出17张牌
			System.out.println("不能出十七张牌 " + handPaiStr + " ，不符合游戏规则");
			flag = false;
			break;
		case 18:// 玩家出18张牌
			if (isLiandui(handPai)) {// 十八张牌：九连对
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"九连对\" " + handPaiStr);
					flag = true;
				} else if (isLiandui(lastHandPais)) {// 牌桌上的不是连对
					System.out.println("\"九连对\" " + handPaiStr + " 压不住 "
							+ handPaiStr);
					flag = false;
				} else if (GameRuleUtil.commpareTwoLianDui(handPai,
						lastHandPais)) {// 玩家出的连对压过牌桌上的连对
					System.out.println("\"九连对\" " + handPaiStr
							+ " 压住 \"九连对\"  " + handPaiStr);
					flag = true;
				} else {
					System.out.println("\"九连对\" " + handPaiStr
							+ " 压不住 \"九连对\" " + handPaiStr);
					flag = false;
				}
			} else {// 玩家出的十八张牌：不符合规则
				System.out.println("十八张牌 " + handPaiStr + " 不是\"九连对\"，不符合游戏规则");
				flag = false;
			}
			break;
		case 19:// 玩家出19张牌
			System.out.println("不能出十九张牌 " + handPaiStr + " ，不符合游戏规则");
			flag = true;
			break;
		case 20:// 玩家出20张牌
			if (isLiandui(handPai)) {// 20张牌：十连对
				if (paiZHuo.isEmpty()) {//
					System.out.println("\"十连对\" " + handPaiStr);
					flag = true;
				} else if (isLiandui(lastHandPais)) {// 牌桌上的不是连对
					flag = false;
				} else if (GameRuleUtil.commpareTwoLianDui(handPai,
						lastHandPais)) {// 玩家出的连对压过牌桌上的连对
					flag = true;
				} else {
					flag = false;
				}
			} else {// 玩家出的二十张牌：不符合规则
				System.out.println("二十张牌 " + handPaiStr + " 不是\"十连对\"，不符合游戏规则");
				flag = false;
			}
			break;
		default:
			break;
		}
		return flag;
	}

	/**
	 * 判断有序数组中存在相同元素个数为x个
	 * 
	 * 第一个元素与第二个元素对比
	 * 
	 * 元素相等，统计相等元素的变量加1
	 * 
	 * 元素不相等，统计相等元素的变量归0，重新统计相等元素个数
	 * 
	 * 第二个元素与第三个元素对比
	 * 
	 * @param paramInts
	 *            有序数组(由大到小，或者由小到大)
	 * @param index
	 * @param num
	 * @return
	 */
	private boolean isXEqual(int[] paramInts, int index, int num, int x) {
		if (num == x) {// 有x个相同元素
			return true;
		} else if (index == paramInts.length - 1) {// 对比到最后一个元素，没有可比较的
			// 说明没有四个相同的元素
			return false;
		} else if (paramInts[index] / 10 == paramInts[index + 1] / 10) {// 相邻元素相等
			// 计算相等元素个数变量num加1，继续对比之后的相邻元素
			// ++num先计算赋值给num然后再用
			return isXEqual(paramInts, index + 1, ++num, x);

		} else {
			// 相邻元素不相等，计算相等元素个数变量设置为0，继续对比之后的相邻元素
			return isXEqual(paramInts, index + 1, 1, x);
		}
	}

	/**
	 * 是否为连对
	 * 
	 * @return
	 */
	private boolean isLiandui(int[] handPai) {
		if (handPai.length % 2 != 0) {// 长度不是偶数
			return false;
		}
		// 大小鬼及2不能出现在连对中
		for (int i = 0; i < handPai.length; i++) {
			if (handPai[i] / 10 == 0 || handPai[i] / 10 == 2) {
				return false;
			}
		}
		if (handPai[0] / 10 == 1) {// 有A
			if (handPai[0] / 10 != handPai[1] / 10) {//
				return false;
			} else if (handPai[handPai.length - 1] / 10 == 13) {// 必须有K
				int[] localHandPai = new int[handPai.length - 2];
				for (int i = 0; i < localHandPai.length; i++) {
					localHandPai[i] = handPai[i + 2];
				}
				return isLiandui(localHandPai);
			}

		}
		// 分队对比
		for (int i = 0; i < handPai.length - 1; i = i + 2) {
			if (handPai[i] != handPai[i + 1]) {// 下标i与下标(i + 1)元素是一对
				if (i == handPai.length - 2) {// 最后两个元素
					// 是连对
					return true;
				} else if (handPai[i + 1] / 10 + 1 != handPai[i + 2] / 10) {// 与下组元素不是连对
					// 不是连对
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 判断飞机带两
	 * 
	 * @param handPai
	 * @return
	 */
	private boolean isFeijiBandTwo(int[] handPai) {
		boolean flag = false;
		if (handPai.length != 8) {
			return flag;
		}
		// 飞机带两 aaabbbXY XaaabbbY XYaaabbb三类型
		// aaabbbbXY
		int[] type1 = new int[] {

		handPai[0], handPai[1], handPai[2],

		handPai[3], handPai[4], handPai[5] };
		if (isFeiji(type1) && handPai[5] / 10 != handPai[6] / 10) {// 飞机带两
			return true;
		}
		// XaaabbbY
		int[] type2 = new int[] {

		handPai[1], handPai[2], handPai[3],

		handPai[4], handPai[5], handPai[6] };
		if (isFeiji(type2) && handPai[6] / 10 != handPai[7] / 10
				&& handPai[0] / 10 != handPai[1] / 10) {// 飞机带两
			return true;
		}
		// XYaaaabbb
		int[] type3 = new int[] {

		handPai[2], handPai[3], handPai[4],

		handPai[5], handPai[6], handPai[7] };
		if (isFeiji(type3) && handPai[1] / 10 != handPai[2] / 10) {// 飞机带两
			return true;
		}
		return flag;
	}

	/**
	 * 判断飞机
	 * 
	 * @param handPai
	 * @return
	 */
	private boolean isFeiji(int[] handPai) {
		boolean flag = false;
		if (handPai.length != 6) {
			return flag;
		}
		// 不能含有扑克牌2
		for (int i = 0; i < handPai.length; i++) {
			if (handPai[i] / 10 == 2) {
				return false;
			}
		}
		int[] oneHalf = new int[] { handPai[0], handPai[1], handPai[2] };
		int[] oneHalf2 = new int[] { handPai[3], handPai[4], handPai[5] };
		if (isXEqual(oneHalf, 0, 1, 3) && isXEqual(oneHalf2, 0, 1, 3)) {
			if (handPai[0] / 10 == 1 && handPai[5] / 10 == 13) {// 有A
				flag = true;
			} else if (handPai[0] / 10 + 1 == handPai[5] / 10) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 判断顺子
	 * 
	 * @param handPai
	 * @return
	 */
	private boolean isShunZi(int[] handPai) {
		boolean flag = true;
		if (handPai.length < 5) {// 顺子需要大于等于5张牌
			flag = false;
		} else {
			// 大小鬼及2不能出现在顺子中
			for (int i = 0; i < handPai.length; i++) {
				if (handPai[i] / 10 == 0 || handPai[i] / 10 == 2) {
					return false;
				}
			}
			// 不能有重复的元素
			for (int i = 0; i < handPai.length - 1; i++) {
				if (handPai[i] == handPai[i + 1]) {
					return false;
				}
			}
			if (handPai[0] / 10 == 1) {// 牌中有A
				if ((handPai[handPai.length - 1] / 10 == 13)// 必须有K
						&& (handPai[handPai.length - 1] / 10 - handPai[1] / 10) == handPai.length - 2) {// 顺子，只能是A-......-9-10-J-Q-K
					flag = true;
				} else {
					flag = false;
				}
			} else if ((handPai[handPai.length - 1] / 10 - handPai[0] / 10) == handPai.length - 1) {//
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;

	}

	/**
	 * 冒泡排序， 由小到大
	 * 
	 * @param paramInts
	 * @return
	 */
	public int[] sortBubblingFromMinToMax(int[] paramInts) {
		return sortBubblingFromMinToMax(paramInts, 0, paramInts.length - 1);
	}

	/**
	 * 
	 * @param paramInts
	 * @param index
	 * @param moreIndex
	 * @return
	 */
	public int[] sortBubblingFromMinToMax(int[] paramInts, int index,
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

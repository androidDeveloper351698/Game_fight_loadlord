package com.game.fight.loadlord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import android.test.AndroidTestCase;

import junit.framework.TestCase;

/**
 * 测试程序类
 * 
 * 1.测试洗牌
 * 
 * 2.测试发牌
 * 
 * 3.测试模拟玩家出牌
 * 
 * @author wangliang
 * 
 */
public class TestGame extends AndroidTestCase {
	/**
	 * 测试接口入口
	 * 
	 * @param arg
	 */
	public  void testMain() {
		TestGame localTestGame = new TestGame();
		GameRule localXipai = new GameRule();
		// 洗完之后的扑克牌
		System.out.println("开始洗牌" + "\n");
		String[] xipaiEnd = CardUtil.xipai(CardUtil.pukepai);
		System.out.println("\n");
		System.out.println("洗牌结束" + "\n");

		// 初始化三个玩家对象
		Gamers wanjiaA = new Gamers();
		wanjiaA.name = "路人甲";
		wanjiaA.status = "农民";
		wanjiaA.position = "左";
		wanjiaA.pais = new ArrayList<String>();
		Gamers wanjiaB = new Gamers();
		wanjiaB.name = "路人乙";
		wanjiaB.status = "农民";
		wanjiaB.position = "右";
		wanjiaB.pais = new ArrayList<String>();
		Gamers wanjiaC = new Gamers();
		wanjiaC.name = "官二代C";
		wanjiaC.status = "地主";
		wanjiaC.position = "下";
		wanjiaC.pais = new ArrayList<String>();
		System.out.println("开始发牌，给三名玩家" + "\n");
		// 给三个玩家发牌
		List<Gamers> listWanjias = CardUtil.fapai(xipaiEnd, wanjiaA,
				wanjiaB, wanjiaC);
		System.out.println("发牌结束" + "\n");
		// 分别打印玩家手里的牌
		for (Gamers wanjia : listWanjias) {
			System.out.println("玩家--- " + wanjia.name + " ---手里的牌" + "\n");
			System.out.println(wanjia.pais.toString() + "\n");
		}
		System.out.println("底牌：" + xipaiEnd[53] + " " + xipaiEnd[52] + " "
				+ xipaiEnd[51] + "\n");
		System.out.println("开始抢地主" + "\n");
		System.out.println("玩家 --- " + wanjiaB.name + " --- 抢到地主" + "\n");
		// --------一种情形----
		// 玩家B是地主
		wanjiaB.status = "地主";
		wanjiaB.pais.add(xipaiEnd[53]);
		wanjiaB.pais.add(xipaiEnd[52]);
		wanjiaB.pais.add(xipaiEnd[51]);
		wanjiaA.status = "农民";
		wanjiaC.status = "农民";

		for (Gamers wanjia : listWanjias) {
			System.out.println("玩家--- " + wanjia.name + " ( " + wanjia.status
					+ " ) ---手里的牌" + "\n");
			System.out.println(wanjia.pais.toString() + "\n");
		}

		System.out.println("开始排序，对玩家手里的牌进行由小到大排序" + "\n");
		System.out.println("开始对玩家 " + wanjiaA.name + " 手中的牌排序" + "\n");
		// 玩家A
		wanjiaA.paiInts = CardUtil.transitionToInts(wanjiaA.pais);
		System.out.println("排序中... 玩家 " + wanjiaA.name + " 手中的牌排序 "
				+ wanjiaA.paiInts[0] + "\n");

		wanjiaA.pais.clear();
		wanjiaA.pais.addAll(CardUtil.transitionToList(wanjiaA.paiInts));
		System.out.println("玩家 " + wanjiaA.name + " 手中的牌排序结束" + "\n");

		// 玩家B
		wanjiaB.paiInts = CardUtil.transitionToInts(wanjiaB.pais);
		wanjiaB.pais.clear();
		wanjiaB.pais.addAll(CardUtil.transitionToList(wanjiaB.paiInts));

		// 玩家C
		wanjiaC.paiInts = CardUtil.transitionToInts(wanjiaC.pais);
		wanjiaC.pais.clear();
		wanjiaC.pais.addAll(CardUtil.transitionToList(wanjiaC.paiInts));

		System.out.println("所有玩家排序结束" + "\n");

		System.out.println("打印排序的结果:" + "\n");

		for (Gamers wanjia : listWanjias) {
			System.out.println("玩家--- " + wanjia.name + " ( " + wanjia.status
					+ " ) ---手里的牌" + "\n");
			System.out.println(wanjia.pais.toString() + "\n");
		}
		System.out.println("开始出牌" + "\n");
		System.out.println("玩家地主 --- " + wanjiaA.name + " --- 出牌" + "\n");
		wanjiaA.operationStatus = "出牌";
		System.out.println(wanjiaA.name + " 手里的牌 \n");
		System.out.println(wanjiaA.pais.toString() + "\n");
		System.out.println("请出牌：\n");
		//
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//
		Scanner scan = new Scanner(System.in);
		List<String> pais = null;
		;// localTestGame.readWanJiaChuPais(scan);
			// 出的牌由小到大排序
		int[] paisInt = new int[] { 12 , 11, 13, 32};// localXipai.sortBubblingFromMinToMax(CardUtil
										// .transitionToInts(pais));
		wanjiaA.chuPai = paisInt;

		GameRule localGuize = new GameRule();
		if (localGuize.isCanChuPai(wanjiaA)) {
			localGuize.paiZHuo.add(wanjiaA.chuPai);
			wanjiaA.operationStatus = "等待";
			System.out.println("玩家 " + wanjiaA.name + " 出牌结束" + "\n\n");
			System.out.println("等待玩家 " + wanjiaB.name + "  出牌" + "\n");
			wanjiaB.operationStatus = "出牌";
		} else {
			System.out.println("玩家 " + wanjiaA.name + " 出的牌不符合游戏规则，请重新出牌："
					+ "\n");
			return;
		}
		wanjiaB.chuPai = new int[] { 21, 22, 42, 43 };

		if (localGuize.isCanChuPai(wanjiaB)) {
			localGuize.paiZHuo.add(wanjiaB.chuPai);
			wanjiaA.operationStatus = "等待";
			System.out.println("玩家 " + wanjiaB.name + " 出牌结束" + "\n\n");
			System.out.println("等待玩家 " + wanjiaC.name + "  出牌" + "\n");
			wanjiaB.operationStatus = "出牌";
		} else {
			System.out.println("请重新出牌" + "\n");
		}

	}

	/**
	 * 读取玩家出的牌
	 * 
	 * @return
	 */
	private List<String> readWanJiaChuPais(BufferedReader br) {
		String pais = null;
		try {
			pais = br.readLine();
			System.out.println("从控制台获取到的数据：" + pais);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] strs = pais.split(" ");

		return Arrays.asList(strs);
	}

	/**
	 * 读取玩家出的牌
	 * 
	 * @return
	 */
	private List<String> readWanJiaChuPais(Scanner scan) {
		String pais = null;
		pais = scan.next();
		System.out.println("从控制台获取到的数据：" + pais + "\n");
		String[] strs = pais.split(" ");

		return Arrays.asList(strs);
	}

}

package com.game.fight.loadlord;

import java.util.List;

/**
 * 定义游戏玩家类
 * 
 * @author wangliang
 * 
 */
public class Gamers {
	/**
	 * 玩家唯一ID
	 */
	public int id;
	/**
	 * 玩家名称
	 */
	public String name;
	/**
	 * 玩家身份
	 * 
	 * "农民" / "地主"
	 */
	public String status;
	/**
	 * 玩家手里的牌
	 */
	public List<String> pais;

	public int[] paiInts;
	/**
	 * 玩家在游戏中的位置
	 * 
	 * "1" / "2" / "3"
	 */
	public String position;
	/**
	 * 玩家要出的牌
	 */
	public int[] chuPai;
	/**
	 * 玩家在游行中的状态
	 * 
	 * "出牌" / "等待" / "胜利" / "失败"
	 */
	public String operationStatus;

}

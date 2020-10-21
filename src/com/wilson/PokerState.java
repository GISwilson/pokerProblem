package com.wilson;

import java.util.Map;

/**
 * 扑克的局势，也就是当前牌的状态
 * 从3开始，J、Q、K、A、2、小王和大王分别用11、12、13、14、15、16和17表示
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class PokerState {
	public PokerState(Map<Integer, Integer> cards, PokerState previousState, PokerOperatorType currentType,int turns) {
		this.cards = cards;
		this.previous = previousState;
		this.change = currentType;
		this.turns = turns;
	}

	public Map<Integer, Integer> getCards() {
		return cards;
	}

	/**
	 * 使用Map存储当前的牌，key是牌，value是对应的数量
	 */
	private Map<Integer, Integer> cards;

	public PokerState getPrevious() {
		return previous;
	}

	/**
	 * 前一次操作的牌状态
	 */
	private PokerState previous;

	public PokerOperatorType getChange() {
		return change;
	}

	/**
	 * 变动的牌型，也就是本次出牌的牌型
	 */
	private PokerOperatorType change;

	public int getTurns() {
		return turns;
	}

	/**
	 * 轮数，从0开始递增
	 */
	private int turns;

	@Override
	public String toString() {
		return change == null ? "不出" : change.toString();
	}
}

package com.wilson;

import java.util.List;

/**
 * 扑克可使用的牌型
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public interface PokerOperatorType {
	/**
	 * 获取自由出牌时，该牌型可以出的所有可能牌
	 * @return
	 */
	List<PokerState> getFreeAvailableStates(PokerState pokerState);

	/**
	 * 获取大于当前牌型的所有可能牌
	 * @return
	 */
	List<PokerState> getBiggerAvailableStates(PokerState pokerState, PokerOperatorType current, int turns);

	/**
	 * 牌型描述
	 * @return
	 */
	@Override
	String toString();
}

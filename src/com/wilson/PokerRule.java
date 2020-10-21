package com.wilson;

import java.util.List;

/**
 * 扑克规则类
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public interface PokerRule {
	/**
	 * 获取自由出牌时，可以出的所有可能牌
	 * @param pokerState 当前牌的状态
	 * @return 所有可能出的牌的列表
	 */
	List<PokerState> freeNext(PokerState pokerState);

	/**
	 * 当对方出某种牌型时，可以出的所有可能牌
	 * @param pokerState 当前牌的状态
	 * @param current 对方出的牌型
	 * @param turns 轮数，每下一个操作都需要将轮数+1
	 * @return 所有可能出的牌的列表
	 */
	List<PokerState> next(PokerState pokerState, PokerOperatorType current, int turns);
}

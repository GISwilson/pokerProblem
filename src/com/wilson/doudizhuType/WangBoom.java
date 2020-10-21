package com.wilson.doudizhuType;

import com.wilson.PokerOperatorType;
import com.wilson.PokerState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 王炸，只有1617这一种类型
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class WangBoom implements PokerOperatorType {

	public WangBoom() {

	}

	@Override
	public List<PokerState> getFreeAvailableStates(PokerState pokerState) {
		Map<Integer,Integer> cards = pokerState.getCards();
		List<PokerState> result = new ArrayList<>(1);
		if(cards.containsKey(16) && cards.containsKey(17)){
			Map<Integer,Integer> newCards = new HashMap<>(cards);
			newCards.remove(16);
			newCards.remove(17);
			PokerState newState = new PokerState(newCards,pokerState,new WangBoom(),pokerState.getTurns() + 1);
			result.add(newState);
		}


		return result;
	}

	@Override
	public List<PokerState> getBiggerAvailableStates(PokerState pokerState, PokerOperatorType current, int turns) {
		List<PokerState> result = new ArrayList<>();
		if (current instanceof WangBoom) {
			return result;
		}
		List<PokerState> list = getFreeAvailableStates(pokerState);
		return list.stream().map(state->new PokerState(state.getCards(),state.getPrevious(),state.getChange(),turns+1)).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "王炸";
	}
}

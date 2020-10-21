package com.wilson.doudizhuType;

import com.wilson.PokerOperatorType;
import com.wilson.PokerState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单张，如3
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class Single implements PokerOperatorType {
	public Integer getNumber() {
		return number;
	}

	private Integer number;

	public Single(Integer number) {
		this.number = number;
	}

	@Override
	public List<PokerState> getFreeAvailableStates(PokerState pokerState) {
		Map<Integer, Integer> cards = pokerState.getCards();
		List<PokerState> result = new ArrayList<>(cards.size());
		for (Map.Entry<Integer, Integer> entry : cards.entrySet()) {
			Integer key = entry.getKey();
			Map<Integer, Integer> newCards = new HashMap<>(cards);
			int newValue = entry.getValue() - 1;
			if (newValue > 0) {
				newCards.put(key, newValue);
			} else {
				newCards.remove(key);
			}
			PokerState newState = new PokerState(newCards, pokerState, new Single(key),pokerState.getTurns() + 1);
			result.add(newState);
		}
		return result;
	}

	@Override
	public List<PokerState> getBiggerAvailableStates(PokerState pokerState, PokerOperatorType current,int turns) {
		List<PokerState> result = new ArrayList<>();
		if (!(current instanceof Single)) {
			return result;
		}
		Single single = (Single) current;
		Map<Integer, Integer> cards = pokerState.getCards();
		for (int num = single.getNumber() + 1; num < 18; num++) {
			int newValue = cards.getOrDefault(num, 0) - 1;
			if (newValue >= 0) {
				Map<Integer, Integer> newCards = new HashMap<>(cards);
				Integer key = num;
				if (newValue > 0) {
					newCards.put(key, newValue);
				} else {
					newCards.remove(key);
				}
				PokerState newState = new PokerState(newCards, pokerState, new Single(key),turns + 1);
				result.add(newState);
			}

		}
		return result;
	}

	@Override
	public String toString() {
		return "一个" + number;
	}
}

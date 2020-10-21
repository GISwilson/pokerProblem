package com.wilson.doudizhuType;

import com.wilson.PokerOperatorType;
import com.wilson.PokerState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 四个的炸弹
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class FourthWithNothing implements PokerOperatorType {
	public Integer getNumber() {
		return number;
	}

	private Integer number;

	public FourthWithNothing(Integer number) {
		this.number = number;
	}

	@Override
	public List<PokerState> getFreeAvailableStates(PokerState pokerState) {
		Map<Integer, Integer> cards = pokerState.getCards();
		List<PokerState> result = new ArrayList<>(cards.size());
		for (Map.Entry<Integer, Integer> entry : cards.entrySet()) {
			int newValue = entry.getValue() - 4;
			if (newValue >= 0) {
				Integer key = entry.getKey();
				Map<Integer, Integer> newCards = new HashMap<>(cards);
				if (newValue > 0) {
					newCards.put(key, newValue);
				} else {
					newCards.remove(key);
				}
				PokerState newState = new PokerState(newCards, pokerState, new FourthWithNothing(key),pokerState.getTurns() + 1);
				result.add(newState);
			}
		}
		return result;
	}

	@Override
	public List<PokerState> getBiggerAvailableStates(PokerState pokerState, PokerOperatorType current, int turns) {
		List<PokerState> result = new ArrayList<>();
		if (current instanceof WangBoom) {
			return result;
		}

		if (!(current instanceof FourthWithNothing)) {
			List<PokerState> list = getFreeAvailableStates(pokerState);
			return list.stream().map(state->new PokerState(state.getCards(),state.getPrevious(),state.getChange(),turns+1)).collect(Collectors.toList());
		}
		FourthWithNothing fouth = (FourthWithNothing) current;
		Map<Integer, Integer> cards = pokerState.getCards();
		for (int num = fouth.getNumber() + 1; num < 16; num++) {
			int newValue = cards.getOrDefault(num, 0) - 4;
			if (newValue >= 0) {
				Map<Integer, Integer> newCards = new HashMap<>(cards);
				Integer key = num;
				if (newValue > 0) {
					newCards.put(key, newValue);
				} else {
					newCards.remove(key);
				}
				PokerState newState = new PokerState(newCards, pokerState, new FourthWithNothing(key),turns + 1);
				result.add(newState);
			}

		}
		return result;
	}

	@Override
	public String toString() {
		return "四个" + number;
	}
}

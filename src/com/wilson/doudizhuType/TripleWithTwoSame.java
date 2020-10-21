package com.wilson.doudizhuType;

import com.wilson.PokerOperatorType;
import com.wilson.PokerState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 三带一对，如333带44
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class TripleWithTwoSame implements PokerOperatorType {
	public Integer getNumber() {
		return number;
	}

	private Integer number;

	public Integer getOther() {
		return other;
	}

	private Integer other;

	public TripleWithTwoSame(Integer number, Integer other) {
		this.number = number;
		this.other = other;
	}

	@Override
	public List<PokerState> getFreeAvailableStates(PokerState pokerState) {
		Map<Integer, Integer> cards = pokerState.getCards();
		List<PokerState> result = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : cards.entrySet()) {
			if (entry.getValue() > 2) {
				Integer key = entry.getKey();
				Map<Integer, Integer> newCards = new HashMap<>(cards);
				int newValue = entry.getValue() - 3;
				if (newValue > 0) {
					newCards.put(key, newValue);
				} else {
					newCards.remove(key);
				}
				for (Map.Entry<Integer, Integer> entry1 : cards.entrySet()) {
					Integer anotherKey = entry1.getKey();
					if (anotherKey - key != 0 && entry1.getValue() > 1) {
						int newValue2 = entry1.getValue() - 2;
						if (newValue2 > 0) {
							newCards.put(anotherKey, newValue2);
						} else {
							newCards.remove(anotherKey);
						}
						PokerState newState = new PokerState(newCards, pokerState, new TripleWithTwoSame(key, anotherKey), pokerState.getTurns() + 1);
						result.add(newState);
					}
				}
			}
		}

		return result;
	}

	@Override
	public List<PokerState> getBiggerAvailableStates(PokerState pokerState, PokerOperatorType current, int turns) {
		List<PokerState> result = new ArrayList<>();
		if (!(current instanceof TripleWithTwoSame)) {
			return result;
		}
		TripleWithTwoSame triple = (TripleWithTwoSame) current;
		Map<Integer, Integer> cards = pokerState.getCards();
		for (int num = triple.getNumber() + 1; num < 16; num++) {
			int newValue = cards.getOrDefault(num, 0) - 3;
			if (newValue >= 0) {
				Integer key = num;
				Map<Integer, Integer> newCards = new HashMap<>(cards);
				if (newValue > 0) {
					newCards.put(key, newValue);
				} else {
					newCards.remove(key);
				}
				for (Map.Entry<Integer, Integer> entry1 : cards.entrySet()) {
					Integer anotherKey = entry1.getKey();
					int newValue2 = entry1.getValue() - 2;
					if (anotherKey - key != 0 && newValue2 >= 0) {
						if (newValue2 > 0) {
							newCards.put(anotherKey, newValue2);
						} else {
							newCards.remove(anotherKey);
						}
						PokerState newState = new PokerState(newCards, pokerState, new TripleWithTwoSame(key, anotherKey), turns + 1);
						result.add(newState);
					}
				}
			}

		}
		return result;
	}

	@Override
	public String toString() {
		return "三个" + number + "带一对" + other;
	}
}

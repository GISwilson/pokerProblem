package com.wilson.doudizhuType;

import com.wilson.PokerOperatorType;
import com.wilson.PokerState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 顺子，如34567  7891011121314
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class Shunzi implements PokerOperatorType {
	public Integer getStart() {
		return start;
	}

	private Integer start;
	private Integer length;

	public Shunzi(Integer start, Integer length) {
		this.start = start;
		this.length = length;
	}

	@Override
	public List<PokerState> getFreeAvailableStates(PokerState pokerState) {
		Map<Integer, Integer> cards = pokerState.getCards();
		List<PokerState> result = new ArrayList<>();
		for (int start = 3; start < 11; start++) {
			int length = 0;
			int end = start;
			//最大到A：14
			while (cards.containsKey(end) && end < 15) {
				length += 1;
				if (length > 4) {
					Map<Integer, Integer> newCards = new HashMap<>(cards);
					for (int index = start; index <= end; index++) {
						newCards.put(index, newCards.get(index) - 1);
					}
					PokerState newState = new PokerState(newCards, pokerState, new Shunzi(start, length),pokerState.getTurns() + 1);
					result.add(newState);
				}
				end += 1;
			}
		}

		return result;
	}

	@Override
	public List<PokerState> getBiggerAvailableStates(PokerState pokerState, PokerOperatorType current, int turns) {
		List<PokerState> result = new ArrayList<>();
		if (!(current instanceof Shunzi)) {
			return result;
		}
		Shunzi single = (Shunzi) current;
		Map<Integer, Integer> cards = pokerState.getCards();
		for (int start = single.start + 1; start < 11; start++) {
			int length = 0;
			int end = start;
			//最大到A：14
			while (cards.containsKey(end) && end < 15 && length < single.length) {
				length += 1;
				if (length == single.length) {
					Map<Integer, Integer> newCards = new HashMap<>(cards);
					for (int index = start; index <= end; index++) {
						newCards.put(index, newCards.get(index) - 1);
					}
					PokerState newState = new PokerState(newCards, pokerState, new Shunzi(start, length),turns + 1);
					result.add(newState);
				}
				end += 1;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(length);
		for (int index = start; index < start + length; index++) {
			sb.append(index);
		}
		return sb.toString();
	}
}

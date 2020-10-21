package com.wilson.doudizhuType;

import com.wilson.PokerOperatorType;
import com.wilson.PokerState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 连对，如334455,7788991010
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class Liandui implements PokerOperatorType {
	public Integer getStart() {
		return start;
	}

	private Integer start;
	private Integer length;

	public Liandui(Integer start, Integer length) {
		this.start = start;
		this.length = length;
	}

	@Override
	public List<PokerState> getFreeAvailableStates(PokerState pokerState) {
		Map<Integer, Integer> cards = pokerState.getCards();
		List<PokerState> result = new ArrayList<>();
		for (int start = 2; start < 13; start++) {
			int length = 0;
			int end = start;
			//最大到A：14
			while (cards.getOrDefault(end,0)>1 && end < 15) {
				length += 1;
				if (length > 2) {
					Map<Integer, Integer> newCards = new HashMap<>(cards);
					for (int index = start; index <= end; index++) {
						int newValue = newCards.get(index) - 2;
						if(newValue > 0){
							newCards.put(index, newValue);
						}else{
							newCards.remove(index);
						}
					}
					PokerState newState = new PokerState(newCards, pokerState, new Liandui(start, length),pokerState.getTurns() + 1);
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
		if (!(current instanceof Liandui)) {
			return result;
		}
		Liandui single = (Liandui) current;
		Map<Integer, Integer> cards = pokerState.getCards();
		for (int start = single.start + 1; start < 13; start++) {
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
					PokerState newState = new PokerState(newCards, pokerState, new Liandui(start, length),turns + 1);
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
			sb.append("对" + index);
		}
		return sb.toString();
	}
}

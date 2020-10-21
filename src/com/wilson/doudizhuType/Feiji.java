package com.wilson.doudizhuType;

import com.wilson.PokerOperatorType;
import com.wilson.PokerState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 飞机不带，如333444,777888999
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class Feiji implements PokerOperatorType {
	public Integer getStart() {
		return start;
	}

	private Integer start;
	private Integer length;

	public Feiji(Integer start, Integer length) {
		this.start = start;
		this.length = length;
	}

	@Override
	public List<PokerState> getFreeAvailableStates(PokerState pokerState) {
		Map<Integer,Integer> cards = pokerState.getCards();
		List<PokerState> result = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : cards.entrySet()) {
			if(entry.getValue() > 2){
				Integer key = entry.getKey();
				int length = 1;
				int nextValue = key + 1;
				while(nextValue < 15 && cards.getOrDefault(nextValue,0) > 2){
					Map<Integer,Integer> newCards = new HashMap<>(cards);
					for(int index = key;index < index+length;index++){
						int indexValue = entry.getValue() - 3;
						if(indexValue > 0){
							newCards.put(index,indexValue);
						}else{
							newCards.remove(index);
						}
					}
					PokerState newState = new PokerState(newCards,pokerState,new Feiji(key,length),pokerState.getTurns() + 1);
						result.add(newState);
				}
			}
		}

		return result;
	}

	@Override
	public List<PokerState> getBiggerAvailableStates(PokerState pokerState, PokerOperatorType current, int turns) {
		List<PokerState> result = new ArrayList<>();
		if (!(current instanceof Feiji)) {
			return result;
		}
		Feiji triple = (Feiji)current;
		Map<Integer, Integer> cards = pokerState.getCards();
		for (int num = triple.getStart() + 1; num < 15; num++) {
			int newValue = cards.getOrDefault(num, 0) - 3;
			if (newValue >= 0) {
				int length = 0;
				int nextNum = num + 1;
				while(nextNum < 15 && cards.getOrDefault(newValue,0) > 2 && length < triple.length){
					length+=1;
					if(length == triple.length){
						Map<Integer,Integer> newCards = new HashMap<>(cards);
						for(int index = num;index < num + triple.length;index++){
							int indexValue = cards.getOrDefault(index, 0) - 3;
							if(indexValue > 0){
								newCards.put(index,indexValue);
							}else{
								newCards.remove(index);
							}
						}
						PokerState newState = new PokerState(newCards,pokerState,new Feiji(num,triple.length),turns + 1);
						result.add(newState);
					}
				}

			}

		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(length);
		for (int index = start; index < start + length; index++) {
			sb.append("三个" + index);
		}
		return sb.toString();
	}
}

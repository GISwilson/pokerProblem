package com.wilson.doudizhuType;

import com.wilson.PokerOperatorType;
import com.wilson.PokerState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 三带一，如333带4
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class TripleWithOne implements PokerOperatorType {
	public Integer getNumber() {
		return number;
	}

	private Integer number;

	public Integer getOther() {
		return other;
	}

	private Integer other;

	public TripleWithOne(Integer number,Integer other) {
		this.number = number;
		this.other = other;
	}

	@Override
	public List<PokerState> getFreeAvailableStates(PokerState pokerState) {
		Map<Integer,Integer> cards = pokerState.getCards();
		List<PokerState> result = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : cards.entrySet()) {
			if(entry.getValue() > 2){
				Integer key = entry.getKey();
				for (Map.Entry<Integer, Integer> entry1 : cards.entrySet()){
					Integer anotherKey = entry1.getKey();
					if(anotherKey - key != 0){
						Map<Integer,Integer> newCards = new HashMap<>(cards);
						int newValue = entry.getValue() - 3;
						if(newValue > 0){
							newCards.put(key,newValue);
						}else{
							newCards.remove(key);
						}
						int newValue2 = entry1.getValue() - 1;
						if(newValue2 > 0){
							newCards.put(anotherKey,newValue2);
						}else{
							newCards.remove(anotherKey);
						}
						PokerState newState = new PokerState(newCards,pokerState,new TripleWithOne(key,anotherKey),pokerState.getTurns() + 1);
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
		if (!(current instanceof TripleWithOne)){
			return result;
		}
		TripleWithOne triple = (TripleWithOne) current;
		Map<Integer, Integer> cards = pokerState.getCards();
		for (int num = triple.getNumber() + 1; num < 16; num++) {
			int newValue = cards.getOrDefault(num, 0) - 3;
			if (newValue >= 0) {
				Integer key = num;
				for (Map.Entry<Integer, Integer> entry1 : cards.entrySet()){
					Integer anotherKey = entry1.getKey();
					if(anotherKey - key != 0){
						Map<Integer,Integer> newCards = new HashMap<>(cards);
						if(newValue > 0){
							newCards.put(key,newValue);
						}else{
							newCards.remove(key);
						}
						int newValue2 = entry1.getValue() - 1;
						if(newValue2 > 0){
							newCards.put(anotherKey,newValue2);
						}else{
							newCards.remove(anotherKey);
						}
						PokerState newState = new PokerState(newCards,pokerState,new TripleWithOne(key,anotherKey),turns + 1);
						result.add(newState);
					}
				}
			}

		}
		return result;
	}

	@Override
	public String toString() {
		return "三个" + number + "带" + other;
	}
}

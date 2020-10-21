package com.wilson.doudizhuType;

import com.wilson.PokerOperatorType;
import com.wilson.PokerState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 四带二，如3333带4,5
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class FourthWithTwo implements PokerOperatorType {
	public Integer getNumber() {
		return number;
	}

	private Integer number;

	public Integer getOther() {
		return other;
	}

	private Integer other;

	public Integer getAnother() {
		return another;
	}

	private Integer another;

	public FourthWithTwo(Integer number,Integer other,Integer another) {
		this.number = number;
		this.other = other;
		this.another = another;
	}

	@Override
	public List<PokerState> getFreeAvailableStates(PokerState pokerState) {
		Map<Integer,Integer> cards = pokerState.getCards();
		List<PokerState> result = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : cards.entrySet()) {
			if(entry.getValue() > 3){
				Integer key = entry.getKey();
				Map<Integer,Integer> newCards = new HashMap<>(cards);
				newCards.remove(key);
				for (Map.Entry<Integer, Integer> entry1 : cards.entrySet()){
					Integer anotherKey = entry1.getKey();
					if(anotherKey - key != 0){
						int newValue2 = entry1.getValue() - 1;
						if(newValue2 > 0){
							newCards.put(anotherKey,newValue2);
						}else{
							newCards.remove(anotherKey);
						}
						//使用newCards遍历
						for (Map.Entry<Integer, Integer> entry2 : newCards.entrySet()){
							Integer anotherKey2 = entry2.getKey();
							if(anotherKey2 - key != 0 && anotherKey2 >= anotherKey){
								int newValue3 = entry2.getValue() - 1;
								if(newValue3 > 0){
									newCards.put(anotherKey2,newValue3);
								}else{
									newCards.remove(anotherKey2);
								}
								PokerState newState = new PokerState(newCards,pokerState,new FourthWithTwo(key,anotherKey,anotherKey2),pokerState.getTurns() + 1);
								result.add(newState);
							}
						}
					}
				}
			}
		}
		return result;
	}

	@Override
	public List<PokerState> getBiggerAvailableStates(PokerState pokerState, PokerOperatorType current, int turns) {
		List<PokerState> result = new ArrayList<>();
		if (!(current instanceof FourthWithTwo)) {
			return result;
		}
		FourthWithTwo triple = (FourthWithTwo) current;
		Map<Integer, Integer> cards = pokerState.getCards();
		for (int num = triple.getNumber() + 1; num < 16; num++) {
			int newValue = cards.getOrDefault(num, 0) - 3;
			if (newValue >= 0) {
				Integer key = num;
				Map<Integer,Integer> newCards = new HashMap<>(cards);
				if(newValue > 0){
					newCards.put(key,newValue);
				}else{
					newCards.remove(key);
				}
				for (Map.Entry<Integer, Integer> entry1 : cards.entrySet()){
					Integer anotherKey = entry1.getKey();
					if(anotherKey - key != 0){
						int newValue2 = entry1.getValue() - 1;
						if(newValue2 > 0){
							newCards.put(anotherKey,newValue2);
						}else{
							newCards.remove(anotherKey);
						}
						//使用newCards遍历
						for (Map.Entry<Integer, Integer> entry2 : newCards.entrySet()){
							Integer anotherKey2 = entry2.getKey();
							if(anotherKey2 - key != 0 && anotherKey2 >= anotherKey){
								int newValue3 = entry2.getValue() - 1;
								if(newValue3 > 0){
									newCards.put(anotherKey2,newValue3);
								}else{
									newCards.remove(anotherKey2);
								}
								PokerState newState = new PokerState(newCards,pokerState,new FourthWithTwo(key,anotherKey,anotherKey2),turns + 1);
								result.add(newState);
							}
						}
					}
				}
			}

		}
		return result;
	}

	@Override
	public String toString() {
		return "四个" + number + "带" + other + "和" + another;
	}
}

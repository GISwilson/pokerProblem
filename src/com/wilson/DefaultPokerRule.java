package com.wilson;

import com.sun.istack.internal.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class DefaultPokerRule implements PokerRule {
	public DefaultPokerRule(List<PokerOperatorType> types){
		this.types = types;
	}

	List<PokerOperatorType> types;




	@Override
	public List<PokerState> freeNext(@NotNull PokerState pokerState) {
		List<PokerState> result = new ArrayList<>();
		Map<Integer,Integer> cards = pokerState.getCards();
		if(cards.isEmpty()){
			return new ArrayList<>();
		}
		for(PokerOperatorType type : types){
			result.addAll(type.getFreeAvailableStates(pokerState));
		}
		return result;
	}

	@Override
	public List<PokerState> next(@NotNull PokerState pokerState, PokerOperatorType current, int turns) {
		List<PokerState> result = new ArrayList<>();
		Map<Integer,Integer> cards = pokerState.getCards();
		if(cards.isEmpty()){
			return new ArrayList<>();
		}
		for(PokerOperatorType type : types){
			result.addAll(type.getBiggerAvailableStates(pokerState,current,turns));
		}
		return result;
	}
}

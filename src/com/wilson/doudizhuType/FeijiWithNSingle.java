package com.wilson.doudizhuType;

import com.wilson.PokerOperatorType;
import com.wilson.PokerState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 飞机待N个单张，如333444带5,6  77788899带3,3,4
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class FeijiWithNSingle implements PokerOperatorType {
	public Integer getStart() {
		return start;
	}

	private Integer start;
	private Integer length;

	public FeijiWithNSingle(Integer start, Integer length) {
		this.start = start;
		this.length = length;
	}

	@Override
	public List<PokerState> getFreeAvailableStates(PokerState pokerState) {
		Map<Integer,Integer> cards = pokerState.getCards();
		List<PokerState> result = new ArrayList<>();

		return result;
	}

	@Override
	public List<PokerState> getBiggerAvailableStates(PokerState pokerState, PokerOperatorType current, int turns) {
		List<PokerState> result = new ArrayList<>();
		if (!(current instanceof FeijiWithNSingle)) {
			return result;
		}
		//FeijiWithNSingle triple = (FeijiWithNSingle)current;

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

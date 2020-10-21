package com.wilson;

/**
 * 玩家，姓名用于唯一标识玩家，PokerState用于记录玩家当前牌的状态
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class PokerPlayer {
	public PokerState getPokerState() {
		return pokerState;
	}

	private final PokerState pokerState;

	public PokerPlayer(String name,PokerState pokerState){
		this.name = name;
		this.pokerState = pokerState;
	}

	public String getName() {
		return name;
	}

	private String name;

	@Override
	public String toString() {
		return name + ":" + pokerState.toString();
	}
}

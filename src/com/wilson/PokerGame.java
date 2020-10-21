package com.wilson;

import java.util.ArrayList;
import java.util.List;

/**
 * 扑克游戏，这里仅考虑2个玩家的游戏（常见的斗地主、跑得快残局都符合这种场景）
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class PokerGame {
	public PokerGame(PokerPlayer player1, PokerPlayer player2, PokerRule pokerRule) {
		this.player1 = player1;
		this.player2 = player2;
		this.pokerRule = pokerRule;
	}

	private PokerPlayer player1;
	private PokerPlayer player2;
	private PokerRule pokerRule;

	public PokerPlayer start() {
		if (player1.getPokerState().getCards().isEmpty()) {
			printRecords();
			return player1;
		}
		if (player2.getPokerState().getCards().isEmpty()) {
			printRecords();
			return player2;
		}
		List<PokerState> p1States = pokerRule.freeNext(player1.getPokerState());
		for (PokerState pokerState : p1States) {
			PokerPlayer player = start(pokerState);
			if (player.getName().equals(player1.getName())) {
				return player1;
			}
		}

		return player2;
	}

	public PokerPlayer start(PokerState currentState) {
		if (player1.getPokerState().getCards().isEmpty()) {
			printRecords();
			return player1;
		}
		if (player2.getPokerState().getCards().isEmpty()) {
			printRecords();
			return player2;
		}
		if (currentState.getCards().isEmpty()) {
			printRecords(currentState);
			return player1;
		}
		List<PokerState> p2States = pokerRule.next(player2.getPokerState(), currentState.getChange(), currentState.getTurns());
		//p2无牌可出
		if (p2States.isEmpty()) {

			PokerGame game = new PokerGame(new PokerPlayer(player1.getName(), currentState), player2, pokerRule);
			return game.start();
		} else {
			for (PokerState p2State : p2States) {
				PokerGame game = new PokerGame(new PokerPlayer(player2.getName(), p2State), new PokerPlayer(player1.getName(), currentState), pokerRule);
				PokerPlayer pokerPlayer = game.start(p2State);
				if (pokerPlayer.getName().equals(player2.getName())) {
					return player2;
				}
			}
			//另一种策略，p2可以选择不出
			PokerGame game = new PokerGame(new PokerPlayer(player1.getName(), currentState), player2, pokerRule);
			PokerPlayer pokerPlayerNotPut = game.start();
			if (pokerPlayerNotPut.getName().equals(player2.getName())) {
				return player2;
			} else {
				return player1;
			}
		}
	}

	public void printRecords() {
		PokerState p1State = player1.getPokerState();
		PokerState p2State = player2.getPokerState();
		PokerPlayer winPlayer = p1State.getTurns() > p2State.getTurns() ? player1 : player2;
		PokerPlayer lossPlayer = p1State.getTurns() > p2State.getTurns() ? player2 : player1;
		ArrayList<PokerPlayer> records = new ArrayList<>();
		records.add(winPlayer);
		PokerPlayer currentPlayer = winPlayer;
		PokerPlayer anotherPlayer = lossPlayer;
		int turns = currentPlayer.getPokerState().getTurns();
		while (turns >= 1) {
			PokerState lastState = currentPlayer.getPokerState().getPrevious();
			if (lastState.getTurns() + 1 == turns) {
//				if (anotherPlayer.getPokerState().getTurns() > 0) {
//					records.add(new PokerPlayer(anotherPlayer.getName(), new PokerState(anotherPlayer.getPokerState().getCards(), anotherPlayer.getPokerState().getPrevious(), null, anotherPlayer.getPokerState().getTurns())));
//				}
				if (lastState.getTurns() > 0) {
					records.add(new PokerPlayer(currentPlayer.getName(), lastState));
				}
				currentPlayer = new PokerPlayer(currentPlayer.getName(), lastState);
				turns = currentPlayer.getPokerState().getTurns();
			} else if (anotherPlayer.getPokerState().getTurns() + 1 == turns) {
				PokerPlayer temp = currentPlayer;
				records.add(anotherPlayer);
				currentPlayer = anotherPlayer;
				anotherPlayer = new PokerPlayer(temp.getName(), temp.getPokerState().getPrevious());
				turns = currentPlayer.getPokerState().getTurns();
			} else {
				System.out.println("logic error!");
			}
		}
		System.out.println("==========================================");
		for (int index = records.size() - 1; index >= 0; index--) {
			PokerPlayer record = records.get(index);
			System.out.print(record);
			System.out.print("->");
		}
		System.out.println(winPlayer.getName() + "胜出");
		System.out.println("==========================================");
	}

	public void printRecords(PokerState currentState) {
		PokerState p1State = player1.getPokerState();
		PokerState p2State = player2.getPokerState();
		PokerPlayer winPlayer = p1State.getTurns() > p2State.getTurns() ? player1 : player2;
		PokerPlayer lossPlayer = p1State.getTurns() > p2State.getTurns() ? player2 : player1;
		ArrayList<PokerPlayer> records = new ArrayList<>();
		records.add(new PokerPlayer(winPlayer.getName(),currentState));
		records.add(winPlayer);
		PokerPlayer currentPlayer = winPlayer;
		PokerPlayer anotherPlayer = lossPlayer;
		int turns = currentPlayer.getPokerState().getTurns();
		while (turns >= 1) {
			PokerState lastState = currentPlayer.getPokerState().getPrevious();
			if (lastState.getTurns() + 1 == turns) {
//				if (anotherPlayer.getPokerState().getTurns() >= 0) {
//					records.add(new PokerPlayer(anotherPlayer.getName(), new PokerState(anotherPlayer.getPokerState().getCards(), anotherPlayer.getPokerState().getPrevious(), null, anotherPlayer.getPokerState().getTurns())));
//				}
				if (lastState.getTurns() > 0) {
					records.add(new PokerPlayer(currentPlayer.getName(), lastState));
				}
				currentPlayer = new PokerPlayer(currentPlayer.getName(), lastState);
				turns = currentPlayer.getPokerState().getTurns();
			} else if (anotherPlayer.getPokerState().getTurns() + 1 == turns) {
				PokerPlayer temp = currentPlayer;
				records.add(anotherPlayer);
				currentPlayer = anotherPlayer;
				anotherPlayer = new PokerPlayer(temp.getName(), temp.getPokerState().getPrevious());
				turns = currentPlayer.getPokerState().getTurns();
			} else {
				System.out.println("logic error!");
			}
		}
		System.out.println("==========================================");
		for (int index = records.size() - 1; index >= 0; index--) {
			PokerPlayer record = records.get(index);
			System.out.print(record);
			System.out.print("->");
		}
		System.out.println(winPlayer.getName() + "胜出");
		System.out.println("==========================================");
	}
}

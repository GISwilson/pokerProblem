package com.wilson;

import com.wilson.doudizhuType.Feiji;
import com.wilson.doudizhuType.FourthWithNothing;
import com.wilson.doudizhuType.Liandui;
import com.wilson.doudizhuType.Shunzi;
import com.wilson.doudizhuType.Single;
import com.wilson.doudizhuType.TripleWithNothing;
import com.wilson.doudizhuType.TripleWithOne;
import com.wilson.doudizhuType.TripleWithTwoSame;
import com.wilson.doudizhuType.Tuple;
import com.wilson.doudizhuType.WangBoom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试程序入口
 * @author GISwilson
 * @date 2020/10/3
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
	// write your code here
        List<PokerOperatorType> types = new ArrayList<>();
        types.add(new Single(1));
        types.add(new Tuple(1));
        types.add(new TripleWithNothing(1));
        types.add(new TripleWithOne(1,2));
        types.add(new TripleWithTwoSame(1,2));
        types.add(new Shunzi(3,5));
        types.add(new Liandui(3,3));
        types.add(new FourthWithNothing(3));
        //types.add(new FourthWithOne(3,4));
        //types.add(new FourthWithTwoSame(3,4,5));
        types.add(new Feiji(3,2));
        types.add(new WangBoom());
        PokerRule rule = new DefaultPokerRule(types);

//        test1(rule);
//        test2(rule);
//        test3(rule);
//        test4(rule);
        test5(rule);
    }

    private static void test1(PokerRule rule){
        Map<Integer,Integer> dizhuMap = new HashMap<>(4);
        dizhuMap.put(3,1);
        dizhuMap.put(14,1);
        dizhuMap.put(16,1);
        PokerPlayer pokerPlayer = new PokerPlayer("地主",new PokerState(dizhuMap,null,null,0));

        Map<Integer,Integer> nongminMap = new HashMap<>(4);
        nongminMap.put(4,1);
        //nongminMap.put(5,1);
        nongminMap.put(16,1);
        PokerPlayer pokerPlayer2 = new PokerPlayer("农民",new PokerState(nongminMap,null,null,0));
        PokerGame game = new PokerGame(pokerPlayer,pokerPlayer2,rule);
        PokerPlayer player = game.start();
        System.out.println("胜出者：" + player.getName());
    }

    /**
     * 农民赢
     * @param rule
     */
    private static void test2(PokerRule rule){
        Map<Integer,Integer> dizhuMap = new HashMap<>();
        dizhuMap.put(4,1);
        dizhuMap.put(5,2);
        dizhuMap.put(7,2);
        dizhuMap.put(10,2);
        dizhuMap.put(13,2);
        dizhuMap.put(15,1);
        //dizhuMap.put(16,1);
        PokerPlayer pokerPlayer = new PokerPlayer("地主",new PokerState(dizhuMap,null,null,0));

        Map<Integer,Integer> nongminMap = new HashMap<>();
        nongminMap.put(8,1);
        //nongminMap.put(5,1);
        nongminMap.put(14,2);
        PokerPlayer pokerPlayer2 = new PokerPlayer("农民",new PokerState(nongminMap,null,null,0));
        PokerGame game = new PokerGame(pokerPlayer,pokerPlayer2,rule);
        PokerPlayer player = game.start();
        System.out.println("胜出者：" + player.getName());
    }

    /**
     * 地主赢
     * @param rule
     */
    private static void test3(PokerRule rule){
        Map<Integer,Integer> dizhuMap = new HashMap<>(8);
        dizhuMap.put(3,3);
        dizhuMap.put(4,1);
        dizhuMap.put(5,1);
        dizhuMap.put(14,1);
        dizhuMap.put(17,1);
        //dizhuMap.put(16,1);
        PokerPlayer pokerPlayer = new PokerPlayer("地主",new PokerState(dizhuMap,null,null,0));

        Map<Integer,Integer> nongminMap = new HashMap<>(4);
        nongminMap.put(12,1);
        nongminMap.put(13,1);
        nongminMap.put(15,3);
        nongminMap.put(16,1);
        PokerPlayer pokerPlayer2 = new PokerPlayer("农民",new PokerState(nongminMap,null,null,0));
        PokerGame game = new PokerGame(pokerPlayer,pokerPlayer2,rule);
        PokerPlayer player = game.start();
        System.out.println("胜出者：" + player.getName());
    }

    /**
     * 地主赢
     * @param rule
     */
    private static void test4(PokerRule rule){
        Map<Integer,Integer> dizhuMap = new HashMap<>(4);
        dizhuMap.put(3,1);
        dizhuMap.put(4,1);
        dizhuMap.put(5,2);
        dizhuMap.put(13,2);
        //dizhuMap.put(16,1);
        PokerPlayer pokerPlayer = new PokerPlayer("地主",new PokerState(dizhuMap,null,null,0));

        Map<Integer,Integer> nongminMap = new HashMap<>(4);
        nongminMap.put(3,1);
        nongminMap.put(4,1);
        nongminMap.put(5,2);
        nongminMap.put(15,2);
        PokerPlayer pokerPlayer2 = new PokerPlayer("农民",new PokerState(nongminMap,null,null,0));
        PokerGame game = new PokerGame(pokerPlayer,pokerPlayer2,rule);
        PokerPlayer player = game.start();
        System.out.println("胜出者：" + player.getName());
    }

    /**
     * 地主赢
     * @param rule
     */
    private static void test5(PokerRule rule){
        Map<Integer,Integer> dizhuMap = new HashMap<>(8);
        dizhuMap.put(3,1);
        dizhuMap.put(4,1);
        dizhuMap.put(14,4);
        dizhuMap.put(16,1);
        dizhuMap.put(17,1);
        PokerPlayer pokerPlayer = new PokerPlayer("地主",new PokerState(dizhuMap,null,null,0));

        Map<Integer,Integer> nongminMap = new HashMap<>(4);
        nongminMap.put(11,1);
        nongminMap.put(15,4);
        PokerPlayer pokerPlayer2 = new PokerPlayer("农民",new PokerState(nongminMap,null,null,0));
        PokerGame game = new PokerGame(pokerPlayer,pokerPlayer2,rule);
        PokerPlayer player = game.start();
        System.out.println("胜出者：" + player.getName());
    }
}

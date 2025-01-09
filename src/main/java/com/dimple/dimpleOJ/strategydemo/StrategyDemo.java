package com.dimple.dimpleOJ.strategydemo;

public class StrategyDemo {
    public static void main(String[] args) {
        //使用策略A
        Context context = new Context(new AStrategy());
        Long a = 1L;
        Long execute = context.execute(a);
        System.out.println(execute);

        //切换策略B 提供一个set 方法，方便切换策略，
        context.setStrategy(new BStrategy());
        context.execute(a);



    }



}

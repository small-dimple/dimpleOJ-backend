package com.dimple.dimpleOJ.strategydemo;

/**
 * 上下文：中介，能运行时动态切换策略
 */
//@AllArgsConstructor
public class Context {

    private Strategy strategy;


    //使用构造函数初始化，使得函数必须先有 strategy 才能使用
    public Context(Strategy strategy){
        this.strategy = strategy;
    }


    public void setStrategy(Strategy strategy){

        this.strategy = strategy;
        //这里将存储的实现类返回给它 private Strategy strategy;

    }

    public Long execute(Long abc){
        return strategy.execute(abc);
    }

}

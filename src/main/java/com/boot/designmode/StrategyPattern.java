package com.boot.designmode;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/11 15:09
 * <h>策略模式</h>
 * <p>
 * 1、定义策略统一接口
 * 2、定义策略具体类实现接口方法
 * 3、定义Context(使用策略容器类)，将策略接口类作为私有属性，通过getter/setter模式获得具体的实现类
 * </P>
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Context context = new Context();
        Strategy strategyA = new ConcreteStrategyA();
        Strategy strategyB = new ConcreteStrategyB();
        context.setStrategy(strategyA);
        context.algorithm();
        context.setStrategy(strategyB);
        context.algorithm();
    }
}

interface Strategy {
    public void algorithm();
}

class ConcreteStrategyA implements Strategy {

    @Override
    public void algorithm() {
        System.out.println("来自策略A");
    }
}

class ConcreteStrategyB implements Strategy {

    @Override
    public void algorithm() {
        System.out.println("来自策略B");
    }
}

class Context {
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void algorithm() {
        strategy.algorithm();
    }
}

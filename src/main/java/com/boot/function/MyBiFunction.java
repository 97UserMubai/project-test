package com.boot.function;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/9 17:21
 * <h></h>
 */
public class MyBiFunction {
    public static void main(String[] args) {
        Function<Integer, Integer> function = a -> a * a;
        Function<Integer, Integer> function1 = a -> a + a;
        System.out.println(function.andThen(function1).apply(3)); //18
        System.out.println(function.compose(function1).apply(3)); //36
        IntFunction<Double> function2 = a -> a + 2.00;
        System.out.println(function2.apply(2));
        IntToDoubleFunction function3 = a -> a + 2.00;
        System.out.println(function3.applyAsDouble(2));
    }
}

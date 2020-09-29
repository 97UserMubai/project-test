package com.boot.designmode.singleton;

import org.springframework.context.annotation.Scope;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/11 11:34
 * <h></h>
 * <p>
 *
 * </p>
 */
@Scope
public class Singleton {
    private static volatile Singleton singleton = null;

    private Singleton() {

    }

    public static synchronized Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}

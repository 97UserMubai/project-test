package com.boot.designmode.easyfactory;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/11 11:25
 * <h></h>
 * <p>
 *
 * </p>
 */
public class EasyFactory {
    public static void main(String[] args) {
        Factory factory = new ConcreteFactoryA();
        Product product = factory.createProduct();
        product.user();
    }
}

interface Product {
    public void user();
}

class ConcreteProductA implements Product {

    @Override
    public void user() {
        System.out.println("来自产品A");
    }
}

class ConcreteProductB implements Product {

    @Override
    public void user() {
        System.out.println("来自产品B");
    }
}

interface Factory {
    public Product createProduct();
}

class ConcreteFactoryA implements Factory {

    @Override
    public Product createProduct() {
        System.out.println("工厂A生产产品A");
        return new ConcreteProductA();
    }
}

class ConcreteFactoryB implements Factory {

    @Override
    public Product createProduct() {
        System.out.println("工厂B生产产品B");
        return new ConcreteProductB();
    }
}


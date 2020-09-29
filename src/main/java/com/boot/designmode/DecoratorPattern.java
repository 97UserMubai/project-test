package com.boot.designmode;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/11 14:25
 * <h></h>
 * <p>
 *  1、创建所有修饰器的父接口，定义一个统一的接口修饰方法
 *  2、创建具体的修饰器组件(实现父接口)实现类，可以拥有自己的属性，拥有自己的构造器初始化出行内容，
 *  同时在继承的接口方法中定义自己的修饰行为
 *  3、创建修饰工具类，该工具类(实现父接口)，同时以第二步的实现类为构造器参数，
 *  在继承的接口方法中，使用构造器.method传递组件修饰内容
 *  4、创建修饰对象 extends 工具类，重写method，通过继承父类super.method得到装饰器的内容，并在该重写方法中添加自己的修饰内核
 * </p>
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
       //component.operation();
        Component decorator = new ConreteDecorator(component);
        decorator.operation();
    }
}

interface Component {
    public void operation();
}

class ConcreteComponent implements Component {

    public ConcreteComponent() {
        System.out.println("这个位置需要初始化具体的装饰品内容");
    }

    @Override
    public void operation() {
        System.out.println("该组件装饰器的独特行为");
    }
}

class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        //通过构造器传参获得该修饰组件的行为
        component.operation();
    }
}

class ConreteDecorator extends Decorator {

    public ConreteDecorator(Component component) {
        super(component);
    }

    public void operation() {
        super.operation();
        addBehavior();
    }

    public void addBehavior() {
        System.out.println("修饰类自己的内核行为");
    }
}
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/11/16 17:22
 * <h></h>
 */
public class ThreadTest {
    @Test
    public void test() {
        MyThread thread = new MyThread();
        Thread thread1 = new Thread(new MyThread2());
        thread.start();
        thread1.start();
        System.out.println("我是不用等待的输出");
    }
}

class MyThread extends Thread {
    public void run() {
        System.out.println("我是三秒后的输出");
    }
}

class MyThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("我是run接口");
    }
}

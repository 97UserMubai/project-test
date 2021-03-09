import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2021/1/14
 **/
public class MathTest {
    @Test
    public void test() {
        System.out.println(Math.pow(10, 5));
    }

    @Test
    public void test2() {
        System.out.println(String.valueOf(new BigDecimal("12345.67").intValue()).length());
        long times = 10000;
        long divideTimes = times / 60;
        System.out.println(1 < 60.0);
        System.out.println(divideTimes);
        System.out.println(Math.ceil(times / 60.0));
    }

    @Test
    public void test3() {
        System.out.println(produceOverTime(10));
        System.out.println(produceOverTime(60));  //1.0
        System.out.println(produceOverTime(60 * 60)); //60.0
        System.out.println(produceOverTime(60 * 60 * 24)); //1440.0
        System.out.println(produceOverTime(60 * 60 * 24 * 3)); // 4320
        for (int i = 0; i <= 100; i++) {
            long index = RandomUtils.nextLong(1, 500000);
            System.out.println(index);
            System.out.println(produceOverTime(index));
        }
    }

    private String produceOverTime(long overTime) {
        if (overTime < 60.0) {
            return "0";
        } else {
            //进行加工
            int index = 0;
            double value = overTime;
            do {
                value = value / 60.0;
                index++;
            } while (value >= 24.0 && index < 3);
            switch (index) {
                case 1:
                    return Math.ceil(overTime / 60.0) + "分钟";
                case 2:
                    return Math.ceil(overTime / 60.0 / 60.0) + "小时";
                default:
                    return Math.ceil(overTime / 60.0 / 60.0 / 24.0) + "天";
            }
        }
    }

    @Test
    public void test5(){
        Long chatCnt = 0L;
        Double replayCnt = 1.0;
        System.out.println(replayCnt/chatCnt);
    }
}

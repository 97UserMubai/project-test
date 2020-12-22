import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/30 9:38
 * <h>比较测试</h>
 */
public class CompareTest {
    @Test
    public void compareValue() {
        //进行 == 和 equals值比较的测试
        // == 比较指针，如果是具体的数值比较则是值比较
        String str1 = "str1";
        String str2 = "str1";
        String str3 = new String("str1");
        //true
        System.out.println(str1 == str2);
        //false
        System.out.println(str1 == str3);
        //true
        System.out.println(str1.equals(str2));
        //true
        System.out.println(str1.equals(str3));
        int integer1 = 1;
        int integer2 = 1;
        //true
        System.out.println(integer1 == integer2);
        double double1 = 1.00;
        //true
        System.out.println(integer1 == double1);
    }

    @Test
    public void test2() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime;
        LocalDateTime localDateTime2 = localDateTime1.minusDays(1);
        LocalDateTime localDateTime3 = localDateTime1.plusDays(2);
        System.out.println(localDateTime.compareTo(localDateTime1));
        System.out.println(localDateTime2.compareTo(localDateTime1));
        System.out.println(localDateTime3.compareTo(localDateTime1));
    }
}

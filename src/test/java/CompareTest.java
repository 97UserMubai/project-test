import org.junit.Test;

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
}

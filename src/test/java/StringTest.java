import org.junit.Test;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/11/17 18:27
 * <h></h>
 */
public class StringTest {
    @Test
    public void test() {
        String prefix = "cascader:";
        String key = "cascader:123";
        System.out.println(key.replace(prefix, ""));
        String value = "123";
        System.out.println(value.charAt(0));
        String test = "A123456789";
        String test2 = "A";
        System.out.println(test.substring(0, test2.length() + 3));
    }
    @Test
    public void test1(){
        String str1 = "1234";
        String str2 = "2345";
        System.out.println(str1.compareTo(str2));
    }
}

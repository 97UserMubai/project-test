import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2021/2/8
 **/
public class RegexTest {

    @Test
    public void test(){
        String regex = "^\\d{15}|\\d{19}$";
        System.out.println("6222032017002940278".matches(regex));
        LocalDate localDate = LocalDate.parse("2020-12-31", DateTimeFormatter.ISO_DATE);
        LocalDate localDate2 = LocalDate.parse("2020-08-18", DateTimeFormatter.ISO_DATE);
        System.out.println(localDate.toEpochDay()-localDate2.toEpochDay());
        System.out.println(136.0/365.0*7000*2);
    }
}

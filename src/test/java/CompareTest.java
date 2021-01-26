import com.boot.bean.IecElecBillDataRecord;
import net.sf.cglib.core.Local;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void test3() {
        LocalDateTime localDate = LocalDateTime.now();
        List<IecElecBillDataRecord> records = new ArrayList<>();
        // 5 1 3 2 4
        records.add(IecElecBillDataRecord.builder().id("1").createTime(localDate.minusHours(2)).build());
        records.add(IecElecBillDataRecord.builder().id("2").createTime(localDate.plusHours(2)).build());
        records.add(IecElecBillDataRecord.builder().id("3").createTime(localDate.plusHours(1)).build());
        records.add(IecElecBillDataRecord.builder().id("4").createTime(localDate.plusMonths(2)).build());
        records.add(IecElecBillDataRecord.builder().id("5").createTime(localDate.minusDays(2)).build());
        Collections.sort(records);
        records.forEach(record-> System.out.println(record.getId()));
        System.out.println("debug");
    }

    @Test
    public void test4(){
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.minusDays(30);
        System.out.println(localDate.toEpochDay()-localDate1.toEpochDay());
    }

    @Test
    public void test5(){
        BigDecimal bigDecimal = new BigDecimal("10000");
        BigDecimal bigDecimal1 = new BigDecimal("10000.01");
        System.out.println(bigDecimal.toString());
        System.out.println(bigDecimal1.toString());

    }
}

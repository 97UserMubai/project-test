import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/29 15:06
 * <h>日期类测试</h>
 */
public class DateTest {
    @Test
    public void testWeekOut() {
        //获取星期几的输出格式： TUESDAY
        System.out.println(LocalDate.now().getDayOfWeek());
    }

    @Test
    public void testSecondCompare() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.minusDays(2);
        //进行秒级的单位比较
        long compareSeconds = localDateTime.toEpochSecond(OffsetDateTime.now().getOffset())
                - localDateTime1.toEpochSecond(OffsetDateTime.now().getOffset());
        System.out.println(compareSeconds);
    }

}

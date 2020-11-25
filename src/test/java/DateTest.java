import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    @Test
    public void congraduation() {
        //番薯纪念日
        LocalDate loveDate = LocalDate.parse("2017-01-10", DateTimeFormatter.ISO_DATE);
        System.out.println("恋爱那天：" + loveDate.toString());
        System.out.println("今天是：" + LocalDate.now().toString());
        System.out.println("王狗子和沈咸鱼的恋爱已经" + (LocalDate.now().toEpochDay() - loveDate.toEpochDay()) + "天了");
    }

    @Test
    public void testHourCompare() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.minusHours(2);
        long compareSeconds = localDateTime.toEpochSecond(OffsetDateTime.now().getOffset())
                - localDateTime1.toEpochSecond(OffsetDateTime.now().getOffset());
        System.out.println(compareSeconds);
    }

    @Test
    public void localDatetimeToDate() {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = LocalDateTime.now().atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        System.out.println(date.toString());
    }

}

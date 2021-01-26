import net.sf.cglib.core.Local;
import org.apache.commons.lang3.StringUtils;
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
        LocalDateTime localDateTime2 = LocalDateTime.now();
        LocalDateTime localDateTime3 = localDateTime.minusHours(1).plusSeconds(30);
        System.out.println(localDateTime1.compareTo(localDateTime));
        System.out.println(localDateTime2.compareTo(localDateTime));
        System.out.println(localDateTime3.compareTo(localDateTime));
        //进行秒级的单位比较
        long compareSeconds = localDateTime.toEpochSecond(OffsetDateTime.now().getOffset())
                - localDateTime3.toEpochSecond(OffsetDateTime.now().getOffset());
        System.out.println(compareSeconds - 3600);
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

    @Test
    public void localDateTest() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getDayOfMonth());
        LocalDate curDate = localDate.minusDays(localDate.getDayOfMonth() - 1);
        System.out.println(curDate);
    }

    @Test
    public void timeTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = localDateTime1.minusSeconds(2);
        LocalDateTime localDateTime3 = localDateTime1.plusSeconds(2);
        System.out.println(localDateTime.compareTo(localDateTime1));
        System.out.println(localDateTime.compareTo(localDateTime2));
        System.out.println(localDateTime.compareTo(localDateTime3));
    }

    /**
     * 测试新买的键盘好不好用
     */
    @Test
    public void testLocalDateTIme() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

    }

    @Test
    public void testHour() {
        System.out.println(LocalDateTime.now().getHour());
    }

    @Test
    public void testHour2() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime time2 = currentTime.minusDays(2).minusSeconds(2500);
        long seconds = currentTime.toEpochSecond(OffsetDateTime.now().getOffset())
                - time2.toEpochSecond(OffsetDateTime.now().getOffset());
        System.out.println(Math.ceil(seconds / 3600.00));
    }

    @Test
    public void testMonthValue() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(StringUtils.leftPad(String.valueOf(localDate.getMonthValue()), 2, '0'));
        LocalDate localDate1 = localDate.plusMonths(10);
        System.out.println(StringUtils.leftPad(String.valueOf(localDate1.getMonthValue()), 2, '0'));

    }

}

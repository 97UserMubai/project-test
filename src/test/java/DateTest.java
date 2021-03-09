import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
    public void testTemps() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);
        System.out.println(localDateTime.toEpochSecond(OffsetDateTime.now().getOffset()));
        LocalDateTime localDateTime1 = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN);
        System.out.println(localDateTime1.toEpochSecond(OffsetDateTime.now().getOffset()));
    }

    @Test
    public void testDate() {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(1614700800, 0,
                OffsetDateTime.now().getOffset());
        System.out.println(localDateTime.toString());
    }

    @Test
    public void test10() {
        long endDaySeconds = LocalDateTime.now().toEpochSecond(OffsetDateTime.now().getOffset());
        System.out.println(endDaySeconds);
        long beginDaySeconds = LocalDateTime.now().minusDays(1).toEpochSecond(OffsetDateTime.now().getOffset());
        System.out.println(beginDaySeconds);
    }

    @Test
    public void test22() {
        List<String> testList = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            testList.add("test" + 12);
        }
        List<List<String>> result = split(testList, 20);
        System.out.println("debug");
    }


    /**
     * 拆分集合
     *
     * @param <T>
     * @param resList 要拆分的集合
     * @param count   每个集合的元素个数
     * @return 返回拆分后的各个集合
     */
    private <T> List<List<T>> split(List<T> resList, int count) {
        if (resList == null || count < 1)
            return null;
        List<List<T>> ret = new ArrayList<List<T>>();
        int size = resList.size();
        if (size <= count) { //数据量不足count指定的大小
            ret.add(resList);
        } else {
            int pre = size / count;
            int last = size % count;
            //前面pre个集合，每个大小都是count个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = new ArrayList<T>();
                for (int j = 0; j < count; j++) {
                    itemList.add(resList.get(i * count + j));
                }
                ret.add(itemList);
            }
            //last的进行处理
            if (last > 0) {
                List<T> itemList = new ArrayList<T>();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * count + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }

}

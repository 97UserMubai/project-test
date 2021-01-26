import com.alibaba.fastjson.JSON;
import com.boot.bean.IecElecBizCompletion;
import com.boot.bean.IecElecBizUnitDetails;
import com.boot.bean.Student;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2020/12/15
 **/
public class BeanPropertyTest {
    @Test
    public void test() {
        IecElecBizCompletion fromDb = IecElecBizCompletion
                .builder().id("1111").orderCode("2222").projectName("ceshi").build();
        List<IecElecBizUnitDetails> details = new ArrayList<>();
        details.add(IecElecBizUnitDetails.builder().id("222details").build());
        IecElecBizCompletion fromHtml = IecElecBizCompletion.builder()
                .iecElecBizUnitDetails(details).receiver("recevicer_test").build();
        BeanUtils.copyProperties(fromHtml, fromDb, "id", "orderCode");
        System.out.println("debug");
    }

    @Test
    public void test2() {
        Map<String, String> map = new HashMap<>();
        map.put("address", "金嘉创意谷3栋3楼");
        map.put("time", LocalDateTime.now().toString());
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void test3() {
        Student student = new Student(1, "name", LocalDate.now());
        Student student2 = new Student(2, "name2", LocalDate.now());
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        TestVo testVo = new TestVo();
        testVo.setStudents(students);
        testVo.setTestName("我是测试");
        System.out.println(JSON.toJSONString(testVo));
        System.out.println(LocalDate.parse("2021-01-13"));
        DayOfWeek[] workingDays = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY};
        System.out.println(Arrays.binarySearch(workingDays, LocalDate.parse("2021-01-04").getDayOfWeek()) < 0);
        System.out.println(Arrays.binarySearch(workingDays, LocalDate.parse("2021-01-05").getDayOfWeek()) < 0);
        System.out.println(Arrays.binarySearch(workingDays, LocalDate.parse("2021-01-06").getDayOfWeek()) < 0);
        System.out.println(Arrays.binarySearch(workingDays, LocalDate.parse("2021-01-07").getDayOfWeek()) < 0);
        System.out.println(Arrays.binarySearch(workingDays, LocalDate.parse("2021-01-08").getDayOfWeek()) < 0);
        System.out.println(Arrays.binarySearch(workingDays, LocalDate.parse("2021-01-09").getDayOfWeek()) < 0);
        System.out.println(Arrays.binarySearch(workingDays, LocalDate.parse("2021-01-10").getDayOfWeek()) < 0);
    }
}

class TestVo {
    private String testName;
    private List<Student> students;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}

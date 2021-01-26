import com.boot.bean.Student;
import com.boot.function.ConsumerContext;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/29 15:12
 * <h>Function接口测试</h>
 */
public class FunctionTest {

    @Test
    public void test() {
        ConsumerContext consumerTest = new ConsumerContext();
        Student student = new Student(1, "wbt", LocalDate.now());
        consumerTest.consumetPrint(student, student11 -> {
            student.setId(2);
            student.setLocalDate(LocalDate.now().minusDays(1));
            student.setName("ceshi");
            System.out.println(student.toString());
        });
    }

    @Test
    public void test2() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "test2", null));
        students.add(new Student(1, "test3", LocalDate.now()));
        students.add(new Student(3, "test4", LocalDate.now()));
        students.add(new Student(4, "test5", LocalDate.now()));
        Map<Integer, List<Student>> studentMap = students.stream().collect(Collectors.groupingBy(Student::getId));
        Student student = new Student(1, null, null);
        List<Student> students1 = new ArrayList<>();
        students1.add(student);
        students1.forEach(item -> {
            studentMap.get(item.getId()).stream().findFirst().ifPresent(info->{
                item.setName(info.getName());
            });
        });
        System.out.println("degub");
    }
}

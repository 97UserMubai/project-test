import com.boot.bean.Student;
import com.boot.function.ConsumerContext;
import org.junit.Test;

import java.time.LocalDate;

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
}

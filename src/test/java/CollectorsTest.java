import com.boot.bean.Student;
import com.boot.utils.CollectorsUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/29 15:10
 * <h>Collectors接口测试</h>
 */
public class CollectorsTest {
    private static List<Integer> integerList = new ArrayList<>();
    static {
        integerList.add(-8);
        integerList.add(-7);
        integerList.add(-6);
        integerList.add(-2);
        integerList.add(0);
        integerList.add(8);
        integerList.add(18);
    }

    @Test
    public void test(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "wbt", LocalDate.now().minusDays(2)));
        studentList.add(new Student(2, "wbt3", LocalDate.now()));
        studentList.add(new Student(3, "wbt4", LocalDate.now().plusDays(2)));
        CollectorsUtils collectorsUtils = new CollectorsUtils();
        collectorsUtils.testCollectors(studentList);
        collectorsUtils.testCollectors2(studentList,integerList);
    }
}

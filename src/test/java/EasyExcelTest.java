import com.alibaba.excel.EasyExcel;
import com.boot.bean.Student;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2020/12/11
 **/
public class EasyExcelTest {

    @Test
    public void easyExport() {
        String fileName = "text.xlsx";
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "张三", LocalDate.now(), LocalDateTime.now()));
        students.add(new Student(2, "lisi", LocalDate.now(), LocalDateTime.now()));
        EasyExcel.write(fileName, Student.class).sheet("测试").doWrite(students);
    }
}

import com.boot.bean.Student;
import com.boot.bean.Student2;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/10/19 14:38
 * <h></h>
 */
public class CopyTest {
    @Test
    public void testCopy() {
        Student student = new Student();
        Student2 student2 = Student2.builder().id(111).name("222").tests(new ArrayList<>()).build();
        BeanUtils.copyProperties(student2, student);
        //测试两个实体的堆栈地址是否相同
        System.out.println(student.toString());
    }
}

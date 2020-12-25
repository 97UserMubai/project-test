import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.Objects;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2020/12/25
 **/
public class PathTest {
    public static final String PATH1 = "template" + File.separator;
    public static final String PATH2 = "template" + File.separator + "装拆工作单模板-互感器.xlsx";
    public static final String MODULE_PATH = Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
            .getResource("")).getPath() + "template" + File.separator;

    @Test
    public void test() {
        System.out.println(new ClassPathResource(PATH1).getPath());
        System.out.println(new ClassPathResource(PATH2).getPath());
        System.out.println(MODULE_PATH);

    }
}

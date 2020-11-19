import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/11/12 11:52
 * <h></h>
 */

public class MapTest {
    public static void main(String[] args) {
        //初始化自定义比较器
        MyComparator comparator = new MyComparator();
        //初始化一个map集合
        Map<String, String> map = new TreeMap<String, String>(comparator);
        //存入数据
        map.put("a", "a");
        map.put("b", "b");
        map.put("f", "f");
        map.put("d", "d");
        map.put("c", "c");
        map.put("g", "g");
        //遍历输出
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            System.out.println(map.get(key));
        }
    }
    //倒序
    static class MyComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            // TODO Auto-generated method stub
            String param1 = (String) o1;
            String param2 = (String) o2;
            return -param1.compareTo(param2);
        }
    }
}

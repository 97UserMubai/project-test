import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/10/19 14:33
 * <h></h>
 */
public class JsonTest {
    @Test
    public void testJsonString() {
        List<String> stringList = new ArrayList<>();
        stringList.add("16626406950");
        System.out.println(JSON.toJSONString(stringList));
        System.out.println(JSON.toJSONString("SMS_204855243"));
        System.out.println(JSON.toJSONString("融通共创"));
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("code", "123");
        list.add(map);
        System.out.println(JSON.toJSONString(list));
        String jsonMap = "[{\"code\":\"金额\"}]";
        Map<String, String> reviewMap = (Map<String, String>) JSONObject.parse(jsonMap);
        System.out.println("debug");
    }

    @Test
    public void testJsonObject() {
        String reponce = "{\"TemplateCode\":\"SMS_204805363\",\"Message\":\"OK\",\"RequestId\":\"6C5534FC-7E21-467B-994B-E058CB26BAB7\",\"Code\":\"OK\"}";
        Map map = (Map) JSON.parse(reponce);
        System.out.println(map.get("Code").toString());
        System.out.println("OK".equals(map.get("Code").toString()));
        System.out.println("OK".equals(map.get("Code")));
        System.out.println("debug");
    }

    @Test
    public void testJsonList() {
        List<String> stringList = new ArrayList<>();
        stringList.add("123");
        stringList.add("456");
        stringList.add("789");
        String jsonStr = JSON.toJSONString(stringList);
        System.out.println(jsonStr);
        String strList = "[\"123\",\"456\",\"789\"]";
        List parseJson = (List) JSON.parse(strList);
        System.out.println("debug");
    }
}

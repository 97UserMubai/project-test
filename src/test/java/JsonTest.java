import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.bean.IecMeteringRecordArea;
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

    @Test
    public void testJsonMap() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("code", "123");
        list.add(map);
        System.out.println(JSON.toJSONString(list));
        List<String> list1 = new ArrayList<>();
        list1.add("f89592446ce5143f23e3612ecbe67641");
        list1.add("f8ead8c9a38bbcc7f3c39b00f8efa529");
        System.out.println(JSON.toJSONString(list1));
    }

    @Test
    public void testBeanJson(){
        String str = "{\"code\":\"788954\",\"createTime\":\"2020-11-27T18:03:05\",\"createUser\":\"test-222\",\"custType\":\"02\",\"effectDayType\":\"1\",\"id\":\"11b7a6550fc5317556706567005e84bd\",\"iecCustomerElecUsers\":[{\"address\":\"珠海\",\"changeSupplyType\":\"02\",\"code\":\"1606399721866\",\"contactDate\":\"2020-11-30\",\"contractCapacity\":1,\"custCode\":\"1606399721866\",\"custGroup\":\"\",\"custId\":\"c86449eaafcd5dc4675a18bd069168d3\",\"custManager\":\"黄河\",\"custName\":\"test05\",\"elecType\":\"2\",\"fax\":\"\",\"highConsumeType\":\"\",\"id\":\"624110b60b11f737b03daa02fd43933b\",\"impactLoadFlag\":\"\",\"indemnificatoryHouseArea\":\"\",\"indemnificatoryHouseCapacity\":\"\",\"indemnificatoryHouseFlag\":\"1\",\"industryType\":\"A\",\"loadFlag\":\"0\",\"meterage\":\"1\",\"name\":\"testUser05\",\"newIndustry\":\"\",\"postAddress\":\"\",\"postCode\":\"528400\",\"productClasses\":\"\",\"recordArea\":\"788954\",\"recordNumber\":1,\"reliabilityFlag\":\"1\",\"restDay\":\"2,3\",\"selfFactoryFlag\":\"0\",\"sourceChangeWay\":\"\",\"sourceLockWay\":\"\",\"sourceType\":\"0\",\"stepType\":\"0\",\"tripleProject\":\"\",\"voltageLevel\":\"\"}],\"manageDepartmentId\":\"36b493d8160c960343384e2f03d51787\",\"manageDepartmentName\":\"开发部\",\"managePersonId\":\"603d50b4256ea0a8d056b4a8a8776334\",\"managePersonName\":\"王杰\",\"name\":\"test-area-change\",\"number\":9238,\"penaltyBeginDay\":10,\"period\":\"1\",\"planElecChargeDay\":5,\"recordCycleBeginDay\":\"2021-01-01\",\"recordCycleEndDay\":\"2023-01-01\",\"recordCycleMonths\":24,\"recordDay\":10,\"recordDepartmentId\":\"eddfcbe1dc1b74a473b35f16c3b55332\",\"recordDepartmentName\":\"市场部\",\"recordPersonId\":\"99ba0326c1962d1d943e61a4b34d0fca\",\"recordTimes\":1,\"remark\":\"抄表区段需要变更，请审核\",\"settleType\":\"0\",\"status\":\"0\",\"supplier\":\"jwd\",\"type\":\"11\",\"updateTime\":\"2020-12-01T19:56:37.121\",\"updateUser\":\"bean伯\"}";
        IecMeteringRecordArea iecMeteringRecordArea = JSON.parseObject(str,IecMeteringRecordArea.class);
        System.out.println("debug");
    }
}

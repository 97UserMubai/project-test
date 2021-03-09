import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boot.bean.IecMeteringRecordArea;
import com.boot.vo.*;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    public void testBeanJson() {
        String str = "{\"code\":\"788954\",\"createTime\":\"2020-11-27T18:03:05\",\"createUser\":\"test-222\",\"custType\":\"02\",\"effectDayType\":\"1\",\"id\":\"11b7a6550fc5317556706567005e84bd\",\"iecCustomerElecUsers\":[{\"address\":\"珠海\",\"changeSupplyType\":\"02\",\"code\":\"1606399721866\",\"contactDate\":\"2020-11-30\",\"contractCapacity\":1,\"custCode\":\"1606399721866\",\"custGroup\":\"\",\"custId\":\"c86449eaafcd5dc4675a18bd069168d3\",\"custManager\":\"黄河\",\"custName\":\"test05\",\"elecType\":\"2\",\"fax\":\"\",\"highConsumeType\":\"\",\"id\":\"624110b60b11f737b03daa02fd43933b\",\"impactLoadFlag\":\"\",\"indemnificatoryHouseArea\":\"\",\"indemnificatoryHouseCapacity\":\"\",\"indemnificatoryHouseFlag\":\"1\",\"industryType\":\"A\",\"loadFlag\":\"0\",\"meterage\":\"1\",\"name\":\"testUser05\",\"newIndustry\":\"\",\"postAddress\":\"\",\"postCode\":\"528400\",\"productClasses\":\"\",\"recordArea\":\"788954\",\"recordNumber\":1,\"reliabilityFlag\":\"1\",\"restDay\":\"2,3\",\"selfFactoryFlag\":\"0\",\"sourceChangeWay\":\"\",\"sourceLockWay\":\"\",\"sourceType\":\"0\",\"stepType\":\"0\",\"tripleProject\":\"\",\"voltageLevel\":\"\"}],\"manageDepartmentId\":\"36b493d8160c960343384e2f03d51787\",\"manageDepartmentName\":\"开发部\",\"managePersonId\":\"603d50b4256ea0a8d056b4a8a8776334\",\"managePersonName\":\"王杰\",\"name\":\"test-area-change\",\"number\":9238,\"penaltyBeginDay\":10,\"period\":\"1\",\"planElecChargeDay\":5,\"recordCycleBeginDay\":\"2021-01-01\",\"recordCycleEndDay\":\"2023-01-01\",\"recordCycleMonths\":24,\"recordDay\":10,\"recordDepartmentId\":\"eddfcbe1dc1b74a473b35f16c3b55332\",\"recordDepartmentName\":\"市场部\",\"recordPersonId\":\"99ba0326c1962d1d943e61a4b34d0fca\",\"recordTimes\":1,\"remark\":\"抄表区段需要变更，请审核\",\"settleType\":\"0\",\"status\":\"0\",\"supplier\":\"jwd\",\"type\":\"11\",\"updateTime\":\"2020-12-01T19:56:37.121\",\"updateUser\":\"bean伯\"}";
        IecMeteringRecordArea iecMeteringRecordArea = JSON.parseObject(str, IecMeteringRecordArea.class);
        System.out.println("debug");
    }

    @Test
    public void testQywxJSON() {
        String str = "{\n" +
                "   \"errcode\": 0,\n" +
                "   \"errmsg\": \"ok\",\n" +
                "   \"external_contact_list\":\n" +
                "    [\n" +
                "        {\n" +
                "            \"external_contact\":\n" +
                "            {\n" +
                "                \"external_userid\":\"woAJ2GCAAAXtWyujaWJHDDGi0mACHAAA\",\n" +
                "                \"name\":\"李四\",\n" +
                "                \"position\":\"Manager\",\n" +
                "                \"avatar\":\"http://p.qlogo.cn/bizmail/IcsdgagqefergqerhewSdage/0\",\n" +
                "                \"corp_name\":\"腾讯\",\n" +
                "                \"corp_full_name\":\"腾讯科技有限公司\",\n" +
                "                \"type\":2,\n" +
                "                \"gender\":1,\n" +
                "                \"unionid\":\"ozynqsulJFCZ2z1aYeS8h-nuasdAAA\",\n" +
                "                \"external_profile\":\n" +
                "                {\n" +
                "                 \"external_attr\":\n" +
                "                  [\n" +
                "                      {\n" +
                "                      \"type\":0,\n" +
                "                      \"name\":\"文本名称\",\n" +
                "                       \"text\":\n" +
                "                        {\n" +
                "                           \"value\":\"文本\"\n" +
                "                        }\n" +
                "                      },\n" +
                "                      {\n" +
                "                      \"type\":1,\n" +
                "                      \"name\":\"网页名称\",\n" +
                "                      \"web\":\n" +
                "                      {\n" +
                "                      \"url\":\"http://www.test.com\",\n" +
                "                      \"title\":\"标题\"\n" +
                "                      }\n" +
                "                },\n" +
                "                {\n" +
                "                  \"type\":2,\n" +
                "                  \"name\":\"测试app\",\n" +
                "                  \"miniprogram\":\n" +
                "                  {\n" +
                "                      \"appid\": \"wx8bd80126147df384\",\n" +
                "                      \"pagepath\": \"/index\",\n" +
                "                      \"title\": \"my miniprogram\"\n" +
                "                  }\n" +
                "                }\n" +
                "              ]\n" +
                "             }\n" +
                "            },\n" +
                "            \"follow_info\":\n" +
                "            {\n" +
                "                \"remark\":\"李部长\",\n" +
                "                \"description\":\"对接采购事务\",\n" +
                "                \"createtime\":1525779812,\n" +
                "                \"tag_id\":[\"etAJ2GCAAAXtWyujaWJHDDGi0mACHAAA\"],\n" +
                "                \"remark_corp_name\":\"腾讯科技\",\n" +
                "                \"remark_mobiles\":\n" +
                "                [\n" +
                "                      \"13800000001\",\n" +
                "                      \"13000000002\"\n" +
                "                ],\n" +
                "                \"oper_userid\":\"rocky\",\n" +
                "                \"add_way\":1\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"external_contact\":\n" +
                "             {\n" +
                "                \"external_userid\":\"woAJ2GCAAAXtWyujaWJHDDGi0mACHBBB\",\n" +
                "                \"name\":\"王五\",\n" +
                "                \"position\":\"Engineer\",\n" +
                "                \"avatar\":\"http://p.qlogo.cn/bizmail/IcsdgagqefergqerhewSdage/0\",\n" +
                "                \"corp_name\":\"腾讯\",\n" +
                "                \"corp_full_name\":\"腾讯科技有限公司\",\n" +
                "                \"type\":2,\n" +
                "                \"gender\":1,\n" +
                "                \"unionid\":\"ozynqsulJFCZ2asdaf8h-nuasdAAA\"\n" +
                "             },\n" +
                "            \"follow_info\":\n" +
                "            {\n" +
                "                \"remark\":\"王助理\",\n" +
                "                \"description\":\"采购问题咨询\",\n" +
                "                \"createtime\":1525881637,\n" +
                "                \"tag_id\":[\"etAJ2GCAAAXtWyujaWJHDDGi0mACHAAA\"],\n" +
                "                \"state\":\"外联二维码1\",\n" +
                "                \"oper_userid\":\"woAJ2GCAAAd1asdasdjO4wKmE8AabjBBB\",\n" +
                "                \"add_way\":3\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"next_cursor\":\"r9FqSqsI8fgNbHLHE5QoCP50UIg2cFQbfma3l2QsmwI\"\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        String externalCOntactList = jsonObject.getString("external_contact_list");
        List<QywxExternalVO> externalUserList = JSONArray.parseArray(externalCOntactList, QywxExternalVO.class);
        for (QywxExternalVO userFromQywx : externalUserList) {
            SjbQywxExternalUserInfo userInfo = new SjbQywxExternalUserInfo();
            BeanUtils.copyProperties(userFromQywx.getExternal_contact(), userInfo);
            //进行字段置换
            userInfo.setExternalUserid(userFromQywx.getExternal_contact().getExternal_userid());
            userInfo.setCorpName(userFromQywx.getExternal_contact().getCorp_name());
            userInfo.setCorpFullName(userFromQywx.getExternal_contact().getCorp_full_name());
            userInfo.setExternalProfile(userFromQywx.getExternal_contact().getExternal_profile());
            QywxExternalFollowInfoVO followInfoVO = userFromQywx.getFollow_info();
            LocalDateTime createTime = LocalDateTime.ofEpochSecond(followInfoVO.getCreatetime(), 0,
                    ZoneOffset.ofHours(8));
            System.out.println("debug");
        }
        System.out.println("debug");
    }

    @Test
    public void test4() {
        String str = "{\n" +
                "    \"external_corp_name\": \"企业简称\",\n" +
                "    \"external_attr\": [\n" +
                "        {\n" +
                "            \"type\": 0,\n" +
                "            \"name\": \"文本名称\",\n" +
                "            \"text\": {\n" +
                "                \"value\": \"文本\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": 1,\n" +
                "            \"name\": \"网页名称\",\n" +
                "            \"web\": {\n" +
                "                \"url\": \"http://www.test.com\",\n" +
                "                \"title\": \"标题\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": 2,\n" +
                "            \"name\": \"测试app\",\n" +
                "            \"miniprogram\": {\n" +
                "                \"appid\": \"wx8bd80126147dfAAA\",\n" +
                "                \"pagepath\": \"/index\",\n" +
                "                \"title\": \"miniprogram\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        ExternalProfileVO externalProfileVO = JSONArray.parseObject(str, ExternalProfileVO.class);
        System.out.println("debug");
    }

    @Test
    public void test5() {
        String str = "[{\n" +
                "        \"group_id\": \"TAG_GROUPID1\",\n" +
                "        \"group_name\": \"GOURP_NAME\",\n" +
                "        \"create_time\": 1557838797,\n" +
                "        \"order\": 1,\n" +
                "        \"deleted\": false,\n" +
                "        \"tag\": [{\n" +
                "                \"id\": \"TAG_ID1\",\n" +
                "                \"name\": \"NAME1\",\n" +
                "                \"create_time\": 1557838797,\n" +
                "                \"order\": 1,\n" +
                "                \"deleted\": false\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"TAG_ID2\",\n" +
                "                \"name\": \"NAME2\",\n" +
                "                \"create_time\": 1557838797,\n" +
                "                \"order\": 2,\n" +
                "                \"deleted\": true\n" +
                "            }\n" +
                "        ]\n" +
                "    }]";
        List<QywxCorpTagVO> externalUserList = JSONArray.parseArray(str, QywxCorpTagVO.class);
        for (QywxCorpTagVO qywxCorpTagVO : externalUserList) {
            System.out.println("debug");
        }
    }

    @Test
    public void test6() {
        String str = "[\"etcSnkCAAA8KZ6B2rTT2OjHGRLGGqtig\",\"etcSnkCAAAPD0ZSAqQhlzui4_EYkwsdg\",\"etcSnkCAAANGbo-nL7kYCAti56M-eM-A\"]";
        List<String> externalUserList = JSONArray.parseArray(str, String.class);
        System.out.println("debug");
    }

    @Test
    public void test7() {
        String str = "{\n" +
                "        \"chat_id\": \"wrOgQhDgAAMYQiS5ol9G7gK9JVAAAA\",\n" +
                "        \"name\": \"销售客服群\",\n" +
                "        \"owner\": \"ZhuShengBen\",\n" +
                "        \"create_time\": 1572505490,\n" +
                "        \"notice\" : \"文明沟通，拒绝脏话\",\n" +
                "        \"member_list\": [{\n" +
                "            \"userid\": \"abel\",\n" +
                "            \"type\": 1,\n" +
                "            \"join_time\": 1572505491,\n" +
                "            \"join_scene\": 1\n" +
                "        }, {\n" +
                "            \"userid\": \"sam\",\n" +
                "            \"type\": 1,\n" +
                "            \"join_time\": 1572505491,\n" +
                "            \"join_scene\": 1\n" +
                "        }, {\n" +
                "            \"userid\": \"wmOgQhDgAAuXFJGwbve4g4iXknfOAAAA\",\n" +
                "            \"type\": 2,\n" +
                "            \"unionid\": \"ozynqsulJFCZ2z1aYeS8h-nuasdAAA\",\n" +
                "            \"join_time\": 1572505491,\n" +
                "            \"join_scene\": 1\n" +
                "        }]\n" +
                "    }";
        QywxGroupChatInfoVO groupChatInfoVO = JSONObject.parseObject(str, QywxGroupChatInfoVO.class);
        System.out.println("debug");
    }


    @Test
    public void test9() {
        String str = "{\n" +
                "    \"errcode\": 0,\n" +
                "    \"errmsg\": \"ok\",\n" +
                "    \"behavior_data\":\n" +
                "    [\n" +
                "        {\n" +
                "        \"stat_time\":1536508800,\n" +
                "        \"chat_cnt\":100,\n" +
                "        \"message_cnt\":80,\n" +
                "        \"reply_percentage\":60.25,\n" +
                "        \"avg_reply_time\":1,\n" +
                "        \"negative_feedback_cnt\":0,\n" +
                "        \"new_apply_cnt\":6,\n" +
                "        \"new_contact_cnt\":5\n" +
                "        },\n" +
                "        {\n" +
                "        \"stat_time\":1536595200,\n" +
                "        \"chat_cnt\":20,\n" +
                "        \"message_cnt\":40,\n" +
                "        \"reply_percentage\":100,\n" +
                "        \"avg_reply_time\":1,\n" +
                "        \"negative_feedback_cnt\":0,\n" +
                "        \"new_apply_cnt\":6,\n" +
                "        \"new_contact_cnt\":5\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        String count = jsonObject.getString("behavior_data");
        List<QywxUserCountVO> userCountVOS = JSONArray.parseArray(count, QywxUserCountVO.class);
        System.out.println("debug");
    }

    @Test
    public void test21() {
        String str = "{\n" +
                "   \"errcode\": 0,\n" +
                "   \"errmsg\": \"ok\",\n" +
                "   \"external_contact\":\n" +
                "   {\n" +
                "        \"external_userid\":\"woAJ2GCAAAXtWyujaWJHDDGi0mACHAAA\",\n" +
                "        \"name\":\"李四\",\n" +
                "        \"position\":\"Manager\",\n" +
                "        \"avatar\":\"http://p.qlogo.cn/bizmail/IcsdgagqefergqerhewSdage/0\",\n" +
                "        \"corp_name\":\"腾讯\",\n" +
                "        \"corp_full_name\":\"腾讯科技有限公司\",\n" +
                "        \"type\":2,\n" +
                "        \"gender\":1,\n" +
                "        \"unionid\":\"ozynqsulJFCZ2z1aYeS8h-nuasdAAA\",\n" +
                "        \"external_profile\":\n" +
                "        {\n" +
                "             \"external_attr\":\n" +
                "              [\n" +
                "                {\n" +
                "                  \"type\":0,\n" +
                "                  \"name\":\"文本名称\",\n" +
                "                   \"text\":\n" +
                "                    {\n" +
                "                       \"value\":\"文本\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                  \"type\":1,\n" +
                "                  \"name\":\"网页名称\",\n" +
                "                  \"web\":\n" +
                "                  {\n" +
                "                      \"url\":\"http://www.test.com\",\n" +
                "                      \"title\":\"标题\"\n" +
                "                  }\n" +
                "                },\n" +
                "                {\n" +
                "                  \"type\":2,\n" +
                "                  \"name\":\"测试app\",\n" +
                "                  \"miniprogram\":\n" +
                "                  {\n" +
                "                      \"appid\": \"wx8bd80126147df384\",\n" +
                "                      \"pagepath\": \"/index\",\n" +
                "                      \"title\": \"my miniprogram\"\n" +
                "                  }\n" +
                "                }\n" +
                "              ]\n" +
                "      }\n" +
                "     },\n" +
                "     \"follow_user\":\n" +
                "      [\n" +
                "        {\n" +
                "          \"userid\":\"rocky\",\n" +
                "          \"remark\":\"李部长\",\n" +
                "          \"description\":\"对接采购事务\",\n" +
                "          \"createtime\":1525779812,\n" +
                "          \"tags\":\n" +
                "           [\n" +
                "               {\n" +
                "                  \"group_name\":\"标签分组名称\",\n" +
                "                  \"tag_name\":\"标签名称\",\n" +
                "                  \"tag_id\":\"etAJ2GCAAAXtWyujaWJHDDGi0mACHAAA\",\n" +
                "                  \"type\":1\n" +
                "               }\n" +
                "           ],\n" +
                "           \"remark_corp_name\":\"腾讯科技\",\n" +
                "           \"remark_mobiles\":\n" +
                "            [\n" +
                "              \"13800000001\",\n" +
                "              \"13000000002\"\n" +
                "            ],\n" +
                "           \"oper_userid\":\"rocky\",\n" +
                "           \"add_way\":1\n" +
                "        },\n" +
                "        {\n" +
                "          \"userid\":\"tommy\",\n" +
                "          \"remark\":\"李总\",\n" +
                "          \"description\":\"采购问题咨询\",\n" +
                "          \"createtime\":1525881637,\n" +
                "          \"state\":\"外联二维码1\",\n" +
                "          \"oper_userid\":\"woAJ2GCAAAXtWyujaWJHDDGi0mACHAAA\",\n" +
                "           \"add_way\":3\n" +
                "         }\n" +
                "     ],\n" +
                "     \"next_cursor\":\"NEXT_CUROSR\"\n" +
                "}\n";
        JSONObject jsonObject = JSONObject.parseObject(str);
        List<QywxExternalFollowInfoVO> qywxExternalUserInfoVO = jsonObject.getJSONArray("follow_user").
                toJavaList(QywxExternalFollowInfoVO.class);
        System.out.println("debug");
    }

    @Test
    public void test22(){
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        System.out.println(JSON.toJSONString(strings));
    }
}

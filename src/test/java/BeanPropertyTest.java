import com.alibaba.fastjson.JSON;
import com.boot.bean.IecElecBizCompletion;
import com.boot.bean.IecElecBizUnitDetails;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2020/12/15
 **/
public class BeanPropertyTest {
    @Test
    public void test() {
        IecElecBizCompletion fromDb = IecElecBizCompletion
                .builder().id("1111").orderCode("2222").projectName("ceshi").build();
        List<IecElecBizUnitDetails> details = new ArrayList<>();
        details.add(IecElecBizUnitDetails.builder().id("222details").build());
        IecElecBizCompletion fromHtml = IecElecBizCompletion.builder()
                .iecElecBizUnitDetails(details).receiver("recevicer_test").build();
        BeanUtils.copyProperties(fromHtml, fromDb, "id", "orderCode");
        System.out.println("debug");
    }

    @Test
    public void test2() {
        Map<String, String> map = new HashMap<>();
        map.put("address", "金嘉创意谷3栋3楼");
        map.put("time", LocalDateTime.now().toString());
        System.out.println(JSON.toJSONString(map));
    }
}

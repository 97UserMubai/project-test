import com.alibaba.excel.EasyExcel;
import com.boot.bean.IecCustomerElecUser;
import com.boot.bean.IecElecBizDeviceMeter;
import com.boot.bean.IecElecBizReception;
import com.boot.bean.Student;
import com.boot.vo.EquipmentPrintVO;
import com.boot.vo.MeterVO;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2020/12/11
 **/
public class EasyExcelTest {

    /**
     *
     */
    @Test
    public void test() {
        //测试2
        //获取基本信息
        EquipmentPrintVO equipmentPrintVO = getReceptionDetails();
        //获取电能表数据列表
        List<MeterVO> meterVOList = getMeterList(equipmentPrintVO);
        //头部模板
        String headTempName = "C:\\Users\\Administrator\\Desktop\\模板测试\\装拆工作单模板-头.xls";
        String finalFileName = "C:\\Users\\Administrator\\Desktop\\result.xls";
        //输出头部内容
        EasyExcel.write(finalFileName).withTemplate(headTempName).sheet().doFill(equipmentPrintVO);
        //开始电能表数据的加工
        Map<String, String> testMap = new HashMap<>();
    }

    private EquipmentPrintVO getReceptionDetails() {
        IecCustomerElecUser iecCustomerElecUser = IecCustomerElecUser.builder()
                .contractCapacity(315).elecType("我是用电类别").meterage("高压低计").build();
        IecElecBizReception iecElecBizReception = IecElecBizReception.builder()
                .orderCode("000001").type("我是业务类别").userCode("我是用电户编号")
                .supplier("jwd公司").custName("哈哈客户").address("哈哈客户的地址")
                .manager("传说中的经办人").phone("经办人手机139").build();
        EquipmentPrintVO equipmentPrintVO = new EquipmentPrintVO();
        BeanUtils.copyProperties(iecCustomerElecUser, equipmentPrintVO);
        BeanUtils.copyProperties(iecElecBizReception, equipmentPrintVO);
        equipmentPrintVO.setPrintDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
        return equipmentPrintVO;
    }

    /**
     * 获取电能数据列表
     *
     * @return 电能数据列表
     */
    private List<MeterVO> getMeterList(EquipmentPrintVO equipmentPrintVO) {
        List<IecElecBizDeviceMeter> meters = new ArrayList<>();
        for (int i = 100000; i <= 100003; i++) {
            meters.add(IecElecBizDeviceMeter.builder().calculateNumber(String.valueOf(i))
                    .changeSign("新装").code("我是资产编号：" + i).voltage("我是电压").current("我是电流")
                    .rate(new BigDecimal("12.22")).precisionLevel("我是准确度等级").type("设备类型：三相").build());
        }
        List<MeterVO> meterVOS = new ArrayList<>();
        meters.forEach(meter -> {
            MeterVO meterVO = new MeterVO();
            BeanUtils.copyProperties(meter, meterVO);
            meterVO.setChangeSign("新装");
            meterVO.setMeterage(equipmentPrintVO.getMeterage());
            meterVOS.add(meterVO);
        });
        return meterVOS;
    }

}

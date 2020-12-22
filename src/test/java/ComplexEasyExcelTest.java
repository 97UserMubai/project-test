import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.boot.bean.IecCustomerElecUser;
import com.boot.bean.IecElecBizDeviceMeter;
import com.boot.bean.IecElecBizReception;
import com.boot.vo.EquipmentPrintVO;
import com.boot.vo.MeterVO;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2020/12/22
 **/
public class ComplexEasyExcelTest {
    @Test
    public void test() {
        //获取基本信息
        EquipmentPrintVO equipmentPrintVO = getReceptionDetails();
        //获取电能表数据列表
        List<MeterVO> meterVOList = getMeterList(equipmentPrintVO);
        String templateFileName = "C:\\Users\\Administrator\\Desktop\\装拆工作单模板2.xls";
        String templateFileName2 = "C:\\Users\\Administrator\\Desktop\\装拆工作单模板3.xls";
        String fileName = "C:\\Users\\Administrator\\Desktop\\测试结果" + System.currentTimeMillis() + ".xls";
        // 方案2 分多次 填充 会使用文件缓存（省内存）
        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        excelWriter.fill(equipmentPrintVO, writeSheet);
        // 千万别忘记关闭流
        excelWriter.finish();
        System.out.println("debug");
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

    /**
     * 数据量大的复杂填充
     * <p>
     * 这里的解决方案是 确保模板list为最后一行，然后再拼接table.还有03版没救，只能刚正面加内存。
     *
     * @since 2.1.1
     */
//    @Test
//    public void complexFillWithTable() {
//        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
//        // {} 代表普通变量 {.} 代表是list的变量
//        // 这里模板 删除了list以后的数据，也就是统计的这一行
//        String templateFileName =
//                TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "complexFillWithTable.xlsx";
//
//        String fileName = TestFileUtil.getPath() + "complexFillWithTable" + System.currentTimeMillis() + ".xlsx";
//        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        // 直接写入数据
//        excelWriter.fill(data(), writeSheet);
//        excelWriter.fill(data(), writeSheet);
//
//        // 写入list之前的数据
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("date", "2019年10月9日13:28:28");
//        excelWriter.fill(map, writeSheet);
//
//        // list 后面还有个统计 想办法手动写入
//        // 这里偷懒直接用list 也可以用对象
//        List<List<String>> totalListList = new ArrayList<List<String>>();
//        List<String> totalList = new ArrayList<String>();
//        totalListList.add(totalList);
//        totalList.add(null);
//        totalList.add(null);
//        totalList.add(null);
//        // 第四列
//        totalList.add("统计:1000");
//        // 这里是write 别和fill 搞错了
//        excelWriter.write(totalListList, writeSheet);
//        excelWriter.finish();
//        // 总体上写法比较复杂 但是也没有想到好的版本 异步的去写入excel 不支持行的删除和移动，也不支持备注这种的写入，所以也排除了可以
//        // 新建一个 然后一点点复制过来的方案，最后导致list需要新增行的时候，后面的列的数据没法后移，后续会继续想想解决方案
//    }
}

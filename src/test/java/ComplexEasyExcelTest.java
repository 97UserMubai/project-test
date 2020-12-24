import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.boot.bean.*;
import com.boot.vo.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
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
 * @Date 2020/12/22
 **/
public class ComplexEasyExcelTest {
    private static final String path = "C:\\Users\\Administrator\\Desktop\\模板测试\\result-";

    @Test
    public void test() {
        //获取基本信息
        EquipmentPrintVO equipmentPrintVO = getReceptionDetails();
        //获取电能表数据列表
        List<MeterVO> meterVOList = getMeterList(equipmentPrintVO);
        String templateFileName = "C:\\Users\\Administrator\\Desktop\\装拆工作单模板.xls";
//        String fileName = "C:\\Users\\Administrator\\Desktop\\测试结果" + System.currentTimeMillis() + ".xls";
        String fileName = "C:\\Users\\Administrator\\Desktop\\测试结果1608692656434.xls";
        //测试sheet
        // 方案2 分多次 填充 会使用文件缓存（省内存）
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet0").build();
        excelWriter.fill(meterVOList, fillConfig, writeSheet);
        excelWriter.fill(equipmentPrintVO, writeSheet);
        // 千万别忘记关闭流
        excelWriter.finish();
        System.out.println("debug");
    }

    @Test
    public void test2() {
        //获取基本信息
        EquipmentPrintVO equipmentPrintVO = getReceptionDetails();
        //获取电能表数据列表
        List<MeterVO> meterVOList = getMeterList(equipmentPrintVO);
        //头部模板
        String headTempName = "C:\\Users\\Administrator\\Desktop\\模板测试\\装拆工作单模板-头.xlsx";
        String path = prepareFilePath();
        String headFileName = path + File.separator + "header" + File.separator + "head.xlsx";
        EasyExcel.write(headFileName).withTemplate(headTempName).sheet().doFill(equipmentPrintVO);
        //电能表模板
        String meterTempName = "C:\\Users\\Administrator\\Desktop\\模板测试\\装拆工作单模板-电能表.xlsx";
        //动态输出电能表数据
        AtomicInteger meterCount = new AtomicInteger(0);
        meterVOList.forEach(meterVO -> {
            String meterFileName = path + File.separator + "meter" + File.separator + "meter-" + meterCount.get() + ".xlsx";
            meterCount.addAndGet(1);
            EasyExcel.write(meterFileName).withTemplate(meterTempName).sheet().doFill(meterVO);
        });
        //动态输出互感器数据
        String tranTempName = "C:\\Users\\Administrator\\Desktop\\模板测试\\装拆工作单模板-互感器.xlsx";
        AtomicInteger transCount = new AtomicInteger(0);
        List<IecElecBizDeviceInductanceTransformer> transformers = new ArrayList<>();
        transformers.add(IecElecBizDeviceInductanceTransformer.builder().calculateNumber("我是计量点编号001").build());
        transformers.add(IecElecBizDeviceInductanceTransformer.builder().calculateNumber("我是计量点编号002").build());
        transformers.add(IecElecBizDeviceInductanceTransformer.builder().calculateNumber("我是计量点编号003").build());
        transformers.forEach(tran -> {
            String tranFileName = path + File.separator + "transformer" + File.separator + "transformer-"
                    + transCount.get() + ".xlsx";
            transCount.addAndGet(1);
            EasyExcel.write(tranFileName).withTemplate(tranTempName).sheet().doFill(tran);
        });
        //动态输出终端数据和采集关系数据
        String loadTempName = "C:\\Users\\Administrator\\Desktop\\模板测试\\装拆工作单模板-终端.xlsx";
        AtomicInteger loadCount = new AtomicInteger(0);
        List<IecElecBizDeviceLoad> loads = new ArrayList<>();
        loads.add(IecElecBizDeviceLoad.builder().elecUserCode("000001").changeSign("新装").build());
        loads.add(IecElecBizDeviceLoad.builder().elecUserCode("000002").changeSign("拆除").build());
        loads.forEach(load -> {
            String loadFileName = path + File.separator + "load" + File.separator + "load-"
                    + loadCount.get() + ".xlsx";
            loadCount.addAndGet(1);
            EasyExcel.write(loadFileName).withTemplate(loadTempName).sheet().doFill(load);
        });
        //输出采集关系数据
        String contactTempName = "C:\\Users\\Administrator\\Desktop\\模板测试\\装拆工作单模板-终端采集关系变更列表.xlsx";
        String loadFileName = path + File.separator + "contact" + File.separator + "contact.xlsx";
        EasyExcel.write(loadFileName).withTemplate(contactTempName).sheet().doFill(loads);
        //输出尾部
        String endTempName = "C:\\Users\\Administrator\\Desktop\\模板测试\\装拆工作单模板-尾部.xlsx";
        String endFileName = path + File.separator + "end" + File.separator + "end.xlsx";
        Map<String, Object> map = new HashMap<>();
        map.put("printDate", LocalDate.now().toString());
        EasyExcel.write(endFileName).withTemplate(endTempName).sheet().doFill(map);
    }

    private String prepareFilePath() {
        String pathTemp = path + System.currentTimeMillis();
        List<String> pathList = new ArrayList<>();
        pathList.add(pathTemp + File.separator + "header");
        pathList.add(pathTemp + File.separator + "meter");
        pathList.add(pathTemp + File.separator + "transformer");
        pathList.add(pathTemp + File.separator + "load");
        pathList.add(pathTemp + File.separator + "end");
        pathList.add(pathTemp + File.separator + "contact");
        File fileDir;
        for (String p : pathList) {
            fileDir = new File(p);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
        }
        return pathTemp;
    }


    @Test
    public void test3() throws IOException {
        File finalFile = new File("C:\\Users\\Administrator\\Desktop\\模板测试\\test\\final.txt");
        FileUtils.copyFile(new File("C:\\Users\\Administrator\\Desktop\\模板测试\\test\\test1.txt"), finalFile);
        File test2 = new File("C:\\Users\\Administrator\\Desktop\\模板测试\\test\\test2.txt");
        FileInputStream fis = new FileInputStream(test2);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer;
        byte[] b = new byte[1024];
        int n;
        while ((n = fis.read(b)) != -1) {
            bos.write(b, 0, n);
        }
        fis.close();
        bos.close();
        buffer = bos.toByteArray();
        FileUtils.writeByteArrayToFile(finalFile, buffer, true);
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

    private List<MeterVO2> getMeterListTest(EquipmentPrintVO equipmentPrintVO) {
        List<IecElecBizDeviceMeter> meters = new ArrayList<>();
        for (int i = 100000; i <= 100003; i++) {
            meters.add(IecElecBizDeviceMeter.builder().calculateNumber(String.valueOf(i))
                    .changeSign("新装").code("我是资产编号：" + i).voltage("我是电压").current("我是电流")
                    .rate(new BigDecimal("12.22")).precisionLevel("我是准确度等级").type("设备类型：三相").build());
        }
        List<MeterVO2> meterVOS = new ArrayList<>();
        meters.forEach(meter -> {
            MeterVoOne meterVoOne = new MeterVoOne();
            MeterVoTwo meterVoTwo = new MeterVoTwo();
            BeanUtils.copyProperties(meter, meterVoOne);
            BeanUtils.copyProperties(meter, meterVoTwo);
            meterVoOne.setChangeSign("新装");
            meterVoTwo.setMeterage(equipmentPrintVO.getMeterage());
            MeterVO2 meterVO = MeterVO2.builder().meterVoOne(meterVoOne).meterVoTwo(meterVoTwo).build();
            meterVOS.add(meterVO);
        });
        return meterVOS;
    }
}

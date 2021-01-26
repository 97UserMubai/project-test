import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.boot.bean.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2021/1/26
 **/
public class ComplexEasyExcelTest2 {

    @Test
    public void test() {
        //获取基本信息
        IecMsg iecMsg = IecMsg.builder().elecUserCode("123").elecUserName("我是用电户").build();
        //获取电能表数据列表
        List<IecCalcMsg> calcMsgList = getCalcMsg();
        List<IecBillMsg> billMsgs = getBillMsg();
        List<IecTranMsg> iecTranMsgs = getTranMsg();
        List<IecPowerMsg> iecPowerMsgs = getPowerMsg();
        List<IecOtherMsg> iecOtherMsgs = getOtherMsg();
        List<IecTotalMsg> iecTotalMsgs = getTotalMsg();
        String templateFileName = "C:\\Users\\Administrator\\Desktop\\recheck-template2.xlsx";
        String fileName = "C:\\Users\\Administrator\\Desktop\\测试结果" + System.currentTimeMillis() + ".xlsx";
//        String fileName = "C:\\Users\\Administrator\\Desktop\\测试结果1608692656434.xls";
        //测试sheet
        // 方案2 分多次 填充 会使用文件缓存（省内存）
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet0").build();
        excelWriter.fill(iecMsg, writeSheet);
        excelWriter.fill(new FillWrapper("record", calcMsgList), fillConfig, writeSheet);
//        excelWriter.fill(calcMsgList, fillConfig, writeSheet);
        excelWriter.fill(new FillWrapper("bill", billMsgs), fillConfig, writeSheet);
        excelWriter.fill(new FillWrapper("tran", iecTranMsgs), fillConfig, writeSheet);
        excelWriter.fill(new FillWrapper("power", iecPowerMsgs), fillConfig, writeSheet);
        excelWriter.fill(new FillWrapper("other", iecOtherMsgs), fillConfig, writeSheet);
        excelWriter.fill(new FillWrapper("total", iecTotalMsgs), fillConfig, writeSheet);
        // 千万别忘记关闭流
        excelWriter.finish();
        System.out.println("debug");
    }

    private List<IecCalcMsg> getCalcMsg() {
        List<IecCalcMsg> calcMsgList = new ArrayList<>();
        for (int i = 100000; i <= 100003; i++) {
            calcMsgList.add(IecCalcMsg.builder().calcCode(String.valueOf(i))
                    .type(String.valueOf(i)).lastNum("100000").currentNum("200000").build());
        }
        return calcMsgList;
    }

    private List<IecBillMsg> getBillMsg() {
        List<IecBillMsg> iecBillMsgs = new ArrayList<>();
        for (int i = 100000; i <= 100003; i++) {
            iecBillMsgs.add(IecBillMsg.builder().calcCode(String.valueOf(i))
                    .bussType(String.valueOf(i)).build());
        }
        return iecBillMsgs;
    }

    private List<IecTranMsg> getTranMsg() {
        List<IecTranMsg> iecTranMsgs = new ArrayList<>();
        for (int i = 20000; i <= 20003; i++) {
            iecTranMsgs.add(IecTranMsg.builder().sign(String.valueOf(i))
                    .days(20000).build());
        }
        return iecTranMsgs;
    }

    private List<IecPowerMsg> getPowerMsg() {
        List<IecPowerMsg> iecPowerMsgs = new ArrayList<>();
        for (int i = 20000; i <= 20003; i++) {
            iecPowerMsgs.add(IecPowerMsg.builder().calcCode(String.valueOf(i))
                    .totalPower(new BigDecimal(324149.33+i)).build());
        }
        return iecPowerMsgs;
    }

    private List<IecOtherMsg> getOtherMsg() {
        List<IecOtherMsg> iecOtherMsgs = new ArrayList<>();
        for (int i = 30000; i <= 30003; i++) {
            iecOtherMsgs.add(IecOtherMsg.builder().calcCode(String.valueOf(i))
                    .morePrice(new BigDecimal(123.4)).build());
        }
        return iecOtherMsgs;
    }

    private List<IecTotalMsg> getTotalMsg() {
        List<IecTotalMsg> iecTotalMsgs = new ArrayList<>();
        for (int i = 30000; i <= 30000; i++) {
            iecTotalMsgs.add(IecTotalMsg.builder().calcCode(String.valueOf(i))
                    .price(new BigDecimal(234.5)).build());
        }
        return iecTotalMsgs;
    }

}

package com.boot.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.boot.bean.IecCustomerElecUser;
import com.boot.bean.IecElecBizDeviceMeter;
import com.boot.bean.IecElecBizReception;
import com.boot.vo.EquipmentPrintVO;
import com.boot.vo.MeterVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2020/12/23
 **/
@RestController
@RequestMapping("excel")
public class ExcelController {
    @RequestMapping("/expor")
    public String exporExcel(HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment; filename=" + "catagory.xls");
        response.setContentType("application/msexcel;charset=UTF-8");//设置类型
        response.setHeader("Pragma", "No-cache");//设置头
        response.setHeader("Cache-Control", "no-cache");//设置头
        response.setDateHeader("Expires", 0);//设置日期头
        String templateFileName = "C:\\Users\\Administrator\\Desktop\\装拆工作单模板.xls";
        ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        //填充两行list
        //获取基本信息
        EquipmentPrintVO equipmentPrintVO = getReceptionDetails();
        //获取电能表数据列表
        List<MeterVO> meterVOList = getMeterList(equipmentPrintVO);
        excelWriter.fill(meterVOList, fillConfig, writeSheet);
        excelWriter.fill(meterVOList, fillConfig, writeSheet);
        //填充普通变量
        excelWriter.fill(equipmentPrintVO, writeSheet);
        excelWriter.finish();
        outputStream.flush();
        response.getOutputStream().close();
        return "system/test/tableTest";
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
        for (int i = 100000; i <= 100000; i++) {
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

    @RequestMapping("/excelMerge")
    public void excelMerge(HttpServletResponse response) throws Exception {
        // 创建HSSFWorkbook，暂时存放数据
        File headPathFile = new File("C:\\Users\\Administrator\\Desktop\\模板测试\\result-1608714950526\\header\\head.xls");
        HSSFWorkbook targetWork = new HSSFWorkbook(new FileInputStream(headPathFile));
        HSSFSheet targetSheet = targetWork.getSheetAt(0);
        // 记录targetWork新建行位置
        int targetLineIndex = 7;
        HSSFWorkbook workbook;
        HSSFSheet sheet;
        File sourcePathFile = new File("C:\\Users\\Administrator\\Desktop\\模板测试\\result-1608714950526\\meter");
        for (File file : Objects.requireNonNull(sourcePathFile.listFiles())) {
            workbook = new HSSFWorkbook(new FileInputStream(file));
            sheet = workbook.getSheetAt(0);
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row sourceRow = sheet.getRow(i);
                Row targetRow = targetSheet.createRow(targetLineIndex);
                targetLineIndex++;
                for (int j = 0; j < sourceRow.getLastCellNum(); j++) {
                    Cell cell = sourceRow.getCell(j);
                    Cell cellNew = targetRow.createCell(j);
                    cellNew.setCellValue(cell.toString());
                    if (j == 0 && StringUtils.isNotBlank(cell.toString())) {
                        //设置样式

                    }
                }
            }
        }
//        targetSheet.addMergedRegion(new CellRangeAddress(0, 7, 0, 0));
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename= final.xls");
        targetWork.write(response.getOutputStream());
    }
}

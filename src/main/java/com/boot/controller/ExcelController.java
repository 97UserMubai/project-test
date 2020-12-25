package com.boot.controller;

import com.alibaba.excel.EasyExcel;
import com.boot.bean.*;
import com.boot.vo.EquipmentPrintVO;
import com.boot.vo.MeterVO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2020/12/23
 **/
@RestController
@RequestMapping("excel")
public class ExcelController {

    /**
     * 文件保存路径
     */
    public static final String TEMP_PATH = System.getProperty("user.dir") + File.separator + "temp" + File.separator;

    public static final String MODULE_PATH = Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
            .getResource("")).getPath() + "template" + File.separator;

    private int loadSize = 0;
    private int meterSize = 0;
    private int tranSize = 0;
    private CellStyle endStyle;

    @GetMapping("/excelMerge")
    public String excelMerge() throws Exception {
        // 创建HSSFWorkbook，暂时存放数据
        String path = produceTaskData();
        File headPathFile = new File(path + File.separator + "header" + File.separator + "head.xlsx");
        XSSFWorkbook targetWork = new XSSFWorkbook(new FileInputStream(headPathFile));
        XSSFSheet targetSheet = targetWork.getSheetAt(0);
        // 记录targetWork新建行位置
        int targetLineIndex = 7;
        CellStyle fontStyle = getFont(targetWork);
        CellStyle masterStyle = getMasterStyle(targetWork);
        getEndStyle(targetWork);
        //进行数据合并
        produceMeterExcel(fontStyle, targetSheet, targetLineIndex, path);
        //进行样式合并
        produceMeterStyle(targetSheet, masterStyle);
        String resultFileName = "result.xlsx";
        targetWork.write(FileUtils.openOutputStream(new File(path + File.separator + resultFileName)));
        return resultFileName;
    }

    private String produceTaskData() {
        //获取基本信息
        EquipmentPrintVO equipmentPrintVO = getReceptionDetails();
        //获取电能表数据列表
        List<MeterVO> meterVOList = getMeterList(equipmentPrintVO);
        //头部模板
        String headTempName = MODULE_PATH + "装拆工作单模板-头.xlsx";
        String path = prepareFilePath();
        String headFileName = path + File.separator + "header" + File.separator + "head.xlsx";
        EasyExcel.write(headFileName).withTemplate(headTempName).sheet().doFill(equipmentPrintVO);
        //电能表模板
        String meterTempName = MODULE_PATH + "装拆工作单模板-电能表.xlsx";
        //动态输出电能表数据
        AtomicInteger meterCount = new AtomicInteger(0);
        meterVOList.forEach(meterVO -> {
            String meterFileName = path + File.separator + "meter" + File.separator + "meter-" + meterCount.get() + ".xlsx";
            meterCount.addAndGet(1);
            EasyExcel.write(meterFileName).withTemplate(meterTempName).sheet().doFill(meterVO);
        });
        meterSize = meterVOList.size();
        //动态输出互感器数据
        String tranTempName = MODULE_PATH + "装拆工作单模板-互感器.xlsx";
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
        tranSize = transformers.size();
        //动态输出终端数据和采集关系数据
        String loadTempName = MODULE_PATH + "装拆工作单模板-终端.xlsx";
        AtomicInteger loadCount = new AtomicInteger(0);
        List<IecElecBizDeviceLoad> loads = new ArrayList<>();
        loads.add(IecElecBizDeviceLoad.builder().elecUserCode("000001").changeSign("新装")
                .communicationMode("我是测试换行换行换行我是测试换行换行换行我是测试换行换行换行").build());
        loads.add(IecElecBizDeviceLoad.builder().elecUserCode("000002").changeSign("拆除")
                .communicationMode("我是测试换行换行换行我是测试换行换行换行我是测试换行换行换行").build());
        loads.forEach(load -> {
            String loadFileName = path + File.separator + "load" + File.separator + "load-"
                    + loadCount.get() + ".xlsx";
            loadCount.addAndGet(1);
            EasyExcel.write(loadFileName).withTemplate(loadTempName).sheet().doFill(load);
        });
        loadSize = loads.size();
        //输出采集关系数据
        if (loadSize > 0) {
            String contactTempName = MODULE_PATH + "装拆工作单模板-终端采集关系变更列表.xlsx";
            String loadFileName = path + File.separator + "contact" + File.separator + "contact.xlsx";
            EasyExcel.write(loadFileName).withTemplate(contactTempName).sheet().doFill(loads);
        }
        //输出尾部
        String endTempName = MODULE_PATH + "装拆工作单模板-尾部.xlsx";
        String endFileName = path + File.separator + "end" + File.separator + "end.xlsx";
        Map<String, Object> map = new HashMap<>();
        map.put("printDate", LocalDate.now().toString());
        EasyExcel.write(endFileName).withTemplate(endTempName).sheet().doFill(map);
        return path;
    }

    private String prepareFilePath() {
        String pathTemp = TEMP_PATH + "result-" + System.currentTimeMillis();
        List<String> pathList = new ArrayList<>();
        pathList.add(pathTemp + File.separator + "header");
        pathList.add(pathTemp + File.separator + "meter");
        pathList.add(pathTemp + File.separator + "transformer");
        pathList.add(pathTemp + File.separator + "load");
        pathList.add(pathTemp + File.separator + "end");
        pathList.add(pathTemp + File.separator + "contact");
        pathList.add(pathTemp + File.separator + "result");
        File fileDir;
        for (String p : pathList) {
            fileDir = new File(p);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
        }
        return pathTemp;
    }

    private void produceMeterExcel(CellStyle fontStyle, XSSFSheet targetSheet, int targetLineIndex, String path)
            throws IOException {
        File sourcePathFile = new File(path + File.separator + "meter");
        targetLineIndex = productCommonExcel(fontStyle, targetSheet, targetLineIndex, sourcePathFile);
        produceTranExcel(fontStyle, targetSheet, targetLineIndex, path);
    }

    private void produceMeterStyle(XSSFSheet targetSheet, CellStyle masterStyle) {
        //设置电能表样式
        int colEndIndex = 6;
        if (meterSize > 0) {
            int colBeginIndex = colEndIndex + 1;
            colEndIndex = colBeginIndex + 8;
            for (int i = 0; i < meterSize; i++) {
                if (i > 0) {
                    colBeginIndex = colEndIndex + 1;
                    colEndIndex = colBeginIndex + 8;
                }
                Row row = targetSheet.getRow(colBeginIndex);
                Cell cell = row.getCell(0);
                cell.setCellValue("电能表变更列表");
                cell.setCellStyle(masterStyle);
                //总共十二列9行,按行进行单元格需求的合并
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colEndIndex, 0, 0));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colEndIndex - 1, 1, 1));
                //第一行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 2, 3));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 4, 5));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 6, 8));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 9, 10));
                //第二行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colBeginIndex + 1, 2, 3));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colBeginIndex + 1, 4, 5));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colBeginIndex + 1, 6, 8));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colBeginIndex + 1, 9, 10));
                //第五行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 4, colBeginIndex + 4, 2, 3));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 4, colBeginIndex + 4, 4, 7));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 4, colBeginIndex + 4, 8, 12));
                //第六行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 5, colBeginIndex + 5, 2, 3));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 5, colBeginIndex + 5, 4, 7));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 5, colBeginIndex + 5, 8, 12));
                //第七行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 6, colBeginIndex + 6, 2, 3));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 6, colBeginIndex + 6, 4, 7));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 6, colBeginIndex + 6, 8, 12));
                //第八行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 7, colBeginIndex + 7, 2, 3));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 7, colBeginIndex + 7, 4, 7));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 7, colBeginIndex + 7, 8, 9));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 7, colBeginIndex + 7, 10, 12));
                //第九行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 8, colBeginIndex + 8, 2, 6));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 8, colBeginIndex + 8, 8, 12));
            }
        }
        produceTranStyle(targetSheet, colEndIndex, masterStyle);
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

    private void produceTranExcel(CellStyle fontStyle, XSSFSheet targetSheet, int targetLineIndex, String path)
            throws IOException {
        File sourcePathFile = new File(path + File.separator + "transformer");
        targetLineIndex = productCommonExcel(fontStyle, targetSheet, targetLineIndex, sourcePathFile);
        produceLoadExcel(fontStyle, targetSheet, targetLineIndex, path);
    }

    private void produceTranStyle(XSSFSheet targetSheet, int colEndIndex, CellStyle masterStyle) {
        //进行横向头部的合并
        if (tranSize > 0) {
            int size = tranSize * 3;
            targetSheet.addMergedRegion(new CellRangeAddress(colEndIndex + 1, colEndIndex + size, 0, 0));
            int colBeginIndex;
            for (int i = 0; i < tranSize; i++) {
                //第一行
                colBeginIndex = colEndIndex + 1;
                colEndIndex = colBeginIndex + 2;
                //进行头部内容加工
                if (i == 0) {
                    Row row = targetSheet.getRow(colBeginIndex);
                    Cell cell = row.getCell(0);
                    cell.setCellValue("互感器变更列表");
                    cell.setCellStyle(masterStyle);
                }
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 1, 2));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 5, 6));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 8, 9));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 10, 11));
                //第二行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colBeginIndex + 1, 1, 2));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colBeginIndex + 1, 5, 6));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colBeginIndex + 1, 8, 9));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colBeginIndex + 1, 10, 11));
                //第三行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 2, colBeginIndex + 2, 2, 6));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 2, colBeginIndex + 2, 8, 12));
            }
        }
        produceLoadStyle(targetSheet, colEndIndex);
    }

    private void produceLoadExcel(CellStyle fontStyle, XSSFSheet targetSheet, int targetLineIndex, String path)
            throws IOException {
        File sourcePathFile = new File(path + File.separator + "load");
        targetLineIndex = productCommonExcel(fontStyle, targetSheet, targetLineIndex, sourcePathFile);
        productContactExcel(fontStyle, targetSheet, targetLineIndex, path);
    }

    private void produceLoadStyle(XSSFSheet targetSheet, int colEndIndex) {
        if (loadSize > 0) {
            int colBeginIndex;
            for (int i = 0; i < loadSize; i++) {
                colBeginIndex = colEndIndex + 1;
                colEndIndex = colBeginIndex + 2;
                //横向头
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colEndIndex, 0, 0));
                //第一行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 1, 3));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 4, 12));
                //第二行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colBeginIndex + 1, 3, 4));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colBeginIndex + 1, 5, 6));
                //第三行
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 2, colBeginIndex + 2, 3, 4));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 2, colBeginIndex + 2, 5, 6));
            }
        }
        produceContactStyle(targetSheet, colEndIndex);
    }

    private void productContactExcel(CellStyle fontStyle, XSSFSheet targetSheet, int targetLineIndex, String path)
            throws IOException {
        File sourcePathFile = new File(path + File.separator + "contact");
        targetLineIndex = productCommonExcel(fontStyle, targetSheet, targetLineIndex, sourcePathFile);
        productEndExcel(targetSheet, targetLineIndex, path);
    }

    private void produceContactStyle(XSSFSheet targetSheet, int colEndIndex) {
        int colBeginIndex = colEndIndex;
        if (loadSize > 0) {
            //进行合并
            colBeginIndex = colEndIndex + 1;
            //头部合并
            targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 0, 12));
            //内容合并
            for (int i = 0; i <= loadSize; i++) {
                colBeginIndex = colBeginIndex + 1;
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 0, 1));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 8, 9));
                targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex, colBeginIndex, 11, 12));
            }
        }
        //尾部合并
        targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 1, colBeginIndex + 2, 0, 12));
        targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 3, colBeginIndex + 4, 0, 12));
        targetSheet.addMergedRegion(new CellRangeAddress(colBeginIndex + 5, colBeginIndex + 6, 0, 12));
    }

    private void productEndExcel(XSSFSheet targetSheet, int targetLineIndex, String path)
            throws IOException {
        File sourcePathFile = new File(path + File.separator + "end");
        productCommonExcel(endStyle, targetSheet, targetLineIndex, sourcePathFile);
    }

    private int productCommonExcel(CellStyle fontStyle, XSSFSheet targetSheet, int targetLineIndex,
                                   File sourcePathFile) throws IOException {
        for (File file : Objects.requireNonNull(sourcePathFile.listFiles())) {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row sourceRow = sheet.getRow(i);
                Row targetRow = targetSheet.createRow(targetLineIndex);
                targetLineIndex++;
                if (Objects.nonNull(sourceRow)) {
                    for (int j = 0; j < sourceRow.getLastCellNum(); j++) {
                        Cell cell = sourceRow.getCell(j);
                        Cell cellNew = targetRow.createCell(j);
                        if (Objects.nonNull(cell) && StringUtils.isNotBlank(cell.toString())) {
                            cellNew.setCellValue(cell.toString());
                        }
                        if (j <= 12) {
                            //只操作前面13列
                            cellNew.setCellStyle(fontStyle);
                        }
                    }
                }
            }
        }
        return targetLineIndex;
    }

    private CellStyle getFont(XSSFWorkbook targetWork) {
        Font font = targetWork.createFont();
        font.setFontHeightInPoints((short) 8);
        CellStyle style = targetWork.createCellStyle();
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        style.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
        style.setWrapText(true);
        return style;
    }

    private CellStyle getMasterStyle(XSSFWorkbook targetWork) {
        CellStyle style = getFont(targetWork);
        style.setRotation((short) 255); //竖形展示
        return style;
    }

    private void getEndStyle(XSSFWorkbook targetWork) {
        endStyle = targetWork.createCellStyle();
        Font font = targetWork.createFont();
        font.setFontHeightInPoints((short) 8);
        font.setBold(true);
        endStyle.setFont(font);
        endStyle.setAlignment(HorizontalAlignment.LEFT); //左上对齐
        endStyle.setVerticalAlignment(VerticalAlignment.TOP);
        endStyle.setBorderLeft(BorderStyle.THIN);//左边框
        endStyle.setBorderRight(BorderStyle.THIN);//右边框
    }
}

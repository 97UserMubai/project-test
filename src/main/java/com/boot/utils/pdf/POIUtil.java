package com.boot.utils.pdf;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author wangbaitao
 * <h>设置单元格颜色</h>
 * @Date 2021/02/05
 */
public class POIUtil {
	public static int[] getColorRGB(Color color){
        int red = 0;
        int green = 0;
        int blue = 0;

        if (color instanceof HSSFColor) {
            HSSFColor hssfColor = (HSSFColor) color;
            short[] rgb = hssfColor.getTriplet();
            red = rgb[0];
            green = rgb[1];
            blue = rgb[2];
        }else  if (color instanceof XSSFColor) {
            XSSFColor xssfColor = (XSSFColor) color;
            byte[] rgb = xssfColor.getRGB();
            if(rgb != null) {
                red = (rgb[0] < 0) ? (rgb[0] + 256) : rgb[0];
                green = (rgb[1] < 0) ? (rgb[1] + 256) : rgb[1];
                blue = (rgb[2] < 0) ? (rgb[2] + 256) : rgb[2];
            }
        }

        if(red != 0 || green != 0 || blue != 0){
            return new int[] {red,green,blue};
        }else return new int[] {255,255,255};
    }
	
    public static int getRGB(Color color){
        int result = 0x00FFFFFF;
        result = new java.awt.Color(0, 0, 0).getRGB();
        return result;


    }
    public static int getBorderRBG(Workbook wb  , short index){
        int result = 0;

        if(wb instanceof HSSFWorkbook){
            HSSFWorkbook hwb = (HSSFWorkbook)wb;
            HSSFColor color =  hwb.getCustomPalette().getColor(index);
            if(color != null){
                result = getRGB(color);
            }
        }

        if(wb instanceof XSSFWorkbook){
            XSSFColor color = new XSSFColor();
            color.setIndexed(index);
            result = getRGB(color);
        }

        return result;
    }
}

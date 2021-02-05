import com.boot.utils.pdf.ExcelConvertPDF;
import com.itextpdf.text.RectangleReadOnly;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Excel2PDFTest {
    private static final String path = "D:\\projects\\testdemo\\src\\main\\resources";

    public static void main(String[] args) throws Exception {
        String templateUrl2 = path + File.separator + "recheck-2222.xlsx";
        //将模版和要写入模版的值传入，转换成workbook
        FileInputStream fis = new FileInputStream(templateUrl2);
        Workbook workbook = new XSSFWorkbook(fis);
        //设置导出的页面的大小
        RectangleReadOnly pageSize = new RectangleReadOnly(1000, 850.0F);
        //定义输出流 也可以支持web的httpRespone
        String pathOfPdf = path + File.separator + "test-" + System.currentTimeMillis() + ".pdf";
        FileOutputStream fos = new FileOutputStream(pathOfPdf);
        ExcelConvertPDF.convertPdf(workbook, fos, pageSize);
    }
}
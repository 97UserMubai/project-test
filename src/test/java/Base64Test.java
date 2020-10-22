import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/10/21 15:21
 * <h></h>
 */
public class Base64Test {
    /**
     * 后缀参数
     */
    private static final String SUFFIX = "SignFileList.NUMBER.FileSuffix";
    /**
     * base64位的处理参数
     */
    private static final String CONTENT = "SignFileList.NUMBER.FileContents";

    @Test
    public void testFileTOBase() throws IOException {
        File file = new File("D:\\projects\\testdemo\\6c83b3de-7539-4d05-b2f0-feffc1de56bb.jpg");
        System.out.println(file.length() / 1024 / 1024);
        InputStream in = new FileInputStream(file);
        byte[] bytes = new byte[in.available()];
        int length = in.read(bytes);
        String baseStr = Base64.encodeBase64String(bytes);
        System.out.println(baseStr);
        //294608
        System.out.println("debug");
    }

    @Test
    public void testHostToFile() throws IOException {
        String host = "http://dsp-1251624226.cos.ap-guangzhou.myqcloud.com/2020/10/20746032-0012-4471-bbcc-12f06c080ef5.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKID74mGUTJk13wa0APmFUVHp9E7l9H977F8%26q-sign-time%3D1603353127%3B4102416000%26q-key-time%3D1603353127%3B4102416000%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3Dd3551609de9de7bcc8ede7f472ee0ef914fef2cb";
        if (host.startsWith("http://")) {
            File fileRoot = new File("");
            String filename = "6c83b3de-7539-4d05-b2f0-feffc1de56bb.jpg";
            String filePath = fileRoot.getCanonicalPath() + File.separator + filename;
            File file = new File(filePath);
            URL urlFile = new URL(host);
            InputStream inputStream = urlFile.openStream();
            OutputStream outputStream = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
//            FileInputStream inputStream2 = new FileInputStream(file);
//            byte[] bytes = new byte[inputStream2.available()];
//            String base64Str = Base64.encodeBase64String(bytes);
            System.out.println(file.length() / 1024);
            System.out.println("debug");
        } else {
            System.out.println("暂时不支持https协议的数据");
        }
    }

    @Test
    public void testListFile() {
        List<String> file = new ArrayList<>();
        System.out.println(file.isEmpty());
        file.add("2");
        System.out.println(file.isEmpty());
    }

    @Test
    public void testReplace() {
        System.out.println(SUFFIX.replace("NUMBER", "1"));
        System.out.println(CONTENT.replace("NUMBER", "1"));
        System.out.println("debug");
    }

    @Test
    public void getClassPath() throws IOException {
//        System.out.println(this.getClass().getResource("/").getPath());
//        System.out.println( ClassLoader.getSystemClassLoader().getResource("./").getPath());
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());

//        System.out.println(filePath);
    }

}

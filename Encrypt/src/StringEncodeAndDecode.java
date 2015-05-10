import java.io.UnsupportedEncodingException;

/**
 * User: Cynric
 * Date: 15/1/21
 * Time: 17:48
 */
public class StringEncodeAndDecode {

    public static void main(String[] args) {
        String str = "华东师范大学中山北路校区(上海)";
        byte[] b1 = new byte[0];
        byte[] b2 = new byte[0];
        byte[] b3 = new byte[0];
        byte[] b4 = new byte[0];
        try {
            b1 = str.getBytes("utf-8");
            b2 = str.getBytes("iso-8859-1");
            b3 = str.getBytes("gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String str1, str2, str3;
        try {
            str1 = new String(str.getBytes("iso-8859-1"), "utf-8");
            str2 = new String(str.getBytes("utf-8"), "iso-8859-1");
            str3 = new String(str2.getBytes("iso-8859-1"), "utf-8");
            str3 = new String(b3, "gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println();
    }
}

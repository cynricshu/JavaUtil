import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * User: Cynric
 * Date: 14-4-20
 * Time: 10:47
 */
public class EncryptSHA {

    public byte[] eccrypt(String info) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] srcBytes = new byte[0];
        try {
            srcBytes = info.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //使用srcBytes更新摘要
        md.update(srcBytes);
        //完成哈希计算，得到result
        byte[] resultBytes = md.digest();
        return resultBytes;
    }

    public static String digestString(String password) throws NoSuchAlgorithmException {
        String newPass;
        newPass = DigestUtils.sha256Hex(password);
        return newPass;
    }

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String msg = in.nextLine();

            System.out.println("明文是：" + msg);
            System.out.println("密文是：" + digestString(msg));
        }
    }
}

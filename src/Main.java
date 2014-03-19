import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Cynric-Shu
 *         Created on 2013-5-5
 */


class GrandFather {
    public GrandFather() {
        System.out.println("this is grandfather");
    }
}

class Father extends GrandFather {
    public Father() {
        System.out.println("this is father");
    }
}

class Child extends Father {
    public Child() {
        System.out.println("this is child");
    }
}

public class Main {

    public static void main(String[] args) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse("2013-13-13T10:10:10");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        String a = "你好";
//
//        byte[] isoByte = null;
//        try {
//            isoByte = a.getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.out.println(new String(isoByte, "iso-8859-1"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }


//        String modelName = "A";
//        Date startTime;
//        Date endTime;
//
//        startTime = new Date();
//        for (int i = 0; i < 100000000; i++) {
//            String[] a = EnumTest.getModelField(modelName);
//            String[] b = EnumTest.getModelText(modelName);
//        }
//        endTime = new Date();
//        System.out.print(endTime.getTime() - startTime.getTime() + " ");
    }

    public void testChineseFilename() {
        String filename = "城市迷雾安卓手机应用设计文档.doc";
        File file = new File(filename);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            System.out.println("exist");
        } else {
            System.out.println("not exist");
        }
    }

    public void method() {
        System.err.println(super.getClass().getName());
        System.err.println(this.getClass().getSuperclass().getName());
    }

    public int factorial(int n) {
        if (n > 0) {
            return n * factorial(n - 1);
        } else {
            return 1;
        }
    }

}



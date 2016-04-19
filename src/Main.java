import java.util.Queue;

/**
 * Created on 2013-5-5
 */

public class Main {

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static void main(String[] args) {

        String a = "0d01";
        byte[] b = hexStringToByteArray(a);

        System.out.println(b);
//        Scanner scanner = new Scanner(System.in);
//
//
//        while (scanner.hasNextLine()) {
//            String lineFirst = scanner.nextLine();
//            String[] split = lineFirst.split("\\s");
//            int n = Integer.parseInt(split[0]);
//            int m = Integer.parseInt(split[1]);
//            int x = Integer.parseInt(split[2]);
//            int y = Integer.parseInt(split[3]);
//            int t = Integer.parseInt(split[4]);
//
//            double[][] matrix = new double[n][m];
//
//            for (int i = 0; i < n; i++) {
//                String line = scanner.nextLine();
//                split = line.split("\\s");
//                for (int j = 0; j < m; j++) {
//                    matrix[i][j] = Double.parseDouble(split[j]);
//                }
//            }
//
//            // end of input read
//
//            // calculate p1 of 'cc'
//            double p1 = 1;
//            for (int k = 0; k < t; k++) {
//                p1 *= matrix[x - 1][y - 1];
//            }
//
//            // list all the choice sequence of 'ss', save them in a queue;
//            int len = m * n;
//            int[] p = new int[t];
//            Queue<Integer> list = new LinkedList<>();
//            Show(t, len, p, list);
//
//            // calculate p2
//            int totalChoice = list.size() / t;
//            double totalP = 0;
//
//            // read the choice sequence
//            for (int i = 0; i < totalChoice; i++) {
//                double temp = 1;
//                for (int j = 0; j < t; j++) {
//                    int candidate = list.poll();
//                    temp *= matrix[candidate / m][candidate % m];
//                }
//                totalP += temp;
//            }
//            double p2 = totalP / totalChoice;
//
//            DecimalFormat decimalFormat = new DecimalFormat("0.0000");
//            if (p1 > p2) {
//                System.out.println("cc");
//                System.out.println(decimalFormat.format(p1));
//            } else if (p1 < p2) {
//                System.out.println("ss");
//                System.out.println(decimalFormat.format(p2));
//            } else {
//                System.out.println("equal");
//                System.out.println(decimalFormat.format(p1));
//            }
//            scanner.nextLine();
//        }
    }

    public static void Show(int t, int len, int p[], Queue list) {
        int a;
        t--;
        for (a = 0; a < len; a++) {
            p[t] = a;
            if (t == 0) {
                for (int b = 0; b < p.length; b++) {
                    list.add(p[b]);
                }
            }
            if (t > 0) Show(t, len, p, list);
        }
    }
}


//    public static long getCnm(int n, int m) {
//        if (n < 0 || m < 0) {
//            throw new IllegalArgumentException("n,m must be > 0");
//        }
//        if (n == 0 || m == 0) {
//            return 1;
//        }
//        if (m > n) {
//            return 0;
//        }
//        if (m > n / 2.0) {
//            m = n - m;
//        }
//        double result = 0.0;
//        for (int i = n; i >= (n - m + 1); i--) {
//            result += Math.log(i);
//        }
//        for (int i = m; i >= 1; i--) {
//            result -= Math.log(i);
//        }
//        result = Math.exp(result);
//        return Math.round(result);
//    }
//
//    public static int getAnm(int n, int m) {
//        int ret;
//
//        ret = factorial(n) / factorial((n - m));
//        return ret;
//    }

//    public static int factorial(int x) {
//        int ret = 1;
//        for (int i = x; i > 0; i--) {
//            ret *= i;
//        }
//        return ret;
//    }

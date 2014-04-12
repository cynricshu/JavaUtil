package test.predict;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * User: Cynric
 * Date: 14-4-7
 * Time: 20:24
 */
public class DataSource {

    static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dbName = "HotelRecommend";
    static String dbUrl = "jdbc:sqlserver://192.168.1.102:1433;DatabaseName=" + dbName;
    static String dbUser = "sa";
    static String dbPwd = "123456";

    public static Connection getConnection() {
        Connection dbConn = null;

        try {
            Class.forName(driverName);
            dbConn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
//            System.out.println("Connection Successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dbConn;
    }
}

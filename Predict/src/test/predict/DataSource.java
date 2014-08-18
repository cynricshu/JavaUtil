package test.predict;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * User: Cynric
 * Date: 14-4-7
 * Time: 20:24
 */
public class DataSource {

    static final String driverName = "org.mariadb.jdbc.Driver";
    static final String dbName = "Recommend";
    static final String ipAddr = "localhost";
    static final String port = "3306";
    static final String dbUrl = "jdbc:mariadb://" + ipAddr + ":" + port + "/" + dbName;
    static final String dbUser = "root";
    static final String dbPwd = "root";

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

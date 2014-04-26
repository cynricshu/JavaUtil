package test.predict;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * User: Cynric
 * Date: 14-4-11
 * Time: 21:41
 */
public class RatingPrediction {

    // 计算用户对项目的预测评分
    public static float predict(int userId,
                                int neighbornum, Algorithm alg) {
        float prediction = 0;
        // 按用户间相似度从高到低获得所有邻居列表
        Map<Integer, List<UserHotelInfo>> map = UserSimilarity.queryAllRatingInfo();
        float userAvgForAllItem = getAvgForAllItem(userId, map); // Ru

        List<UserNeiborSim> neighborlist = UserSimilarity.orderSimilarity(userId, map, neighbornum, alg);

        float numerator = 0;
        float denominator = 0;
        int itemId = getPredictItemId(userId, neighborlist);

        for (UserNeiborSim userNeiborSim : neighborlist) {
            int neiborId = userNeiborSim.getNeighborid();
            float sim = userNeiborSim.getSimilarity();

            Float R = getMarkForItem(neiborId, itemId, map);
            Float Ravg = getAvgForAllItem(neiborId, map);

            if (R != null) {
                numerator += sim * (R - Ravg);
                denominator += Math.abs(sim);
            }

        }

        prediction = userAvgForAllItem + numerator / denominator;
        return prediction;

    }

    public static Float getAvgForAllItem(int userId, Map<Integer, List<UserHotelInfo>> map) {
        if (map.containsKey(userId)) {
            List<UserHotelInfo> list = map.get(userId);

            float sum = 0;
            for (UserHotelInfo userHotelInfo : list) {
                sum += userHotelInfo.getGeneral();
            }

            return sum / list.size();

        } else {
            System.err.printf("userId not exist");
            return null;
        }
    }

    public static Float getMarkForItem(int userId, int itemId, Map<Integer, List<UserHotelInfo>> map) {
        if (map.containsKey(userId)) {
            List<UserHotelInfo> list = map.get(userId);

            float sum = 0;
            int appear = 0;
            for (UserHotelInfo userHotelInfo : list) {
                if (itemId == userHotelInfo.getHotelId()) {
                    sum += userHotelInfo.getGeneral();
                    appear += 1;
                }
            }
            if (appear == 0) {
                return null;
            } else {
                return sum / appear;
            }
        } else {
            System.err.printf("userId not exist");
            return null;
        }
    }


    public static int getPredictItemId(int userId, List<UserNeiborSim> neighborlist) {
        Connection conn = DataSource.getConnection();
        String array = "";
        for (UserNeiborSim sim : neighborlist) {
            array += "," + sim.getNeighborid();
        }
        array = array.substring(1);

        String sql = "select count(t1.username) as sum, t1.hotelid from commentinfo t1 inner join userinfo t2 on t1.username = t2.username\n" +
                "where t2.UserID in (" + array + ") \n" +
                "and t1.HotelID not in \n" +
                "(\n" +
                "select t1.hotelId from commentinfo t1 inner join userinfo t2 on t1.username = t2.username\n" +
                "where t2.userId = " + userId + " \n" +
                ")\n" +
                "group by t1.HotelID \n" +
                "order by sum desc";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int hotelId = rs.getInt("hotelId");
                return hotelId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    //计算 RF
    public static float queryRF(int userid, String cname, float rating) {
        Connection conn = DataSource.getConnection();
        String sql1 = "SELECT COUNT(*) FROM hotel_rating_info1 WHERE user_id=? AND " + cname + " = ?";
        String sql2 = "SELECT COUNT(*) FROM hotel_rating_info1 WHERE user_id=?";
        PreparedStatement pst1 = null;
        ResultSet rs1 = null;
        PreparedStatement pst2 = null;
        ResultSet rs2 = null;
        float num1 = 0;
        float num2 = 0;
        try {
            pst1 = conn.prepareStatement(sql1);
            pst1.setInt(1, userid);
            pst1.setFloat(2, rating);
            rs1 = pst1.executeQuery();
            pst2 = conn.prepareStatement(sql2);
            pst2.setInt(1, userid);
            rs2 = pst2.executeQuery();
            while (rs1.next()) {
                num1 = rs1.getInt(1);
            }
            while (rs2.next()) {
                num2 = rs2.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs1.close();
                rs2.close();
                pst1.close();
                pst2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        float f = num1 / num2;
        return f;
    }


    //计算 IUF
    public static float queryIUF(String cname, float rating) {
        Connection conn = DataSource.getConnection();
        String sql1 = "SELECT COUNT(DISTINCT user_id) FROM hotel_rating_info1";
        String sql2 = "SELECT COUNT(DISTINCT user_id) FROM hotel_rating_info1 WHERE " + cname + " = ?";
        PreparedStatement pst1 = null;
        ResultSet rs1 = null;
        PreparedStatement pst2 = null;
        ResultSet rs2 = null;
        float num1 = 0;
        float num2 = 0;
        try {
            pst1 = conn.prepareStatement(sql1);
            rs1 = pst1.executeQuery();
            pst2 = conn.prepareStatement(sql2);
            pst2.setFloat(1, rating);
            rs2 = pst2.executeQuery();
            while (rs1.next()) {
                num1 = rs1.getInt(1);
            }
            while (rs2.next()) {
                num2 = rs2.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs1.close();
                rs2.close();
                pst1.close();
                pst2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        float f = num1 / num2;
        return f;
    }


}

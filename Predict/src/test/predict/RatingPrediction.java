package test.predict;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * User: Cynric
 * Date: 14-4-11
 * Time: 21:41
 */
public class RatingPrediction {

    // 计算用户对项目的预测评分
    public static float getPrediction(int activeUserid, int preItemid,
                                      int neighbornum) {
        float prediction = 0;
        // 按用户间相似度从高到低获得所有邻居列表
        Map<Integer, List<UserHotelInfo>> map = UserSimilarity.queryAllRatingInfo();
        List<UserNeiborSim> neighborlist = UserSimilarity.orderSimilarity(activeUserid, map);

        // 得到目标用户的平均评分值
        float activeavg_rating = user_rating_avg(activeUserid);
        float neiboravgrating = 0;

        int neiborid = 0;
        float neiborWeight = 0;
        float neiborSumWeight = 0;
        float neiborItemRating = 0;
        float part = 0;// 预测公式的后半部分
        int n = 0;
        for (int i = 0; i < neighborlist.size(); i++) {
            neiborItemRating = neibor_item_rating(neiborid, preItemid);// 得到邻居用户对该项目的评分
            if (neiborItemRating != 0) {
                n++;
                if (n <= neighbornum) {
                    neiborid = neighborlist.get(i).getNeighborid();
                    neiborWeight = neighborlist.get(i).getSimilarity();

                    neiborSumWeight += neiborWeight;
                    neiboravgrating = user_rating_avg(neiborid);// 得到邻居用户的平均评分值
                    part += Math.abs((neiborItemRating - neiboravgrating)) * neiborWeight;
                } else {
                    break;
                }
            }
        }
        prediction = activeavg_rating + part / neiborSumWeight;
//        prediction = toShow(prediction); //格式化
        return prediction;
    }

    public static float user_rating_avg(int userId) {
        float f = 0;

        return f;
    }

    public static float neibor_item_rating(int neiborId, int itemId) {
        float f = 0;

        return f;
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

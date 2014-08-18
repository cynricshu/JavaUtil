package test.predict;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * User: Cynric
 * Date: 14-4-7
 * Time: 20:20
 */
public class UserSimilarity {

    interface ValueGetter {
        float getValue(UserHotelInfo userHotelInfo);
    }

    public static float getPearsonSimilarity(int activeUserid, int otherUserid, ValueGetter valueGetter) {
        List<UserHotelInfo> p1 = queryRatingInfo(activeUserid); //获取目标用户的打分信息
        List<UserHotelInfo> p2 = queryRatingInfo(otherUserid); //获取邻居用户的打分信息
        return getPearsonSimilarity(p1, p2, valueGetter);
    }

    public static float getPearsonSimilarity(List<UserHotelInfo> p1, List<UserHotelInfo> p2, ValueGetter valueGetter) {
        float weight = 0;
        int asize = p1.size(); // 得到用户 a 的评分数目
        int osize = p2.size(); // 得到用户 o 的评分数目
        float activesum = 0;
        float othersum = 0;
        float activeSqrSum = 0;
        float otherSqrSum = 0;
        float combinedSum = 0;
        int aUserItemid = 0;
        int oUserItemid = 0;
        float aUserRating = 0;
        float oUserRating = 0;
        int numCommonItems = 0; // 相同评分项的个数
        for (int p = 0; p < asize; p++) {
            aUserRating = valueGetter.getValue(p1.get(p));
            aUserItemid = p1.get(p).getHotelId();

            for (int q = 0; q < osize; q++) {
                oUserRating = valueGetter.getValue(p2.get(q));
                oUserItemid = p2.get(q).getHotelId();
                // 对相同项目评分进行计算
                if (aUserItemid == oUserItemid) {
                    activesum += aUserRating;
                    othersum += oUserRating;
                    activeSqrSum += (aUserRating * aUserRating);
                    otherSqrSum += (oUserRating * oUserRating);
                    combinedSum += (aUserRating * oUserRating);
                    numCommonItems++;
                }
            }
        }
        // 用户间相似度
        weight = (float) ((numCommonItems * combinedSum - activesum *
                othersum) / (Math.sqrt(numCommonItems * activeSqrSum - activesum * activesum) * Math.sqrt(numCommonItems * otherSqrSum - othersum * othersum)));
        return weight;
    }

    public static float getPearsonSimilarity_health(int activeUserid,
                                                    int otherUserid) {
        return getPearsonSimilarity(activeUserid, otherUserid, new ValueGetter() {
            @Override
            public float getValue(UserHotelInfo userHotelInfo) {
                return userHotelInfo.getHygiene();
            }
        });
    }

    public static float getPearsonSimilarity_health(List<UserHotelInfo> p1,
                                                    List<UserHotelInfo> p2) {
        return getPearsonSimilarity(p1, p2, new ValueGetter() {
            @Override
            public float getValue(UserHotelInfo userHotelInfo) {
                return userHotelInfo.getHygiene();
            }
        });
    }

    public static float getPearsonSimilarity_service(int activeUserid,
                                                     int otherUserid) {
        return getPearsonSimilarity(activeUserid, otherUserid, new ValueGetter() {
            @Override
            public float getValue(UserHotelInfo userHotelInfo) {
                return userHotelInfo.getService();
            }
        });
    }

    public static float getPearsonSimilarity_service(List<UserHotelInfo> p1,
                                                     List<UserHotelInfo> p2) {
        return getPearsonSimilarity(p1, p2, new ValueGetter() {
            @Override
            public float getValue(UserHotelInfo userHotelInfo) {
                return userHotelInfo.getService();
            }
        });
    }

    public static float getPearsonSimilarity_equipment(int activeUserid,
                                                       int otherUserid) {
        return getPearsonSimilarity(activeUserid, otherUserid, new ValueGetter() {
            @Override
            public float getValue(UserHotelInfo userHotelInfo) {
                return userHotelInfo.getFacility();
            }
        });
    }

    public static float getPearsonSimilarity_equipment(List<UserHotelInfo> p1,
                                                       List<UserHotelInfo> p2) {
        return getPearsonSimilarity(p1, p2, new ValueGetter() {
            @Override
            public float getValue(UserHotelInfo userHotelInfo) {
                return userHotelInfo.getFacility();
            }
        });
    }

    public static float getPearsonSimilarity_environment(int activeUserid,
                                                         int otherUserid) {
        return getPearsonSimilarity(activeUserid, otherUserid, new ValueGetter() {
            @Override
            public float getValue(UserHotelInfo userHotelInfo) {
                return userHotelInfo.getLocation();
            }
        });
    }

    public static float getPearsonSimilarity_environment(List<UserHotelInfo> p1,
                                                         List<UserHotelInfo> p2) {
        return getPearsonSimilarity(p1, p2, new ValueGetter() {
            @Override
            public float getValue(UserHotelInfo userHotelInfo) {
                return userHotelInfo.getLocation();
            }
        });
    }

    public static float getPearsonSimilarity_General(int activeUserid,
                                                     int otherUserid) {
        return getPearsonSimilarity(activeUserid, otherUserid, new ValueGetter() {
            @Override
            public float getValue(UserHotelInfo userHotelInfo) {
                return userHotelInfo.getGeneral();
            }
        });
    }

    public static float getPearsonSimilarity_General(List<UserHotelInfo> p1,
                                                     List<UserHotelInfo> p2) {
        return getPearsonSimilarity(p1, p2, new ValueGetter() {
            @Override
            public float getValue(UserHotelInfo userHotelInfo) {
                return userHotelInfo.getGeneral();
            }
        });
    }

    public static float getAvgPearsonSimilarity(int activeUserid,
                                                int otherUserid) {
        List<UserHotelInfo> p1 = queryRatingInfo(activeUserid);
        List<UserHotelInfo> p2 = queryRatingInfo(otherUserid);
        return getAvgPearsonSimilarity(p1, p2);
    }

    public static float getAvgPearsonSimilarity(List<UserHotelInfo> p1, List<UserHotelInfo> p2) {
        float weight = 0;
        weight = (getPearsonSimilarity_health(p1, p2)
                + getPearsonSimilarity_service(p1, p2)
                + getPearsonSimilarity_equipment(p1, p2) +
                getPearsonSimilarity_environment(p1, p2)) / 4;
        return weight;
    }

    public static float getEDSimilarity(int activeUserid, int otherUserid) {
        List<UserHotelInfo> p1 = queryRatingInfo(activeUserid);
        List<UserHotelInfo> p2 = queryRatingInfo(otherUserid);
        return getEDSimilarity(p1, p2);
    }

    public static float getEDSimilarity(List<UserHotelInfo> p1, List<UserHotelInfo> p2) {
        float weight = 0;
        int asize = p1.size();// 得到用户a的评分数目
        int osize = p2.size();// 得到用户o的评分数目
        float dr = 0;
        float drsum = 0;
        int aUserItemid = 0;
        int oUserItemid = 0;
        float aUserRatinghe = 0;
        float oUserRatinghe = 0;
        float aUserRatingse = 0;
        float oUserRatingse = 0;
        float aUserRatingeq = 0;
        float oUserRatingeq = 0;
        float aUserRatingen = 0;
        float oUserRatingen = 0;
        int numCommonItems = 0;
        for (int p = 0; p < asize; p++) {
            for (int q = 0; q < osize; q++) {
                aUserRatinghe = p1.get(p).getHygiene();
                aUserRatingse = p1.get(p).getService();
                aUserRatingeq = p1.get(p).getFacility();
                aUserRatingen = p1.get(p).getLocation();
                aUserItemid = p1.get(p).getHotelId();

                oUserRatinghe = p2.get(q).getHygiene();
                oUserRatingse = p2.get(q).getService();
                oUserRatingeq = p2.get(q).getFacility();
                oUserRatingen = p2.get(q).getLocation();
                oUserItemid = p2.get(q).getHotelId();
                // 对相同项目评分进行计算
                if (aUserItemid == oUserItemid) {
                    float diff1 = aUserRatinghe - oUserRatinghe;
                    float diff2 = aUserRatingse - oUserRatingse;
                    float diff3 = aUserRatingeq - oUserRatingeq;
                    float diff4 = aUserRatingen - oUserRatingen;
                    dr = (float) Math.sqrt((diff1 * diff1) + (diff2 * diff2)
                            + (diff3 * diff3) + (diff4 * diff4));
                    drsum += dr;
                    numCommonItems++;
                }
            }
        }
        weight = numCommonItems / (numCommonItems + drsum);
        return weight;
    }

    public static List<UserNeiborSim> orderSimilarity(int userId, int neighbornum, Algorithm alg) {
        Map<Integer, List<UserHotelInfo>> map = queryAllRatingInfo();
        return orderSimilarity(userId, map, neighbornum, alg);
    }

    public static List<UserNeiborSim> orderSimilarity(int userId, Map<Integer, List<UserHotelInfo>> map, int neighbornum, final Algorithm alg) {
        List<UserNeiborSim> list = new ArrayList<>();

        List<UserHotelInfo> userRatingList = map.get(userId);

        for (Object obj : map.keySet()) {
            int currUserId = Integer.parseInt(obj.toString());
            if (currUserId != userId) {
                List<UserHotelInfo> neiborRatingList = map.get(currUserId);

                float similarity = 0f;
                switch (alg) {
                    case SINGLE:
                        similarity = getPearsonSimilarity_General(userRatingList, neiborRatingList);
                        break;
                    case MULTIPLE:
                        similarity = getAvgPearsonSimilarity(userRatingList, neiborRatingList);
                        break;
                    case ED:
                        similarity = getEDSimilarity(userRatingList, neiborRatingList);
                        break;

                    default:
                        break;
                }


                UserNeiborSim userNeiborSim = new UserNeiborSim();
                userNeiborSim.setNeighborid(currUserId);
                userNeiborSim.setSimilarity(similarity);
                list.add(userNeiborSim);
            }
        }

        Collections.sort(list, new Comparator<UserNeiborSim>() {
            @Override
            public int compare(UserNeiborSim o1, UserNeiborSim o2) {
                Float s1 = o1.getSimilarity();
                Float s2 = o2.getSimilarity();
                if (s1.isNaN() && !s2.isNaN()) {
                    return 1;
                } else if (!s1.isNaN() && s2.isNaN()) {
                    return -1;
                } else {
                    switch (alg) {
                        case SINGLE:
                            if (s1 < s2) {
                                return 1;
                            } else if (s1 > s2) {
                                return -1;
                            } else {
                                return 0;
                            }
                        case MULTIPLE:
                            if (s1 < s2) {
                                return 1;
                            } else if (s1 > s2) {
                                return -1;
                            } else {
                                return 0;
                            }
                        case ED:
                            if (s1 < s2) {
                                return -1;
                            } else if (s1 > s2) {
                                return 1;
                            } else {
                                return 0;
                            }
                        default:
                            break;
                    }

                }
                return 0;
            }
        });
        if (list.size() < neighbornum) {
            return list;
        } else {
            return list.subList(0, neighbornum);
        }
    }

    static List<UserHotelInfo> queryRatingInfo(int userId) {
        List<UserHotelInfo> list = new ArrayList<>();

        String sql = "select t1.*, t2.userId from commentinfo t1 left join userinfo t2 on t1.username = t2.username where userId = " + userId;
        Connection conn = DataSource.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(getUserHotelInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    static Map<Integer, List<UserHotelInfo>> queryAllRatingInfo() {
        Map<Integer, List<UserHotelInfo>> map = new HashMap<>();
        List<UserHotelInfo> list = new ArrayList<>();

        String sql = "select t1.*, t2.userId from commentinfo t1 inner join userinfo t2 on t1.username = t2.username " +
                " order by userId ";
        Connection conn = DataSource.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int currUserId = 0;
            if (rs.next()) {
                currUserId = rs.getInt("userId");
                list.add(getUserHotelInfo(rs));
            }
            while (rs.next()) {
                int newUserId = rs.getInt("userId");
                if (currUserId != newUserId) {
                    map.put(currUserId, list);

                    list = new ArrayList<>();
                    currUserId = newUserId;
                }
                list.add(getUserHotelInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static UserHotelInfo getUserHotelInfo(ResultSet rs) {
        UserHotelInfo userHotelInfo = new UserHotelInfo();
        try {
            userHotelInfo.setUserId(rs.getInt("userId"));
            userHotelInfo.setHotelId(rs.getInt("hotelId"));
            userHotelInfo.setGeneral(rs.getFloat("comment"));
            userHotelInfo.setHygiene(rs.getFloat("sanitation"));
            userHotelInfo.setFacility(rs.getFloat("facility"));
            userHotelInfo.setLocation(rs.getFloat("position"));
            userHotelInfo.setService(rs.getFloat("service"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userHotelInfo;
    }
}

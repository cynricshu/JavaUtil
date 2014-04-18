package test.predict;

import java.util.Scanner;

/**
 * User: Cynric
 * Date: 14-4-12
 * Time: 10:19
 */
public class BootStrap {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
//        while (true) {
//            System.out.println("new turn");
//            System.out.println("please input a userId");
//            int userId = Integer.parseInt(in.nextLine());
//            System.out.println("please input another userId");
//            int otherId = Integer.parseInt(in.nextLine());
//            System.out.println("AVG Similarity: " + UserSimilarity.getAvgPearsonSimilarity(userId, otherId));
//            System.out.println("ED Similarity: " + UserSimilarity.getEDSimilarity(userId, otherId));
//            System.out.println();
//
//        }

//        while (true) {
//            System.out.println("new turn");
//            System.out.println("please input a userId");
//            int userId = Integer.parseInt(in.nextLine());
//            List<UserNeiborSim> list = UserSimilarity.orderSimilarity(userId, map);
////            list.toString();
//
//
//            List<Integer> hotelIdList = Count.getMaxCountHotelId(list.subList(0, 20), map, 5);
//            for (Integer i : hotelIdList) {
//                System.out.printf(i.toString() + " ");
//            }
//
//
//        }


        while (true) {
            System.out.println("new turn");
            System.out.println("please input a userId");
            int userId = Integer.parseInt(in.nextLine());
//            System.out.println("please input an itemId");
//            int itemId = Integer.parseInt(in.nextLine());
            System.out.println("please input the number of neighbours");
            int neighbourNum = Integer.parseInt(in.nextLine());
            System.out.println("Prediction for ED: " + RatingPrediction.predict(userId, neighbourNum));
            System.out.println();
        }


    }

//    public static float getAvgPearsonSimilarity(int activeUserid,
//                                                int otherUserid) {
//        float weight = 0;
//        weight = (getPearsonSimilarity_health(activeUserid, otherUserid)
//                + getPearsonSimilarity_service(activeUserid, otherUserid)
//                + getPearsonSimilarity_equipment(activeUserid, otherUserid) +
//                getPearsonSimilarity_environment(activeUserid, otherUserid)) / 4;
//        return weight;
//    }
//
//    public static float getPearsonSimilarity_health(int activeUserid,
//                                                    int otherUserid) {
//        float weight = 0;
//        List<UserHotelInfo> p1 = UserSimilarity.queryRatingInfo(activeUserid); //获取目标用户的打分信息
//        List<UserHotelInfo> p2 = UserSimilarity.queryRatingInfo(otherUserid); //获取邻居用户的打分信息
//        int asize = p1.size(); // 得到用户 a 的评分数目
//        int osize = p2.size(); // 得到用户 o 的评分数目
//        float activesum = 0;
//        float othersum = 0;
//        float activeSqrSum = 0;
//        float otherSqrSum = 0;
//        float combinedSum = 0;
//        int aUserItemid = 0;
//        int oUserItemid = 0;
//        float aUserRating = 0;
//        float oUserRating = 0;
//        int numCommonItems = 0;
//        for (int p = 0; p < asize; p++) {
//            for (int q = 0; q < osize; q++) {
//                aUserRating = p1.get(p).getHygiene();
//                aUserItemid = p1.get(p).getHotelId();
//
//                oUserRating = p1.get(p).getHygiene();
//                oUserItemid = p2.get(q).getHotelId();
//                // 对相同项目评分进行计算
//                if (aUserItemid == oUserItemid) {
//                    activesum += aUserRating;
//                    othersum += oUserRating;
//                    activeSqrSum += (aUserRating * aUserRating);
//                    otherSqrSum += (oUserRating * oUserRating);
//                    combinedSum += (aUserRating * oUserRating);
//                    numCommonItems++;
//                }
//            }
//        }
//        // 用户间相似度
//        weight = (float) ((numCommonItems * combinedSum - activesum *
//                othersum) / (Math.sqrt(numCommonItems * activeSqrSum - activesum * activesum) * Math.sqrt(numCommonItems * otherSqrSum - othersum * othersum)));
//        return weight;
//    }
//
//    public static float getPearsonSimilarity_service(int activeUserid,
//                                                     int otherUserid) {
//        float weight = 0;
//        List<UserHotelInfo> p1 = UserSimilarity.queryRatingInfo(activeUserid); //获取目标用户的打分信息
//        List<UserHotelInfo> p2 = UserSimilarity.queryRatingInfo(otherUserid); //获取邻居用户的打分信息
//        int asize = p1.size(); // 得到用户 a 的评分数目
//        int osize = p2.size(); // 得到用户 o 的评分数目
//        float activesum = 0;
//        float othersum = 0;
//        float activeSqrSum = 0;
//        float otherSqrSum = 0;
//        float combinedSum = 0;
//        int aUserItemid = 0;
//        int oUserItemid = 0;
//        float aUserRating = 0;
//        float oUserRating = 0;
//        int numCommonItems = 0;
//        for (int p = 0; p < asize; p++) {
//            for (int q = 0; q < osize; q++) {
//                aUserRating = p1.get(p).getService();
//                aUserItemid = p1.get(p).getHotelId();
//
//                oUserRating = p1.get(p).getService();
//                oUserItemid = p2.get(q).getHotelId();
//                // 对相同项目评分进行计算
//                if (aUserItemid == oUserItemid) {
//                    activesum += aUserRating;
//                    othersum += oUserRating;
//                    activeSqrSum += (aUserRating * aUserRating);
//                    otherSqrSum += (oUserRating * oUserRating);
//                    combinedSum += (aUserRating * oUserRating);
//                    numCommonItems++;
//                }
//            }
//        }
//        // 用户间相似度
//        weight = (float) ((numCommonItems * combinedSum - activesum *
//                othersum) / (Math.sqrt(numCommonItems * activeSqrSum - activesum * activesum) * Math.sqrt(numCommonItems * otherSqrSum - othersum * othersum)));
//        return weight;
//    }
//
//    public static float getPearsonSimilarity_equipment(int activeUserid,
//                                                       int otherUserid) {
//        float weight = 0;
//        List<UserHotelInfo> p1 = UserSimilarity.queryRatingInfo(activeUserid); //获取目标用户的打分信息
//        List<UserHotelInfo> p2 = UserSimilarity.queryRatingInfo(otherUserid); //获取邻居用户的打分信息
//        int asize = p1.size(); // 得到用户 a 的评分数目
//        int osize = p2.size(); // 得到用户 o 的评分数目
//        float activesum = 0;
//        float othersum = 0;
//        float activeSqrSum = 0;
//        float otherSqrSum = 0;
//        float combinedSum = 0;
//        int aUserItemid = 0;
//        int oUserItemid = 0;
//        float aUserRating = 0;
//        float oUserRating = 0;
//        int numCommonItems = 0;
//        for (int p = 0; p < asize; p++) {
//            for (int q = 0; q < osize; q++) {
//                aUserRating = p1.get(p).getFacility();
//                aUserItemid = p1.get(p).getHotelId();
//
//                oUserRating = p1.get(p).getFacility();
//                oUserItemid = p2.get(q).getHotelId();
//                // 对相同项目评分进行计算
//                if (aUserItemid == oUserItemid) {
//                    activesum += aUserRating;
//                    othersum += oUserRating;
//                    activeSqrSum += (aUserRating * aUserRating);
//                    otherSqrSum += (oUserRating * oUserRating);
//                    combinedSum += (aUserRating * oUserRating);
//                    numCommonItems++;
//                }
//            }
//        }
//        // 用户间相似度
//        weight = (float) ((numCommonItems * combinedSum - activesum *
//                othersum) / (Math.sqrt(numCommonItems * activeSqrSum - activesum * activesum) * Math.sqrt(numCommonItems * otherSqrSum - othersum * othersum)));
//        return weight;
//    }
//
//    public static float getPearsonSimilarity_environment(int activeUserid,
//                                                         int otherUserid) {
//        float weight = 0;
//        List<UserHotelInfo> p1 = UserSimilarity.queryRatingInfo(activeUserid); //获取目标用户的打分信息
//        List<UserHotelInfo> p2 = UserSimilarity.queryRatingInfo(otherUserid); //获取邻居用户的打分信息
//        int asize = p1.size(); // 得到用户 a 的评分数目
//        int osize = p2.size(); // 得到用户 o 的评分数目
//        float activesum = 0;
//        float othersum = 0;
//        float activeSqrSum = 0;
//        float otherSqrSum = 0;
//        float combinedSum = 0;
//        int aUserItemid = 0;
//        int oUserItemid = 0;
//        float aUserRating = 0;
//        float oUserRating = 0;
//        int numCommonItems = 0;
//        for (int p = 0; p < asize; p++) {
//            for (int q = 0; q < osize; q++) {
//                aUserRating = p1.get(p).getLocation();
//                aUserItemid = p1.get(p).getHotelId();
//
//                oUserRating = p1.get(p).getLocation();
//                oUserItemid = p2.get(q).getHotelId();
//                // 对相同项目评分进行计算
//                if (aUserItemid == oUserItemid) {
//                    activesum += aUserRating;
//                    othersum += oUserRating;
//                    activeSqrSum += (aUserRating * aUserRating);
//                    otherSqrSum += (oUserRating * oUserRating);
//                    combinedSum += (aUserRating * oUserRating);
//                    numCommonItems++;
//                }
//            }
//        }
//        // 用户间相似度
//        weight = (float) ((numCommonItems * combinedSum - activesum *
//                othersum) / (Math.sqrt(numCommonItems * activeSqrSum - activesum * activesum) * Math.sqrt(numCommonItems * otherSqrSum - othersum * othersum)));
//        return weight;
//    }
}

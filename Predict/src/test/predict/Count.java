package test.predict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Cynric
 * Date: 14-4-12
 * Time: 11:12
 */
public class Count {

    public static Map<Integer, Integer> countMap = new HashMap<>();


    public static void addCount(int hotelId) {
        int count = 0;
        if (countMap.containsKey(hotelId)) {
            count = countMap.get(hotelId);
        }
        count += 1;
        countMap.put(hotelId, count);
    }

    public static List<Integer> getMaxCount(int num) {
        List<Integer> list = new ArrayList<>(num);

        for (int i = 0; i < num; i++) {
            int maxHotelId = 0;
            int max = 0;
            for (int hotelId : countMap.keySet()) {
                int current = countMap.get(hotelId);
                if (max < current) {
                    max = current;
                    maxHotelId = hotelId;
                }
            }
            countMap.remove(maxHotelId);
            list.add(maxHotelId);
        }
        return list;
    }

    public static List<Integer> getMaxCountHotelId(List<UserNeiborSim> list, Map<Integer, List<UserHotelInfo>> map, int num) {

        for (UserNeiborSim userNeiborSim : list) {
            List<UserHotelInfo> userHotelInfoList = map.get(userNeiborSim.getNeighborid());

            for (UserHotelInfo userHotelInfo : userHotelInfoList) {
                addCount(userHotelInfo.getHotelId());
            }
        }

        return getMaxCount(num);
    }

}

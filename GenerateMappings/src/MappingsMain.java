import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MappingsMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<String[]> list = parsePlainInput(sc);

//		while (sc.hasNextLine()) {
//			String next = sc.nextLine();
//			if ("zzz".equals(next))
//				break;
//			else {
//				sb.append(next);
//			}
//		}
//		String input = sb.toString();
//

//		try {
//			JSONObject inputJson = new JSONObject(input);
//			Iterator iter = inputJson.keys();
//
//			while (iter.hasNext()) {
//				String key = (String) iter.next();
//				if (key.equals("_id")) 
//					continue;
//				if (key.equals("id"))
//					continue;
//				JSONObject attribute = (JSONObject) inputJson.get(key);
//				String type = "string";
//				if (attribute.has("type")) {
//					type = (String) attribute.get("type");
//					type = parseType(type);
//				}
//				String[] kvp = new String[2];
//				kvp[0] = key;
//				kvp[1] = type;
//				list.add(kvp);
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}

        System.out.println(list.size());

        //生成xml文件
        Java2XML j2x = new Java2XML();
        j2x.BuildXMLDoc(list, "WaterPollution_HourAvgData");
    }


    private static List<String[]> parsePlainInput(Scanner in) {
        List<String[]> list = new ArrayList<>();

        while (in.hasNextLine()) {
            String next = in.nextLine();
            if (next == null || "".equals(next))
                continue;
            String[] s = next.split("\\s");
            if (s.length > 1 && s[1].contains("(")) {
                s[1] = s[1].substring(0, s[1].indexOf('('));
            }
            if ("zzz".equals(next))
                break;
            else {
                list.add(s);
            }
        }
        return list;
    }
}

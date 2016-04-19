import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
//        String originRegex = "/api/GIS/alarmPara/(?<modelName>dq|ds|fq|ws|zs).*";
        String originRegex = "/v0.1/(?!user/signup).*";
        String input = "/v0.1/device/1";

        List<String> list = getGroupNames(originRegex);
        for (String s : list) {
            System.out.println(s);
        }

        Pattern pattern = Pattern.compile(originRegex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            System.out.println("match");
//            System.out.println(matcher.group("modelName"));
//            System.out.println(matcher.group("value"));
        } else {
            System.out.println("didn't match");
        }
    }


    private static List<String> getGroupNames(String s) {
        List<String> list = new ArrayList<>();

        int startIndex;
        int endIndex;

        while (s.contains("<")) {
            startIndex = s.indexOf("<");
            endIndex = s.indexOf(">");

            String temp = s.substring(startIndex + 1, endIndex);
            list.add(temp);
            s = s.substring(endIndex + 1);
        }

        return list;

    }

}
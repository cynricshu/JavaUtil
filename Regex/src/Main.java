import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String originRegex = "/api/BPM/form\\?(?<key>taskid|processid)=(?<value>.*)";
        String input = "/api/BPM/form?taskid=704";
        List<String> list = getGroupNames(originRegex);
        for (String s : list) {
            System.out.println(s);
        }

        Pattern pattern = Pattern.compile(originRegex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            System.out.println(matcher.group("key"));
            System.out.println(matcher.group("value"));
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
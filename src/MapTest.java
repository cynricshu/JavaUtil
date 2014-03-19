import java.util.HashMap;
import java.util.Map;

/**
 * User: Cynric
 * Date: 13-12-12
 * Time: 16:27
 */
public class MapTest {
    private final static Map dataModels = new HashMap<String, Map>();
    private final static Map log = new HashMap<String, String[]>();
    private final static String[] logField = new String[]{"level", "serverNodeName", "applicationName", "logDate", "message"};
    private final static String[] logFieldText = new String[]{"级别", "节点名", "应用名", "时间", "信息"};

    static {
        dataModels.put("Log", log);
        log.put("field", logField);
        log.put("text", logFieldText);


    }

    public static Map getModelInfo(String modelName) {
        return (Map) dataModels.get(modelName);
    }

    public static String[] getModelField(String modelName) {
        return (String[]) ((Map) dataModels.get(modelName)).get("field");
    }

    public static String[] getModelText(String modelName) {
        return (String[]) ((Map) dataModels.get(modelName)).get("text");
    }
}

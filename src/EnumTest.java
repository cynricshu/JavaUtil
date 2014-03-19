/**
 * User: Cynric
 * Date: 13-12-12
 * Time: 16:27
 */
public class EnumTest {
    public static Model getModel(String modelName) {
        return Model.valueOf(modelName);
    }

    public static String[] getModelField(String modelName) {
        return Model.valueOf(modelName).getField();
    }

    public static String[] getModelText(String modelName) {
        return Model.valueOf(modelName).getText();
    }

    private static enum Model {
        Log(new String[]{"level", "serverNodeName", "applicationName", "logDate", "message"},
                new String[]{"级别", "节点名", "应用名", "时间", "信息"}),
        A(new String[]{"level"},
                new String[]{"级别"});
        private String[] _field = null;
        private String[] _text = null;

        Model(String[] field, String[] text) {
            _field = field;
            _text = text;
        }

        public String[] getField() {
            return _field;
        }

        public String[] getText() {
            return _text;
        }
    }
}

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Java2XML {

    public void BuildXMLDoc(List<String[]> list, String tableName) {
        // 创建根节点 list;
        Element root = new Element("hibernate-mapping");
        // 根节点添加到文档中；
        Document doc = new Document(root);

        DocType docType = new DocType("hibernate-mapping",
                "-       //Hibernate/Hibernate Mapping DTD 3.0//EN",
                "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd");
        doc.setDocType(docType);

        //生成表名
        Element tableElement = new Element("class");
        tableElement.setAttribute("table", "DB_PEMEP_" + tableName);
        tableElement.setAttribute("entity-name", tableName);
        root.addContent(tableElement);

        //生成主键
        Element idElement = new Element("id");
        idElement.setAttribute("name", "_id");
        idElement.setAttribute("column", "ID");
        idElement.setAttribute("type", "long");
        idElement.addContent(new Element("generator").setAttribute("class",
                "native"));
        tableElement.addContent(idElement);

        // Element modelElement = new Element("property");
        // modelElement.setAttribute("name", "modelname");
        // modelElement.setAttribute("type", "string");
        // modelElement.addContent(new Element("column").setAttribute("name",
        // "modelname"));
        // tableElement.addContent(modelElement);

        for (String[] o : list) {
            // 创建节点 user;
            Element elements = new Element("property");
            // 给 user 节点添加属性 id;
            elements.setAttribute("name", o[0]);
            elements.setAttribute("type", parseType(o[1]));

            elements.addContent(new Element("column")
                    .setAttribute("name", o[0]));
            tableElement.addContent(elements);
        }

        XMLOutputter XMLOut = new XMLOutputter();
        // 输出xml文件；
        try {
            XMLOut.output(doc, new FileOutputStream(
                    "../Enterprise Apps Engine/apps/PEMEP/conf/datasources/mappings/PEMEP/"
                            + tableName + ".hbm.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseType(String s) {
        switch (s) {
            case "number":
                return "int";
            case "bigint":
                return "long";
            case "real":
                return "double";
            case "datetime":
                return "timestamp";
            case "varchar":
                return "string";
            default:
                return s.toLowerCase();
        }
    }

}
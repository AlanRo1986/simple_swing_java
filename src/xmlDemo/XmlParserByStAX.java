package xmlDemo;

import model.AreaModel;
import model.AreaNode;
import system.lib.OutPut;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 拉解析器解析
 * Created by alan on 2018/12/16.
 */
public class XmlParserByStAX extends OutPut {

    private String path;

    private List<AreaModel> areaModels = new ArrayList<>();

    public XmlParserByStAX() {
    }

    public XmlParserByStAX(String path) {
        this.path = path;
    }

    public List<AreaModel> getAreaModels() {
        return areaModels;
    }

    public void parser() {
        try {
            InputStream in = new FileInputStream(path);
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader reader = factory.createXMLStreamReader(in);

            AreaModel province = null;
            List<AreaNode> citys = null;
            List<AreaNode> areas = null;
            AreaNode city = null;
            long t = System.currentTimeMillis();
            areaModels = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    switch (reader.getName().toString()) {
                        case "province":
                            province = new AreaModel();
                            province.setProvince(new AreaNode(reader.getAttributeValue(null,"name"),
                                    Integer.valueOf(reader.getAttributeValue(null,"postcode"))));
                            citys = new ArrayList<>();
                            break;
                        case "city":
                            city = new AreaNode(reader.getAttributeValue(null,"name"),
                                    Integer.valueOf(reader.getAttributeValue(null,"postcode")));
                            areas = new ArrayList<>();
                            break;
                        case "area":
                            areas.add(new AreaNode(reader.getAttributeValue(null,"name"),
                                    Integer.valueOf(reader.getAttributeValue(null,"postcode"))));
                            break;
                    }
                } else if (event == XMLStreamConstants.END_ELEMENT) {
                    switch (reader.getName().toString()) {
                        case "province":
                            province.setCitys(citys);
                            areaModels.add(province);
                            break;
                        case "city":
                            city.setChild(areas);
                            citys.add(city);
                            break;
                        case "area":
                            break;
                    }

                } else if (event == XMLStreamConstants.END_DOCUMENT) {
                    out("Use StAXParser object,and use time is " + (System.currentTimeMillis() - t) + "ms");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

    public void test() {
        String str = "";
        for (AreaModel a : areaModels) {
            str += a.getProvince() + "\n";
            for (AreaNode n : a.getCitys()) {
                str += "\t" + n + "\n";
                for (AreaNode j : n.getChild()) {
                    str += "\t\t" + j + "\n";
                }
            }
        }
        out(str);
    }


}

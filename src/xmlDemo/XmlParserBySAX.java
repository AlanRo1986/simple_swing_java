package xmlDemo;

import model.AreaModel;
import model.AreaNode;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import system.lib.OutPut;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Stream解析bySAX
 * Created by alan on 2018/12/16.
 */
public class XmlParserBySAX extends OutPut {

    private String path = "d:/test/area.xml";

    private List<AreaModel> areaModels;

    public XmlParserBySAX() {
    }

    public XmlParserBySAX(String path) {
        this.path = path;
    }

    public List<AreaModel> getAreaModels() {
        return areaModels;
    }

    public void parser() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(path, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void test(){
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

    private long t = 0;
    private DefaultHandler handler = new DefaultHandler() {

        private AreaModel province;
        private List<AreaNode> citys;
        private List<AreaNode> areas;
        private AreaNode city;


        @Override
        public void startDocument() throws SAXException {
            areaModels = new ArrayList<>();
            t = System.currentTimeMillis();

//            out("start....");
        }

        @Override
        public void endDocument() throws SAXException {
            out("Use SAXParser object,and use time is " + (System.currentTimeMillis() - t) + "ms");
        }


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
                case "province":
                    province = new AreaModel();
                    province.setProvince(new AreaNode(attributes.getValue("name"), Integer.valueOf(attributes.getValue("postcode"))));

                    citys = new ArrayList<>();
                    break;
                case "city":
                    city = new AreaNode(attributes.getValue("name"), Integer.valueOf(attributes.getValue("postcode")));
                    areas = new ArrayList<>();
                    break;
                case "area":
                    areas.add(new AreaNode(attributes.getValue("name"), Integer.valueOf(attributes.getValue("postcode"))));
                    break;
            }
        }


        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
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
        }
    };




}

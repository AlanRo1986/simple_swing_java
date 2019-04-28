package xmlDemo;

import model.AreaModel;
import model.AreaNode;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import system.lib.OutPut;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Document解析
 * Created by alan on 2018/12/16.
 */
public class XmlParserByDocument extends OutPut {

    private String path;

    List<AreaModel> areaModels = new ArrayList<>();

    public XmlParserByDocument() {
    }

    public XmlParserByDocument(String path) {
        this.path = path;
    }

    public List<AreaModel> getAreaModels() {
        return areaModels;
    }

    public void parser() {
        long t = System.currentTimeMillis();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(path);
            Element element = document.getDocumentElement();

            out("document v" + document.getXmlVersion() + " encode " + document.getInputEncoding());
            if ("root".equals(element.getTagName())) {
                NodeList nodeList = element.getChildNodes();

                AreaModel area = null;
                for (int i = 0; i < nodeList.getLength(); i++) {
                    String nodeName = nodeList.item(i).getNodeName();
                    if ("province".equals(nodeName)) {
                        area = new AreaModel(parserNode(nodeList.item(i)), parserNodeList(nodeList.item(i).getChildNodes()));
                        areaModels.add(area);

                    }
                }

                out("Use Document object and use time is " + (System.currentTimeMillis() - t) + "ms.");

            } else {
                throw new Exception("invalid xml file.");
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

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

    private List<AreaNode> parserNodeList(NodeList list) {
        List<AreaNode> nodes = new ArrayList<>();
        int l = list.getLength();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).hasChildNodes()) {
                AreaNode node = parserNode(list.item(i));
                node.setChild(parserNodeList(list.item(i).getChildNodes()));
                nodes.add(node);
            } else {
                AreaNode node = parserNode(list.item(i));
                if (node != null) {
                    nodes.add(node);
                }
            }
        }
        return nodes;
    }

    private AreaNode parserNode(Node node) {
        AreaNode areaNode = null;
        NamedNodeMap attrs = node.getAttributes();
        if (attrs != null) {
            areaNode = new AreaNode(attrs.getNamedItem("name").getTextContent(), Integer.valueOf(attrs.getNamedItem("postcode").getTextContent()));
        }
        return areaNode;
    }

}

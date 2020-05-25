package Tools;

import Database.Hotelroom;
import Database.Request;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class LoadReq {

    private ArrayList<Request> req_list;
    private Request req;

    public ArrayList<Request> getReq_list() {
        return req_list;
    }

    public LoadReq() {
        req_list=new ArrayList<Request>();
    }

    public void add_req(Request e)
    {
        req_list.add(e);
    }

    public void readDB()
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = db.parse(new FileInputStream(new File("src/main/resources/requests.xml")));
            // System.out.println(doc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        NodeList entries = doc.getElementsByTagName("request");

        int num = entries.getLength();

        for (int i = 0; i < num; i++) {
            Element node = (Element) entries.item(i);
            listAllAttributes(node);
            add_req(req);
        }
    }

    private void listAllAttributes(Element element) {

        NamedNodeMap attributes = element.getAttributes();
        req=new Request();
        int numAttrs = attributes.getLength();

        for (int i = 0; i < numAttrs; i++) {
            Attr attr = (Attr) attributes.item(i);

            String attrName = attr.getNodeName();
            String attrValue = attr.getNodeValue();

            //    System.out.println(attrName + " " + attrValue);
            superSetter(element.getNodeName(), attrName, attrValue);

        }
    }

    public void superSetter(String node_name,String attrName, String attrValue)
    {
        switch (attrName)
        {
            case "ID":req.setID(attrValue);
                break;
            case "customer":req.setCustomer(attrValue);
                break;
            case "owner":req.setOwner(attrValue);
                break;
            case "status":req.setStatus(attrValue);
                break;
        }
    }


}

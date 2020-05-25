package Tools;

import Database.Request;
import controllers.Remind;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;

public class EditReqXML {



    public void EditStatus(Request r)
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc=null;
        try {
            doc = db.parse(new FileInputStream(new File("src/main/resources/requests.xml")));
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        NodeList entries = doc.getElementsByTagName("request");
        Element request=null;

        for(int i=0 ;i< entries.getLength();i++)
        {
            request = (Element) entries.item(i);
            if(request.getAttribute("ID").equals(r.getID())
                    && request.getAttribute("owner").equals(r.getOwner())
            && request.getAttribute("customer").equals(r.getCustomer()))
            {

                request.setAttribute("status",r.getStatus());

                writeXMLFile(doc);
                break;
            }
        }
    }

    private static void writeXMLFile(Document doc)
    {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/main/resources/requests.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        // System.out.println("XML file updated successfully");
    }
}

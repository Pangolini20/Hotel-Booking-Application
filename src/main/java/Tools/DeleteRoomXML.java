package Tools;

import Database.Hotelroom;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DeleteRoomXML
{
    public void deleteRoom(Hotelroom r)
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
            doc = db.parse(new FileInputStream(new File("src/main/resources/rooms.xml")));
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        Element root=null;
        root = doc.getDocumentElement();

        NodeList entries = doc.getElementsByTagName("room");
        Element room=null;
        for(int i=0 ;i< entries.getLength();i++)
        {
             room = (Element) entries.item(i);
             if(room.getAttribute("ID").equals(r.getID()) /*&&
                     room.getAttribute("owner").equals(r.getOwner())*/) {
                 System.out.println(room.getAttribute("ID"));
                 root.removeChild(room);
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
        StreamResult result = new StreamResult(new File("src/main/resources/rooms.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
       // System.out.println("XML file updated successfully");
    }

}

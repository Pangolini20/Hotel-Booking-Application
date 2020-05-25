package Tools;

import Database.Hotelroom;
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

public class EditRoomXML {

    public void editRoom(Hotelroom r,Hotelroom change)
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

        NodeList entries = doc.getElementsByTagName("room");
        Element room=null;

        for(int i=0 ;i< entries.getLength();i++)
        {
            room = (Element) entries.item(i);
            if(room.getAttribute("ID").equals(r.getID()) &&
                    room.getAttribute("owner").equals(r.getOwner())) {

                room.setAttribute("ID",change.getID());
                room.setAttribute("price",change.getPrice());
                room.setAttribute("size",change.getSize());
                room.setAttribute("nr_person",change.getNr_pers());

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

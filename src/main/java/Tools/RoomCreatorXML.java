package Tools;

import Database.Hotelroom;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

public class RoomCreatorXML {

    public static void createRoom(Hotelroom room)
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = documentBuilder.parse("src/main/resources/rooms.xml");
        } catch (SAXException e) {
                    e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element root=null;
        root = document.getDocumentElement();

        Element new_room=document.createElement("room");
        new_room.setAttribute("ID",room.getID());
        new_room.setAttribute("owner",room.getOwner());
        new_room.setAttribute("price",room.getPrice());
        new_room.setAttribute("size",room.getSize());
        new_room.setAttribute("available",String.valueOf(room.isAvailable()));
        new_room.setAttribute("ocuppied_byusr",room.getUsr_occup());
        new_room.setAttribute("nr_person",room.getNr_pers());

        root.appendChild(new_room);
        //*
        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        StreamResult result = new StreamResult("src/main/resources/rooms.xml");
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}

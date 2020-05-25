package Tools;


import Database.Hotelroom;
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

public class LoadRooms {

    private ArrayList<Hotelroom> roomlist;
    private Hotelroom room;

    public ArrayList<Hotelroom> getRoomlist() {
        return roomlist;
    }

    public LoadRooms() {
        roomlist=new ArrayList<Hotelroom>();
    }

    private void addRoom(Hotelroom room)
    {
        roomlist.add(room);
    }

    public void readDB() {
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
            doc = db.parse(new FileInputStream(new File("src/main/resources/rooms.xml")));
           // System.out.println(doc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        NodeList entries = doc.getElementsByTagName("room");

        int num = entries.getLength();

        for (int i = 0; i < num; i++) {
            Element node = (Element) entries.item(i);
            listAllAttributes(node);
            addRoom(room);
        }

    }

    private void listAllAttributes(Element element) {

        NamedNodeMap attributes = element.getAttributes();
        room=new Hotelroom();
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
                case "ID": room.setID(attrValue);
                    break;
                case "owner": room.setOwner(attrValue);
                    break;
                case "price": room.setPrice(attrValue);
                    break;
                case "size": room.setSize(attrValue);
                    break;
                case "available":
                    room.setAvailable(Boolean.valueOf(attrValue));
                    break;
                case "ocuppied_byusr": room.setUsr_occup(attrValue);
                    break;
                case "nr_person": room.setNr_pers(attrValue);
                    break;
            }
        }
}


package Tools;

import Database.Customer;
import Database.HotelOwner;
import Database.User;
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

public class LoadDatabase {

    public /*static*/ ArrayList<User> userlist;

    public /*static*/ void addUser(User user) {
        userlist.add (user);
    }

    public ArrayList<User> getArray() {
        return userlist;
    }

    public LoadDatabase() {
        userlist=new ArrayList<User>() ;

    }

    public /*static*/ void readDB() {
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
            doc = db.parse(new FileInputStream(new File("src/main/java/Database/database.xml")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        NodeList entries = doc.getElementsByTagName("hotelowner");

        int num = entries.getLength();

        for (int i = 0; i < num; i++) {
            Element node = (Element) entries.item(i);
            listAllAttributes(node);
            addUser(user);
            }

            entries = doc.getElementsByTagName("customer");
            num = entries.getLength();

            for (int i = 0; i < num; i++) {
                Element node = (Element) entries.item(i);
                listAllAttributes(node);
                addUser(user);
            }

    }

    private /*static*/ void listAllAttributes(Element element) {

        if(element.getNodeName().equals("hotelowner"))
        {
            user=new HotelOwner();
        }
        else
            user=new Customer();

       System.out.println("List attributes for node: " + element.getNodeName());

        // get a map containing the attributes of this node
        NamedNodeMap attributes = element.getAttributes();

        // get the number of nodes in this map
        int numAttrs = attributes.getLength();

        for (int i = 0; i < numAttrs; i++)
        {
            Attr attr = (Attr) attributes.item(i);

            String attrName = attr.getNodeName();
            String attrValue = attr.getNodeValue();
            superSetter(element.getNodeName(),attrName,attrValue);
            System.out.println("Found attribute: " + attrName + " with value: " + attrValue);
        }




    }

    private /*static*/ User user;

    /*
    * Implement a room setter and add them to the array
    *
    *
    * */
    public void roomSetter(String attrName,String attrValue)
    {


    }

    public /*static*/ void superSetter(String node_name,String attrName, String attrValue)
    {

            switch (attrName)
            {
            case "EIN":((HotelOwner)user).setEIN(attrValue);
                break;

            case "address":((HotelOwner)user).setAddress(attrValue);
                break;

            case "facilities":((HotelOwner)user).setFacilities(attrValue);
                break;
        ///////////////////////////////////////////////////////////////////////////
            case "role":user.setRole(attrValue);
                break;

            case "username":user.setUsername(attrValue);
                break;

            case "password":user.setPassword(attrValue);
                break;

            case "full_name":user.setFull_name(attrValue);
                break;

            case "phone":user.setPhone(attrValue);
                break;
        }
    }

    public void print()
    {
        for(User e : userlist)
        {
            System.out.println(e.toString());
        }
    }
}
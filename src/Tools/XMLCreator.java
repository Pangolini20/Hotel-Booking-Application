package Tools;

import Database.Customer;
import Database.HotelOwner;
import Database.User;
import org.w3c.dom.Attr;
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

public class XMLCreator {

    public static void createXMLnode(User user)  {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = documentBuilder.parse("./src/database.xml");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       //* code that adds users to database
        Element root=null;
        root = document.getDocumentElement();

        Element newUser=null;
        if(user instanceof HotelOwner) {
            newUser = document.createElement("hotelowner");
            newUser.setAttribute("role",user.getRole());
            newUser.setAttribute("EIN",((HotelOwner) user).getEIN());
            newUser.setAttribute("address",((HotelOwner) user).getAddress());
            newUser.setAttribute("facilities",((HotelOwner) user).getFacilities());
        }
        else if(user instanceof Customer) {
            newUser = document.createElement("customer");
            newUser.setAttribute("role",user.getRole());
        }

            newUser.setAttribute("password",user.getPassword());
            newUser.setAttribute("username",user.getUsername());
            newUser.setAttribute("phone",user.getPhone());
            newUser.setAttribute("full_name",user.getFull_name());



        /* Element name = document.createElement("name");
        name.appendChild(document.createTextNode(server.getName()));
        newServer.appendChild(name);
        */

        root.appendChild(newUser);
        //*
        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        StreamResult result = new StreamResult("./src/database.xml");
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

}

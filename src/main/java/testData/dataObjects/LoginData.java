package testData.dataObjects;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import testData.dataTypes.User;
import utilities.XmlUtils;

public class LoginData {

    private String xmlPath = "testData/users.xml";

    public static String invalidCredentialsErrorText = "Sorry, we can’t find an account with that email. You can register for a new account or get help here.";

    public static User mainUser(){
        User user = new User();
        user.email = "testIonut@gmail.com";
        user.password = "dummyPassword123";
        user.firstName = "Ionut";
        user.lastName = "Trusca";
        return user;
    }


    //---------------------------------------------- Methods that get Data Dynamically -----------------------------

    /**
     * Gets the user test data from given XML File
     * @param userName
     * @return
     */
    public User getUserFromFile(String userName){
        User user = new User();
        try {
            boolean userFound = false;
            Document doc = XmlUtils.getDocumentFromXmlFile(this.xmlPath);

            NodeList nList = doc.getElementsByTagName("user");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String currentUserName = eElement.getAttribute("name");

                    if (currentUserName.equals(userName)){
                        userFound = true;
                        user.email = eElement.getElementsByTagName("email").item(0).getTextContent();
                        user.password = eElement.getElementsByTagName("password").item(0).getTextContent();
                        user.firstName = eElement.getElementsByTagName("firstName").item(0).getTextContent();
                        user.lastName = eElement.getElementsByTagName("lastName").item(0).getTextContent();
                        break;
                    }
                }
            }
            if (!userFound){
                throw new Exception("User: " + userName + " NOT FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


}

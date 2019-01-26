package utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Config { 
	
	public static String profile;
	public static String browser;
	public static String homepage;
	public static String language;
	public static final String configFilePath = "config/config.xml";
	
	public static String getHomepage(){
		return homepage;
	}
	
	public static void setTestProfile(String[] testArgs){
		if (testArgs.length > 0){
			loadProfile(testArgs[0]);
		}
		else{
			loadProfile("Default");
		}
	}
	
	public static void loadProfile(String profileName){
		try {
			boolean profileFound = false;
			File fXmlFile = new File(configFilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("CONFIGURATION:");
			System.out.println("----------------------------");

			NodeList nList = doc.getElementsByTagName("profile");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					profile = eElement.getAttribute("name");

					if (profile.equals(profileName)){
						profileFound = true;
						System.out.println("Profile : " + profile);
						homepage = eElement.getElementsByTagName("homepage").item(0).getTextContent();
						System.out.println("homepage : " + homepage);
						browser = eElement.getElementsByTagName("browser").item(0).getTextContent();
						System.out.println("browser : " + browser);
						language = eElement.getElementsByTagName("language").item(0).getTextContent();
						System.out.println("language : " + language);
						break;
					}
				}
			}
			if (!profileFound){
				throw new Exception("Profile: " + profileName + " NOT FOUND");
			}
			System.out.println("----------------------------");
		    } catch (Exception e) {
			e.printStackTrace();
		    }
	}

	//Save a modified XML profile
	public static void saveModifiedProfile(File xmlFile, Document doc){
		try{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}

package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utilities.Config;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by trusc on 5/20/2017.
 */
public class SettingsWindow {

    static ArrayList<Label> parameterNames = new ArrayList<Label>();
    static ArrayList<TextField> parameterValues = new ArrayList<TextField>();
    static String selectedProfile;

    public static void display(){
        Stage window = new Stage();

        HBox profileSelectorLayout = new HBox();
        HBox buttonsLayout = new HBox();
        GridPane grid = new GridPane();
        VBox profileOptionsBox = new VBox();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(profileSelectorLayout);
        borderPane.setCenter(grid);
        borderPane.setBottom(buttonsLayout);
        borderPane.setRight(profileOptionsBox);


        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setVgap(5);
        grid.setHgap(15);

        //Block events on other windows
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Settings");
        Scene pageManagerScene = new Scene(borderPane, 600, 350);
        borderPane.setPadding(new Insets(10,10,10,10));
        profileOptionsBox.setPadding(new Insets(5,5,5,5));

        Label label = new Label("Select Profile: ");
        ComboBox profileSelector = new ComboBox();
        profileSelector.getItems().addAll(getProfilesList());
        profileSelector.getSelectionModel().selectFirst();

        //Set the default properties of the profile
        selectedProfile = profileSelector.getValue().toString();
        setPropertiesList(selectedProfile);   //read the value from selectBox
        displayPropertiesOnGrid(grid);

        Button saveButton = new Button("Save");
        Button closeButton = new Button("Close");
        Button editProfileButton = new Button("Edit Profile");

        //events
        profileSelector.setOnAction(e -> {
            selectedProfile = profileSelector.getValue().toString();
            setPropertiesList(selectedProfile);
            displayPropertiesOnGrid(grid);
        });

        saveButton.setOnAction(e -> {
            for (TextField parameterValue : parameterValues) {
                parameterValue.setEditable(false);
                parameterValue.setStyle("-fx-background-color: lightgrey;");
            }
            savePropertiesList(selectedProfile);
        });
        closeButton.setOnAction(e -> {
            window.close();
        });

        editProfileButton.setOnAction(e -> {
            for (TextField parameterValue : parameterValues) {
                parameterValue.setEditable(true);
                parameterValue.setStyle("-fx-background-color: white;");
            }
        });

        profileSelectorLayout.getChildren().addAll(label, profileSelector);
        profileSelectorLayout.setAlignment(Pos.TOP_RIGHT);

        buttonsLayout.getChildren().addAll(saveButton, closeButton);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.setSpacing(30);

        profileOptionsBox.getChildren().addAll(editProfileButton);

        window.setScene(pageManagerScene);
        window.showAndWait();
    }

    private static void setPropertiesList(String profileName) {
        ArrayList<Label> names = new ArrayList<>();
        ArrayList<TextField> values = new ArrayList<>();

        File fXmlFile = new File(Config.configFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("profile");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String profile = eElement.getAttribute("name");

                    if (profile.equals(profileName)){
                        NodeList propoertyNodes = eElement.getChildNodes();
                        for (int i=1; i<propoertyNodes.getLength(); i=i+2){
                            names.add(new Label (propoertyNodes.item(i).getNodeName()));
                            TextField value = new TextField (propoertyNodes.item(i).getTextContent());
                            value.setEditable(false);
                            value.setStyle("-fx-background-color: lightgrey;");
                            values.add(value);
                        }
                        break;
                    }
                }
            }
            parameterNames = names;
            parameterValues = values;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static ArrayList<String> getProfilesList(){
        ArrayList<String> profilesList = new ArrayList<>();
        try {
            boolean profileFound = false;
            File fXmlFile = new File(Config.configFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("profile");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    profilesList.add(eElement.getAttribute("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profilesList;
    }

    private static void displayPropertiesOnGrid(GridPane grid){
        grid.getChildren().clear();
        int parametersNumber = parameterNames.size();

        grid.getChildren().addAll(parameterNames);
        grid.getChildren().addAll(parameterValues);
        //Add the profile label
        Label profileName = new Label(selectedProfile);
        profileName.setFont(Font.font(null, FontWeight.BOLD, 16));
        grid.getChildren().add(profileName);
        grid.setConstraints(profileName, 0, 0);

        for (int i=0; i<parametersNumber; i++){
            grid.setConstraints(parameterNames.get(i), 0, i+1);
            grid.setConstraints(parameterValues.get(i), 1, i+1);
            parameterValues.get(i).setPrefWidth(400);
        }
    }

    private static void savePropertiesList(String profileName) {
        ArrayList<Label> names = new ArrayList<>();
        ArrayList<TextField> values = new ArrayList<>();

        File fXmlFile = new File(Config.configFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("profile");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String profile = eElement.getAttribute("name");

                    if (profile.equals(profileName)){
                        NodeList propoertyNodes = eElement.getChildNodes();
                        for (int i=1; i<propoertyNodes.getLength(); i=i+2){
                            propoertyNodes.item(i).setTextContent(parameterValues.get(i/2).getText());
                        }
                        break;
                    }
                }
            }
            Config.saveModifiedProfile(fXmlFile, doc);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}

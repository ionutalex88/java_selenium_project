package gui;

import aut.AUT;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by trusc on 5/20/2017.
 */
public class PagesManager {

    public static ArrayList<String> existingPages;

    public static void display(){
        Stage window = new Stage();

        HBox topLayout = new HBox();
        HBox buttonsLayout = new HBox();
        VBox centrelayout = new VBox();
        VBox pageOptionsBox = new VBox();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topLayout);
        borderPane.setCenter(centrelayout);
        borderPane.setBottom(buttonsLayout);
        borderPane.setRight(pageOptionsBox);

        //Block events on other windows
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Pages Manager");
        Scene pageManagerScene = new Scene(borderPane, 600, 500);
        borderPane.setPadding(new Insets(10,10,10,10));
        pageOptionsBox.setPadding(new Insets(5, 5, 5, 5));
        centrelayout.setPadding(new Insets(5, 5, 5, 5));

        Label label = new Label("Pages List");
        label.setFont(Font.font(null, FontWeight.BOLD, 16));

        //Set the default properties of the profile
        ListView pagesList = new ListView();
        pagesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);



        //Get the pages list and display it
        getExistingPagesList();
        displayPagesList(pagesList);

        Button saveButton = new Button("Save");
        Button closeButton = new Button("Close");
        Button editPageButton = new Button("Edit Page");
        Button deletePage = new Button("Delete Page");
        Button addNewPageButton = new Button("Add New Page");

        //events
        saveButton.setOnAction(e -> {
            //TBD
        });
        closeButton.setOnAction(e -> {
            window.close();
        });

        editPageButton.setOnAction(e -> {
            if (pagesList.getSelectionModel().isEmpty()) {
                new GuiCommons().displayWarning("Please select a page");
            } else {
                String selectedPage;
                selectedPage = pagesList.getSelectionModel().getSelectedItem().toString();
                try {
                    new PageEditor(selectedPage).display();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        topLayout.getChildren().addAll(label);
        topLayout.setAlignment(Pos.CENTER_LEFT);

        centrelayout.getChildren().addAll(pagesList);
        centrelayout.setAlignment(Pos.TOP_LEFT);

        buttonsLayout.getChildren().addAll(saveButton, closeButton);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.setSpacing(30);

        pageOptionsBox.getChildren().addAll(addNewPageButton, editPageButton, deletePage);
        addNewPageButton.setMaxWidth(Double.MAX_VALUE);
        editPageButton.setMaxWidth(Double.MAX_VALUE);
        deletePage.setMaxWidth(Double.MAX_VALUE);
        pageOptionsBox.setSpacing(15);

        window.setScene(pageManagerScene);
        window.showAndWait();

    }

    private static void getExistingPagesList(){
        AUT aut = new AUT();
        ArrayList<String> pagesList = new ArrayList<>();
        for (Field field : aut.getClass().getDeclaredFields()) {
            if (field.getName() != "driver") {
                String pageName = field.getAnnotatedType().getType().getTypeName().replace("pages.", "").toString();
                pagesList.add(pageName);
            }
        }
        existingPages = pagesList;
    }

    private static void displayPagesList(ListView pagesList){
        pagesList.getItems().clear();
        pagesList.getItems().addAll(existingPages);
    }


}

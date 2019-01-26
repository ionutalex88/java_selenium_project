package gui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utilities.PageObjectParser;
import utilities.ReflectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by trusc on 5/21/2017.
 */
public class PageEditor {

    public PageEditor(String pageName){
        this.pageName = pageName;
    }

    String pageName;
    Stage window;
    TableView<PageObject> objectsTable;
    TextField objectNameTextField, locatorTextField;
    ComboBox objectTypeSelectBox, locatorTypeComboBox;

    public void display() throws IOException, ClassNotFoundException {
        window = new Stage();

        HBox topLayout = new HBox();
        VBox bottomLayout = new VBox();
        HBox pageButtonsLayout = new HBox();
        HBox objectButtonsLayout = new HBox();
        HBox objectEditorLayout = new HBox();
        GridPane grid = new GridPane();
        VBox editorOptionsBox = new VBox();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topLayout);
        borderPane.setCenter(grid);
        borderPane.setBottom(bottomLayout);
        borderPane.setRight(editorOptionsBox);


        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setVgap(5);
        grid.setHgap(15);

        //Block events on other windows
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Page Editor");
        Scene pageEditorScene = new Scene(borderPane, 900, 500);
        borderPane.setPadding(new Insets(10,10,10,10));

        editorOptionsBox.setPadding(new Insets(5, 5, 5, 5));
        editorOptionsBox.setSpacing(10);

        bottomLayout.setPadding(new Insets(5, 5, 5, 5));

        Label label = new Label(pageName);
        label.setFont(Font.font(null, FontWeight.BOLD, 20));

        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font(null, FontWeight.BOLD, 12));
        Button closeButton = new Button("Close");
        closeButton.setFont(Font.font(null, FontWeight.BOLD, 12));

        Button editObjectButton = new Button("Edit Object");
        editObjectButton.setMaxWidth(Double.MAX_VALUE);

        Button deleteButton = new Button("Delete Object");
        deleteButton.setOnAction(e -> deleteButtonClicked());
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setStyle(Styles.lightCoralButtonStyle);

        objectTypeSelectBox = new ComboBox();
        objectTypeSelectBox.getItems().addAll(getPageObjectTypes());
        objectTypeSelectBox.setPromptText("Object Type");

        objectNameTextField = new TextField();
        objectNameTextField.setPromptText("Object Name");

        locatorTypeComboBox = new ComboBox();
        locatorTypeComboBox.getItems().addAll(getLocatorTypes());
        locatorTypeComboBox.setPromptText("Locator Type");
        locatorTypeComboBox.setStyle("-fx-background-color: white;");

        locatorTextField = new TextField();
        locatorTextField.setPromptText("Locator/Selector Value");
        locatorTextField.setMinWidth(330);

        Button addNewObjectButton = new Button("Add Object");
        addNewObjectButton.setOnAction(e -> addObjectButtonClicked());

        Button saveSelectedObjectButton = new Button("Save Object");
        saveSelectedObjectButton.setOnAction(e -> saveSelectedObjectClicked());

        objectEditorLayout.setSpacing(5);
        objectEditorLayout.getChildren().addAll(objectTypeSelectBox, objectNameTextField, locatorTypeComboBox, locatorTextField);

        //Name the columns
        TableColumn<PageObject, String> objectType = new TableColumn<>("Object Type");
        objectType.setMinWidth(50);
        objectType.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<PageObject, String> objectNameColumn = new TableColumn<>("Object Name");
        objectNameColumn.setMinWidth(200);
        objectNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<PageObject, String> locatorTypeColumn = new TableColumn<>("Locator Type");
        locatorTypeColumn.setMinWidth(100);
        locatorTypeColumn.setCellValueFactory(new PropertyValueFactory<>("locatorType"));

        TableColumn<PageObject, String> locatorColumn = new TableColumn<>("Locator/Selector Value");
        locatorColumn.setMinWidth(350);
        locatorColumn.setCellValueFactory(new PropertyValueFactory<>("locator"));

        objectsTable = new TableView<>();
        objectsTable.setItems(getObjectsList());
        objectsTable.getColumns().addAll(objectType, objectNameColumn, locatorTypeColumn, locatorColumn);
        objectsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        grid.getChildren().addAll(objectsTable);

        saveButton.setOnAction(e -> {
        });

        closeButton.setOnAction(e -> {
            window.close();
        });

        editObjectButton.setOnAction(e -> {
            PageObject selectedObject = objectsTable.getSelectionModel().getSelectedItem();
            if (selectedObject != null) {
                objectTypeSelectBox.getSelectionModel().select(selectedObject.getType());
                objectNameTextField.setText(selectedObject.getName());
                locatorTypeComboBox.getSelectionModel().select(selectedObject.getLocatorType());
                locatorTextField.setText(selectedObject.getLocator());
            }
        });

        topLayout.getChildren().addAll(label);
        topLayout.setAlignment(Pos.BASELINE_LEFT);

        objectButtonsLayout.getChildren().addAll(addNewObjectButton, saveSelectedObjectButton);
        objectButtonsLayout.setAlignment(Pos.CENTER);
        objectButtonsLayout.setSpacing(30);

        pageButtonsLayout.getChildren().addAll(saveButton, closeButton);
        pageButtonsLayout.setAlignment(Pos.CENTER_RIGHT);
        pageButtonsLayout.setSpacing(30);

        bottomLayout.getChildren().addAll(objectEditorLayout, objectButtonsLayout, pageButtonsLayout);
        bottomLayout.setSpacing(10);

        editorOptionsBox.getChildren().addAll(editObjectButton, deleteButton);

        window.setScene(pageEditorScene);
        window.showAndWait();
    }

    private void saveSelectedObjectClicked() {
        PageObject editedPageObject = getEditedPageObject();
        int index = objectsTable.getSelectionModel().getFocusedIndex();
        objectsTable.getItems().set(index, editedPageObject);
        clearPageObjectEditor();
        objectsTable.getSelectionModel().select(index);
    }


    private PageObject getEditedPageObject(){
        if (objectTypeSelectBox.getSelectionModel().getSelectedItem() != null &&
                !objectNameTextField.getText().isEmpty() &&
                !locatorTextField.getText().isEmpty() &&
                locatorTypeComboBox.getSelectionModel().getSelectedItem() != null) {
            String selectedObjectType = objectTypeSelectBox.getSelectionModel().getSelectedItem().toString();
            String selectedObjectName = objectNameTextField.getText();
            String selectedLocatorType = locatorTypeComboBox.getSelectionModel().getSelectedItem().toString();
            String selectedLocator = locatorTextField.getText();

            return new PageObject(selectedObjectType, selectedObjectName, selectedLocatorType, selectedLocator);
        }
        else {
            new GuiCommons().displayWarning("Please fill in all fields");
            return null;
        }
    }

    //Clears all the object Editor Fields
    private void clearPageObjectEditor(){
        objectTypeSelectBox.getSelectionModel().clearSelection();
        objectNameTextField.clear();
        locatorTypeComboBox.getSelectionModel().clearSelection();
        locatorTextField.clear();
    }

    private void addObjectButtonClicked() {
        PageObject pageObject = getEditedPageObject();
        if (pageObject != null) {
            objectsTable.getItems().add(pageObject);
            clearPageObjectEditor();
        }
    }

    private void deleteButtonClicked() {
        ObservableList<PageObject> allObjects;
        PageObject selectedObject;
        allObjects = objectsTable.getItems();
        selectedObject = objectsTable.getSelectionModel().getSelectedItem();

        allObjects.removeAll(selectedObject);
    }

    public ObservableList<PageObject> getObjectsList() throws IOException {
        ObservableList<PageObject> objects = new PageObjectParser(pageName).getPageObjects();
        return  objects;
    }

    private ArrayList<String> getPageObjectTypes() throws IOException, ClassNotFoundException {
        ArrayList<String> objectTypes = ReflectionUtils.getClassesName("objectTypes");
        return objectTypes;
    }

    private ArrayList<String> getLocatorTypes() {
        ArrayList<String> locatorTypes = new ArrayList<>(Arrays.asList("cssSelector", "xPath", "id", "class"));
        return locatorTypes;
    }

}


package gui;

import javafx.collections.FXCollections;
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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by trusc on 5/21/2017.
 */
public class TestEditor {

    public TestEditor(String testName){
        this.testName = testName;
    }

    String testName;
    Stage window;
    TableView<TestStep> table;
    TextField pageTextField, objectTextField, methodTextField, parametersTextField, variableTextField;
    ComboBox stepTypeSelectBox;

    public void display(){
        window = new Stage();

        HBox topLayout = new HBox();
        VBox bottomLayout = new VBox();
        HBox testButtonsLayout = new HBox();
        HBox stepButtonsLayout = new HBox();
        HBox stepEditorLayout = new HBox();
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

        window.setTitle("Test Editor");
        Scene testManagerScene = new Scene(borderPane, 1250, 500);
        borderPane.setPadding(new Insets(10,10,10,10));

        editorOptionsBox.setPadding(new Insets(5, 5, 5, 5));
        editorOptionsBox.setSpacing(10);

        bottomLayout.setPadding(new Insets(5, 5, 5, 5));

        Label label = new Label(testName);
        label.setFont(Font.font(null, FontWeight.BOLD, 20));

        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font(null, FontWeight.BOLD, 12));
        Button closeButton = new Button("Close");
        closeButton.setFont(Font.font(null, FontWeight.BOLD, 12));

        Button editStepButton = new Button("Edit Step");
        editStepButton.setMaxWidth(Double.MAX_VALUE);

        Button deleteButton = new Button("Delete Step");
        deleteButton.setOnAction(e -> deleteButtonClicked());
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setStyle(Styles.lightCoralButtonStyle);

        Button moveUpButton = new Button("Move Up");
        moveUpButton.setOnAction(e -> moveUpButtonClicked());
        moveUpButton.setMaxWidth(Double.MAX_VALUE);
        moveUpButton.setStyle(Styles.lightBlueButtonStyle);

        Button moveDownButton = new Button("Move Down");
        moveDownButton.setOnAction(e -> moveDownnClicked());
        moveDownButton.setMaxWidth(Double.MAX_VALUE);
        moveDownButton.setStyle(Styles.lightBlueButtonStyle);


        //Create the stepType combobox
        stepTypeSelectBox = new ComboBox();
        stepTypeSelectBox.getItems().addAll(getStepTypes());
        stepTypeSelectBox.setPromptText("Step Type");
        stepTypeSelectBox.setOnAction(e -> {
            if (stepTypeSelectBox.getValue() != null) {
                String stepType = stepTypeSelectBox.getValue().toString();

                switch (stepType) {
                    case "Store":
                        break;
                    case "Log Info":
                        pageTextField.setEditable(false);
                        pageTextField.setStyle("-fx-background-color: lightgrey;");
                        objectTextField.setEditable(false);
                        objectTextField.setStyle("-fx-background-color: lightgrey;");
                        methodTextField.setEditable(false);
                        methodTextField.setStyle("-fx-background-color: lightgrey;");
                        variableTextField.setEditable(false);
                        variableTextField.setStyle("-fx-background-color: lightgrey;");
                        break;
                    default:
                        variableTextField.setEditable(false);
                        variableTextField.setStyle("-fx-background-color: lightgrey;");
                }
            }
        });

        //Create Input Fields
        pageTextField = new TextField();
        pageTextField.setPromptText("Page");
        pageTextField.setMinWidth(160);

        objectTextField = new TextField();
        objectTextField.setPromptText("Object");

        methodTextField = new TextField();
        methodTextField.setPromptText("Method");
        methodTextField.setMinWidth(200);

        parametersTextField = new TextField();
        parametersTextField.setPromptText("Parameters (comma separated)");
        parametersTextField.setMinWidth(300);

        variableTextField = new TextField();
        variableTextField.setPromptText("Variable");

        Button addNewStepButton = new Button("Add New Step To Bottom");
        addNewStepButton.setOnAction(e -> addNewStepButtonClicked());

        Button insertStepButton = new Button("Insert Above Selected Step");
        insertStepButton.setOnAction(e -> insertStepButtonClicked());

        Button saveSelectedStepButton = new Button("Save As Selected Step");
        saveSelectedStepButton.setOnAction(e -> saveSelectedStepClicked());

        stepEditorLayout.setSpacing(5);
        stepEditorLayout.getChildren().addAll(stepTypeSelectBox, pageTextField, objectTextField, methodTextField, parametersTextField, variableTextField);

        //Name the columns
        TableColumn<TestStep, String> stepType = new TableColumn<>("Step Type");
        stepType.setMinWidth(50);
        stepType.setCellValueFactory(new PropertyValueFactory<>("stepType"));

        TableColumn<TestStep, String> pageColumn = new TableColumn<>("Page");
        pageColumn.setMinWidth(200);
        pageColumn.setCellValueFactory(new PropertyValueFactory<>("page"));

        TableColumn<TestStep, String> objectColumn = new TableColumn<>("Object");
        objectColumn.setMinWidth(150);
        objectColumn.setCellValueFactory(new PropertyValueFactory<>("object"));

        TableColumn<TestStep, String> methodColumn = new TableColumn<>("Method");
        methodColumn.setMinWidth(200);
        methodColumn.setCellValueFactory(new PropertyValueFactory<>("method"));

        TableColumn<TestStep, String> parametersColumn = new TableColumn<>("Parameters");
        parametersColumn.setMinWidth(340);
        parametersColumn.setCellValueFactory(new PropertyValueFactory<>("parameters"));

        TableColumn<TestStep, String> storedVariableNameColumn = new TableColumn<>("Variable");
        storedVariableNameColumn.setMinWidth(120);
        storedVariableNameColumn.setCellValueFactory(new PropertyValueFactory<>("storedVariableName"));


        table = new TableView<>();
        table.setItems(getTestStep());
        table.getColumns().addAll(stepType, pageColumn, objectColumn, methodColumn, parametersColumn, storedVariableNameColumn);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setTableNotSortable();

        grid.getChildren().addAll(table);

        saveButton.setOnAction(e -> {
        });

        closeButton.setOnAction(e -> {
            window.close();
        });

        editStepButton.setOnAction(e -> {
            TestStep selectedStep = table.getSelectionModel().getSelectedItem();
            if (selectedStep != null) {
                stepTypeSelectBox.getSelectionModel().select(selectedStep.getStepType());
                pageTextField.setText(selectedStep.getPage());
                objectTextField.setText(selectedStep.getObject());
                methodTextField.setText(selectedStep.getMethod());
                parametersTextField.setText(selectedStep.getParameters());
                variableTextField.setText(selectedStep.getStoredVariableName());
            }
        });

        topLayout.getChildren().addAll(label);
        topLayout.setAlignment(Pos.BASELINE_LEFT);

        stepButtonsLayout.getChildren().addAll(addNewStepButton, saveSelectedStepButton, insertStepButton);
        stepButtonsLayout.setAlignment(Pos.CENTER);
        stepButtonsLayout.setSpacing(30);

        testButtonsLayout.getChildren().addAll(saveButton, closeButton);
        testButtonsLayout.setAlignment(Pos.CENTER_RIGHT);
        testButtonsLayout.setSpacing(30);

        bottomLayout.getChildren().addAll(stepEditorLayout, stepButtonsLayout, testButtonsLayout);
        bottomLayout.setSpacing(10);

        editorOptionsBox.getChildren().addAll(editStepButton, deleteButton, moveUpButton, moveDownButton);

        window.setScene(testManagerScene);
        window.showAndWait();
    }

    private void saveSelectedStepClicked() {
        TestStep editedStep = getEditedStep();
        int index = table.getSelectionModel().getFocusedIndex();
        table.getItems().set(index, editedStep);
        clearStepEditor();
        table.getSelectionModel().select(index);
    }

    private void moveDownnClicked() {
        int selectedIndex = table.getSelectionModel().getFocusedIndex();
        TestStep selectedStep = table.getItems().get(selectedIndex);
        if (selectedIndex != table.getItems().size()-1){
            table.getItems().add(selectedIndex+2, selectedStep);
            table.getItems().remove(selectedIndex);
            table.getSelectionModel().select(selectedIndex+1);
        }
    }

    private void moveUpButtonClicked() {
        int selectedIndex = table.getSelectionModel().getFocusedIndex();
        TestStep selectedStep = table.getItems().get(selectedIndex);
        if (selectedIndex != 0){
            table.getItems().add(selectedIndex-1, selectedStep);
            table.getItems().remove(selectedIndex+1);
            table.getSelectionModel().select(selectedIndex-1);
        }
    }


    private TestStep getEditedStep(){
        if (stepTypeSelectBox.getSelectionModel().getSelectedItem() != null) {
            String selectedStepType = stepTypeSelectBox.getSelectionModel().getSelectedItem().toString();
            String page = pageTextField.getText();
            String object = objectTextField.getText();
            String method = methodTextField.getText();
            String parameters = parametersTextField.getText();
            String variableName = variableTextField.getText();

            return new TestStep(selectedStepType, page, object, method, parameters, variableName);
        }
        else
            return null;
    }

    //Clears all the step Editor Fields
    private void clearStepEditor(){
        stepTypeSelectBox.getSelectionModel().clearSelection();
        pageTextField.clear();
        objectTextField.clear();
        methodTextField.clear();
        parametersTextField.clear();
        variableTextField.clear();
    }

    //Actions on add new Step button
    private void addNewStepButtonClicked() {
        TestStep step = getEditedStep();
        if (step != null) {
            table.getItems().add(step);
            clearStepEditor();
        }
    }

    //Inserts at the selected position
    private void insertStepButtonClicked() {
        int position =  table.getSelectionModel().getFocusedIndex();

        TestStep step = getEditedStep();
        if (step != null) {
            table.getItems().add(position, step);
            clearStepEditor();
        }
    }

    //actions on delete button - To be completed
    private void deleteButtonClicked() {
        ObservableList<TestStep> allSteps;
        TestStep selectedStep;
        allSteps = table.getItems();
        selectedStep = table.getSelectionModel().getSelectedItem();

        allSteps.removeAll(selectedStep);
    }

    //get Test Step
    public ObservableList<TestStep> getTestStep(){
        ObservableList<TestStep> steps = FXCollections.observableArrayList();
        steps.add(new TestStep("Type", "Page", "Object", "method", "Parameters", ""));
        steps.add(new TestStep("Type1", "Page1", "Object1", "method1", "Parameters1", ""));
        steps.add(new TestStep("Type2", "Page2", "Object2", "method2", "Parameters2", "Var3"));
        steps.add(new TestStep("Type3", "Page3", "Object3", "method3", "Parameters3", ""));
        return  steps;
    }

    private ArrayList<String> getStepTypes(){
        ArrayList<String> stepTypes = new ArrayList<>(Arrays.asList("Action", "Store", "Checkpoint", "Log Info"));
        return stepTypes;
    }

    private void setTableNotSortable(){
        ObservableList<TableColumn<TestStep, ?>> columns = table.getColumns();
        for (TableColumn<TestStep, ?> column : columns) {
            column.setSortable(false);
        }
    }

}


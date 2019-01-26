package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by trusc on 5/20/2017.
 */
public class NewTestPrompt {

    String testName;

    public String display(){
        Stage window = new Stage();

        VBox messageLayout = new VBox();
        HBox buttonsLayout = new HBox();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(messageLayout);
        borderPane.setCenter(buttonsLayout);

        borderPane.setPadding(new Insets(10,10,10,10));

        //Block events on other windows
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("New Test Name");
        Scene pageManagerScene = new Scene(borderPane, 300, 120);

        Label label = new Label("Insert the name for the new test:");
        TextField testNameTextField = new TextField();

        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        okButton.setOnAction(e -> {
            testName = testNameTextField.getText().replace(" ","");
            window.close();
        });

        cancelButton.setOnAction(e -> {
            testName = "";
            window.close();
        });

        messageLayout.getChildren().addAll(label, testNameTextField);
        messageLayout.setSpacing(10);
        messageLayout.setAlignment(Pos.CENTER_LEFT);

        buttonsLayout.getChildren().addAll(okButton, cancelButton);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.setSpacing(30);

        window.setScene(pageManagerScene);
        window.showAndWait();

        return testName;
    }
}

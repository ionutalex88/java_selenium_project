package gui;/**
 * Created by trusc on 5/20/2017.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tutorial extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Stage window;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Automation Framework GUI");

        //Close corretly
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        StackPane buttonsLayout = new StackPane();
        StackPane editorLayout = new StackPane();

        BorderPane borderPane = new BorderPane();
        VBox topMenu = new VBox();
        HBox bottomMenu = new HBox();

        borderPane.setTop(topMenu);
        borderPane.setCenter(buttonsLayout);
        borderPane.setBottom(bottomMenu);

        //Main scene
        Scene mainScene = new Scene(borderPane, 350, 250);
        Scene scriptEditorScene = new Scene(editorLayout, 1024, 600);



        Button newTestScriptButton = new Button("New Test Script");
        Button editTestScript = new Button("Edit Test Script");
        Button managePages = new Button("Manage Pages");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");

        Label mainMenuLabel = new Label("Main Menu");
        Label blank = new Label(" ");


        topMenu.getChildren().addAll(mainMenuLabel, blank);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.setSpacing(20);

        bottomMenu.getChildren().addAll(exitButton);
        bottomMenu.setAlignment(Pos.CENTER);

        newTestScriptButton.setMaxWidth(200);
        editTestScript.setMaxWidth(200);
        managePages.setMaxWidth(200);

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(Insets.EMPTY);
        vbButtons.setAlignment(Pos.BASELINE_CENTER);
        vbButtons.getChildren().addAll(newTestScriptButton, editTestScript, managePages, settingsButton);

        buttonsLayout.getChildren().add(vbButtons);


        //events
        newTestScriptButton.setOnAction(e -> window.setScene(scriptEditorScene));
        managePages.setOnAction(e -> PagesManager.display());


        //Editor scene
        Button backToMainButton = new Button("Back To Main Menu");
        backToMainButton.setOnAction(e -> window.setScene(mainScene));

        exitButton.setOnAction(e -> closeProgram());

        editorLayout.getChildren().addAll(backToMainButton);



        window.setScene(mainScene);
        window.show();
    }

    //Close correctly
    private void closeProgram() {
        boolean answer = new ConfirmBox().display("Quit program", "Are you sure you want to quit?");
        if (answer == true){
            window.close();
        }

    }

}

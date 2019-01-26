package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by trusc on 5/20/2017.
 */
public class ConfirmBox {

    boolean answer;

    public boolean display(String title, String message){
        Stage window = new Stage();

        VBox messageLayout = new VBox();
        HBox buttonsLayout = new HBox();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(messageLayout);
        borderPane.setCenter(buttonsLayout);

        borderPane.setPadding(new Insets(10,10,10,10));

        //Block events on other windows
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        Scene pageManagerScene = new Scene(borderPane, 250, 80);

        Label label = new Label(message);
        Button noButton = new Button("No");
        Button yesButton = new Button("Yes");

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        messageLayout.getChildren().addAll(label);
        messageLayout.setAlignment(Pos.CENTER);

        buttonsLayout.getChildren().addAll(yesButton, noButton);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.setSpacing(30);

        window.setScene(pageManagerScene);
        window.showAndWait();

        return answer;
    }
}

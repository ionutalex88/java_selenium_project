package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by trusc on 5/21/2017.
 */
public class GuiCommons {

    public void displayWarning(String message){
        Alert warning = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        warning.showAndWait();
    }
}

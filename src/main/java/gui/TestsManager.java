package gui;

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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by trusc on 5/20/2017.
 */
public class TestsManager {

    public static ArrayList<String> existingTests;

    public static void display(){
        Stage window = new Stage();

        HBox topLayout = new HBox();
        HBox buttonsLayout = new HBox();
        VBox centrelayout = new VBox();
        VBox testOptionsBox = new VBox();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topLayout);
        borderPane.setCenter(centrelayout);
        borderPane.setBottom(buttonsLayout);
        borderPane.setRight(testOptionsBox);

        //Block events on other windows
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Test's Selector");
        Scene testsManagerScene = new Scene(borderPane, 600, 500);
        borderPane.setPadding(new Insets(10,10,10,10));
        testOptionsBox.setPadding(new Insets(5, 5, 5, 5));
        centrelayout.setPadding(new Insets(5, 5, 5, 5));

        Label label = new Label("Tests List");
        label.setFont(Font.font(null, FontWeight.BOLD, 16));

        //Set the default properties of the profile
        ListView testsList = new ListView();
        testsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);



        //Get the pages list and display it
        getExistingTestsList();
        displayTestsList(testsList);

        Button saveButton = new Button("Save");
        Button closeButton = new Button("Close");
        Button editTestButton = new Button("Edit Test");
        Button deleteTestButton = new Button("Delete Test");
        Button addNewTestButton = new Button("Add New Test");

        //events
        saveButton.setOnAction(e -> {
            //TBD
        });
        closeButton.setOnAction(e -> {
            window.close();
        });

        editTestButton.setOnAction(e -> {
            if (testsList.getSelectionModel().isEmpty()) {
                new GuiCommons().displayWarning("Please select a test");
            } else {
                String selectedTest;
                selectedTest = testsList.getSelectionModel().getSelectedItem().toString();
                new TestEditor(selectedTest).display();
            }
        });

        addNewTestButton.setOnAction(e -> {
            String testName = new NewTestPrompt().display();
            if (testName != ""){
                new TestEditor(testName).display();
            }
        });

        topLayout.getChildren().addAll(label);
        topLayout.setAlignment(Pos.CENTER_LEFT);

        centrelayout.getChildren().addAll(testsList);
        centrelayout.setAlignment(Pos.TOP_LEFT);

        buttonsLayout.getChildren().addAll(saveButton, closeButton);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.setSpacing(30);

        testOptionsBox.getChildren().addAll(addNewTestButton, editTestButton, deleteTestButton);
        addNewTestButton.setMaxWidth(Double.MAX_VALUE);
        editTestButton.setMaxWidth(Double.MAX_VALUE);
        deleteTestButton.setMaxWidth(Double.MAX_VALUE);
        testOptionsBox.setSpacing(15);

        window.setScene(testsManagerScene);
        window.showAndWait();

    }

    private static void getExistingTestsList(){
        try {
            Class[] classes = getClasses("tests");
            ArrayList<String> testsList = new ArrayList<>();
            for (Class testClass : classes) {
                testsList.add(testClass.getSimpleName());
                }
            existingTests = testsList;
            } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static void displayTestsList(ListView testsList){
        testsList.getItems().clear();
        testsList.getItems().addAll(existingTests);
    }


    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }
}

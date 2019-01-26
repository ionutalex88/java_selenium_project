package utilities;

import gui.PageObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by trusc on 6/6/2017.
 */
public class PageObjectParser {

    public PageObjectParser (String pageName) throws IOException {
        this.pageLocation = pagesFolder + pageName + ".java";
    }

    String pagesFolder = "src/pages/";
    String pageLocation;

    public ObservableList<PageObject> getPageObjects(){
        ObservableList<PageObject> pageObjects = FXCollections.observableArrayList();

        try {
            File file = new File(pageLocation);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            boolean objectsMarker = false;
            boolean objectsEnd = false;

            while ((line = bufferedReader.readLine()) != null && !objectsEnd) {
                if (line.contains("=Methods for this page=")){
                    objectsEnd = true;
                }

                if (line.contains("=Objects on page=")){
                    objectsMarker = true;
                }

                if (objectsMarker && line.contains("public")) {
                    String objectLine = line;

                    String[] objectWords = objectLine.split(" ");
                    int firstIndex = Arrays.asList(objectWords).indexOf("public");
                    if (firstIndex == -1)
                        firstIndex = Arrays.asList(objectWords).indexOf("\tpublic");

                    String type = objectWords[firstIndex+1];
                    String name = objectWords[firstIndex+2];

                    String restConcatenat="";
                    for (int j=firstIndex+5; j<objectWords.length; j++){
                        restConcatenat = restConcatenat + objectWords[j] + " ";
                    }
                    restConcatenat = restConcatenat.substring(0, restConcatenat.length()-1);

                    String locator = restConcatenat.replace(objectWords[firstIndex+1]+"(By.", "");
                    locator = locator.substring(0, locator.length()-4);

                    String separator = "(\"";
                    locator = locator.replace(separator,"#SEPARATOR#");

                    objectWords = locator.split("#SEPARATOR#");

                    String locatorType = objectWords[0];

                    locator = "";
                    for (int j=1; j<objectWords.length; j++){
                        if (j!=objectWords.length-1)
                            locator = locator + objectWords[j] + " ";
                        else
                            locator = locator + objectWords[j];
                    }

                    pageObjects.add(new PageObject(type, name, locatorType, locator));
                }

            }
            fileReader.close();
            System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

       return  pageObjects;
    }

}

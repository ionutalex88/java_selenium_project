package gui;

/**
 * Created by trusc on 5/26/2017.
 */
public class PageObject {

    private String type;
    private String name;
    private String locatorType;
    private String locator;

    public PageObject(){
        this.type = "";
        this.name = "";
        this.locatorType = "";
        this.locator = "";
    }

    public PageObject(String objectType, String objectName, String locatorType, String locator){
        this.type = objectType;
        this.name = objectName;
        this.locatorType = locatorType;
        this.locator = locator;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }
}

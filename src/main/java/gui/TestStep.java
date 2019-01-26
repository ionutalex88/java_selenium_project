package gui;

/**
 * Created by trusc on 5/26/2017.
 */
public class TestStep {

    private String stepType;
    private String page;
    private String object;
    private String method;
    private String parameters;
    private String storedVariableName;

    public TestStep(){
        this.stepType = "";
        this.page = "";
        this.object = "";
        this.method = "";
        this.parameters = "";
        this.storedVariableName = "";
    }

    public TestStep (String stepType, String page, String object, String method, String parameters, String storedVariableName){
        this.stepType = stepType;
        this.page = page;
        this.object = object;
        this.method = method;
        this.parameters = parameters;
        this.storedVariableName = storedVariableName;
    }


    public String getStepType() {
        return stepType;
    }

    public void setStepType(String stepType) {
        this.stepType = stepType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getStoredVariableName() {
        return storedVariableName;
    }

    public void setStoredVariableName(String storedVariableName) {
        this.storedVariableName = storedVariableName;
    }



}

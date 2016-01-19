package org.sergei;

import java.util.List;

/**
 * Created by sergei on 1/19/16.
 * @org.apache.xbean.XBean element="simple" rootElement="true";
 */
public class SimpleBean {
    private String myProperty;
    private SimpleController simpleController;
    private List<ComplexController> controllers;

    public String getMyProperty() {
        return myProperty;
    }

    public void setMyProperty(String myProperty) {
        this.myProperty = myProperty;
    }

    public SimpleController getSimpleController() {
        return simpleController;
    }

    public void setSimpleController(SimpleController simpleController) {
        this.simpleController = simpleController;
    }

    public List<ComplexController> getControllers() {
        return controllers;
    }

    /**
     * @org.apache.xbean.Property alias="controllers" nestedType="org.sergei.ComplexController";
     */
    public void setControllers(List<ComplexController> controllers) {
        this.controllers = controllers;
    }
}

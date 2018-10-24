/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main._core.propertor;

/**
 * @author MSD
 */
public class PropertorBag {

    public PropertorBag(Object propertor, String value, boolean seprator) {
        this.propertor = propertor;
        this.value = value;
        this.seprator = seprator;
    }


    private Object propertor;
    private String value;
    private boolean seprator;

    public Object getPropertor() {
        return propertor;
    }

    public void setPropertor(Object propertor) {
        this.propertor = propertor;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSeprator() {
        return seprator;
    }

    public void setSeprator(boolean seprator) {
        this.seprator = seprator;
    }

    public boolean getIsOn() {
        return "true".equals(value);
    }

}

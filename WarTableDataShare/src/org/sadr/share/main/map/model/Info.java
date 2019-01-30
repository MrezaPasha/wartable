package org.sadr.share.main.map.model;

import java.io.Serializable;
import java.util.List;

public class Info implements Serializable {

    private String Name;
    private String Description;
    private List<Layer> layers;

    public Info() {
    }

    public Info(String name, String description, List<Layer> layers) {
        Name = name;
        Description = description;
        this.layers = layers;
    }

// Getter and Setters


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }
}

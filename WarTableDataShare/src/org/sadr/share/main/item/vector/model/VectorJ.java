package org.sadr.share.main.item.vector.model;

import com.google.gson.annotations.SerializedName;

public class VectorJ {

    @SerializedName("Name")
    private String name;

    @SerializedName("Description")
    private String description;

    @SerializedName("MapSessionId")
    private String mapSessionId;

    @SerializedName("Type")
    private String type;

    @SerializedName("VectorType")
    private String vectorType;

    @SerializedName("Filename")
    private  String filename;

    @SerializedName("Bounds")
    private String bounds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMapSessionId() {
        return mapSessionId;
    }

    public void setMapSessionId(String mapSessionId) {
        this.mapSessionId = mapSessionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVectorType() {
        return vectorType;
    }

    public void setVectorType(String vectorType) {
        this.vectorType = vectorType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }
}

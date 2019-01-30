package org.sadr.share.main.map.model;

import java.io.Serializable;
import java.util.List;

public class Layer implements Serializable {

    private long Id;
    private String Name;
    private String Type;
    private int SamplesWidth;
    private int SamplesHeight;
    private int TileCountHorz;
    private int TileCountVert;
    private List<Double> Bounds;
    private int MinHeight;
    private int MaxHeight;
    private double SpacingHorz;
    private double SpacingVert;
    private int SpacingHeight;

    public Layer(long id, String name, String type, int samplesWidth, int samplesHeight, int tileCountHorz, int tileCountVert, List<Double> bounds, int minHeight, int maxHeight, double spacingHorz, double spacingVert, int spacingHeight) {
        Id = id;
        Name = name;
        Type = type;
        SamplesWidth = samplesWidth;
        SamplesHeight = samplesHeight;
        TileCountHorz = tileCountHorz;
        TileCountVert = tileCountVert;
        Bounds = bounds;
        MinHeight = minHeight;
        MaxHeight = maxHeight;
        SpacingHorz = spacingHorz;
        SpacingVert = spacingVert;
        SpacingHeight = spacingHeight;
    }

    public Layer() {
    }

    // Getter & Setters


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getSamplesWidth() {
        return SamplesWidth;
    }

    public void setSamplesWidth(int samplesWidth) {
        SamplesWidth = samplesWidth;
    }

    public int getSamplesHeight() {
        return SamplesHeight;
    }

    public void setSamplesHeight(int samplesHeight) {
        SamplesHeight = samplesHeight;
    }

    public int getTileCountHorz() {
        return TileCountHorz;
    }

    public void setTileCountHorz(int tileCountHorz) {
        TileCountHorz = tileCountHorz;
    }

    public int getTileCountVert() {
        return TileCountVert;
    }

    public void setTileCountVert(int tileCountVert) {
        TileCountVert = tileCountVert;
    }

    public List<Double> getBounds() {
        return Bounds;
    }

    public void setBounds(List<Double> bounds) {
        Bounds = bounds;
    }

    public int getMinHeight() {
        return MinHeight;
    }

    public void setMinHeight(int minHeight) {
        MinHeight = minHeight;
    }

    public int getMaxHeight() {
        return MaxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        MaxHeight = maxHeight;
    }

    public double getSpacingHorz() {
        return SpacingHorz;
    }

    public void setSpacingHorz(double spacingHorz) {
        SpacingHorz = spacingHorz;
    }

    public double getSpacingVert() {
        return SpacingVert;
    }

    public void setSpacingVert(double spacingVert) {
        SpacingVert = spacingVert;
    }

    public int getSpacingHeight() {
        return SpacingHeight;
    }

    public void setSpacingHeight(int spacingHeight) {
        SpacingHeight = spacingHeight;
    }
}

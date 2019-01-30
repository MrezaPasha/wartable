package org.sadr.share.main.item.object.model;

import java.io.Serializable;

public class ModelJsonObject implements Serializable {

  /*  "Name":	"تانک",
        "Description":	"تانک جنگی",
        "Category":	"علائم",
        "SubCategory":	"",
        "MeshFilename":	"tank.cmo",
        "Scale": 5.0*/
  private String Name ;
  private String Description;
  private String Category;
  private String SubCategory;
  private String MeshFilename;
  private double Scale;


    public ModelJsonObject() {
    }

    public ModelJsonObject(String name, String description, String category, String subCategory, String meshFilename, double scale) {
        Name = name;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        MeshFilename = meshFilename;
        Scale = scale;
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

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(String subCategory) {
        SubCategory = subCategory;
    }

    public String getMeshFilename() {
        return MeshFilename;
    }

    public void setMeshFilename(String meshFilename) {
        MeshFilename = meshFilename;
    }

    public double getScale() {
        return Scale;
    }

    public void setScale(double scale) {
        Scale = scale;
    }
}

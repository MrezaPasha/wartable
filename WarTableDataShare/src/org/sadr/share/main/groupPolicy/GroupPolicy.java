package org.sadr.share.main.groupPolicy;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.accessCategoury.AccessCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@PersianName("سیایت های گروهی")
@Entity
@Table(name = "Service.GroupPolicy")
public class GroupPolicy extends GenericDataModel<GroupPolicy> implements Serializable {

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String _ACCESS_CATEGORIES = "accessCategories";

    private static String[] actColumns;
    private static String[] relColumns;
    private static String[] virColumns;

    public static void setAvrColumns(String acts, String virts, String rels) {
        if (acts != null) {
            actColumns = acts.split(",");
        }
        if (virts != null) {
            virColumns = virts.split(",");
        }
        if (rels != null) {
            relColumns = rels.split(",");
        }
    }

    // static fields end


    @PersianName("نام")
    private String name;


    @PersianName("توضیحات")
    private String description;



    @ManyToMany
    @JoinTable(name = "Service.GroupPolicy_AccessCategory",
        joinColumns = {
            @JoinColumn(name = "groupPolicy_id", nullable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "accessCategory_id")})
    @PersianName("سطح دسترسی")
    private Set<AccessCategory> accessCategories;



    // METHODS


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

    public Set<AccessCategory> getAccessCategories() {
        return accessCategories;
    }

    public void setAccessCategories(Set<AccessCategory> accessCategories) {
        this.accessCategories = accessCategories;
    }


}

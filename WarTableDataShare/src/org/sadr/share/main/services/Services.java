package org.sadr.share.main.services;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main._types.TtRpcFunction;
import org.sadr.share.main.accessCategoury.AccessCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@PersianName("سرویس ها")
@Entity
@Table(name = "Service.Services")
public class Services extends GenericDataModel<Services> implements Serializable {


    public static final String SERVICE = "service";
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

    @PersianName(" سرویس")
    private TtRpcFunction service;

    @ManyToMany
    @JoinTable(name = "Service.AccessCategory_Services",
        joinColumns = {
            @JoinColumn(name = "services_id", nullable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "accessCategory_id")})
    @PersianName("سطح دسترسی")
    private Set<AccessCategory> accessCategories;


    // METHODS


    public TtRpcFunction getService() {
        return service;
    }

    public void setService(TtRpcFunction service) {
        this.service = service;
    }

    public Set<AccessCategory> getAccessCategories() {
        return accessCategories;
    }

    public void setAccessCategories(Set<AccessCategory> accessCategories) {
        this.accessCategories = accessCategories;
    }


}

package org.sadr.share.main.accessCategoury;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.accessCategoury._types.TtAccessCategoryType;
import org.sadr.share.main.groupPolicy.GroupPolicy;
import org.sadr.share.main.services.Services;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@PersianName("سطح دسترسی")
@Entity
@Table(name = "Service.AccessCategory")
public class AccessCategory extends GenericDataModel<AccessCategory> implements Serializable {


    public static final String ACCESS_CATEGORY_TYPE = "accessCategoryType";
    public static final String _GROUP_POLICIES = "groupPolicies";
    public static final String _SERVICES = "groupPolicies";
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



    @PersianName("سطح دسترسی")
    private TtAccessCategoryType accessCategoryType;


    @ManyToMany
    @JoinTable(name = "Service.GroupPolicy_AccessCategory",
            joinColumns = {
                    @JoinColumn(name = "accessCategory_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "groupPolicy_id")})
    @PersianName("سیاست گروهی")
    private Set<GroupPolicy> groupPolicies;

    @ManyToMany
    @JoinTable(name = "Service.AccessCategory_Services",
            joinColumns = {
                    @JoinColumn(name = "accessCategory_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "services_id")})
    @PersianName("سطح دسترسی ")
    private Set<Services> services;


    // METHODS


    public TtAccessCategoryType getAccessCategoryType() {
        return accessCategoryType;
    }

    public void setAccessCategoryType(TtAccessCategoryType accessCategoryType) {
        this.accessCategoryType = accessCategoryType;
    }

    public Set<GroupPolicy> getGroupPolicies() {
        return groupPolicies;
    }

    public void setGroupPolicies(Set<GroupPolicy> groupPolicies) {
        this.groupPolicies = groupPolicies;
    }

    public Set<Services> getServices() {
        return services;
    }

    public void setServices(Set<Services> services) {
        this.services = services;
    }
}

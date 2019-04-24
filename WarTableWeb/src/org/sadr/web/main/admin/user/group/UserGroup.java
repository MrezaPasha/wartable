/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main.admin.user.group;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system.task.Task;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @author dev1
 */
@PersianName(value = "گروه کاربران")
@Entity
@Table(name = "Web.Admin.User.Group")
public class UserGroup extends GenericDataModel<UserGroup> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String TITLE = "title";public static final String _USERS = "users";public static final String _PARENT = "parent";public static final String _CHILDS = "childs";public static final String _TASKS = "tasks";public static final String $AUTHORIZED_MENU = "authorizedMenu";public static final String $ACT_COLUMNS = "actColumns";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $SECRET_NOTE = "secretNote";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @SafeHtml
    @NotEmpty
    @Size(max = 150)
    @PersianName("عنوان")
    private String title;

    @ManyToMany
    @JoinTable(name = "Web.Admin.User.User_UserGroup",
            joinColumns = {
                    @JoinColumn(name = "userGroup_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")})
    @PersianName("کاربران")
    private Set<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("سرگروه")
    private UserGroup parent;

    @OneToMany(mappedBy = "parent")
    @PersianName("زیرگروه ها")
    private Set<UserGroup> childs;

    @ManyToMany
    @JoinTable(name = "Web.System.Task_UserGroup",
            joinColumns = {
                    @JoinColumn(name = "userGroup_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "task_id")})
    @PersianName("سطح دسترسی")
    private Set<Task> tasks;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS

    public String getTitle() {
        return (getEntityState() != null && getEntityState().isTrashOrRemove()) ? title + " <حذف شده>" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthorizedMenu() {
        String am = "";
        for (Task t : tasks) {
            am += t.getMenuIdentity() + ",";
        }
        return am;
    }

    public Object getSecretNote() {
        return "{\"userGrId\":" + getId() + "}";
    }

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public UserGroup getParent() {
        return parent;
    }

    public void setParent(UserGroup parent) {
        this.parent = parent;
    }

    public Set<UserGroup> getChilds() {
        return childs;
    }

    /////
    public void setChilds(Set<UserGroup> childs) {
        this.childs = childs;
    }

    public Set<User> getUsers() {
        return users;
    }

    /////
    public void setUsers(Set<User> users) {
        this.users = users;
    }

}

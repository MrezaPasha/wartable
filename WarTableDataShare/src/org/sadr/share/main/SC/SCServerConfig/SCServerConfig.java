package org.sadr.share.main.SC.SCServerConfig;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@PersianName("تنظیمات سرور کنترل امنیت")
@Entity
@Table(name = "Service.SCServerConfig")
public class SCServerConfig extends GenericDataModel<SCServerConfig> implements Serializable {



    public static final String SC_SERVER_IP = "SCServerIp";
    public static final String SC_SERVER_PORT = "SCServerPort";
    public static final  String SC_SERVER_USERNAME = "SCServerUsername";
    public static final String SC_SERVER_PASSWORD = "SCServerPassword";

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


    public static String[] getActColumns() {
        return actColumns;
    }

    public static String[] getVirColumns() {
        return virColumns;
    }

    public static String[] getRelColumns() {
        return relColumns;
    }

    // static fields end


    @PersianName("آدرس سرور کنترل امنیت")
    @Column(nullable = false)
    private String SCServerIp;

    @PersianName("شماره پورت سرور کنترل امنیت")
    @Column(nullable = false)
    private String SCServerPort;

    @PersianName("شناسه سرور کنترل امنیت")
    @Column(nullable = false)
    private String SCServerUsername;

    @PersianName("پسورد سرور کنترل امنیت")
    @Column(nullable = false)
    private String SCServerPassword;



    // METHODS


    public String getSCServerIp() {
        return SCServerIp;
    }

    public void setSCServerIp(String SCServerIp) {
        this.SCServerIp = SCServerIp;
    }

    public String getSCServerPort() {
        return SCServerPort;
    }

    public void setSCServerPort(String SCServerPort) {
        this.SCServerPort = SCServerPort;
    }

    public String getSCServerUsername() {
        return SCServerUsername;
    }

    public void setSCServerUsername(String SCServerUsername) {
        this.SCServerUsername = SCServerUsername;
    }

    public String getSCServerPassword() {
        return SCServerPassword;
    }

    public void setSCServerPassword(String SCServerPassword) {
        this.SCServerPassword = SCServerPassword;
    }
}

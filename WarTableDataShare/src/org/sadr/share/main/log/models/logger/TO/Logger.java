package org.sadr.share.main.log.models.logger.TO;

import java.io.Serializable;

public class Logger implements Serializable {

    private String Time;
    private String SoftwareID;
    private String SoftwareName;
    private String SoftwareVersion;
    private String serverHostname;
    private String ServerIP;
    private String PortNumber;
    private String clientHostname;
    private String ClientIP;
    private String PageTitle;
    private String URL;
    private String Username;
    private String UserUniqueID;
    private String ActionType;
    private String Sensitivity;
    private String Importance;
    private String Flag;
    private String SubType;
    private String SubTypeDescription;
    private String Description;

    // Constructors

    public Logger(String time, String softwareID, String softwareName, String softwareVersion, String serverHostname, String serverIP, String portNumber, String clientHostname, String clientIP, String pageTitle, String URL, String username, String userUniqueID, String actionType, String sensitivity, String importance, String flag, String subType, String subTypeDescription, String description) {
        Time = time;
        SoftwareID = softwareID;
        SoftwareName = softwareName;
        SoftwareVersion = softwareVersion;
        this.serverHostname = serverHostname;
        ServerIP = serverIP;
        PortNumber = portNumber;
        this.clientHostname = clientHostname;
        ClientIP = clientIP;
        PageTitle = pageTitle;
        this.URL = URL;
        Username = username;
        UserUniqueID = userUniqueID;
        ActionType = actionType;
        Sensitivity = sensitivity;
        Importance = importance;
        Flag = flag;
        SubType = subType;
        SubTypeDescription = subTypeDescription;
        Description = description;
    }

    public Logger() {
    }

    // Getter & Setter


    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSoftwareID() {
        return SoftwareID;
    }

    public void setSoftwareID(String softwareID) {
        SoftwareID = softwareID;
    }

    public String getSoftwareName() {
        return SoftwareName;
    }

    public void setSoftwareName(String softwareName) {
        SoftwareName = softwareName;
    }

    public String getSoftwareVersion() {
        return SoftwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        SoftwareVersion = softwareVersion;
    }

    public String getServerHostname() {
        return serverHostname;
    }

    public void setServerHostname(String serverHostname) {
        this.serverHostname = serverHostname;
    }

    public String getServerIP() {
        return ServerIP;
    }

    public void setServerIP(String serverIP) {
        ServerIP = serverIP;
    }

    public String getPortNumber() {
        return PortNumber;
    }

    public void setPortNumber(String portNumber) {
        PortNumber = portNumber;
    }

    public String getClientHostname() {
        return clientHostname;
    }

    public void setClientHostname(String clientHostname) {
        this.clientHostname = clientHostname;
    }

    public String getClientIP() {
        return ClientIP;
    }

    public void setClientIP(String clientIP) {
        ClientIP = clientIP;
    }

    public String getPageTitle() {
        return PageTitle;
    }

    public void setPageTitle(String pageTitle) {
        PageTitle = pageTitle;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUserUniqueID() {
        return UserUniqueID;
    }

    public void setUserUniqueID(String userUniqueID) {
        UserUniqueID = userUniqueID;
    }

    public String getActionType() {
        return ActionType;
    }

    public void setActionType(String actionType) {
        ActionType = actionType;
    }

    public String getSensitivity() {
        return Sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        Sensitivity = sensitivity;
    }

    public String getImportance() {
        return Importance;
    }

    public void setImportance(String importance) {
        Importance = importance;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getSubType() {
        return SubType;
    }

    public void setSubType(String subType) {
        SubType = subType;
    }

    public String getSubTypeDescription() {
        return SubTypeDescription;
    }

    public void setSubTypeDescription(String subTypeDescription) {
        SubTypeDescription = subTypeDescription;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

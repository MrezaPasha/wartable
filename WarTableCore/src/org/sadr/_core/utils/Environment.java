/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.utils;

import org.sadr._core._type.TtProjectPath;
import org.sadr._core.utils._type.TtIdeEnvironment;
import org.sadr._core.utils._type.TtOsEnvironment;

/**
 * @author dev1
 */
public class Environment {

    public static final String FILE_SEPARATOR = "/";
    private static final Environment INSTANCE = new Environment();

    public static Environment getInstance() {
        return INSTANCE;
    }

    private Environment() {
        _CORE_NAME = "WarTableCore";
        _REPOSITORY_ROOT_ADDRESS = "/repository/ROOT";
        _REPOSITORY_ADDRESS = "/repository";
        _REGISTERY_ROOT_ADDRESS = "/registery/ROOT";
        _WEBINF_ADDRESS = "/web/WEB-INF";
        String hostPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();

        if (hostPath.indexOf("webapps") != -1) {
            ideEnvironment = TtIdeEnvironment.ServerExecution;

        } else if (hostPath.indexOf("_out/artifacts/") != -1) {
            ideEnvironment = TtIdeEnvironment.Intellij;
        } else {
            OutLog.pl("IDE is not determined!");
        }

        if (ideEnvironment == TtIdeEnvironment.ServerExecution) {
            serverRun(hostPath);
        } else if (ideEnvironment == TtIdeEnvironment.Intellij) {
            intellijInit(hostPath);
        }

        osEnvironment = projectAbsolutePath != null && projectAbsolutePath.contains(":") ? TtOsEnvironment.Windows : TtOsEnvironment.Linux;
    }

    private String _CORE_NAME, _REPOSITORY_ADDRESS, _REPOSITORY_ROOT_ADDRESS, _SRC_MAIN_ADDRESS, _REGISTERY_ROOT_ADDRESS, _PROJECT_NAME, _WEBINF_ADDRESS;
    private String projectAbsolutePath, coreAbsolutePath, contextPath, webDomain, coreBuild, projectBuild;
    private TtIdeEnvironment ideEnvironment;
    private TtOsEnvironment osEnvironment;

    private void serverRun(String hostPath) {
        projectAbsolutePath = hostPath.substring(0, hostPath.indexOf("webapps") - 1);
        coreAbsolutePath = null;
        _PROJECT_NAME = projectAbsolutePath.substring(projectAbsolutePath.lastIndexOf("/") + 1);
        projectBuild = hostPath.substring(0, hostPath.indexOf("WEB-INF") + 8);
    }

    private void intellijInit(String hostPath) {
        String projectDir;
        int i;

        projectDir = hostPath.substring(0, hostPath.indexOf("/_out"));
        i = hostPath.indexOf("artifacts/") + 10;
        _PROJECT_NAME = hostPath.substring(i, hostPath.indexOf("/", i));
        projectAbsolutePath = projectDir + FILE_SEPARATOR + _PROJECT_NAME;
        coreAbsolutePath = projectDir + FILE_SEPARATOR + _CORE_NAME;
        projectBuild = hostPath.substring(0, hostPath.indexOf("WEB-INF") + 8);
        coreBuild = projectBuild.replace(_PROJECT_NAME, _CORE_NAME);
        //
        _SRC_MAIN_ADDRESS = "/src/org/sadr";
    }

    public void printInfo() {
        OutLog.sl("================== Environment info:\n"
            + "\n> OS_ENVIRONMENT: " + osEnvironment
            + "\n> IDE_ENVIRONMENT: " + ideEnvironment
            + "\n> CORE_NAME: " + _CORE_NAME
            + "\n> CORE_ABSOLUTE_PATH: " + coreAbsolutePath
            + "\n> PROJECT_NAME: " + _PROJECT_NAME
            + "\n> PROJECT_ABSOLUTE_PATH: " + projectAbsolutePath
            + "\n> REPOSITORY_ROOT_ADDRESS: " + _REPOSITORY_ROOT_ADDRESS
            + "\n> REPOSITORY_ADDRESS: " + _REPOSITORY_ADDRESS
            + "\n> REGISTERY_ROOT_ADDRESS: " + _REGISTERY_ROOT_ADDRESS
            + "\n> WEBINF_ADDRESS: " + _WEBINF_ADDRESS
            + "\n> CORE_BUILD: " + coreBuild
            + "\n> PROJECT_BUILD: " + projectBuild
            + "\n> getContextPath: " + getContextPath()
            + "\n> getWebDomain: " + getWebDomain()
            + "\n\n");
    }

    /////====//////////////////////////////    PROJECT
    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getWebDomain() {
        return webDomain;
    }

    public void setWebDomain(String webDomain) {
        this.webDomain = webDomain;
    }

    /////====//////////////////////////////    PROJECT
    public String getRepositoryRootAddress() {
        return _REPOSITORY_ROOT_ADDRESS;
    }

    public String getRepositoryAddress() {
        return _REPOSITORY_ADDRESS;
    }

    public String getProjectName() {
        return _PROJECT_NAME;
    }

    public String getProjectAP() {
        return projectAbsolutePath;
    }

    public String getProjectRepositoryAP() {
        return projectAbsolutePath + _REPOSITORY_ROOT_ADDRESS;
    }

    public String getProjectSourceCodeAP() {
        return projectAbsolutePath + _SRC_MAIN_ADDRESS;
    }

    public String getProjectRegistryAP() {
        return projectAbsolutePath + _REGISTERY_ROOT_ADDRESS;
    }

    public String getProjectWebInfAP(TtProjectPath tpp) {
        return projectAbsolutePath + _WEBINF_ADDRESS
            + FILE_SEPARATOR + tpp.getPath();
    }

    public String getProjectBuildWebInfAP(TtProjectPath tpp) {
        return projectBuild + tpp.getPath();
    }

    public String getCoreWebInfAP(TtProjectPath tpp) {
        return coreAbsolutePath
            + _WEBINF_ADDRESS
            + FILE_SEPARATOR + tpp.getPath();
    }

    /////====//////////////////////////////    CORE
}

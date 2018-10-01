package org.sadr._core._type;

public enum TtProjectPath {

    Resource("resources"),
    ResourceFront("resources-f"),
    ResourcePanel("resources-p"),
    View("view"),
    Config("conf"),
    ConfigLayout("conf/layouts"),
    ConfigMessage("conf/message"),
    ConfigMessageCore("conf/message/core"),
    ConfigMessageProject("conf/message/project"),
    ConfigSystem("conf/system"),;

    private final String path;

    private TtProjectPath(String p) {
        path = p;
    }

    public String getPath() {
        return path;
    }

}

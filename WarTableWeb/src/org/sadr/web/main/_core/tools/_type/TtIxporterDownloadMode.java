package org.sadr.web.main._core.tools._type;

public enum TtIxporterDownloadMode {

    ResourceAddress(""),
    FileControllerAddress("/panel/file/dl/"),;
    private final String address;

    private TtIxporterDownloadMode(String a) {
        address = a;
    }

    public String getAddress() {
        return address;
    }
}

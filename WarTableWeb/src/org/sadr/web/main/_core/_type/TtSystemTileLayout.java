package org.sadr.web.main._core._type;

public enum TtSystemTileLayout {

    Front("FrontTemplate", "سایت", "front"),
    Panel("PanelTemplate", "پنل", "panel"),;

    private final String title;
    private final String key;
    private final String folder;

    private TtSystemTileLayout(String k, String t, String f) {
        title = t;
        key = k;
        folder = f;
    }

    public String getTitle() {
        return title;
    }

    public String getFolder() {
        return folder;
    }

    public String getKey() {
        return key;
    }
}

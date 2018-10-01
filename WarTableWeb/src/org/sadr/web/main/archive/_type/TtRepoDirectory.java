package org.sadr.web.main.archive._type;

/**
 * @author masoud
 */
public enum TtRepoDirectory {

    Root("root"),
    _Trash("_trash"),
    Db_Backup("dbBackup"),
    ExportExcel("exportExcel")
    ,;

    private final String key;

    private TtRepoDirectory(String k) {
        key = k;
    }

    public String getKey() {
        return key;
    }

}

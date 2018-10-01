package org.sadr.web.main.archive._type;

/**
 * @author masoud
 */
public enum TtFileSystemParamQuery {

    List("ls", "لیست پوشه ها"),
    Content("cnt", "محتوای پوشه"),
    RemoveFile("rmf", "حذف فایل"),
    RemoveDirectory("rmd", "حذف پوشه"),
    DownloadFile("dlf", "دانلود فایل"),
    EditFile("edt", "بررسی ویرایش فایل"),
    MakeDirectory("mkdir", "ایجاد پوشه"),;

    private final String key;
    private final String title;

    private TtFileSystemParamQuery(String k, String t) {
        key = k;
        title = t;
    }

    public String getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }

    public static TtFileSystemParamQuery getByKey(String k) {
        for (TtFileSystemParamQuery value : values()) {
            if (value.key.equals(k)) {
                return value;
            }
        }
        return null;
    }
}

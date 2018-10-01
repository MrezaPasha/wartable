package org.sadr.web.main.archive.file.file;

import com.google.gson.annotations.SerializedName;
import org.sadr._core.meta.annotation.PersianName;

import java.util.HashMap;

@PersianName("پارامترهای فایل سیستم")
public class FileSystemParams {
    @SerializedName("q")
    private String query;

    @SerializedName("a")
    private HashMap<String, Object> arguments;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public HashMap<String, Object> getArguments() {
        return arguments;
    }

    public void setArguments(HashMap<String, Object> arguments) {
        this.arguments = arguments;
    }
}

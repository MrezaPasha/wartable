package org.sadr.share.main.map.model;

import java.io.Serializable;

public class DownloadResponse implements Serializable {
    private double size;
    private String content;



    // Constructor


    public DownloadResponse(double size, String content) {
        this.size = size;
        this.content = content;
    }

    public DownloadResponse() {
    }



    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

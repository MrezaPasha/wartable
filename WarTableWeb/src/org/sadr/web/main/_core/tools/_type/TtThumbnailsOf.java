package org.sadr.web.main._core.tools._type;

import org.imgscalr.Scalr;
import org.sadr.web.main.archive._type.TtRepoDirectory;

import java.awt.image.BufferedImageOp;

/**
 * @author masoud
 */
public enum TtThumbnailsOf {

    Human_Pic("", TtRepoDirectory.Root, TtUploadIfExist.RenameNewFile, TtUploadOrginalFile.KeepOrginalFile, Scalr.Method.BALANCED, Scalr.Mode.FIT_EXACT, TtThumnailCropMode.DontCrop, Scalr.OP_ANTIALIAS, TtThumbnailSize._120X160),

    ;

    private final String title;
    private final TtUploadIfExist uploadIfExist;
    private final TtUploadOrginalFile uploadOrginalFile;
    private final Scalr.Method method;
    private final Scalr.Mode mode;
    private final TtThumnailCropMode cropMode;
    private final BufferedImageOp imageOp;
    private final TtThumbnailSize[] thumbs;
    private final TtRepoDirectory repoDirectory;

    private TtThumbnailsOf(String t, TtRepoDirectory rdi, TtUploadIfExist tuie, TtUploadOrginalFile tuof, Scalr.Method m, Scalr.Mode mo, TtThumnailCropMode cm, BufferedImageOp sc, TtThumbnailSize... tn) {
        title = t;
        repoDirectory = rdi;
        uploadIfExist = tuie;
        uploadOrginalFile = tuof;
        method = m;
        mode = mo;
        cropMode = cm;
        imageOp = sc;
        thumbs = tn;

    }

    public TtThumbnailSize[] getThumbs() {
        return thumbs;
    }

    public String getTitle() {
        return title;
    }

    public TtUploadIfExist getUploadIfExist() {
        return uploadIfExist;
    }

    public TtUploadOrginalFile getUploadOrginalFile() {
        return uploadOrginalFile;
    }

    public Scalr.Method getMethod() {
        return method;
    }

    public Scalr.Mode getMode() {
        return mode;
    }

    public TtThumnailCropMode getCropMode() {
        return cropMode;
    }

    public BufferedImageOp getImageOp() {
        return imageOp;
    }

    public static TtThumbnailsOf getByKey(String k) {
        for (TtThumbnailsOf value : values()) {
            if (value.thumbs.equals(k)) {
                return value;
            }
        }
        return null;
    }

    public TtRepoDirectory getRepoDirectory() {
        return repoDirectory;
    }
}

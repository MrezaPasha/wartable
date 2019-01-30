package org.sadr.share.main.meetingRecFile;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.meetingRecFile._types.TtMeetingRecFileType;
import org.sadr.share.main.meetingSetting.MeetingSetting;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@PersianName("فایل صوتی و تصویری جلسه")
@Entity
@Table(name = "Service.MeetingRecFile")

public class MeetingRecFile extends GenericDataModel<MeetingRecFile> implements Serializable {



    public static final String FILE_TYPE = "fileType";
    public static final String FILE_NAME = "fileName";
    public static final String SIZE = "size";
    public static final String _MEETING_SETTING = "meetingSetting";
    private static String[] actColumns;
    private static String[] relColumns;
    private static String[] virColumns;

    public static void setAvrColumns(String acts, String virts, String rels) {
        if (acts != null) {
            actColumns = acts.split(",");
        }
        if (virts != null) {
            virColumns = virts.split(",");
        }
        if (rels != null) {
            relColumns = rels.split(",");
        }
    }

    // static fields end




    @PersianName("نوع فایل")
    private TtMeetingRecFileType fileType;

    @PersianName("نام فایل")
    private String fileName ;


    @PersianName("سایز فایل")
    private double size;


    @PersianName("تنظیمات جلسه")
    @ManyToOne(fetch = FetchType.LAZY)
    private MeetingSetting meetingSetting;








    public TtMeetingRecFileType getFileType() {
        return fileType;
    }

    public void setFileType(TtMeetingRecFileType fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public MeetingSetting getMeetingSetting() {
        return meetingSetting;
    }

    public void setMeetingSetting(MeetingSetting meetingSetting) {
        this.meetingSetting = meetingSetting;
    }



}

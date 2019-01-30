package org.sadr.share.main.meetingSetting;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meetingRecFile.MeetingRecFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@PersianName("تنظیمات جلسه")
@Entity
@Table(name = "Service.MeetingSetting")
public class MeetingSetting extends GenericDataModel<MeetingSetting> implements Serializable {

    public static final String _MEETING = "meeting";
    public static final String NAME = "name" ;
    public static final String DESCRIPTION = "description" ;
    public static final String START_DATE_TIME = "startDateTime";
    public static final String END_DATE_TIME = "endDateTime";
    public static final String _REC_FILES = "recFiles";


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




    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("جلسه")
    private Meeting meeting;

    @PersianName("نام تنطیم")
    private String name;

    @PersianName("توضیحات تنظیم")
    private String description;

    @PersianName("تاریخ شروع جلسه")
    private String startDateTime;



    @PersianName("تاریخ پایان جلسه")
    private String endDateTime;



    @PersianName("فایل های ذخیره شده")
    @OneToMany(mappedBy = "meetingSetting")
    private Set<MeetingRecFile> recFiles;




    // METHODS


    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }


    public Set<MeetingRecFile> getRecFiles() {
        return recFiles;
    }

    public void setRecFiles(Set<MeetingRecFile> recFiles) {
        this.recFiles = recFiles;
    }


}

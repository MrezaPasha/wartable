package org.sadr.share.main.meetingItemLayer;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingItemLayer._types.TtMeetingItemLayerDeleted;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;


@PersianName("لایه المان های روی میز")
@javax.persistence.Entity
@Table(name = "Service.MeetingItemLayer")
public class MeetingItemLayer extends GenericDataModel<MeetingItemLayer> implements Serializable {

    public static final String NAME = "name";
    public static final String CREATION_DATE_TIME = "creationDateTime";
    public static final String DELETED = "deleted";
    public static final String _MEETING_ITEMS = "meetingItems";
    public static final String ORDER = "order";
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




    @PersianName("نام لایه اشیا")
    private String name;

    @PersianName("تاریخ ایجاد")
    private String creationDateTime;

    @PersianName("وضععیت")
    private TtMeetingItemLayerDeleted deleted;

    @OneToMany(mappedBy = "meetingItemLayer")
    @PersianName("اشیای لایه")
    private Set<MeetingItem> meetingItems;

    @PersianName("ترتیب")
    private Integer order;


    // getter and setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public TtMeetingItemLayerDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(TtMeetingItemLayerDeleted deleted) {
        this.deleted = deleted;
    }

    public Set<MeetingItem> getMeetingItems() {
        return meetingItems;
    }

    public void setMeetingItems(Set<MeetingItem> meetingItems) {
        this.meetingItems = meetingItems;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}

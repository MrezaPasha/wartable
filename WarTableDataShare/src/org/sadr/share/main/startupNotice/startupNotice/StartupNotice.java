package org.sadr.share.main.startupNotice.startupNotice;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.startupNotice.item.StartupNoticeItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;


@PersianName("فراخوان")
@Entity
@Table(name = "Service.StartupNotice")
public class StartupNotice extends GenericDataModel<StartupNotice> implements Serializable {


    public static final String _RECEIVERS = "receivers";
    public static final String _RECEIVER_TEMPS = "receiverTemps";
    public static final String MESSAGE_BODY = "messageBody";
    public static final String SEND_DATE_TIME = "sendDateTime";
    public static final String TITLE = "title";

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

    @PersianName("عنوان")
    private String title;

    @PersianName("زمان ارسال")
    private String sendDateTime;

    @PersianName("محتوای پیام")
    @Column(length = 1024)
    private String messageBody;


    @OneToMany(mappedBy = "startupNotice")
    @PersianName("گیرندگان")
    private Set<StartupNoticeItem> receivers;


    //temp field only for save and update in form
    @OneToMany()
    @PersianName("گیرندگان")
    private Set<ServiceUser> receiverTemps;

    // METHODS


    public String getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(String sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Set<StartupNoticeItem> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<StartupNoticeItem> receivers) {
        this.receivers = receivers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<ServiceUser> getReceiverTemps() {
        return receiverTemps;
    }

    public void setReceiverTemps(Set<ServiceUser> receiverTemps) {
        this.receiverTemps = receiverTemps;
    }
}

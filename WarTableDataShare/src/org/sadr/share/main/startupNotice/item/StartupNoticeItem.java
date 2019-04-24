package org.sadr.share.main.startupNotice.item;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.startupNotice._type.TtStartupNoticeSendStatus;
import org.sadr.share.main.startupNotice.startupNotice.StartupNotice;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@PersianName("موارد فراخوان")
@Entity
@Table(name = "Service.StartupNotice.Item")
public class StartupNoticeItem extends GenericDataModel<StartupNoticeItem> implements Serializable {


    public static final String DELIVER_DATE_TIME = "deliverDateTime";
    public static final String SEND_STATUS = "sendStatus";
    public static final String _STARTUP_NOTICE = "startupNotice";
    public static final String _SERVICE_USER = "serviceUser";
    public static final String $RECEIVER_NAME = "receiveName";

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

    @PersianName("زمان دریافت")
    private String deliverDateTime;

    @PersianName("وضعیت دریافت")
    private TtStartupNoticeSendStatus sendStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("فراخوان")
    private StartupNotice startupNotice;


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("گیرنده")
    private ServiceUser serviceUser;

    // METHODS

    @PersianName("نام گیرنده")
    public String getReceiveName() {
        return serviceUser != null ? serviceUser.getFullName() : "";
    }

    public String getDeliverDateTime() {
        return deliverDateTime;
    }

    public void setDeliverDateTime(String deliverDateTime) {
        this.deliverDateTime = deliverDateTime;
    }

    public TtStartupNoticeSendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(TtStartupNoticeSendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }

    public StartupNotice getStartupNotice() {
        return startupNotice;
    }

    public void setStartupNotice(StartupNotice startupNotice) {
        this.startupNotice = startupNotice;
    }

    public ServiceUser getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }
}

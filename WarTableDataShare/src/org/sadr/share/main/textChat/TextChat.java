package org.sadr.share.main.textChat;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.textChat._types.TtTextChatSendStatus;
import org.sadr.share.main.textChat._types.TtTextChatType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@PersianName("پیام ها")
@Entity
@Table(name = "Service.TextChat")
public class TextChat extends GenericDataModel<TextChat> implements Serializable {


    public static final String _ROOM = "room";
    public static final String _SENDER = "sender";
    public static final String _RECEIVERS = "receivers";
    public static final String SEND_DATE_TIME = "sendDateTime";
    public static final String SEND_STATUS = "sendStatus";
    public static final String CHAT_TYPE = "chatType";
    public static final String MESSAGE_BODY = "messageBody";
    public static final String DELIVER_DATE_TIME = "deliverDateTime";
    public static final String $SENDER_FUUL_NAME = "senderFullName";
    public static final String _PRIVATE_CHAT_RECEIVER = "privateChatReceiver";


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

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("اتاق")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("فرستنده")
    private ServiceUser sender;


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("گیرنده خصوصی")
    private ServiceUser privateChatReceiver;





    @ManyToMany
    @JoinTable(name = "Service.TextChat_ServiceUser",
        joinColumns = {
            @JoinColumn(name = "textChat_id", nullable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "serviceUser_id")})
    @PersianName("گیرنده یا گیرندگان")
    private Set<ServiceUser> receivers;

    @PersianName("زمان ارسال پیام")
    private String sendDateTime;

    @PersianName("وضعیت ارسال")
    private TtTextChatSendStatus sendStatus;

    @PersianName("نوع پیام")
    private TtTextChatType chatType;

    @PersianName("محتوای پیام")
    @Column(length = 1024)
    private String messageBody;

    @PersianName("زمان دریافت پیام")
    private String deliverDateTime;


    // METHODS

    @PersianName("فرستنده")
    public String getSenderFullName(){
        return sender!=null? sender.getFullName():"";
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ServiceUser getSender() {
        return sender;
    }

    public void setSender(ServiceUser sender) {
        this.sender = sender;
    }


    public String getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(String sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public TtTextChatSendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(TtTextChatSendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }

    public TtTextChatType getChatType() {
        return chatType;
    }

    public void setChatType(TtTextChatType chatType) {
        this.chatType = chatType;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getDeliverDateTime() {
        return deliverDateTime;
    }

    public void setDeliverDateTime(String deliverDateTime) {
        this.deliverDateTime = deliverDateTime;
    }

    public Set<ServiceUser> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<ServiceUser> receivers) {
        this.receivers = receivers;
    }

    public ServiceUser getPrivateChatReceiver() {
        return privateChatReceiver;
    }

    public void setPrivateChatReceiver(ServiceUser privateChatReceiver) {
        this.privateChatReceiver = privateChatReceiver;
    }
}

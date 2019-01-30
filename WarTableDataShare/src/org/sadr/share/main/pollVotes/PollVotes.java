package org.sadr.share.main.pollVotes;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.roomPolls.RoomPolls;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


@PersianName("رای های جلسه")
@Entity
@Table(name = "Service.PollVotes")
public class PollVotes extends GenericDataModel<PollVotes> implements Serializable {
    public static final String _POLLS = "poll";
    public static final String _USER = "user";
    public static final String VOTE = "vote";

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

    public static String[] getActColumns() {
        return actColumns;
    }

    public static String[] getVirColumns() {
        return virColumns;
    }

    public static String[] getRelColumns() {
        return relColumns;
    }


    // static fields end


    @PersianName("نظرسنجی")
    @ManyToOne(fetch = FetchType.LAZY)
    private RoomPolls poll;

    @PersianName("کاربر رای دهنده")
    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceUser user;

    @PersianName("رای کاربر")
    private String vote;


    // METHODS


    public RoomPolls getPoll() {
        return poll;
    }

    public void setPoll(RoomPolls poll) {
        this.poll = poll;
    }

    public ServiceUser getUser() {
        return user;
    }

    public void setUser(ServiceUser user) {
        this.user = user;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}

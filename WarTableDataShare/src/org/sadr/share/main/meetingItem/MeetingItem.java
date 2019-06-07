package org.sadr.share.main.meetingItem;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.item.entity.Entity;
import org.sadr.share.main.item.media.Media;
import org.sadr.share.main.item.object.Object;
import org.sadr.share.main.item.position.Position;
import org.sadr.share.main.item.svg.Svg;
import org.sadr.share.main.item.vector.Vector;
import org.sadr.share.main.item.weather.Weather;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meetingItem._types.TtItemType;
import org.sadr.share.main.meetingItem._types.TtMeetingItemDeleted;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;


@PersianName("المان های روی میز")
@javax.persistence.Entity
@Table(name = "Service.MeetingItem")
public class MeetingItem extends GenericDataModel<MeetingItem> implements Serializable {


    public static final String _INSERTER_USER = "inserterUser";
    /*
        public static final String _MRML = "mrml";
    */
    public static final String _MEETING = "meeting";
    public static final String _OBJECT = "object";
    public static final String _MEDIA = "media";
    public static final String _WEATHER = "weather";
    public static final String _ENTITY = "entity";
    public static final String _POSITION = "position";
    public static final String _SVG = "svg";
    public static final String _VECTOR = "vector";
    public static final String ITEM_TYPE = "itemType";
    public static final String POSX = "posX";
    public static final String POSY = "posY";
    public static final String POSZ = "posZ";
    public static final String ROLL = "roll";
    public static final String PITCH = "pitch";
    public static final String ANGLE = "angle";
    public static final String SCALE = "scale";
    public static final String YAW = "yaw";
    public static final String COMMENT = "comment";
    public static final String DELETED = "deleted";
    public static final String ORDER = "order";
    public static final String $VECTOR_NAME = "vectorName";
    public static final String $VECTOR_FILE_NAME= "vectorFileName";
    public static final String $VECTOR_TYPE= "vectorType";
/*
    public static final String _MEETING_ITEM_LAYER = "meetingItemLayer";
*/

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
    @PersianName("کاربر بارگذار کننده")
    private ServiceUser inserterUser;

   /*@OneToOne()
   @PersianName("لایه")
   private  Mrml mrml;*/


    /*@OneToMany(mappedBy = "meetingItem")
    @PersianName("لایه")
    private Set<Mrml>  mrmls;*/


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("جلسه")
    private Meeting meeting;


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("اشیای میز")
    private Object object;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("مدیای میز")
    private Media media;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("آب و هوای میز")
    private Weather weather;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("مسیرهای میز")
    private Entity entity;

    @OneToOne(fetch = FetchType.LAZY)
    @PersianName("موقعیت های میز")
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("SVG")
    private Svg svg;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("بردار")
    private Vector vector;

    @PersianName("نوع شی")
    private TtItemType itemType;


    @PersianName("مختصات طولی")
    private double posX;

    @PersianName("مختصات عرضی")
    private double posY;

    @PersianName("مختصات ارتفاعی")
    private double posZ;

    @PersianName("چرخش افقی")
    private double roll;

    @PersianName("چرخش عمودی")
    private double pitch;

    @PersianName("زاویه چرخش")
    private double angle;

    @PersianName("بزرگنمایی")
    private double scale;

    @PersianName("بین طول و عرض")
    private double yaw;

    @PersianName("توضیحات")
    private String comment;

    @PersianName("وضعیت حضور شی")
    private TtMeetingItemDeleted deleted;


    @PersianName("ترتیب")
    private Integer order;


    /*@PersianName("لایه")
    @ManyToOne(fetch = FetchType.LAZY)
    private MeetingItemLayer meetingItemLayer;*/


    @PersianName("نام")
    public String getVectorName() {
        return vector != null ? vector.getName() : "";
    }

    @PersianName("آدرس فایل")
    public String getVectorFileName() {
        return vector != null ? vector.getFileName() : "";
    }

    @PersianName("نوع")
    public String getVectorType() {
        return vector != null ? vector.getVectorType() : "";
    }


    // MEYHODS


    /*public static String getInserterUser() {
        return _INSERTER_USER;
    }

    public void setInserterUser(ServiceUser inserterUser) {
        this.inserterUser = inserterUser;
    }*/

    public ServiceUser getInserterUser() {
        return inserterUser;
    }

    public void setInserterUser(ServiceUser inserterUser) {
        this.inserterUser = inserterUser;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TtItemType getItemType() {
        return itemType;
    }

    public void setItemType(TtItemType itemType) {
        this.itemType = itemType;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getPosZ() {
        return posZ;
    }

    public void setPosZ(double posZ) {
        this.posZ = posZ;
    }

    public double getRoll() {
        return roll;
    }

    public void setRoll(double roll) {
        this.roll = roll;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TtMeetingItemDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(TtMeetingItemDeleted deleted) {
        this.deleted = deleted;
    }

    /*public MeetingItemLayer getMeetingItemLayer() {
        return meetingItemLayer;
    }

    public void setMeetingItemLayer(MeetingItemLayer meetingItemLayer) {
        this.meetingItemLayer = meetingItemLayer;
    }*/

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Svg getSvg() {
        return svg;
    }

    public void setSvg(Svg svg) {
        this.svg = svg;
    }

    public Vector getVector() {
        return vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }
}

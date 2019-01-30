package org.sadr.share.main.item.position;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.item.position._types.TtPositionType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@PersianName("موقعیت های روی میز")
@Entity
@Table(name = "Service.Position")
public class Position extends GenericDataModel<Position> implements Serializable {

    public static final String FONT = "font";
    public static final String COLOR = "color";
    public static final String POSITION = "position";
    public static final String POSITION_TYPE = "positionType";
    public static final String NAME ="name";
    public static final String DESCRIPTION = "description";


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





    @PersianName("فونت")
    private String font;

    @PersianName("نام")
    private String name;

    @PersianName("توضیحات")
    private String description;

    @PersianName("رنگ")
    private String color;

    @PersianName("موقعیت")
    private String position;

    @PersianName("نوع جایگاه")
    private TtPositionType positionType;



    // METHODS


    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public TtPositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(TtPositionType positionType) {
        this.positionType = positionType;
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


}

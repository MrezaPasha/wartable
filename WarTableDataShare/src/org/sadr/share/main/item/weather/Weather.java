package org.sadr.share.main.item.weather;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.item.weather._types.TtWeatherType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@PersianName("آب و هوای روی میز")
@Entity
@Table(name = "Service.Weather")
public class Weather extends GenericDataModel<Weather> implements Serializable {




    public static final String NAME  = "name" ;
    public static final String DESCRIPTION = "description" ;
    public static final String WEATHER_TYPE = "weatherType";
    public static final String ROTATION = "rotation";

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





    @PersianName("نام")
    private String name;

    @PersianName("توضیحات")
    private String description;

    @PersianName("نوع هوا")
    private TtWeatherType weatherType;


    @PersianName("زاویه چرخش")
    private double rotation;


    // METHODS


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

    public TtWeatherType getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(TtWeatherType weatherType) {
        this.weatherType = weatherType;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}

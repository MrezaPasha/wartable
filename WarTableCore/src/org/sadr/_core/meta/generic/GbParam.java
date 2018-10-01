package org.sadr._core.meta.generic;

import com.google.gson.annotations.SerializedName;
import org.sadr._core.meta.annotation.PersianName;

/**
 * @author masoud
 */
@PersianName("پارامترها")
public class GbParam {

    @SerializedName("k")
    private String key;
    @SerializedName("n")
    private String name;
    @SerializedName("v")
    private Object value;
    @SerializedName("o")
    private String operator;
    @SerializedName("t")
    private String type;
    @SerializedName("s")
    private Boolean noSpace;

    public GbParam(String name, GbParam gb) {
        this.key = gb.key;
        this.name = name;
        this.value = gb.value;
        this.operator = gb.operator;
        this.type = gb.type;
        this.noSpace = gb.noSpace;
    }

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
    public Boolean getNoSpace() {
        return noSpace != null ? noSpace : false;
    }

    public void setNoSpace(Boolean noSpace) {
        this.noSpace = noSpace;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "GbParam{" + "key=" + key + ", name=" + name + ", value=" + value + ", valueType=" + (value == null ? " NULL" : value.getClass().getTypeName()) + ", operator=" + operator + ", noSpace=" + noSpace + '}';
    }

}

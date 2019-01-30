package org.sadr._core.meta.generic;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.sadr._core._type.TtSecurityTag;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core._type.TtIxporterFileType;

/**
 * @author masoud
 */
@PersianName("پارامترهای خروجی")
public class IxporterParams {

    @SerializedName("tp")
    private String type;
    @SerializedName("en")
    private Boolean isEncrypt;
    @SerializedName("sg")
    private Boolean isSign;
    @SerializedName("zp")
    private Boolean isZip;
    @SerializedName("st")
    private int securityTag;
    @SerializedName("ps")
    private int pageSize;
    @SerializedName("pi")
    private int pageIndex;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS

    public static IxporterParams getObject(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, IxporterParams.class);
    }

    public TtIxporterFileType getFileType() {
        return TtIxporterFileType.getByKey(type);
    }

    public TtSecurityTag getSecurityTag() {
        return TtSecurityTag.getByOrdinal(securityTag);
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS

    public void setSecurityTag(int securityTag) {
        this.securityTag = securityTag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(Boolean encrypt) {
        isEncrypt = encrypt;
    }

    public Boolean getIsSign() {
        return isSign;
    }

    public void setIsSign(Boolean sign) {
        isSign = sign;
    }

    public Boolean getIsZip() {
        return isZip;
    }

    public void setIsZip(Boolean zip) {
        isZip = zip;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}

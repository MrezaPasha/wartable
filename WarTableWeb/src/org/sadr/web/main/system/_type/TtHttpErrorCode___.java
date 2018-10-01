package org.sadr.web.main.system._type;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author masoud
 */
public enum TtHttpErrorCode___ {

    BadRequest_400("400", "N.http.error.400.title", "N.http.error.400.des"),
    Unauthorized_401("401", "N.http.error.401.title", "N.http.error.401.des"),
    Forbidden_403("403", "N.http.error.403.title", "N.http.error.403.des"),
    MethodNotAllowed_405("405", "N.http.error.405.title", "N.http.error.405.des"),
    PayloadTooLarge_413("413", "N.http.error.413.title", "N.http.error.413.des"),
    URITooLong_414("414", "N.http.error.404.title", "N.http.error.404.des"),
    NoResponse_444("444", "N.http.error.444.title", "N.http.error.444.des"),
    SSLCertificateError_495("495", "N.http.error.495.title", "N.http.error.495.des"),
    InternalServerError_500("500", "N.http.error.500.title", "N.http.error.500.des"),
    ServiceUnavailable_503("503", "N.http.error.503.title", "N.http.error.503.des"),
    NotFound_404("404", "N.http.error.404.title", "N.http.error.404.des");

    public static TtHttpErrorCode___ getByKey(String key) {
        for (TtHttpErrorCode___ value : values()) {
            if (value.getKey().equals(key)) {
                return value;
            }
        }
        return null;
    }

    private TtHttpErrorCode___(String k, String mct, String mcd) {
        key = k;
        messageCodeTitle = mct;
        messageCodeDes = mcd;
    }

    private String key;
    private String messageCodeTitle;
    private String messageCodeDes;

    public String getKey() {
        return key;
    }

    //== front
    public String getFrontTile() {
        return "f.e." + key;
    }

    public ModelAndView ___getFrontDisModel() {
        return new ModelAndView("f.e." + key);
    }

    public String getMessageCodeTitle() {
        return messageCodeTitle;
    }

    public String getMessageCodeDes() {
        return messageCodeDes;
    }


    //== panel
    public String getPanelTile() {
        return "p.e." + key;
    }

    public ModelAndView ___getPanelDisModel() {
        return new ModelAndView("p.e." + key);
    }

}

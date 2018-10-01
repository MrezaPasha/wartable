package org.sadr.web.main._core.utils._type;

/**
 * @author masoud
 */
public enum TtIsonStatus {

    Ok("ok"),
    Nok("nok"),
    Unknown("unknown");

    private final String key;

    private TtIsonStatus(String k) {
        key = k;
    }

    public String getKey() {
        return key;
    }

}

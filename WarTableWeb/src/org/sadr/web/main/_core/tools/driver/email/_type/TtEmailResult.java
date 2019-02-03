package org.sadr.web.main._core.tools.driver.email._type;

/**
 *
 * @author dev1
 */
public enum TtEmailResult {
    Sent(0, ""),
    Sending(1, ""),
    Initialized(2, ""),
    InvalidEmalFormat(3, ""),
    UnAccessible(4, ""),
    Failed(5, ""),
    NullGateWay(6, ""),
    NullReciever(7, ""),
    NullEmail(8, ""),
    NullEmailTemplate(9, "") 
    ;

    private String key;
    private int value;

    private TtEmailResult(int v, String k) {
        key = k;
        value = v;
    }

    public int getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

}

package org.sadr.share.main.log._types;

public enum TtSendStatus {

    RealTime("بلادرنگ"),
    Delay("ارسال با تاخیر"),
    SpecificTime("ارسال در زمان مشخص");

    private String sendStatusName;

    TtSendStatus(String sendStatusName) {
        this.sendStatusName = sendStatusName;
    }

    TtSendStatus() {
    }

    public String getSendStatusName() {
        return sendStatusName;
    }

    public void setSendStatusName(String sendStatusName) {
        this.sendStatusName = sendStatusName;
    }

    public static TtSendStatus getByOrdinal(int o) {
        for (TtSendStatus f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}

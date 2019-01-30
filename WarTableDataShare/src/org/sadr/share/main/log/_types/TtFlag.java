package org.sadr.share.main.log._types;

public enum  TtFlag {

    Success("Success"),
    Failure("Failure"),
    Error("Error");

    private String flagName;

    TtFlag(String flagName) {
        this.flagName = flagName;
    }

    TtFlag() {
    }

    // getter and setter start

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }


    // getter and setter end

    public static TtFlag getByOrdinal(int o) {
        for (TtFlag f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }


}

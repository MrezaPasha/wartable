package org.sadr.share.main.log._types;

public enum TtSensitivity {

    Necessary("Necessary"),
    Alarm("Alarm"),
    Exception("Exception"),
    Notification("Notification"),
    Debug("Debug");

    private String sensitivityName;

    TtSensitivity(String sensitivityName) {
        this.sensitivityName = sensitivityName;
    }

    TtSensitivity() {
    }

    // getter and setter start

    public String getSensitivityName() {
        return sensitivityName;
    }

    public void setSensitivityName(String sensitivityName) {
        this.sensitivityName = sensitivityName;
    }


    // getter and setter end

    public static TtSensitivity getByOrdinal(int o) {
        for (TtSensitivity f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}

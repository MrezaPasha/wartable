package org.sadr.share.main.log._types;

public enum  TtImportanceLevel {
    LevelOne("low importance"),
    LevelTwo("medium Importance"),
    LevelThree("vital important");

    private String importanceName;

    TtImportanceLevel(String importanceName) {
        this.importanceName = importanceName;
    }

    TtImportanceLevel() {
    }

    // getter and setter start
    public String getImportanceName() {
        return importanceName;
    }

    public void setImportanceName(String importanceName) {
        this.importanceName = importanceName;
    }
    // getter and setter end

    public static TtImportanceLevel getByOrdinal(int o) {
        for (TtImportanceLevel f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}

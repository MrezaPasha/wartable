package org.sadr.share.main._types;

public enum TtImportance {
    LowImportance(1,"LowImportance"),
    MediumImportance(2,"MediumImportance"),
    HighImportance(3,"HighImportance");

    private int id;
    private String name;

    TtImportance() {
    }

    TtImportance(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TtImportance getByOrdinal(int o) {
        for (TtImportance f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}

package org.sadr._core._type;

/**
 * @author masoud
 */
public enum TtEntityState {

    Exist("موجود"),
    Trash("سطل بازیافت"),
    Remove("حذف شده");

    private final String title;

    private TtEntityState(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

    public boolean isTrashOrRemove() {
        return this == Remove || this == Trash;
    }

}

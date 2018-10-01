package org.sadr.web.main.note._type;

/**
 * @author masoud
 */
public enum TtNoteImportance {

    Low("کم"),
    Medium("متوسط"),
    High("زیاد"),
   ;

    private final String title;

    private TtNoteImportance(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

}

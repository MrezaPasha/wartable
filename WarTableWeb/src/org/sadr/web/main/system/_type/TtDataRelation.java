package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtDataRelation {

    OneToOne("OneToOne", "یک به یک"),
    OneToMany("OneToMany", "یک به چند"),
    ManyToOne("ManyToOne", "چند به یک"),
    ManyToMany("ManyToMany", "چند به چند");

    private final String key;
    private final String title;

    private TtDataRelation(String k, String t) {
        key = k;
        title = t;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

}

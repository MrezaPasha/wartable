package org.sadr.share.main.item.object._types;

public enum TtObjectPrivacy {

    PUBLIC("عمومی"),
    PRIVATE("خصوصی");

    private String title;


    TtObjectPrivacy(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public static TtObjectPrivacy getByOrdinal(int o){
        for (TtObjectPrivacy f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }



}

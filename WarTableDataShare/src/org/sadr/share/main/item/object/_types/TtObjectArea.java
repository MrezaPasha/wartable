package org.sadr.share.main.item.object._types;

public enum TtObjectArea {


    All("همه جا"),
    Water("آب"),
    dust("خاک"),
    sea("دریا"),
    forest("جنگل"),
    desert("بیابان");


    private String name ;

    TtObjectArea(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static TtObjectArea getByOrdinal(int o){
        for (TtObjectArea f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }
}

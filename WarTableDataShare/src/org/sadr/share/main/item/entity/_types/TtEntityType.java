package org.sadr.share.main.item.entity._types;


public enum TtEntityType {
    Pattern(),
    RectanglePattern(),
    Path(),
    Poly(),
    Point();

    TtEntityType() {
    }

    public static TtEntityType getByOrdinal(int o){
        for (TtEntityType f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }


}

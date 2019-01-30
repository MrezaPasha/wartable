package org.sadr.share.main.item.position._types;

public enum TtPositionType {

    Nothing(),
    Text(),
    Image();

    public static TtPositionType getByOrdinal(int o){
        for (TtPositionType f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }}

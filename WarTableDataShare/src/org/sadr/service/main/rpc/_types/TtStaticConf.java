package org.sadr.service.main.rpc._types;

public enum TtStaticConf {
    BroadCast("fanout");

    private String confValue;

    TtStaticConf(String confValue) {
        this.confValue = confValue;
    }

    TtStaticConf() {
    }

    public String getConfValue() {
        return confValue;
    }

    public void setConfValue(String confValue) {
        this.confValue = confValue;
    }

    public static TtStaticConf getByOrdinal(int o){
        for (TtStaticConf f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }
}

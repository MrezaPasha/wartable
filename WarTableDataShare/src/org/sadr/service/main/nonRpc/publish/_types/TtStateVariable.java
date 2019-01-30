package org.sadr.service.main.nonRpc.publish._types;

public enum TtStateVariable {
    Changed("1"),
    UnChanged("0");


    private String name;

    TtStateVariable(String name) {
        this.name = name;
    }

    TtStateVariable() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TtStateVariable getByOrdinal(int o){
        for (TtStateVariable f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }
}

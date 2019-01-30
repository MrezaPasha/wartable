package org.sadr.service.main.nonRpc.publish._core;

import org.sadr.service.main.nonRpc.publish._types.TtPrivilageFlags;
import org.sadr.service.main.nonRpc.publish._types.TtStateVariable;

public class PrivilageFlags {

    private String privilageFlagBytes;


    public String getPrivilageFlagBytes() {
        return privilageFlagBytes;
    }

    public String setPrivilageFlagBytes(boolean change, TtPrivilageFlags ... validState) {
        String finalResult = new String("");
        int index = 0;
        if (change)
        {
            /*for (TtState ttState : TtState.values()) {

            } */
            while (TtPrivilageFlags.getByOrdinal(index) != null) {
                for (int i = 0; i < validState.length; i++) {
                    if (TtPrivilageFlags.getByOrdinal(index) == validState[i]) {
                        finalResult += TtStateVariable.Changed.getName();
                        index++;
                    } else if (TtPrivilageFlags.getByOrdinal(index) != validState[i]) {
                        finalResult += TtStateVariable.UnChanged.getName();
                        index++;
                    }
                }
            }
        }
        else
        {
            while (TtPrivilageFlags.getByOrdinal(index) != null) {
                for (int i = 0; i < validState.length; i++) {
                    if (TtPrivilageFlags.getByOrdinal(index) == validState[i]) {
                        finalResult += TtStateVariable.UnChanged.getName();
                        index++;
                    } else if (TtPrivilageFlags.getByOrdinal(index) != validState[i]) {
                        finalResult += TtStateVariable.Changed.getName();
                        index++;
                    }
                }
            }

        }
        return finalResult;
    }

    // constructor

    public PrivilageFlags() {
    }
}


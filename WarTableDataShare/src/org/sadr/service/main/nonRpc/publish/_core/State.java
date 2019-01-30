package org.sadr.service.main.nonRpc.publish._core;

import org.sadr.service.main.nonRpc.publish._types.TtState;

import java.util.ArrayList;
import java.util.List;

public class State {
    private String stateBytes;



    public String getStateBytes() {
        return stateBytes;
    }

    public List<Integer> setStateBytes(boolean change, TtState... validStates) {

        List<Integer> finalResult = new ArrayList<Integer>();
        if (change)
        {
            for (TtState validState: validStates)
            {
                finalResult.add(validState.ordinal());
            }
        }
        return finalResult;
        //String finalResult = new String("");
        /*List<Integer> finalResult = new ArrayList<Integer>();
        int index = 0;
        int lenght = validState.length;
        System.out.println(validState.length);
        if (change)
        {
            while (TtState.getByOrdinal(index) != null) {
                for (int i = 0 ; i < lenght ; i++)
                {
                    if (TtState.getByOrdinal(index) == validState[i]) {
                       *//* finalResult += TtStateVariable.Changed.getName();*//*
                        finalResult.add( Integer.parseInt(TtStateVariable.Changed.getName()));
                        index++;
                    } else if (TtState.getByOrdinal(index) != validState[i]) {
                        finalResult.add(Integer.parseInt(TtStateVariable.UnChanged.getName()));
                        index++;
                    }

                }
            }

        }
        else
        {
            while (TtState.getByOrdinal(index) != null) {
                for (int i = 0; i < validState.length; i++) {
                    if (TtState.getByOrdinal(index) == validState[i]) {
                        finalResult.add(Integer.parseInt(TtStateVariable.UnChanged.getName()));
                        index++;
                    } else if (TtState.getByOrdinal(index) != validState[i]) {
                        finalResult.add( Integer.parseInt(TtStateVariable.Changed.getName()));
                        index++;
                    }
                }
            }

        }
        return finalResult;*/

    }

    // constructor

    public State() {
    }
}

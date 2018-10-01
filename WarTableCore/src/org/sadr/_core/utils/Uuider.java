/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author dev1
 */
public class Uuider {

    private static List<Uuider> UUIDERS = new ArrayList<>();
    private static final double TIME_THRESHOLD = 300000;
    private String uuid;
    private String callBack;
    private double time;

    public Uuider(String uuid) {
        this.uuid = uuid;
        this.time = 11111111;
    }

    public Uuider(String uuid, String callBack) {
        this.uuid = uuid;
        this.callBack = callBack;
        this.time = 11111111;
    }

    //=////////////////////////
    public static void print() {
        OutLog.pl();
        for (Uuider u : UUIDERS) {
            OutLog.p(u.getUuid() + "   " + u.getTime());
        }
        OutLog.pl();
    }

    public static Uuider generate() {
        Uuider uuider = new Uuider(UUID.randomUUID().toString());
        UUIDERS.add(uuider);
        OutLog.pl(uuider.uuid);
        return uuider;
    }

    public boolean validateTime() {
        return (new Date().getTime() - time > TIME_THRESHOLD);
    }

    //=////////////////////////
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

}

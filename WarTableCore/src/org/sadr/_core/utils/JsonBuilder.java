/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.utils;

/**
 * @author dev1
 */
public class JsonBuilder {

    public static Object toJson(String... ses) {
        if (ses == null || ses.length < 2) {
            return "\"\"";
        }
        String ps = "{\""
            + ses[0] + "\":"
            + (ses[1] != null && ses[1].startsWith("[")
            ? ses[1]
            : "\"" + ses[1] + "\"");
        for (int i = 3; i < ses.length; i += 2) {
            ps += ",\"" + ses[i - 1] + "\":" + (ses[i] != null && ses[i].startsWith("[") ? ses[i] : "\"" + ses[i] + "\"");
        }
        ps += "}";
        return ps;
    }

    public static Object toJsonArray(String name, String[] val) {
        if (val == null || val.length < 1) {
            return "{\"" + name + "\":[]}";
        }
        String ps = "{\"" + name + "\":[\"" + val[0] + "\"";
        for (int i = 1; i < val.length; i++) {
            ps += ",\"" + val[i] + "\"";
        }
        ps += "]}";
        return ps;
    }

    public static Object toJsonArray(String[] val) {
        if (val == null || val.length < 1) {
            return "[]";
        }
        String ps = "[\"" + val[0] + "\"";
        for (int i = 1; i < val.length; i++) {
            ps += ",\"" + val[i] + "\"";
        }
        ps += "]";
        return ps;
    }

    public static Object toJsonArray(String name, int[] val) {
        if (val == null || val.length < 1) {
            return "{\"" + name + "\":[]}";
        }
        String ps = "{\"" + name + "\":[" + val[0];
        for (int i = 1; i < val.length; i++) {
            ps += "," + val[i] + "";
        }
        ps += "]}";
        return ps;
    }

    public static Object toJsonArray(int[] val) {
        if (val == null || val.length < 1) {
            return "[]";
        }
        String ps = "[" + val[0];
        for (int i = 1; i < val.length; i++) {
            ps += "," + val[i] + "";
        }
        ps += "]";
        return ps;
    }

    public static Object toJsonArray(String name, double[] val) {
        if (val == null || val.length < 1) {
            return "{\"" + name + "\":[]}";
        }
        String ps = "{\"" + name + "\":[" + val[0];
        for (int i = 1; i < val.length; i++) {
            ps += "," + val[i] + "";
        }
        ps += "]}";
        return ps;
    }

    public static Object toJsonArray(double[] val) {
        if (val == null || val.length < 1) {
            return "[]";
        }
        String ps = "[" + val[0];
        for (int i = 1; i < val.length; i++) {
            ps += "," + val[i] + "";
        }
        ps += "]";
        return ps;
    }

    public static String addToArray(String srcJson, String newItem) {

        if (srcJson == null || srcJson.isEmpty()) {
            srcJson = "[" + newItem + "]";
        } else if (!srcJson.startsWith("[") && !srcJson.endsWith("]")) {
            OutLog.pl(srcJson);
            return null;
        } else {
            srcJson = srcJson.substring(0, srcJson.length() - 1) + "," + newItem + "]";
        }
        return srcJson;
    }


}

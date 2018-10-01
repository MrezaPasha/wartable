/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.utils;

/**
 * @author dev1
 * @apiNote to createJsps Restrictions Paths
 */
public class RePa {

    public static String p__(String item) {
        return item;
    }

    public static String p__(String... items) {
        if (items == null || items.length == 0) {
            return "";
        }
        String s = items[0];
        for (int i = 1; i < items.length; i++) {
            s += "." + items[i];
        }
        return s;
    }
}

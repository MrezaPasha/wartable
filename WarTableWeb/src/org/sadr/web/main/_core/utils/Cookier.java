/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main._core.utils;

import org.sadr._core.utils._type.TtCookierVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dev1
 */
public class Cookier {

    //=========================================  getValue
    public static String getValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            int i = 0;
            while (i < cookies.length) {
                if (cookies[i].getName().equals(key)) {
                    return cookies[i].getValue();
                }
                i++;
            }
        }
        return null;
    }

    public static String getValue(HttpServletRequest request, TtCookierVariable variable) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            int i = 0;
            while (i < cookies.length) {
                if (cookies[i].getName().equals(variable.getKey())) {
                    return cookies[i].getValue();
                }
                i++;
            }
        }
        return null;
    }

    public static void setCookie(HttpServletResponse response, TtCookierVariable variable, String value) {
        Cookie c = new Cookie(variable.getKey(), value);
        c.setMaxAge(variable.getAge());
        c.setPath(variable.getPath());
        response.addCookie(c);
    }

    public static void setCookie(HttpServletResponse response, String key, String value) {
        Cookie c = new Cookie(key, value);
        c.setMaxAge(10 * 24 * 60 * 60);
        c.setPath("/");
        response.addCookie(c);
    }

    //=========================================  deleteCookie
    public static void deleteCookie(HttpServletResponse response, String key) {
        Cookie c = new Cookie(key, null);
        c.setMaxAge(0);
        c.setPath("/");
        response.addCookie(c);
    }

    public static void deleteCookie(HttpServletResponse response, TtCookierVariable var) {
        Cookie c = new Cookie(var.getKey(), null);
        c.setMaxAge(0);
        c.setPath("/");
        response.addCookie(c);
    }
}

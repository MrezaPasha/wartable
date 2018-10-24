/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.utils;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * @author dev2
 */
public class SpringMessager {

    private static ReloadableResourceBundleMessageSource messageSource;

    public static void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
        SpringMessager.messageSource = messageSource;
    }

    public static String get(String code) {
        try {
            return messageSource.getMessage(
                code,
                null,
                LocaleContextHolder.getLocale()
            );
        } catch (Exception e) {
            OutLog.pl(e.toString());
            return "!" + code + "!";
        }
    }

    public static String get(String code, Object... params) {
        try {
            return messageSource.getMessage(
                code,
                params,
                LocaleContextHolder.getLocale()
            );
        } catch (Exception e) {
            OutLog.pl(e.toString());
            return "!" + code + "!";
        }
    }

    public static String get(String code, Locale locale,Object... params) {
        try {
            return messageSource.getMessage(
                code,
                params,
                locale
            );
        } catch (Exception e) {
            OutLog.pl(e.toString());
            return "_" + code + "_";
        }
    }
}

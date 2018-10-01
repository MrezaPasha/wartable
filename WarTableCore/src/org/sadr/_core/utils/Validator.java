package org.sadr._core.utils;


import java.util.regex.Pattern;

/**
 * @author dev1
 */
public class Validator {

    private static final Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private static final Pattern imagePattern = Pattern.compile("(^.*(\\.(?i)(jpg|jpeg|png|gif|bmp|tiff|tif|svg|psd|tga))$)");
    private static final Pattern pdfPattern = Pattern.compile("([^\\s]+(\\.(?i)(pdf))$)");
    private static final Pattern videoPattern = Pattern.compile("([^\\s]+(\\.(?i)(mp4|mpg|mpeg|mpv|mp2|mpe|3gp|3g2|swf|mov|wmv|mkv|flv|vob|ogg|ogv|avi|webm|rm|rmvb|svi|m4v|m2v))$)");
    private static final Pattern textPattern = Pattern.compile("([^\\s]+(\\.(?i)(txt|doc|docx|log|msg|odt|pages|rtf|tex|wpd|wps))$)");

    private Validator() {
    }

    public static boolean passwordMin(final String value, boolean required) {
        if (!required && (value == null || value.isEmpty())) {
            return true;
        }
        return value != null && value.length() > 3;
    }


    public static boolean email(final String value, boolean required) {
        if (!required && (value == null || value.isEmpty())) {
            return true;
        }
        return emailPattern.matcher(value).matches();
    }

    public static boolean mobile(final String value, boolean required) {
        if (!required && (value == null || value.isEmpty())) {
            return true;
        }
        return value.matches("^[0][9][0-9]{9}$") || value.matches("^[۰][۹][۰-۹]{9}$");
    }

    public static boolean image(String fileName) {
        return imagePattern.matcher(fileName).matches();
    }


    public static boolean pdf(String fileName) {
        return pdfPattern.matcher(fileName).matches();
    }


    public static boolean video(String fileName) {
        return videoPattern.matcher(fileName).matches();
    }


    public static boolean text(String fileName) {
        return textPattern.matcher(fileName).matches();
    }


    //////  PERSIAN DATE TIME

    public static boolean persianDateTime(String value) {
        if (
            value.matches("^([1][2-4][0-9]{2})/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])\\s(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])$")
                || value.matches("^([۱][۲-۴][۰-۹]{2})/(۰[۱-۹]|۱[۰-۲])/(۰[۱-۹]|[۱۲][۰-۹]|۳[۰۱])\\s(۲[۰-۳]|[۰۱][۰-۹]):([۰-۵][۰-۹]):([۰-۵][۰-۹])$")) {
            return true;
        }
        return false;
    }

}

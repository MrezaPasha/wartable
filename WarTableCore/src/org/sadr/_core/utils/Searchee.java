package org.sadr._core.utils;

import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.generic.GB;
import org.springframework.ui.Model;

public class Searchee {

    private static Searchee instance = new Searchee();

    public static Searchee getInstance() {
        return instance;
    }

    private Searchee() {
    }

    private String getRepath(String[] fields) {
        /////// rebuild path
        boolean isSplit = false;
        String rePath = "";
        for (String f : fields) {
            if ("#".equals(f)) {
                isSplit = true;
                break;
            }
        }
        if (isSplit) {
            String fStr = "";
            for (String s : fields) {
                fStr += "," + s;
            }
            fStr = fStr.substring(1);
            String[] fls = fStr.split(",#,");
            for (String f : fls) {
                rePath += "#" + GB.rePath(f.split(","));
            }
            rePath = rePath.substring(1);
        } else {
            rePath = GB.rePath(fields);
        }
        return rePath;
    }

    public void setAttribute(Model model, String name, TtDataType dataType, TtRestrictionOperator operator, boolean ignoreSpaces, String... fields) {

        model.addAttribute(name,
                "[\"" +
                        getRepath(fields) + "\",\"" +
                        dataType.getBrifKey() + "\",\"" +
                        operator.getToken() + "\"," +
                        ignoreSpaces + "]"

        );
    }

    public void setAttributeArray(Model model, String name, TtDataType dataType, TtRestrictionOperator operator, boolean ignoreSpaces, String... fields) {
        model.addAttribute(name,
                new String[]{
                        getRepath(fields),
                        dataType.getBrifKey(),
                        operator.getToken(),
                        "" + ignoreSpaces
                }

        );
    }

}

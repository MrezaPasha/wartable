package org.sadr._core.utils;

import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtIxporterModeSection;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core._type.TtSecurityTag;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.utils._type.TtSearcheeStrategy;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class Searchee {

//    private static Searchee instance = new Searchee(null, null);

//    public static Searchee getInstance() {
//        return instance;
//    }

    private Searchee(Class<?> clazz, Model model) {
        this.clazz = clazz;
        this.model = model;
        this.
            model.
            addAttribute("securityTags",
                TtSecurityTag.values());
    }

    Class<?> clazz;
    Model model;

    public static Searchee init(Class<?> clazz, Model model) {
        return new Searchee(clazz, model);
    }

    public static String field(String name) {
        return name;
    }

    public static String field(String name, Class<?> clazz) {
        return clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1) + "." + name;
    }

    /////
    private String getRepath(String[] fields) {
        /////// rebuild path
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].contains(".")) {
                fields[i] = fields[i].substring(fields[i].indexOf(".") + 1);
            }
        }

        return GB.rePath(fields);
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

    public Searchee setAttribute(TtDataType dataType, TtRestrictionOperator operator, TtSearcheeStrategy strategy, String... fields) {
        return setAttribute(dataType, operator, strategy, null, fields);
    }

    public Searchee setAttribute(TtDataType dataType, TtRestrictionOperator operator, TtSearcheeStrategy strategy, Object value, String... fields) {
        List<String[]> map;
        if (model.containsAttribute("searchInputs")) {
            map = (List<String[]>) model.asMap().get("searchInputs");
        } else {
            map = new ArrayList<>();
        }
        String name = "";
        String key = "";
        int i = 0;
        while (i < fields.length) {
            name += " : " + SpringMessager.get(fields[i].contains(".") ?
                fields[i] :
                clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1) + "." + fields[i]
            );
            key += fields[i] + "_";
            i++;
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        key += CodeGenerator.code(3);
        switch (operator) {
            case GreaterEqual:
                name += " (از)";
                break;
            case LessEqual:
                name += " (تا)";
                break;
            case LessThan:
                name += " (تا)";
                break;
            case GreatThan:
                name += " (از)";
                break;
        }
        map.add(
            new String[]{
                key,
                name.substring(3),
                "[\"" +
                    getRepath(fields) + "\",\"" +
                    dataType.getBrifKey() + "\",\"" +
                    operator.getToken() + "\"," +
                    (strategy == TtSearcheeStrategy.IgnoreWhiteSpaces) + "]",
                dataType.getBrifKey(),
                "" + (strategy == TtSearcheeStrategy.HiddenAutoFill),  // boolean for hidden field
                value != null ? value.toString() : "",  // default value of field
                "" + (strategy == TtSearcheeStrategy.HiddenAutoFill)  // set this field to search query on loading of form
            }

        );
        model.addAttribute("searchInputs", map);
        return this;
    }


}

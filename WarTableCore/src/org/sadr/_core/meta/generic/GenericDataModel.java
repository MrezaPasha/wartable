package org.sadr._core.meta.generic;

import org.sadr._core._type.*;
import org.sadr._core.meta.annotation.ExporterMode;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.utils.ParsCalendar;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * @param <T>
 * @author masoud
 * @author masoud
 */
@MappedSuperclass
public class GenericDataModel<T extends Serializable> implements Serializable {

    public static final String ID = "id";
    public static final String CREATE_DATE_TIME = "createDateTime";
    public static final String MODIFY_DATE_TIME = "modifyDateTime";
    public static final String ENTITY_STATE = "entityState";
    public static final String $CREATE_DATE_S_M = "createDateSM";
    public static final String $CREATE_DATE_TIME_S_M = "createDateTimeSM";
    public static final String $MODIFY_DATE_TIME_S_M = "modifyDateTimeSM";
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
//    @SequenceGenerator(name="person_generator", sequenceName = "")
    @PersianName("شناسه")
    private long id;

    @Size(max = 30)
    @PersianName("زمان ثبت")
    private String createDateTime;

    @Size(max = 30)
    @PersianName("زمان آخرین ویرایش")
    private String modifyDateTime;

    @ExporterMode(TtIxporterModeSection.NoInExcel)
    @Column(nullable = false)
    @PersianName("وضعیت موجودیت")
    private TtEntityState entityState;

    //    /**
//     * null and empty > all, -1 > none, id tails > allowed users
//     */
//    @Size(max = 255)
//    @PersianName("کاربران مجاز")
//    private String allowedUserIds;
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
    public TtEntityState getEntityState() {
        return entityState;
    }

    public void setEntityState(TtEntityState entityState) {
        this.entityState = entityState;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getCreateDate() {
        try {
            return createDateTime != null && !createDateTime.isEmpty() ? createDateTime.split(" ")[0] : createDateTime;
        } catch (Exception e) {
            return createDateTime;
        }
    }

    public String getCreateDateTimeSM() {
        return ParsCalendar.getInstance().getDateTimeInMonthString(createDateTime);
    }

    public String getCreateDateSM() {
        return ParsCalendar.getInstance().getDateInMonthString(createDateTime);
    }

    public String getModifyDateTime() {
        return modifyDateTime;
    }

    public void setModifyDateTime(String modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }

    public String getModifyDateTimeSM() {
        return ParsCalendar.getInstance().getDateTimeInMonthString(modifyDateTime);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id == null ? 0 : id;
    }

    public long getIdi() {
        return id;
    }

    private String[] getAvrFields() {
        String[] acFs, viFs, reFs, fields;
        int l = 0;
//        OutLog.p();
        try {
            acFs = (String[]) getClass().getMethod("getActColumns").invoke(this);
            viFs = (String[]) getClass().getMethod("getVirColumns").invoke(this);
            reFs = (String[]) getClass().getMethod("getRelColumns").invoke(this);
//            OutLog.p(Arrays.toString(acFs));
//            OutLog.p(Arrays.toString(viFs));
//            OutLog.p(Arrays.toString(reFs));
            if (acFs != null) {
                l = acFs.length;
            }

            if (viFs != null) {
                l += viFs.length;
            }

            if (reFs != null) {
                l += reFs.length;
            }

            fields = new String[l];
            l = 0;
            if (acFs != null) {
                for (int i = 0; i < acFs.length; l++, i++) {
                    fields[l] = acFs[i];
                }
            }

            if (viFs != null) {
                for (int i = 0; i < viFs.length; l++, i++) {
                    fields[l] = viFs[i];
                }
            }

            if (reFs != null) {
                for (int i = 0; i < reFs.length; l++, i++) {
                    fields[l] = reFs[i];
                }
            }
//            OutLog.p(Arrays.toString(fields));
            return fields;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            return null;
        }
    }


    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public String getExportTitle() {
        return "" + id;
    }

    private String getJson(TtToJsonNullStrategy nullStrategy, TtToJsonTtStrategy ttStrategy, TtToJsonSubStrategy subStrategy, GB gb, JB jb, String[] fields) {
        StringBuilder json = new StringBuilder("{");

        if (fields != null && fields.length > 0) {
            if (id > 0) {
                json.append("\"id\":").append(id).append(",");
            }
        } else if (gb != null) {
            fields = gb.getColumnsAndGbs();
            if (id > 0) {

                json.append("\"id\":").append(id).append(",");
            }
        } else if (jb != null) {
            fields = jb.getFieldsAndJbs();
            if (id > 0) {
                json.append("\"id\":").append(id).append(",");
            }
        } else {
            fields = getAvrFields();
        }
        if (fields == null) {
            return "{}";
        }

        Object o;
        Class clazz = getClass();

        for (String f : fields) {
            if (f == null) {
                continue;
            }
            try {
                o = clazz.getMethod("get" + f.substring(0, 1).toUpperCase() + f.substring(1)).invoke(this);

                //==============  null objects
                if (o == null) {
                    if (nullStrategy == TtToJsonNullStrategy.IncludNulls) {
                        json.append("\"").append(f).append("\":\"null\",\n");
                    }
                }
                //==============  Integer, Double, Float, Boolean   OR   SecretNote
                else if (o instanceof Integer || o instanceof Double || o instanceof Float || o instanceof Boolean || "secretNote".equals(f)) {
                    json.append("\"").append(f).append("\":").append(o.toString()).append(",\n");
                }
                //==============  Tt Types
                else if (o.getClass().getSimpleName().startsWith("Tt")) {
                    switch (ttStrategy) {
                        case TitleMode:
                            json.append("\"").append(f).append("\":\"");
                            try {
                                json.append(o.getClass().getMethod("getTitle").invoke(o)).append("\",\n");
                            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                json.append(o.toString()).append("\",\n");
                            }
                            break;
                        case OrdinalMode:
                            json.append("\"").append(f).append("\":\"");
                            try {
                                json.append(o.getClass().getMethod("ordinal").invoke(o)).append("\",\n");
                            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                json.append(o.toString()).append("\",\n");
                            }
                            break;
                        case TitleThenKeyMode:
                            json.append("\"").append(f).append("\":\"");
                            try {
                                json.append(o.getClass().getMethod("getTitle").invoke(o)).append("\",\n");
                            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                try {
                                    json.append(o.getClass().getMethod("getKey").invoke(o)).append("\",\n");
                                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                                    json.append(o.toString()).append("\",\n");
                                }
                            }
                            break;
                        default:
                            json.append("\"").append(f).append("\":\"").append(o.toString()).append("\",\n");
                            break;
                    }
                }
                //==============  SubClasses Relations
                else if (o.getClass().getModifiers() == 1 && subStrategy == TtToJsonSubStrategy.IncludSubs) {
                    if (gb != null) {
                        json.append("\"").append(f).append("\":");
                        try {
                            json.append(o.getClass().getMethod("toJson", TtToJsonNullStrategy.class, TtToJsonTtStrategy.class, TtToJsonSubStrategy.class, GB.class).invoke(o, nullStrategy, ttStrategy, subStrategy, gb.getByClassAndName(o.getClass(), f)).toString())
                                .append(",\n");
                        } catch (Exception e) {
                            json.append("\"EXP_G\",\n");
                        }
                    } else if (jb != null) {
                        json.append("\"").append(f).append("\":");
                        try {
                            json.append(o.getClass().getMethod("toJson", TtToJsonNullStrategy.class, TtToJsonTtStrategy.class, TtToJsonSubStrategy.class, JB.class).invoke(o, nullStrategy, ttStrategy, subStrategy, jb.getByClassAndName(o.getClass(), f)).toString())
                                .append(",\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                            json.append("\"EXP_J\",\n");
                        }
                    }
                }

                //==============  Others (String)
                else {
                    json.append("\"").append(f).append("\":\"").append(o.toString().replaceAll("\t|\"", "")).append("\",\n");
                }
            } catch (NoSuchMethodException ex) {
                try {
                    if (clazz.getDeclaredField(f).getType().equals(boolean.class)) {
                        o = clazz.getMethod("is" + f.substring(0, 1).toUpperCase() + f.substring(1)).invoke(this);
                        json.append("\"").append(f).append("\":\"").append(o.toString()).append("\",\n");
                    }
                } catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex1) {
                    json.append("\"").append(f).append("\":\"").append("EXP_b").append("\",\n");
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                json.append("\"").append(f).append("\":\"").append("EXP").append("\",\n");
            }
        }

        if (json.length() > 2) {
            json = json.replace(json.length() - 2, json.length(), "}");
        } else {
            json.append("}");
        }
        return json.toString();
    }

    public String toJson() {
        return getJson(TtToJsonNullStrategy.IgnoreNulls, TtToJsonTtStrategy.TitleMode, TtToJsonSubStrategy.IncludSubs, null, null, (String[]) null);
    }

    ///////////////////////////  Fields
    public String toJson(String... fields) {
        return getJson(TtToJsonNullStrategy.IgnoreNulls, TtToJsonTtStrategy.TitleMode, TtToJsonSubStrategy.IncludSubs, null, null, fields);
    }

    public String toJson(TtToJsonNullStrategy nullStrategy, TtToJsonTtStrategy ttStrategy, TtToJsonSubStrategy subStrategy, String... fields) {
        return getJson(nullStrategy, ttStrategy, subStrategy, null, null, fields);
    }

    ///////////////////////////  JBs
    public String toJson(JB jb) {
        return getJson(TtToJsonNullStrategy.IgnoreNulls, TtToJsonTtStrategy.TitleMode, TtToJsonSubStrategy.IncludSubs, null, jb, null);
    }

    public String toJson(TtToJsonNullStrategy nullStrategy, TtToJsonTtStrategy ttStrategy, TtToJsonSubStrategy subStrategy, JB jb) {
        return getJson(nullStrategy, ttStrategy, subStrategy, null, jb, null);
    }

    //////////////////////////// GBs
    public String toJson(GB gb) {
        return getJson(TtToJsonNullStrategy.IgnoreNulls, TtToJsonTtStrategy.TitleMode, TtToJsonSubStrategy.IncludSubs, gb, null, null);
    }

    public String toJson(TtToJsonNullStrategy nullStrategy, TtToJsonTtStrategy ttStrategy, TtToJsonSubStrategy subStrategy, GB gb) {
        return getJson(nullStrategy, ttStrategy, subStrategy, gb, null, null);
    }

    ///////////////////////////////////////////////
//    @Transient
//    private final Class<T> clazz;
//    private final String ss;
//
//    public GenericDataModel() {
//        Type t = getClass().getGenericSuperclass();
//        ParameterizedType pt = (ParameterizedType) t;
//        clazz = (Class) pt.getActualTypeArguments()[0];
//        OutLog.pl(clazz.getSimpleName());
//        ss = clazz.getName();
//    }
//    public String toJson() {
//        return new JSONSerializer()
//                .exclude("*.class")
//                .serialize(clazz);
//    }
}

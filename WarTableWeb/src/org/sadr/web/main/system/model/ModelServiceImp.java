package org.sadr.web.main.system.model;

import org.hibernate.annotations.Type;
import org.hibernate.criterion.Restrictions;
import org.hibernate.validator.constraints.NotEmpty;
import org.sadr._core._type.TtDataType;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main.system._type.*;
import org.sadr.web.main.system.field.Field;
import org.sadr.web.main.system.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author masoud
 */
@Service
//@Component
public class ModelServiceImp extends GenericServiceImpl<Model, ModelDao> implements ModelService {

    private FieldService fieldService;

    @Autowired
    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    //===================================================
    private java.lang.reflect.Field[] getModelFields(Class<?> ac) {
        java.lang.reflect.Field[] scFields,
            cFields, modelFields;
        int scSize, cSize, fIndex;

        scFields = ac.getSuperclass().getDeclaredFields();
        scSize = scFields.length;
        for (java.lang.reflect.Field df : scFields) {
            if (scSize > 0 && ("clazz".equals(df.getName()) || java.lang.reflect.Modifier.isStatic(df.getModifiers()))) {
                scSize--;
            }
        }
        cFields = ac.getDeclaredFields();
        cSize = cFields.length;
        for (java.lang.reflect.Field df : cFields) {
            if (cSize > 0 && ("clazz".equals(df.getName()) || java.lang.reflect.Modifier.isStatic(df.getModifiers()))) {
                OutLog.pl(df.getName() + "   " + java.lang.reflect.Modifier.isStatic(df.getModifiers()));
                cSize--;
            }
        }

        modelFields = new java.lang.reflect.Field[scSize + cSize];
        fIndex = 0;
        for (java.lang.reflect.Field df : scFields) {
            if ("clazz".equals(df.getName()) || java.lang.reflect.Modifier.isStatic(df.getModifiers())) {
                continue;
            }
            modelFields[fIndex++] = df;
        }
        for (java.lang.reflect.Field df : cFields) {
            if ("clazz".equals(df.getName()) || java.lang.reflect.Modifier.isStatic(df.getModifiers())) {
                continue;
            }
            modelFields[fIndex++] = df;
        }
        return modelFields;
    }

    private void fillModelField(java.lang.reflect.Field mf, Field f, Model mo) {
        if (mf.isAnnotationPresent(OneToOne.class)) {
            f.setMoDataRelation(TtDataRelation.OneToOne);
            f.setMoDataRelationDes(mf.getAnnotation(OneToOne.class).mappedBy());
            if (f.getMoDataRelationDes() == null || f.getMoDataRelationDes().isEmpty()) {
                f.setDbTitle(mf.getName() + "_id");
                f.setIsBidirectional(true);
            } else {
                f.setDbTitle(null);
                f.setIsBidirectional(false);
            }
        } else if (mf.isAnnotationPresent(OneToMany.class)) {
            f.setMoDataRelation(TtDataRelation.OneToMany);
            f.setMoDataRelationDes(mf.getAnnotation(OneToMany.class).mappedBy());
            f.setIsBidirectional(false);
            f.setDbTitle(null);
        } else if (mf.isAnnotationPresent(ManyToOne.class)) {
            f.setMoDataRelation(TtDataRelation.ManyToOne);
            f.setDbTitle(mf.getName() + "_id");
            f.setIsBidirectional(true);
        } else if (mf.isAnnotationPresent(ManyToMany.class)) {
            f.setMoDataRelation(TtDataRelation.ManyToMany);
            f.setMoDataRelationDes(mf.getAnnotation(ManyToMany.class).mappedBy());
            f.setDbTitle(null);
            f.setIsBidirectional(false);
        } else {
            f.setDbTitle(mf.getName());
            f.setIsBidirectional(true);
        }

        if (mf.isAnnotationPresent(Type.class)) {
            f.setIsEncrypted("encryptedString".equals(mf.getAnnotation(Type.class).type()));
        } else {
            f.setIsEncrypted(false);
        }
        f.setMoMinSize(0);
        f.setMoMaxSize(0);
        if (mf.isAnnotationPresent(Type.class) && "text".equals(mf.getAnnotation(Type.class).type())) {
            f.setMoType(TtDataType.Text.getModelType());
        } else {
            f.setMoType(mf.getGenericType().getTypeName());
            if (f.getMoDataTypeTt() == TtDataType.String) {
                if (mf.isAnnotationPresent(Size.class)) {
                    f.setMoMinSize(mf.getAnnotation(Size.class).min());
                    f.setMoMaxSize(mf.getAnnotation(Size.class).max());
                    if (f.getMoMaxSize() > 255) {
                        f.setMoType(TtDataType.String_LT.getModelType());
                    }
                } else {
                    f.setMoMinSize(0);
                    f.setMoMaxSize(255);
                }
            }
        }
        TtDataType mtdt = f.getMoDataTypeTt();

        f.setMoTitle(mf.getName());
        f.setIsMoNullable(!(mf.isAnnotationPresent(NotNull.class)
            || mf.isAnnotationPresent(NotEmpty.class)
            || mf.isAnnotationPresent(Id.class)
            || (mf.isAnnotationPresent(Column.class) && !mf.getAnnotation(Column.class).nullable())
            || (mtdt != null && mtdt.isPrimaryType())));
        f.setMoDefaultValue("");
        f.setMoExtra("");
        f.setMoKey("");
        f.setMoModifier(mtdt != null ? mtdt.getModifiers() : 0);
        f.setIsMoRefreshed(true);
        f.setIsDbRefreshed(false);
        f.setModel(mo);

        f.setDbExtra("");
        f.setDbKey("");
        f.setDbSize(0);
        f.setDbType("");
    }

    private void fillDbFiled(ResultSet rs, ResultSet rsMeta, ResultSet rsDelta, Field f, Model mo) throws SQLException {
        f.setDbTitle(rs.getString("field"));
        String tps = rs.getString("type");
        if (tps != null && tps.contains("varchar")) {
            f.setDbType(TtDataType.String.getSqlType());
            try {
                f.setDbSize(Integer.parseInt(tps.substring(
                    tps.indexOf("(") + 1,
                    tps.indexOf(")")
                )));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            f.setDbType(tps);
        }
        f.setIsDbNullable("YES".equals(rs.getString("null")));
        f.setDbDefaultValue(rs.getString("default"));
        f.setDbExtra(rs.getString("extra"));
        f.setDbKey(rs.getString("key"));
        f.setIsDbRefreshed(true);
        f.setModel(mo);

        f.setIsDbPrimary(false);
        f.setDbConstraint("");
        f.setDbRefColumn("");
        f.setDbRefTable("");
        f.setDbIndex("");

        rsMeta.first();
        while (rsMeta.next()) {
            if (f.getDbTitle() != null && f.getDbTitle().equals(rsMeta.getString("COLUMN_NAME"))) {
                String cn = rsMeta.getString("CONSTRAINT_NAME");
                if (cn != null && cn.equals("PRIMARY")) {
                    f.setIsDbPrimary(true);
                    f.setDbConstraint(cn);
                } else if (cn != null && cn.startsWith("FK")) {
                    f.setIsDbFK(true);
                    f.setDbConstraint(cn);
                    f.setDbRefColumn(rsMeta.getString("REFERENCED_COLUMN_NAME"));
                    f.setDbRefTable(rsMeta.getString("REFERENCED_TABLE_NAME"));
                }
                break;
            }
        }
        rsDelta.first();
        while (rsDelta.next()) {
            if (f.getDbTitle() != null && f.getDbTitle().equals(rsDelta.getString("Column_name"))) {
                f.setDbIndex(rsDelta.getString("Key_name"));
                break;
            }
        }
    }

    private TtModelAdaptResult preparingAdaption(Model mo) {
        if (mo == null) {
            OutLog.pl("");
            return TtModelAdaptResult.ModelNotFound;
        }
        OutLog.pl(mo.getTableName());
        ///--------------------------------------- PREPARING
        mo.setIsRefreshed(false);
        mo.setStatus(TtModelStatus.Unknown);
        this.update(mo);

        List<Field> fields = this.fieldService.findAllBy(Restrictions.eq("model", mo));
        for (Field f : fields) {
            f.setIsMoRefreshed(false);
            f.setIsDbRefreshed(false);
            f.setStatus(TtFieldStatus.Unknown);
            this.fieldService.update(f);
        }

        //----------------------------------------
        List<Class<?>> annotatedClasses = WebConfigHandler.getModelClassesRest();
        String clan = null;
        for (Class<?> ac : annotatedClasses) {
            if (ac.isAnnotationPresent(Table.class)) {
                if (mo.getTableName().equals(ac.getAnnotation(Table.class).name())) {
                    clan = ac.getName();
                }
            }
        }

        if (clan == null) {
            return TtModelAdaptResult.ModelDeleted;
        }

        Class<?> clazz;
        try {
            clazz = Class.forName(clan);
            OutLog.pl("" + clazz.getName());
            String[] dp = WebConfigHandler.getDatabaseParamRest();
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection(
                dp[0],
                dp[1],
                dp[2])) {
                OutLog.pl("--");

                doAdaption(mo, clazz, con);
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelServiceImp.class.getName()).log(Level.SEVERE, null, ex);
                return TtModelAdaptResult.ModelNotFound;

            }
            return TtModelAdaptResult.Success;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            return TtModelAdaptResult.ModelNotFound;
        }

    }

    private void doAdaption(Model mo, Class<?> ac, Connection con) throws SQLException {
        Statement stmt, stmtDelta, stmtMeta;
        stmt = con.createStatement();
        stmtDelta = con.createStatement();
        stmtMeta = con.createStatement();
        ResultSet rs, rsMeta, rsDelta;

        int ix, dbFieldsSize;
        List<Field> dbfields;
        java.lang.reflect.Field[] modelFields;

        dbfields = this.fieldService.findAllBy(Restrictions.eq("model", mo));
        dbFieldsSize = dbfields.size();

        modelFields = getModelFields(ac);

        for (java.lang.reflect.Field mf : modelFields) {
            ix = -1;
            OutLog.p(mf.getName());

            for (int j = 0; j < dbFieldsSize; j++) {
                if (dbfields.get(j).getMoTitle() != null && dbfields.get(j).getMoTitle().equals(mf.getName())) {
                    ix = j;
                    break;
                }
            }
            if (ix == -1) {
                Field f = new Field();
                fillModelField(mf, f, mo);
                this.fieldService.save(f);

            } else {
                Field df = dbfields.get(ix);
                fillModelField(mf, df, mo);
                this.fieldService.update(df);
            }
        }

        dbfields = this.fieldService.findAllBy(Restrictions.eq("model", mo));
        dbFieldsSize = dbfields.size();

        ////
        rs = stmt.executeQuery("SHOW COLUMNS FROM `" + mo.getTableName() + "`");
        rsDelta = stmtDelta.executeQuery("SHOW INDEX FROM `" + mo.getTableName() + "`");
        rsMeta = stmtMeta.executeQuery("SELECT COLUMN_NAME,CONSTRAINT_NAME,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME "
            + " FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE "
            + "WHERE TABLE_NAME='" + mo.getTableName() + "' AND table_schema='" + WebConfigHandler.getDatabaseParamRest()[3] + "';");

        OutLog.pl("SELECT COLUMN_NAME,CONSTRAINT_NAME,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME "
            + " FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE "
            + "WHERE TABLE_NAME='" + mo.getTableName() + "' AND table_schema='" + WebConfigHandler.getDatabaseParamRest()[3] + "';");

        while (rs.next()) {
            OutLog.p(rs.getString("field"));

            ix = -1;
            for (int j = 0; j < dbFieldsSize; j++) {
                if (dbfields.get(j) != null && dbfields.get(j).getDbTitle() != null && dbfields.get(j).getDbTitle().equals(rs.getString("field"))) {
                    ix = j;
                    break;
                }
            }
            if (ix == -1) {
                OutLog.pl("");
                for (int j = 0; j < dbFieldsSize; j++) {
                    if (dbfields.get(j) != null) {
                        OutLog.pl(dbfields.get(j).getMoPreviousTitle() + "    " + rs.getString("field"));
                    }
                    if (dbfields.get(j) != null
                        && dbfields.get(j).getMoPreviousTitle() != null
                        && dbfields.get(j).getMoPreviousTitle().equals(rs.getString("field"))) {
                        OutLog.pl("");
                        ix = j;
                        break;
                    }
                }
            }
            if (ix == -1) {
                Field f = new Field();
                fillDbFiled(rs, rsMeta, rsDelta, f, mo);
                f.setIsMoRefreshed(false);
                f.setIsBidirectional(true);
                this.fieldService.save(f);
            } else {
                Field df = dbfields.get(ix);
                fillDbFiled(rs, rsMeta, rsDelta, df, mo);
                this.fieldService.update(df);
                dbfields.set(ix, null);
            }
        }

        dbfields = this.fieldService.findAllBy(Restrictions.eq("model", mo));
        dbFieldsSize = dbfields.size();

        boolean isFieldChanged = false;
        for (Field f : dbfields) {
            if (f.verifyStatus()) {
                isFieldChanged = true;
            }
            this.fieldService.update(f);
        }

        Field rf, nf;
        for (int i = 0; i < dbFieldsSize; i++) {
            rf = dbfields.get(i);
            if (rf != null && rf.getIsRemoveFromModelAndDB()) {
                this.fieldService.delete(rf.getId());
                dbfields.set(i, null);
            }
//            else if (rf != null && rf.getIsRemoveFromModel()) {
//                for (int j = 0; j < dbFieldsSize; j++) {
//                    nf = dbfields.get(j);
//                    if (nf != null && nf.getIsNew()) {
//                        OutLog.pl(rf.getDbTitle() + "   " + nf.getMoTitle());
//                        if (nf.isIndirectEqual(rf)) {
//                            OutLog.p("merged " + nf.getId());
////                            Field nf = this.fieldService.findById(nf.getId());
//                            nf.setDbDefaultValue(rf.getDbDefaultValue());
//                            nf.setDbExtra(rf.getDbExtra());
//                            nf.setDbKey(rf.getDbKey());
//                            nf.setDbSize(rf.getDbSize());
//                            nf.setDbTitle(rf.getDbTitle());
//                            nf.setDbType(rf.getDbType());
//                            nf.setIsDbNullable(rf.getIsDbNullable());
//                            nf.setStatus(TtFieldStatus.Changed);
//                            this.fieldService.update(nf);
//                            this.fieldService.delete(rf.getId());
//                            dbfields.set(i, null);
//                            break;
//                        }
//                    }
//                }
//            }
        }
        mo.setIsRefreshed(true);
        mo.verifyStatus(isFieldChanged);
        this.update(mo);

        rs.close();
        rsDelta.close();
        rsDelta.close();
        stmt.close();
    }

    private TtModelRebuildResult build(List<Model> models) {
        String sq = "", psq = "", empToNull = "";
        for (Model mo : models) {
            sq += "ALTER TABLE `" + mo.getTableName() + "` ";
            List<Field> fs = this.fieldService.findAllBy(Restrictions.eq("model", mo));
            if (fs == null) {
                return TtModelRebuildResult.ModelNotFound;
            }
            boolean isFirst = true;
            for (Field f : fs) {
                switch (f.getStatus()) {
                    case New:
                        if (isFirst) {
                            isFirst = false;
                        } else {
                            sq += ", ";
                        }
                        sq += "ADD COLUMN `" + f.getDbTitleFromMo() + "` "
                            + f.getDbTypeFromMo()
                            + (f.getMoDataTypeTt() == TtDataType.String ? " CHARACTER SET 'utf8' COLLATE 'utf8_persian_ci' " : " ")
                            + f.getDbNullFromMo();
                        break;
                    case Changed:
                        if (isFirst) {
                            isFirst = false;
                        } else {
                            sq += ", ";
                        }
                        f.getDbTypeFromMo();
                        sq += "CHANGE COLUMN `" + f.getDbTitle() + "` `"
                            + f.getDbTitleFromMo() + "` "
                            + f.getDbTypeFromMo()
                            + (f.getMoDataTypeTt() == TtDataType.String ? " CHARACTER SET 'utf8' COLLATE 'utf8_persian_ci' " : " ")
                            + f.getDbNullFromMo();
                        OutLog.pl(f.getMoType());
                        OutLog.pl(f.getDbType());
                        if (TtDataType.Integer_JL.getModelType().equals(f.getMoType())
                            && (TtDataType.String.getSqlType().equals(f.getDbType())
                            || TtDataType.String.getSqlType().equals(f.getDbType()))) {
                            empToNull += "UPDATE `" + mo.getTableName() + "` SET `" + f.getDbTitle() + "`=NULL WHERE `id`>'0' AND (`" + f.getDbTitle() + "` IS NULL OR `" + f.getDbTitle() + "` NOT REGEXP '[0-9]'); ";
                            OutLog.pl("change:  Integer_JL");
                        } else if (TtDataType.Integer.getModelType().equals(f.getMoType())
                            && (TtDataType.String.getSqlType().equals(f.getDbType())
                            || TtDataType.String.getSqlType().equals(f.getDbType()))) {
                            empToNull += "UPDATE `" + mo.getTableName() + "` SET `" + f.getDbTitle() + "`=0 WHERE `id`>'0' AND (`" + f.getDbTitle() + "` IS NULL OR `" + f.getDbTitle() + "` NOT REGEXP '[0-9]'); ";
                            OutLog.pl("change:  Integer");
                        }
                        break;
                    case RemoveFromModel:
                        if (isFirst) {
                            isFirst = false;
                        } else {
                            sq += ", ";
                        }
                        if (f.getIsDbFK()) {
                            if (f.getDbConstraint() != null && !f.getDbConstraint().isEmpty()) {
                                psq += "ALTER TABLE `" + mo.getTableName() + "` "
                                    + "DROP FOREIGN KEY `" + f.getDbConstraint() + "`;";

                            }
                            sq += "DROP COLUMN `" + f.getDbTitle() + "` ";
                            if (f.getDbIndex() != null && !f.getDbIndex().isEmpty()) {
                                sq += ", DROP INDEX `" + f.getDbIndex() + "` ";

                            }
                        } else {
                            sq += "DROP COLUMN `" + f.getDbTitle() + "` ";
                        }
                }
            }
            sq += ";";
        }

        String[] dp = WebConfigHandler.getDatabaseParamRest();
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection(
                dp[0],
                dp[1],
                dp[2])) {
                OutLog.pl("--");
                Statement stmt;
                stmt = con.createStatement();

                String[] qr = empToNull.split(";");
                for (String s : qr) {
                    OutLog.pl("empToNull : " + s);
                    if (s != null && !s.trim().isEmpty()) {
                        boolean execute = stmt.execute(s + ";");
                        OutLog.pl("PExe: " + execute);
                    }
                }

                qr = psq.split(";");
                for (String s : qr) {
                    OutLog.pl("psq : " + s);
                    if (s != null && !s.trim().isEmpty()) {
                        boolean execute = stmt.execute(s + ";");
                        OutLog.pl("PExe: " + execute);
                    }
                }
                qr = sq.split(";");
                for (String s : qr) {
                    OutLog.pl(s);
                    if (s != null && !s.trim().isEmpty()) {
                        boolean execute = stmt.execute(s + ";");
                        OutLog.pl("Exe: " + execute);
                    }
                }
                stmt.close();
                con.close();
                return TtModelRebuildResult.Success;

            } catch (SQLException ex) {
                Logger.getLogger(ModelServiceImp.class.getName()).log(Level.SEVERE, null, ex);
                return TtModelRebuildResult.ModelNotFound;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            return TtModelRebuildResult.ModelNotFound;
        }
    }

    //===================================================
    @Override
    public Model findByClassName(String className) {
        return dao.findByClassName(className);
    }

    @Override
    public Model findByPackageName(String packageName) {
        return dao.findByPackageName(packageName);
    }

    @Override
    public TtModelAdaptResult adapt(int id) {
        Model mo = this.findById(id);
        return preparingAdaption(mo);
    }

    @Override
    public TtModelAdaptResult adapt(String className) {
        Model mo = this.findBy(Restrictions.eq("className", className));
        if (mo == null) {
            List<Class<?>> annotatedClasses = WebConfigHandler.getModelClasses();
            for (Class<?> ac : annotatedClasses) {
                OutLog.pl("ac.getName() " + ac.getName());
                if (ac.getName().equals(className) && ac.isAnnotationPresent(Table.class)) {
                    mo = new Model();
                    mo.setClassName(ac.getName());
                    mo.setPackageName(ac.getPackage().getName());
                    mo.setMessageCode(ac.getSimpleName().substring(0, 1).toLowerCase() + ac.getSimpleName().substring(1));
                    mo.setIsRefreshed(true);
                    mo.setStatus(TtModelStatus.Unknown);
                    mo.setTableName(ac.getAnnotation(Table.class).name());
                    this.save(mo);
                    break;
                }
            }
        }
        return preparingAdaption(mo);
    }

    @Override
    public TtModelAdaptResult adapt(List<Model> models) {
        if (models != null) {
            for (Model m : models) {
                m.setIsRefreshed(false);
                m.setStatus(TtModelStatus.Unknown);
                this.update(m);
            }
        }
        List<Field> fields = this.fieldService.findAll();
        if (fields != null) {
            for (Field f : fields) {
                f.setIsMoRefreshed(false);
                f.setIsDbRefreshed(false);
                f.setStatus(TtFieldStatus.Unknown);
                this.fieldService.update(f);
            }
        }
        //----------------------------------------
        List<Class<?>> annotatedClasses = WebConfigHandler.getModelClassesRest();

        String[] dp = WebConfigHandler.getDatabaseParamRest();
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection(
                dp[0],
                dp[1],
                dp[2])) {
                OutLog.pl("connection created");

                int i = 0;
                String tableName;
                Model mo;

                for (Class<?> ac : annotatedClasses) {
                    OutLog.pl((++i) + " annotatedClasses " + ac.getSimpleName() + "  ");
                    if (!ac.isAnnotationPresent(Table.class)) {
                        continue;
                    }

                    tableName = ac.getAnnotation(Table.class).name();

                    mo = this.findBy(Restrictions.eq("tableName", tableName));

                    if (mo == null) {
                        mo = new Model();
                    }
                    mo.setClassName(ac.getName());
                    mo.setPackageName(ac.getPackage().getName());
                    mo.setMessageCode(ac.getSimpleName().substring(0, 1).toLowerCase() + ac.getSimpleName().substring(1));
                    mo.setIsRefreshed(true);
                    mo.setStatus(TtModelStatus.Unknown);
                    mo.setTableName(tableName);
                    if (mo.getIdi() == 0) {
                        this.save(mo);
                    } else {
                        this.update(mo);
                    }
                    doAdaption(mo, ac, con);
                }
                con.close();
                return TtModelAdaptResult.Success;

            } catch (SQLException ex) {
                Logger.getLogger(ModelServiceImp.class.getName()).log(Level.SEVERE, null, ex);
                return TtModelAdaptResult.ModelNotFound;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelServiceImp.class.getName()).log(Level.SEVERE, null, ex);

            return TtModelAdaptResult.ModelNotFound;
        }
    }

    @Override
    public TtModelRebuildResult rebuild(int id) {

        Model m = this.findById(id);
        if (m == null) {
            return TtModelRebuildResult.ModelNotFound;
        }
        if (!m.getIsChanged()) {
            return TtModelRebuildResult.NoThingToRebuild;
        }
        List<Model> ls = new ArrayList<>();
        ls.add(m);
        return build(ls);
    }

    @Override
    public TtModelRebuildResult rebuild(String className) {
        Model m = this.findBy(Restrictions.eq("className", className));
        if (m == null) {
            return TtModelRebuildResult.ModelNotFound;
        }
        if (!m.getIsChanged()) {
            return TtModelRebuildResult.NoThingToRebuild;
        }
        List<Model> ls = new ArrayList<>();
        ls.add(m);
        return build(ls);
    }

    @Override
    public TtModelRebuildResult rebuild(List<Model> models) {

        if (models == null || models.isEmpty()) {
            return TtModelRebuildResult.NoThingToRebuild;
        }

        return build(models);
    }

    @Override
    public TtModelRebuildResult adaptAndRebuild(String className) {
        Model mo = this.findBy(Restrictions.eq("className", className));
        OutLog.pl(className);
        if (mo == null) {
            List<Class<?>> annotatedClasses = WebConfigHandler.getModelClassesRest();
            for (Class<?> ac : annotatedClasses) {
                OutLog.pl("ac.getName() " + ac.getName());
                if (ac.getName().equals(className) && ac.isAnnotationPresent(Table.class)) {
                    mo = new Model();
                    mo.setClassName(ac.getName());
                    mo.setPackageName(ac.getPackage().getName());
                    mo.setMessageCode(ac.getSimpleName().substring(0, 1).toLowerCase() + ac.getSimpleName().substring(1));
                    mo.setIsRefreshed(true);
                    mo.setStatus(TtModelStatus.Unknown);
                    mo.setTableName(ac.getAnnotation(Table.class).name());
                    this.save(mo);
                    break;
                }
            }
        }
        if (preparingAdaption(mo) == TtModelAdaptResult.Success) {
            return rebuild(className);
        } else {
            return TtModelRebuildResult.ModelNotFound;
        }
    }

}

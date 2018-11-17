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

}

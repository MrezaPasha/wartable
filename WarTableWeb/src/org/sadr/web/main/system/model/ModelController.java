package org.sadr.web.main.system.model;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.utils.JsonBuilder;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.SuperAdminTask;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.system._type.TtModelAdaptResult;
import org.sadr.web.main.system._type.TtModelRebuildResult;
import org.sadr.web.main.system._type.TtModelStatus;
import org.sadr.web.main.system.field.Field;
import org.sadr.web.main.system.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import java.sql.SQLException;
import java.util.List;

/**
 * @author masoud
 * @version 1.96.01.02
 */
@PersianName("مدیریت مدل")
@RestController
public class ModelController extends GenericControllerImpl<Model, ModelService> {

}

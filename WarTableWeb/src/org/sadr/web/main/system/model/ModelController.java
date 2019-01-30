package org.sadr.web.main.system.model;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



/**
 * @author masoud
 * @version 1.96.01.02
 */
@PersianName("مدیریت مدل")
@RestController
public class ModelController extends GenericControllerImpl<Model, ModelService> {

}

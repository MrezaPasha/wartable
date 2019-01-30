package org.sadr.share.main.grade;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class GradeShareServiceImp extends GenericServiceImpl<Grade, GradeDao> implements GradeShareService {
}

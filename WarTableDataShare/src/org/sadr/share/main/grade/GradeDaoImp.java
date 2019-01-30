package org.sadr.share.main.grade;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class GradeDaoImp extends GenericDaoImpl<Grade> implements GradeDao{
}

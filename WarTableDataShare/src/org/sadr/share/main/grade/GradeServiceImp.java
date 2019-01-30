package org.sadr.share.main.grade;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class GradeServiceImp extends GenericServiceImpl<Grade,GradeDao> implements GradeService
{
}

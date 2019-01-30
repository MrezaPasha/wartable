package org.sadr.share.main.log.models.importance;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ImportanceServiceImp extends GenericServiceImpl<Importance,ImportanceDao> implements ImportanceService {
}

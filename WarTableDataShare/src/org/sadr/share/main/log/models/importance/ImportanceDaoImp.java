package org.sadr.share.main.log.models.importance;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class ImportanceDaoImp extends GenericDaoImpl<Importance> implements ImportanceDao {
}

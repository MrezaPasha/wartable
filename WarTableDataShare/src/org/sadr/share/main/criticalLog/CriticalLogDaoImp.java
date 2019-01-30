package org.sadr.share.main.criticalLog;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class CriticalLogDaoImp extends GenericDaoImpl<CriticalLog> implements CriticalLogDao {
}

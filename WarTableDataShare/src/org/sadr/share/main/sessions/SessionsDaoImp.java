package org.sadr.share.main.sessions;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class SessionsDaoImp extends GenericDaoImpl<Sessions> implements SessionsDao {
}

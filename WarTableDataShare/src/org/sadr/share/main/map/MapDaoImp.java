package org.sadr.share.main.map;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component
public class MapDaoImp extends GenericDaoImpl<Map> implements MapDao {
}

package org.sadr.share.main.layer;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class LayerDaoImp extends GenericDaoImpl<Layer> implements LayerDao {
}

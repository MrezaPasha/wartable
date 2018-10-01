package org.sadr.share.main.layer.layer;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class LayerServiceImp extends GenericServiceImpl<Layer, LayerDao> implements LayerService {
}

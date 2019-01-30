package org.sadr.share.main.map;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class MapShareServiceImp extends GenericServiceImpl<Map, MapDao> implements MapShareService {
}

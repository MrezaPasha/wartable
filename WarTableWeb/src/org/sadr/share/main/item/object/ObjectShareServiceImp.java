package org.sadr.share.main.item.object;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class ObjectShareServiceImp extends GenericServiceImpl<Object, ObjectDao> implements ObjectShareService {
}

package org.sadr.share.main.material.material;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class MaterialServiceImp extends GenericServiceImpl<Material, MaterialDao> implements MaterialService {
}

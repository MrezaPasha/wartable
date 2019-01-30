package org.sadr.share.main.mrml;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MrmlServiceImp extends GenericServiceImpl<Mrml,MrmlDao> implements MrmlService {
}

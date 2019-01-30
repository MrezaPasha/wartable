package org.sadr.share.main.accessCategoury;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class AccessCategoryServiceImp extends GenericServiceImpl<AccessCategory,AccessCategoryDao> implements AccessCategoryService {
}

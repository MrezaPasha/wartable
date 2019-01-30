package org.sadr.share.main.groupPolicy;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.share.main.accessCategoury.AccessCategory;

import java.util.Set;

public interface GroupPolicyService extends GenericService<GroupPolicy> {

    public java.lang.String setStateBytes(boolean change, Set<AccessCategory> accessCategories);

}

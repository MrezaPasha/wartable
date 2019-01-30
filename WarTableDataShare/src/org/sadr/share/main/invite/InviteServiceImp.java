package org.sadr.share.main.invite;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class InviteServiceImp extends GenericServiceImpl<Invite,InviteDao> implements InviteService {
}

package org.sadr.share.main.sessions;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class SessionsShareServiceImp extends GenericServiceImpl<Sessions, SessionsDao> implements SessionsShareService {
}

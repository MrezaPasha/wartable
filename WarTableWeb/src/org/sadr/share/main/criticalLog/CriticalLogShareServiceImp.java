package org.sadr.share.main.criticalLog;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class CriticalLogShareServiceImp extends GenericServiceImpl<CriticalLog, CriticalLogDao> implements CriticalLogShareService {
}

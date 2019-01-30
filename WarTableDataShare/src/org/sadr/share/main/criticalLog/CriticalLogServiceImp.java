package org.sadr.share.main.criticalLog;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CriticalLogServiceImp extends GenericServiceImpl<CriticalLog,CriticalLogDao> implements CriticalLogService{

}

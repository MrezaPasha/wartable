package org.sadr.share.main.log.models.serviceLog;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.share.main._types.TtImportance;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log._types.TtFlag;
import org.sadr.share.main.log._types.TtSensitivity;
import org.sadr.share.main.log._types.TtSubType;

public interface ServiceLogService extends GenericService<ServiceLog> {
    public  void log(String url , String username , String userUniqueId , String subtypeDescription , String description);
    public  void log(String url , String username , String userUniqueId , TtActionType actionType , TtSensitivity sensitivity , TtImportance importance , TtFlag flag , TtSubType subType , String subtypeDescription , String description);


}

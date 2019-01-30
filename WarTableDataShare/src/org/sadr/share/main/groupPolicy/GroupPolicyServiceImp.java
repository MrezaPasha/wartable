package org.sadr.share.main.groupPolicy;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.service.main.nonRpc.publish._types.TtState;
import org.sadr.service.main.nonRpc.publish._types.TtStateVariable;
import org.sadr.share.main.accessCategoury.AccessCategory;
import org.sadr.share.main.accessCategoury._types.TtAccessCategoryType;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class GroupPolicyServiceImp extends GenericServiceImpl<GroupPolicy,GroupPolicyDao> implements GroupPolicyService {

    @Override
    public String setStateBytes(boolean change, Set<AccessCategory> accessCategories )  {
        List<TtAccessCategoryType> accessCategoryTypes = new ArrayList<TtAccessCategoryType>();
        StringBuffer stringBuffer = new StringBuffer();
        for (AccessCategory accessCategory : accessCategories)
        {
            accessCategoryTypes.add(accessCategory.getAccessCategoryType());
        }
        //String finalResult = new String("");
        //List<Integer> finalResult = new ArrayList<>();
        String finalResult ;

        int index = 0;
        int lenght = accessCategoryTypes.size();
        //System.out.println(validState.length);
        if (change) {
            while (TtAccessCategoryType.getByOrdinal(index) != null) {
                for (TtAccessCategoryType accessCategoryType : accessCategoryTypes) {
                    if (TtAccessCategoryType.getByOrdinal(index) == accessCategoryType) {
                        //finalResult += TtStateVariable.Changed.getName();
                        //finalResult.add(TtStateVariable.Changed.ordinal());
                        stringBuffer.append(TtStateVariable.Changed.ordinal());
                        index++;
                    } else if (TtAccessCategoryType.getByOrdinal(index) != accessCategoryType) {
                        //finalResult += TtStateVariable.UnChanged.getName();
                        //finalResult.add(TtStateVariable.UnChanged.ordinal());
                        stringBuffer.append(TtStateVariable.UnChanged.ordinal());
                        index++;
                    }
                }
            }
        }
        else
        {
            while (TtAccessCategoryType.getByOrdinal(index) != null) {
                for (TtAccessCategoryType accessCategoryType : accessCategoryTypes) {
                    if (TtAccessCategoryType.getByOrdinal(index) == accessCategoryType) {
                        //finalResult.add(TtStateVariable.UnChanged.ordinal());
                        stringBuffer.append(TtStateVariable.UnChanged.ordinal());
                        index++;
                    } else if (TtAccessCategoryType.getByOrdinal(index) != accessCategoryType) {
                        //finalResult.add(TtStateVariable.Changed.ordinal());
                        stringBuffer.append(TtStateVariable.Changed.ordinal());
                        index++;
                    }
                }
            }

        }
        finalResult = stringBuffer.toString();
        return finalResult;
    }

    public String setAllStateBytes()
    {
        String finalResult ;
        StringBuffer stringBuffer = new StringBuffer();

        try
        {

            int index = 0;
            while (TtAccessCategoryType.getByOrdinal(index) != null) {
                //finalResult.add(TtStateVariable.Changed.ordinal());
                stringBuffer.append("1");
                index ++;
            }
        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
        }
        finalResult = stringBuffer.toString();
        return finalResult;
    }
}

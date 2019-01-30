package org.sadr.service.main.seed;

import org.hibernate.criterion.Restrictions;
import org.sadr.service.config.IOCContainer;
import org.sadr.service.main.rpc._types.TtConfig;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;
import org.sadr.share.main.baseErrors.BaseErrors;
import org.sadr.share.main.baseErrors.BaseErrorsServiceImp;

import java.util.List;

public class Seed {


    public static void initBaseConfigs() {

        try {
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp) IOCContainer.GetBeans(BaseConfigServiceImp.class);

//            List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
//            if (!baseConfigs.isEmpty()) {
            BaseConfig baseConfig = new BaseConfig();
                for (TtConfig config : TtConfig.values()) {
                    baseConfig.setConfigId(config);
                    baseConfig.setConfigName(config.getConfigName());
                    baseConfig.setConfigValue(config.getConfigDefaultValue());
                    baseConfigServiceImp.save(baseConfig);


                }
//            }
//            else if (baseConfigs.isEmpty()) {
//                for (TtConfig config : TtConfig.values()) {
//                    BaseConfig bstConfig = new BaseConfig();
//                    bstConfig.setConfigId(config);
//                    bstConfig.setConfigName(config.getConfigName());
//                    bstConfig.setConfigValue(config.getConfigDefaultValue());
//                    baseConfigServiceImp.save(bstConfig);
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initBaseErrors()
    {
        try
        {
            BaseErrorsServiceImp baseErrorsServiceImp = (BaseErrorsServiceImp) IOCContainer.GetBeans(BaseErrorsServiceImp.class);
            List<BaseErrors> baseErrorss = baseErrorsServiceImp.findAll();
            if (!baseErrorss.isEmpty())
            {
                for (TtErrors errors : TtErrors.values())
                {
                    BaseErrors baseErrors = baseErrorsServiceImp.findBy(Restrictions.eq(BaseErrors.ERROR_ID,errors));
                    if (baseErrors != null)
                    {
                        if (baseErrors.getErrorValue() == null)
                        {
                            baseErrors.setErrorValue(errors.getErrorValueService());
                            baseErrorsServiceImp.update(baseErrors);

                        }

                    }
                    else if (baseErrors == null)
                    {
                        BaseErrors bstErrors = new BaseErrors();
                        bstErrors.setErrorId(errors);
                        bstErrors.setErrorDescription(errors.getErrorValueService());
                        baseErrorsServiceImp.save(bstErrors);

                    }
                }
            }
            else if (baseErrorss.isEmpty())
            {
                for (TtErrors errors : TtErrors.values())
                {
                    BaseErrors baseErrors = new BaseErrors();
                    baseErrors.setErrorId(errors);
                    baseErrors.setErrorDescription(errors.getErrorValueService());
                    baseErrors.setErrorValue(errors.getErrorValueService());
                    baseErrorsServiceImp.save(baseErrors);
                }
            }



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

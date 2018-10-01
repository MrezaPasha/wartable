package org.sadr.web.main.admin._type;

import org.sadr.web.main._core.propertor.PropertorInWeb;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;

/**
 * @author masoud
 */
public enum TtUserAttemptType {

    Signin("تلاش برای ورود",
            TtPropertorInWebList.UserAttemptSigninRageTime,
            TtPropertorInWebList.UserAttemptSigninBlockTime,
            TtPropertorInWebList.UserAttemptSigninAttemptMax,
            TtPropertorInWebList.UserAttemptSigninBlockMax),
    ReSignin("تلاش برای ورود به عملیات های سطح دو",
            TtPropertorInWebList.UserAttemptSigninRageTime,
            TtPropertorInWebList.UserAttemptSigninBlockTime,
            TtPropertorInWebList.UserAttemptSigninAttemptMax,
            TtPropertorInWebList.UserAttemptSigninBlockMax),
    ;

    private final String title;

    private final TtPropertorInWebList rangeTime; //seconds- بازه زمانی که فعالیت های کاربر ثبت می شود
    private final TtPropertorInWebList blockTime; //seconds- مدت زمانی که مرورگر کاربر قفل می شود    -    مقدار زمان بلاک باید از زمانه بازه فعالیت بیشتر باشد
    private final TtPropertorInWebList attemptMaxCount;// تعداد تلاش های مجاز در بازه فعالیت- بعد از تعداد مجاز ریکپچا نمایش داده می شود
    private final TtPropertorInWebList blockMaxCount;// تعداد فعالیتهایی که بیش از آن مرورگر قفل می شود

    private TtUserAttemptType(String t, TtPropertorInWebList rt, TtPropertorInWebList bt, TtPropertorInWebList amc, TtPropertorInWebList bmc) {
        title = t;
        rangeTime = rt;
        blockTime = bt;
        attemptMaxCount = amc;
        blockMaxCount = bmc;
    }

    public String getTitle() {
        return title;
    }


    public int getRangeMiliSec() {
        return PropertorInWeb.getInstance().getPropertyInt(rangeTime) * 1000;
    }

    public int getBlockSec() {
        return PropertorInWeb.getInstance().getPropertyInt(blockTime);
    }

    public int getBlockMiliSec() {
        return PropertorInWeb.getInstance().getPropertyInt(blockTime) * 1000;
    }

    public int getAttemptMaxCount() {
        return PropertorInWeb.getInstance().getPropertyInt(attemptMaxCount);
    }

    public int getBlockMaxCount() {
        return PropertorInWeb.getInstance().getPropertyInt(blockMaxCount);
    }

}

package org.sadr.service.main.rpc._types;

public enum TtConfig {
    BannedTimePeriod("300","بازه زمانی تلاش مجدد به ثانیه"),
    BannedRetryCount("5","تعداد دفعات تلاش مجدد"),
    LockBannedTimePeriod("300","بازه زمانی قفل شدن اکانت"),
    SoftwareID("1","شناسه نرم افزار"),
    SoftwareName("wartable","نام نرم افزار"),
    SoftwareVersion("1.0.0","ورژن نرم افزار"),
    SoftwareSize("255","حجم نرم افزار"),
    LastSoftwareVersion("1.0.1","ورژن قبلی نرم افزار"),
    LastSoftwareSize("254","حجم ورژن قبلی نرم افزار"),
    ServerHostname("sadr.local","نام آدرس سرور"),
    ServerIP("10.0.21.4","آپی سرور"),
    ServerPort("5672","پورت سرور"),
    ServerLoginUsername("khabir","شناسه لاگین سرور"),
    ServerLoginPassword("12345678","پسورد لاگین سرور"),
    ServerQueue("serverRPC","صف سرور"),
    LoggerIPAddress("192.168.1.23","آدرس دیتابیش لاگ"),
    LoggerPort("5432","آدرس پورت لاگ"),
    LoggerDbName("log","نام دیتابیس لاگ"),
    LoggerDbUsername("root","شناسه دیتابیس لاگ"),
    LoggerDbPassword("P@ssw0rds","پسورد دیتابیس لاگ"),
    LoggerTableName("logtable","نام جدول لاگ"),
    SessionTimeoutMinute("300","زمان منقضی شدن نشست کاربر به ثانیه"),
    VoipServerIp("192.168.88.238","آدرس سرور ارتباط صوتی"),
    VoipServerRestPort("5000","َشماره پورت سرویس دهنده صوتی");
    private final String configDefaultValue;
    private final String configName;

    TtConfig(String configDefaultValue, String configName) {
        this.configDefaultValue = configDefaultValue;
        this.configName = configName;
    }

    public String getConfigDefaultValue() {
        return configDefaultValue;
    }

    public String getConfigName() {
        return configName;
    }
}

package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtRegisteryKey {


    IxportCryptKey("ixport_crypt_key", "کلید رمزنگاری خروجی گزارش", TtRegisteryType.Ixport),
    IxportPrivateKey("ixport_private_key", "کلید خصوصی خروجی گزارش", TtRegisteryType.Ixport),
    IxportPublicKey("ixport_public_key", "کلید عمومی خروجی گزارش", TtRegisteryType.Ixport),
    BackupCryptKey("backup_crypt_key", "کلید رمزنگاری پشتیان گیری", TtRegisteryType.Backup),
    BackupPrivateKey("backup_private_key", "کلید خصوصی پشتیان گیری", TtRegisteryType.Backup),
    BackupPublicKey("backup_public_key", "کلید عمومی پشتیان گیری", TtRegisteryType.Backup),;

    private final String key;
    private final String title;
    private final TtRegisteryType type;

    private TtRegisteryKey(String k, String t, TtRegisteryType trt) {
        key = k;
        title = t;
        type = trt;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public TtRegisteryType getType() {
        return type;
    }


    public static TtRegisteryKey getByOrdinal(int ordinal) {
        for (TtRegisteryKey value : values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }

}

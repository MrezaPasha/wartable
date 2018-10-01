package org.sadr._core.utils._type;

/**
 * @author masoud
 */
public enum TtCookierVariable {

    UserAutoLoginUUID("_mxd_sa_UAuL", 864000, "/"),// 10 days
    UserPorterUUID("_mxd_sa_UPrt", 153792000, "/"),// 5 years
    ReturnUrlAfterSignin("_mxd_sa_RetUrl", 150, "/");// 00:02:30

    private String key;
    private int age;
    private String path;

    private TtCookierVariable(String k, int a, String p) {
        key = k;
        age = a;
        path = p;
    }

    public String getKey() {
        return key;
    }

    public int getAge() {
        return age;
    }

    public String getPath() {
        return path;
    }

}

package org.sadr.share.main._types;

public enum TtMemberStatus {

    Deactive("member is deactive"),
    Active("member is active");

    private String memberStatusName;

    TtMemberStatus(String memberStatusName) {
        this.memberStatusName = memberStatusName;
    }

    public String getMemberStatusName() {
        return memberStatusName;
    }

    public void setMemberStatusName(String memberStatusName) {
        this.memberStatusName = memberStatusName;
    }

    public static TtMemberStatus getByOrdinal(int o) {
        for (TtMemberStatus f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}

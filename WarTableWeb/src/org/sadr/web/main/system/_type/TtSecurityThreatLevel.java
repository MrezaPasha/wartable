package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtSecurityThreatLevel {

    Low("پایین", "احتمال حمله وجود ندارد."),
    Moderate("متوسط", "حمله امکان پذیر است، ولی احتمال رخداد صفر است."),
    Substantial("قابل توجه", "احتمال حمله وجود دارد"),
    Severe("شدید", "احتمال رخداد حمله بسیار زیاد است."),
    Critical("بحرانی", "انتظار می رود به زودی حمله رخ دهد.");

    private final String level;
    private final String title;

    private TtSecurityThreatLevel(String l, String t) {
        level = l;
        title = t;
    }

    public String getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }
}

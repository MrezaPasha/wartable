package org.sadr.web.main._core.utils;

import org.sadr._core.utils.SpringMessager;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Serializable;
import java.util.Map;

/**
 * @author dev1
 * @version 1.95.11.15
 */
public class Notice2 implements Serializable {

    public static Notice2[] addNotices(Notice2... ns) {
        return ns;
    }

    public static Notice2[] mergeNotices(Notice2[] ns1, Notice2... ns2) {
        Notice2[] d = new Notice2[ns1.length + ns2.length];
        int i = 0;
        for (; i < ns1.length; i++) {
            d[i] = ns1[i];
        }
        for (int j = 0; j < ns2.length; j++, i++) {
            d[i] = ns2[j];
        }
        return d;
    }

    public static void initRedirectAttr(RedirectAttributes r, Notice2... ns) {
        if (r.getFlashAttributes().containsKey("notice")) {
            ns = mergeNotices((Notice2[]) r.getFlashAttributes().get("notice"), ns);
        }
        r.addFlashAttribute("notice", ns);
    }

    public static void initPostError(RedirectAttributes r, BindingResult bindingResult) {
        String s = "";
        for (FieldError e : bindingResult.getFieldErrors()) {
            s += "، " + SpringMessager.get(e.getObjectName() + "." + e.getField());
        }
        Notice2[] ns = addNotices(new Notice2("N1.all.validation.error", s.length() > 1 ? s.substring(2) : ""));

        r.addFlashAttribute("notice", ns);
    }

    public static void initRedirectAttr(boolean doMerge, RedirectAttributes r, Notice2... ns) {
        if (doMerge && r.getFlashAttributes().containsKey("notice")) {
            ns = mergeNotices((Notice2[]) r.getFlashAttributes().get("notice"), ns);
        }
        r.addFlashAttribute("notice", ns);
    }

    public static void initModelAttr(Model m, Notice2... ns) {
        if (m.containsAttribute("notice")) {
            Map<String, Object> asMap = m.asMap();
            for (Map.Entry<String, Object> objmap : asMap.entrySet()) {
                if ("notice".equals(objmap.getKey())) {
                    ns = mergeNotices((Notice2[]) objmap.getValue(), ns);
                    break;
                }

            }
        }
        m.addAttribute("notice", ns);
    }

    public static void initModelAttr(boolean doMerge, Model m, Notice2... ns) {
        if (doMerge && m.containsAttribute("notice")) {
            Map<String, Object> asMap = m.asMap();
            for (Map.Entry<String, Object> objmap : asMap.entrySet()) {
                if ("notice".equals(objmap.getKey())) {
                    ns = mergeNotices((Notice2[]) objmap.getValue(), ns);
                    break;
                }

            }
        }
        m.addAttribute("notice", ns);
    }

    public static void releaseModelNotice(Model m) {
        m.addAttribute("notice", null);
    }

    public static void releaseRedirectNotice(RedirectAttributes r) {
        r.addFlashAttribute("notice", null);
    }

    public Notice2(String code) {
        this.code = code;
        this.type = TtNotice.Danger;
    }

    public Notice2(String code, String... params) {
        this.code = code;
        this.type = TtNotice.Danger;
        this.params = params;
    }

    public Notice2(String code, Object secretNote, String... params) {
        this.code = code;
        this.type = TtNotice.Danger;
        this.params = params;
        this.secretNote = secretNote.toString();
    }

    public Notice2(String code, TtNotice type, String... params) {
        this.code = code;
        this.type = type;
        this.params = params;
    }

    public Notice2(String code, Object secretNote, TtNotice type, String... params) {
        this.code = code;
        this.type = type;
        this.params = params;
        this.secretNote = secretNote.toString();
    }

    String code;
    TtNotice type;
    String[] params;
    String secretNote;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public String getMessage() {
        return SpringMessager.get(code, params);
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TtNotice getType() {
        return type;
    }

    public void setType(TtNotice type) {
        this.type = type;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String getParamsString() {
        if (params == null || params.length == 0) {
            return "";
        }
        String ps = "<strong>'" + params[0] + "'</strong>";
        for (int i = 1; i < params.length; i++) {
            ps += ",<strong>'" + params[i] + "'</strong>";
        }
        return ps;
    }

    public String getParamsJson() {
        if (params == null || params.length == 0) {
            return "\"\"";
        }

        String ps = "[\"" + params[0] + "\"";
        for (int i = 1; i < params.length; i++) {
            ps += ",\"" + params[i] + "\"";
        }
        ps += "]";
        return ps;
    }

    public String getCssClass() {
        return type.getCssClass();
    }

    @Override
    public String toString() {
        return "{\"c\":\"" + code + "\", \"t\":\"" + type + "\", \"s\":" + getParamsJson() + ",\"sn\":" + secretNote + "}";
    }

    public String toJson() {
        return "{\"c\":\"" + code + "\", \"t\":\"" + type + "\", \"s\":" + getParamsJson() + ",\"sn\":" + secretNote + "}";
    }

}

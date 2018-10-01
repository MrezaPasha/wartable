/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main._core.utils;

import org.sadr._core.utils.SpringMessager;
import org.sadr.web.main._core.utils._type.TtIsonStatus;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.system._type.TtHttpErrorCode___;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author dev1
 */
public class Ison {

    public static Ison init() {
        Ison obj = new Ison();
        obj.props = new StringBuilder();
        obj.status = TtIsonStatus.Unknown;
        obj.isBuilt = false;
        return obj;
    }

    public static Ison init(TtHttpErrorCode___ code) {
        Ison obj = new Ison();
        obj.props = new StringBuilder();
        obj.status = TtIsonStatus.Nok;
        obj.isBuilt = false;
        obj.nots = new Notice2[]{new Notice2(code.getMessageCodeTitle())};
        return obj;
    }

    public static Ison init(String json) {
        Ison obj = new Ison();
        obj.props = new StringBuilder("," + json);
        obj.status = TtIsonStatus.Unknown;
        obj.isBuilt = false;
        return obj;
    }

    public static Ison initMessages(String spMsg, TtNotice tn) {
        Ison obj = new Ison();
        obj.props = new StringBuilder();
        obj.status = TtIsonStatus.Nok;
        obj.isBuilt = false;
        obj.nots = Notice2.addNotices(new Notice2(spMsg, tn));
        return obj;
    }

    public static ResponseEntity<String> response(HttpHeaders headers, String spMsg, TtNotice tn) {
        return new ResponseEntity<>(
            Ison.init()
                .setStatus(TtIsonStatus.Nok)
                .setMessages(Notice2.addNotices(new Notice2(spMsg, tn)))
                .toJson(),
            headers, HttpStatus.OK);
    }

    public static ResponseEntity<String> response(HttpHeaders headers, String spMsg, TtNotice tn, TtIsonStatus tis) {
        return new ResponseEntity<>(
            Ison.init()
                .setStatus(tis)
                .setMessages(Notice2.addNotices(new Notice2(spMsg, tn)))
                .toJson(),
            headers, HttpStatus.OK);
    }

    public static ResponseEntity<String> response(HttpHeaders headers, String spMsg, TtNotice tn, TtIsonStatus tis, String params) {
        return new ResponseEntity<>(
            Ison.init()
                .setStatus(tis)
                .setMessages(Notice2.addNotices(new Notice2(spMsg, tn, params)))
                .toJson(),
            headers, HttpStatus.OK);
    }

    private Ison() {
    }

    boolean isBuilt;
    private StringBuilder props;
    private Notice2[] nots;
    private TtIsonStatus status;

    public Ison setMessages(Notice2... ns) {
        this.nots = ns;
        return this;
    }

    public Ison setProperty(String prop, String value) {
        props.append(",\"").append(prop).append("\":\"").append(value).append("\"");
        return this;
    }

    public Ison setPropertyJson(String prop, String value) {
        props.append(",\"").append(prop).append("\":").append(value);
        return this;
    }

    public Ison setProperty(String prop, Boolean value) {
        props.append(",\"").append(prop).append("\":").append(value);
        return this;
    }

    public Ison setStatus(TtIsonStatus status) {
        this.status = status;
//        props.append(",\"status\":\"").append(status.getTitle()).append("\"");
        return this;
    }

    public Ison setArray(String prop, String... value) {
        props.append(",\"").append(prop).append("\":[");
        if (value.length > 0) {
            props.append("\"").append(value[0]).append("\"");
            for (int i = 1; i < value.length; i++) {
                props.append(",\"").append(value[i]).append("\"");
            }
        }
        props.append("]");
        return this;
    }

    public Ison setProperty(String prop, int value) {
        props.append(",\"").append(prop).append("\":").append(value);
        return this;
    }

    public Ison setProperty(String prop, float value) {
        props.append(",\"").append(prop).append("\":").append(value);
        return this;
    }

    public Ison setArray(String prop, int... value) {
        props.append(",\"").append(prop).append("\":[");
        if (value.length > 0) {
            props.append(value[0]);
            for (int i = 1; i < value.length; i++) {
                props.append(",").append(value[i]);
            }
        }
        props.append("]");
        return this;
    }

    public Ison setProperty(String prop, Ison value) {
        props.append(",\"").append(prop).append("\":").append(value.toJson());
        return this;
    }

    public Ison setArray(String prop, Ison... value) {
        props.append(",\"").append(prop).append("\":[");
        if (value.length > 0) {
            props.append(value[0].toJson());
            for (int i = 1; i < value.length; i++) {
                props.append(",").append(value[i].toJson());
            }
        }
        props.append("]");
        return this;
    }

    public Ison setArray(String prop, List<Ison> value) {
        props.append(",\"").append(prop).append("\":[");
        if (value != null && value.size() > 0) {
            int size = value.size();
            props.append(value.get(0).toJson());
            for (int i = 1; i < size; i++) {
                props.append(",").append(value.get(i).toJson());
            }
        }
        props.append("]");
        return this;
    }

    public String toJson() {
        if (!isBuilt) {
            isBuilt = true;
            props.replace(0, 1, "{\"result\":{")
                .append("},\"status\":\"")
                .append(status.getKey())
                .append("\",\"messages\":[");
            if (nots != null && nots.length > 0) {
                props.append("{\"text\":\"").append(SpringMessager.get(nots[0].getCode(), nots[0].getParams()))
                    .append("\",\"cssClass\":\"").append(nots[0].getCssClass()).append("\"}");

                for (int i = 1; i < nots.length; i++) {
                    try {
                        props.append(",{\"text\":\"").append(SpringMessager.get(nots[i].getCode(), nots[i].getParams())
                        ).append("\",\"cssClass\":\"").append(nots[i].getCssClass()).append("\"}");
                    } catch (Exception e) {
                        props.append("{\"text\":\"").append("<متن یافت نشد>")
                            .append("\",\"cssClass\":\"").append(nots[0].getCssClass()).append("\"}");
                    }
                }
            }
            props.append("]}");
        }
//        OutLog.pl(props.toString());
        return props.toString();
    }

    public ResponseEntity<String> toResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(
            toJson(),
            headers, HttpStatus.OK);
    }
}

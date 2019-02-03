package org.sadr.web.main._core.tools.driver.email;

import org.sadr._core.utils.Environment;
import org.sadr.web.main._core.propertor.PropertorInWeb;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;
import org.sadr.web.main.system.irror.irror.Irror;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dev1
 */
public class EmailDriver {

    private final static EmailDriver instance = new EmailDriver();

    public static EmailDriver getInstance() {
        return instance;
    }

    private EmailDriver() {
    }

    ///////////////////////
    public void send(Irror irror, String emails) {

        switch (irror.getLevel()) {
            case Warn:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertEmailWarn))
                    return;
                break;
            case Fatal:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertEmailFatal))
                    return;
                break;
            case Debug:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertEmailDebug))
                    return;
                break;
            case Error:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertEmailError))
                    return;
                break;
            case Info:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertEmailInfo))
                    return;
                break;
            case Trace:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertEmailTrace))
                    return;
                break;
            default:
                return;

        }

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> doSend(irror, emails));
        executor.shutdown();

    }

    private void doSend(Irror irror, String emails) {

        final String username = PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.IrrorAlertEmailBoxAddress);
        final String password = PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.IrrorAlertEmailBoxPassword);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.mime.charset", "UTF-8");
        props.put("mail.smtp.starttls.enable", PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertEmailBoxTlsOn) + "");
        props.put("mail.smtp.host", PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.IrrorAlertEmailBoxHost));
        props.put("mail.smtp.port", PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.IrrorAlertEmailBoxPort));
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.port", PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.IrrorAlertEmailBoxPort));

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.IrrorAlertEmailBoxAddress)));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emails));
            message.setSubject("خطای سیستمی در سامانه " + Environment.getInstance().getProjectName() + " | " + irror.getIdi() + " | " + irror.getLevel(), "UTF-8");

            message.setContent(
                    "<div style='font-family:tahoma'>" +
                            "<div style:'font-weight:bold'>" +
                            "خطای سیستمی" + "<br/><br/><hr/><br/>" +
                            "شناسه خطا: " +
                            irror.getIdi() + "<br/>" +
                            "\n سطح: " +
                            irror.getLevel() + "<br/>" +
                            "\n نام کاربر: " +
                            irror.getUserFullName() + "<br/>" +
                            "\n کد خطا: " +
                            irror.getHttpErrorCode() + "<br/>" +
                            "\n علت: " +
                            irror.getCause() + "<br/><hr/><br/>" +
                            "</div>" +
                            "\n جزئیات: " + "<br/>" +
                            irror.getMessage() +
                            "</div>"
                    , "text/html; charset=utf-8");
            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

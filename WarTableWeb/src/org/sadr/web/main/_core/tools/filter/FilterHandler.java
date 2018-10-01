/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main._core.tools.filter;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils._type.TtCookierVariable;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main._core.utils.Cookier;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author dev1
 */
@RestController
@PersianName("مدیریت فیلتر")
public class FilterHandler implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
        OutLog.sl("FILTER HANDLER INITIATED... ");
        // loading init method
        WebConfigHandler.loadPropertors();
        OutLog.sl(" > Propertors loaded completely :) ");

        WebConfigHandler.initPrime();
        OutLog.sl(" > Project loaded completely :) ");

    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().startsWith(request.getContextPath() + "/resource")) {
            response.setDateHeader("Expires", System.currentTimeMillis() + 604800000L);

            chain.doFilter(request, response); // Just continue chain.
        } else {
            response.setHeader("Expires", "Tue, 03 Jul 2001 06:00:00 GMT");
            response.setDateHeader("Last-Modified", new Date().getTime());
            String uuid = Cookier.getValue(request, TtCookierVariable.UserPorterUUID.getKey());
            if (uuid == null || uuid.isEmpty()) {
                String agentSignature = request.getHeader("User-Agent");
                uuid = UUID.randomUUID().toString() + "_"
                        + (agentSignature == null ? "" : agentSignature.trim().replace(" ", "").toUpperCase().codePoints().sum());
                Cookier.setCookie(response, TtCookierVariable.UserPorterUUID, uuid);
            }
            if ((req.getAttribute("returnToUrl") != null && !req.getAttribute("returnToUrl").toString().isEmpty()) && !req.getAttribute("returnToUrl").toString().contains(".ico")) {
                Cookier.setCookie(response, TtCookierVariable.ReturnUrlAfterSignin, req.getAttribute("returnToUrl").toString());
                req.removeAttribute("returnToUrl");
            } else if (req.getParameter("returnToUrl") != null && !req.getParameter("returnToUrl").isEmpty() && !req.getParameter("returnToUrl").contains(".ico")) {
                Cookier.setCookie(response, TtCookierVariable.ReturnUrlAfterSignin, req.getParameter("returnToUrl"));
                req.removeAttribute("returnToUrl");
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        OutLog.pl("FILTER HANDLER DESTROIED. ");
    }
}

package org.sadr.web.config;

import com.captcha.botdetect.web.servlet.SimpleCaptchaServlet;
import org.sadr._core.utils.Environment;
import org.sadr._core.utils.OutLog;
import org.sadr.share.config.ShareConfigHandler;
import org.sadr.web.main._core.tools.filter.FilterHandler;
import org.sadr.web.main._core.tools.listener.SessionAttributeListener;
import org.sadr.web.main._core.tools.listener.SessionListener;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;
import java.util.EnumSet;

/**
 * @author masoud
 */
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        OutLog.sl("  ----------<([ IN THE NAME OF ALLAH ])>----------\n\n\n ");
        OutLog.sl(Environment.getInstance().getProjectName());
        OutLog.sl("Starting WebInitializer...");

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        WebConfigHandler.loadConfigs();
        ShareConfigHandler.loadConfigs();
//        ServiceConfigHandler.loadConfigs();

        ctx.register(WebConfig.class);
        ctx.register(WebConfigHandler.getConfigClassArrays());
        ctx.register(ShareConfigHandler.getConfigClassArrays());
//        ctx.register(ServiceConfigHandler.getConfigClassArrays());
        ctx.setServletContext(servletContext);
        servletContext.addListener(new ContextLoaderListener(ctx));
        servletContext.addListener(new SessionListener());
        servletContext.addListener(new SessionAttributeListener());

        servletContext.setInitParameter("defaultHtmlEscape", "true");

        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

        Dynamic servletCaptcha = servletContext.addServlet("BotDetect Captcha", new SimpleCaptchaServlet());
        servletCaptcha.addMapping("/botdetectcaptcha");
        servletCaptcha.setLoadOnStartup(1);

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("characterEncoding", characterEncodingFilter);
        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

        // addConfigClass custom filter
        FilterRegistration.Dynamic filterHandler = servletContext.addFilter("filterHandler", new FilterHandler());
        filterHandler.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

        // addConfigClass custom filter  >  urlwriter
        FilterRegistration.Dynamic urlRewriteFilter = servletContext.addFilter("urlRewriteFilter", new UrlRewriteFilter());
        String dm;
            urlRewriteFilter.setInitParameter("confPath", "/WEB-INF/conf/system/urlrewrite_local.xml");
        urlRewriteFilter.addMappingForUrlPatterns(dispatcherTypes, false, "/*");

        // Initing contex in ConfigHandler . 
        // loading init methods. called in filter init 
        WebConfigHandler.setWebApplicationContext(ctx);
        Environment.getInstance().setContextPath(servletContext.getContextPath());
        dm = servletContext.getRealPath("");
        if (dm != null) {
            dm = dm.replaceAll("\\\\", "/");
            int ix, lx;
            ix = dm.indexOf("domains");
            if (ix != -1) {
                lx = dm.indexOf(Environment.FILE_SEPARATOR, ix + 8);
                if (lx != -1) {
                    Environment.getInstance().setWebDomain(dm.substring(ix + 8, lx));
                }
            } else {
                Environment.getInstance().setWebDomain(servletContext.getVirtualServerName());
            }
        }



        /*************** Service Initializer **************/
//        HttpService.start();

    }
}

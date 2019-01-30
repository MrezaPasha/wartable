package org.sadr.service.config;

import org.sadr._core.utils.Environment;
import org.sadr._core.utils.OutLog;
import org.sadr.service.main.http.controller.TomcatConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;
import java.util.EnumSet;

/**
 * @author masoud
 */
public class ServiceInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        OutLog.sl("  ----------<([ IN THE NAME OF ALLAH ])>----------\n\n\n ");
        OutLog.sl(Environment.getInstance().getProjectName());
        OutLog.sl("Starting WebInitializer...");

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        //ctx.setAllowBeanDefinitionOverriding(true);
        ServiceConfigHandler.loadConfigs();

        ctx.register(TomcatConfig.class);
        ctx.register(ServiceConfig.class);
        ctx.register(ServiceConfigHandler.getConfigClassArrays());
        ctx.setServletContext(servletContext);
        servletContext.addListener(new ContextLoaderListener(ctx));


        servletContext.setInitParameter("defaultHtmlEscape", "true");

        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);


        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("characterEncoding", characterEncodingFilter);
        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");


        /*************** Service Initializer **************/
        //HttpService.start();
        OutLog.pl();

        IOCContainer.setContext(ctx);

    }
}

package org.sadr.config;

import org.sadr._core.utils.Cryptor;
import org.sadr._core.utils.Environment;
import org.sadr._core.utils.OutLog;
import org.sadr.share.config.ShareConfigHandler;
import org.sadr.web.config.WebConfig;
import org.sadr.web.config.WebConfigHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


/**
 * @author masoud
 */
@Configuration
public class TestConfig {

    public static void main(String[] args) {
        System.out.println(Cryptor.encrypt("P@ssw0rds"));
    }

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Bean
    AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext() {
        OutLog.sl("  ----------<([ IN THE NAME OF ALLAH ])>----------\n\n\n ");
        OutLog.sl(Environment.getInstance().getProjectName());
        OutLog.sl("AnnotationConfigWebApplicationContext Initializing...");

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setServletContext(webApplicationContext.getServletContext());

        WebConfigHandler.loadConfigs();
        ShareConfigHandler.loadConfigs();

        ctx.register(WebConfig.class);
        ctx.register(WebConfigHandler.getConfigClassArrays());
        ctx.register(ShareConfigHandler.getConfigClassArrays());

        // Initing contex in ConfigHandler .
        // loading init methods. called in filter init
        WebConfigHandler.setWebApplicationContext(ctx);
        Environment.getInstance().setContextPath(ctx.getServletContext().getContextPath());
        String dm;
        dm = ctx.getServletContext().getRealPath("");
        if (dm != null) {
            dm = dm.replaceAll("\\\\", "/");
            int ix, lx;
            ix = dm.indexOf("domains");
            if (ix != -1) {
                lx = dm.indexOf(Environment.FILE_SEPARATOR, ix + 8);
                if (lx != -1) {
                    Environment.getInstance().setWebDomain(dm.substring(ix + 8, lx));
                }
            }
        }
        return ctx;
    }
}

package org.sadr.service.config;

import org.springframework.web.context.WebApplicationContext;

public class IOCContainer {

    private static WebApplicationContext context ;



    public static Object GetBeans(Class C) {

        return context.getBean(C);
    }

    public static void setContext(WebApplicationContext context) {
        IOCContainer.context = context;
    }
}




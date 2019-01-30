package org.sadr.service.main.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.sadr._core.utils.OutLog;
import org.sadr.service.main.http.controller.Controller;

public class HttpService {

    public static void main(String[] args) throws Exception{
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8099);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(1);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                Controller.class.getCanonicalName());

        try {
            jettyServer.start();
            jettyServer.join();
            OutLog.pl("111111111111111111");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jettyServer.join();

            //jettyServer.destroy();
        }

    }

    public static void start() {
        try {
//            ExecutorService service = Executors.newFixedThreadPool(4);
//            service.submit(new Runnable() {
//                public void run() {
            main(null);
//                }
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

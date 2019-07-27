package home.maks.bean.app;

import home.maks.bean.web.WebMvcConfig;
import home.maks.config.Config;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.io.IOException;
import java.util.EnumSet;

@Configuration
public class JettyConfig {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server createJettyServer(Config config, ApplicationContext applicationContext) throws IOException {
        ServletContextHandler servletContextHandler = createServletContextHandler(createWebContext(applicationContext));

        Server server = new Server(config.getServerPort());
        server.setHandler(servletContextHandler);
        return server;
    }

    private ServletContextHandler createServletContextHandler(WebApplicationContext webContext) throws IOException {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setErrorHandler(null);

        contextHandler.setResourceBase(new ClassPathResource("webapp")
                .getURI().toString());
        contextHandler.setContextPath("/");

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
        ServletHolder springServletHolder = new ServletHolder("mvc-dispatcher", dispatcherServlet);
        contextHandler.addServlet(springServletHolder, "/");
        contextHandler.addEventListener(new ContextLoaderListener(webContext));

        return contextHandler;
    }

    private AnnotationConfigWebApplicationContext createWebContext(ApplicationContext applicationContext) {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.registerShutdownHook();
        webApplicationContext.setConfigLocation(WebMvcConfig.class.getPackage().getName());
        webApplicationContext.setParent(applicationContext);
        return webApplicationContext;
    }
}

package com.epam.provider.web.listener;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;


@WebListener("applications context listener")
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context=event.getServletContext();
        String fileName=context.getInitParameter("log4j-config-name");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        String fullPath=file.getAbsolutePath();
        PropertyConfigurator.configure(fullPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}

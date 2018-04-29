package com.epam.provider.web.listener;

import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.dao.pool.ConnectionPoolException;
import com.epam.provider.util.resource.ResourceManager;
import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


@WebListener("applications context listener")
public class ContextListener implements ServletContextListener {

  private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ServletContext context = event.getServletContext();
    String fileName = context.getInitParameter("log4j-config-name");
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(fileName).getFile());
    String fullPath = file.getAbsolutePath();
    PropertyConfigurator.configure(fullPath);
    ResourceManager.initResources();
    try {
      ConnectionPool.getInstance().initPoolData();
    } catch (ConnectionPoolException e) {
      LOGGER.log(Level.FATAL, e.getMessage());
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    try {
      ConnectionPool.getInstance().clearConnectionQueue();
    } catch (ConnectionPoolException e) {
      LOGGER.log(Level.FATAL, e.getMessage());
    }
  }


}

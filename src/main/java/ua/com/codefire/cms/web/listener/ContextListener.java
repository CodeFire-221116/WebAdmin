package ua.com.codefire.cms.web.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by human on 12/6/16.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MainPersistenceUnit");

//        ServletContext servletContext = sce.getServletContext();
//        servletContext.setAttribute("factory", factory);

        System.out.println("CONTEXT LOADED");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.removeAttribute("factory");

        System.out.println("CONTEXT UNLOADED");
    }
}

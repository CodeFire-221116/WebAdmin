package ua.com.codefire.cms.listener;

import ua.com.codefire.cms.model.AttributeNames;
import ua.com.codefire.cms.model.Fields;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by human on 12/6/16
 */

@WebListener
public class UsersSessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    private Map<String, String> users = new HashMap<>();

    private ServletContext context;

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        context = se.getSession().getServletContext();

        HttpSession session = se.getSession();
        users.put(session.getId(), Fields.USER_ANONYMOUS);

        context.setAttribute(Fields.USERS, users);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        if (event.getName().equals(AttributeNames.SESSION_USERNAME)) {

            String username = event.getValue().toString();
            users.put(event.getSession().getId(), username);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        HttpSession session = se.getSession();
        users.remove(session.getId());

        context.setAttribute(Fields.USERS, users);
    }
}

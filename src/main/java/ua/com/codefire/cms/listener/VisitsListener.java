package ua.com.codefire.cms.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by human on 12/6/16.
 */
@WebListener
public class VisitsListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        Enumeration<String> headers = req.getHeaderNames();

//        System.out.println(req.getRemoteAddr());

        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
//            System.out.printf("%s: %s\n", headName, req.getHeader(headName));
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }
}

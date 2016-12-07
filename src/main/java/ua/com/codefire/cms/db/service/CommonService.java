package ua.com.codefire.cms.db.service;

import ua.com.codefire.cms.db.repo.CommonRepo;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 07.12.2016.
 */
abstract class CommonService<T> {
    EntityManagerFactory factory;
    CommonRepo<T> commonRepo;

    void setFactoryFromRequest(HttpServletRequest req) {
        Object factory = req.getServletContext().getAttribute("factory");
        if(factory != null) {
            try {
                this.factory = (EntityManagerFactory)factory;
            } catch (ClassCastException ex) {
                System.out.println("Wrong parameter put into context. StackTrace:\n" + ex);
            } catch (Exception ex) {
                System.out.println("Unexpected exception, while filling factory. StackTrace:\n" + ex);
            }
        }
    }

    abstract Long create(T objToCreate);
    abstract T read(Long idToFind);
    abstract Boolean update(T objToUpdate);
    abstract Boolean delete(Long objToDeleteId);
}

package ua.com.codefire.cms.db.service.implemetation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.BookEntity;
import ua.com.codefire.cms.db.repo.abstraction.IBookRepo;
import ua.com.codefire.cms.db.repo.implementation.BookRepo;
import ua.com.codefire.cms.db.service.abstraction.IBookService;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by User on 10.12.2016.
 */
/**
 * An implementation of entity-specific Service Interface.
 * The object of this class needs to be put in the IBookService variable in case of need in entity-specific
 * methods and in the ICommonService(BookEntity) variable in case of need in CRUD operations.
 */
public class BookService implements IBookService {
    /**
     * IBookRepo variable, which holds a link to BookRepo object in order to communicate with DataBase
     * with it`s help
     */
    private IBookRepo bookRepo;

    /**
     * Creates a new instance of Service, intended to work with articles. Retrieves entity factory from request context
     * and creates a new repository, based on this factory, in order to communicate with DataBase
     * @param req HttpServletRequest instance, which can be retrieved in servlet`s methods.
     */
    public BookService(HttpServletRequest req) {
        Object factory = req.getServletContext().getAttribute("factory");
        if(factory != null) {
            try {
                bookRepo = new BookRepo(new EntityManagerHelper((EntityManagerFactory)factory));
            } catch (ClassCastException ex) {
                System.out.println("Wrong parameter put into context. StackTrace:\n" + ex);
            } catch (Exception ex) {
                System.out.println("Unexpected exception, while generating article repository. StackTrace:\n" + ex);
            }
        }
    }

    @Override
    public Long create(BookEntity objToCreate) {
        return bookRepo.create(objToCreate);
    }

    @Override
    public BookEntity read(Long idToFind) {
        return bookRepo.read(idToFind);
    }

    @Override
    public Boolean update(BookEntity objToUpdate) {
        return bookRepo.update(objToUpdate);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        return bookRepo.delete(objToDelete);
    }

    @Override
    public List<BookEntity> getAllEntities() {
        return bookRepo.getAllEntities();
    }

    @Override
    public Long getAmountOfEntities() {
        return bookRepo.getAmountOfEntities();
    }
}

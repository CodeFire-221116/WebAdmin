package ua.com.codefire.cms.db.service.implemetation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.Book;
import ua.com.codefire.cms.db.repo.abstraction.IBookRepo;
import ua.com.codefire.cms.db.repo.implementation.BookRepo;
import ua.com.codefire.cms.db.service.abstraction.IBookService;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by User on 10.12.2016.
 */
public class BookService implements IBookService {
    private IBookRepo bookRepo;

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
    public Long create(Book objToCreate) {
        return bookRepo.create(objToCreate);
    }

    @Override
    public Book read(Long idToFind) {
        return bookRepo.read(idToFind);
    }

    @Override
    public Boolean update(Book objToUpdate) {
        return bookRepo.update(objToUpdate);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        return bookRepo.delete(objToDelete);
    }

    @Override
    public List<Book> getAllEntities() {
        return bookRepo.getAllEntities();
    }
}

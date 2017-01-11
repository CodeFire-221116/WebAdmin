package ua.com.codefire.cms.db.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.stereotype.Service;
import ua.com.codefire.cms.db.entity.BookEntity;
import ua.com.codefire.cms.db.service.abstraction.IBookService;
import ua.com.codefire.cms.db.repo.BookEntityRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of entity-specific Service Interface.
 * The object of this class needs to be put in the IBookService variable in case of need in entity-specific
 * methods and in the ICommonService(BookEntity) variable in case of need in CRUD operations.
 * The creation has to be with the help of @Autowired annotation
 * @author ankys
 */
@Service
public class BookService implements IBookService {
    private static final Logger LOGGER = Logger.getLogger(BookEntityRepository.class.getName());

    /**
     * BookEntityRepository variable, which is a Spring Data Interface, intended to work with DataBase
     * with it`s help
     */
    @Autowired
    private BookEntityRepository bookRepo;

    @Override
    public Long create(BookEntity objToCreate) {
        try {
            BookEntity savedBook = bookRepo.saveAndFlush(objToCreate);
            return savedBook.getId();
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (EntityExistsException ex) {
            LOGGER.log(Level.SEVERE, "Such book already exists.", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while creating new book.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return null;
    }

    @Override
    public BookEntity read(Long idToFind) {
        return bookRepo.findOne(idToFind);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        try {
            bookRepo.delete(objToDelete);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No book found by such id.", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while deleting book.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception, while deleting book.", ex);
        }
        return false;
    }

    @Override
    public Boolean update(BookEntity objToCreate) {
        try {
            bookRepo.saveAndFlush(objToCreate);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No such book found.", ex);
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while updating book.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return false;
    }

    @Override
    public List<BookEntity> getAllEntities() {
        return bookRepo.findAll();
    }

    @Override
    public Long getAmountOfEntities() {
        return bookRepo.count();
    }
}

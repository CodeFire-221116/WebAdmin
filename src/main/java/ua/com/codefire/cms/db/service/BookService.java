package ua.com.codefire.cms.db.service;

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
 * Created by User on 27.12.2016.
 */
@Service
public class BookService implements IBookService {
    private BookEntityRepository bookRepo;
    private static final Logger LOGGER = Logger.getLogger(BookEntityRepository.class.getName());

    @Autowired
    public BookService(BookEntityRepository bookEntityRepository) {
        this.bookRepo = bookEntityRepository;
    }

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

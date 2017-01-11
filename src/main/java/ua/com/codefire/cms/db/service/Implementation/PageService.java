package ua.com.codefire.cms.db.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.stereotype.Service;
import ua.com.codefire.cms.db.entity.PageEntity;
import ua.com.codefire.cms.db.service.abstraction.IPageService;
import ua.com.codefire.cms.db.repo.PageEntityRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of entity-specific Service Interface.
 * The object of this class needs to be put in the IPageService variable in case of need in entity-specific
 * methods and in the ICommonService(PageEntity) variable in case of need in CRUD operations.
 * The creation has to be with the help of @Autowired annotation
 * @author ankys
 */
@Service
public class PageService implements IPageService {
    private PageEntityRepository pageRepo;
    private static final Logger LOGGER = Logger.getLogger(PageEntityRepository.class.getName());

    @Autowired
    public PageService(PageEntityRepository pageEntityRepository) {
        this.pageRepo = pageEntityRepository;
    }

    @Override
    public Long create(PageEntity objToCreate) {
        try {
            PageEntity savedPage = pageRepo.saveAndFlush(objToCreate);
            return savedPage.getId();
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (EntityExistsException ex) {
            LOGGER.log(Level.SEVERE, "Such page already exists.", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while creating new page.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return null;
    }

    @Override
    public PageEntity read(Long idToFind) {
        return pageRepo.findOne(idToFind);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        try {
            pageRepo.delete(objToDelete);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No page found by such id.", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while deleting page.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception, while deleting page.", ex);
        }
        return false;
    }

    @Override
    public Boolean update(PageEntity objToCreate) {
        try {
            pageRepo.saveAndFlush(objToCreate);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No such page found.", ex);
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while updating page.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return false;
    }

    @Override
    public List<PageEntity> getAllEntities() {
        return pageRepo.findAll();
    }

    @Override
    public Long getAmountOfEntities() {
        return pageRepo.count();
    }
}

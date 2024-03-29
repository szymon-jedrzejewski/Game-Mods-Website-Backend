package com.gmw.repository.sql.mod;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Mod;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import com.gmw.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ModRepository implements Repository<Mod> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final PersistenceManager persistenceManager;

    public ModRepository(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public Long create(Mod newMod) throws SqlRepositoryException {
        try {
            LOGGER.debug("Creating new mod!");
            Mod mod = (Mod) persistenceManager.create(newMod);
            Long id = mod.getId();
            LOGGER.debug("New mod with id " + id + " was created!");
            return id;
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during creating mod! " + newMod, e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void update(Mod mod) throws SqlRepositoryException {
        try {
            persistenceManager.update(mod);
            LOGGER.debug("Mod with id " + mod.getId() + " was updated!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during updating mod!", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void delete(Long id) throws SqlRepositoryException {
        try {
            persistenceManager.delete(id, new Mod().getTableName());
            LOGGER.debug("Mod with id " + id + " was deleted!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during deleting mod!", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public List<Mod> find(QuerySpec querySpec) throws SqlRepositoryException {
        try {
            return persistenceManager
                    .find(querySpec)
                    .stream()
                    .map(Mod.class::cast)
                    .toList();
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during searching values!", e);
            throw new SqlRepositoryException();
        }
    }
}

package com.gmw.repository.sql;


import com.gmw.model.Field;
import com.gmw.model.Game;
import com.gmw.model.View;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.sql.SqlSafePersistenceManager;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.repository.sql.field.FieldSqlRepository;
import com.gmw.repository.sql.game.GameSqlRepository;
import com.gmw.repository.sql.view.ViewSqlRepository;
import com.gmw.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class SqlRepositoryManager implements RepositoryManager {

    private static final Logger logger = LogManager.getLogger();
    private PersistenceManager persistenceManager;

    public SqlRepositoryManager() {
        try {
            this.persistenceManager = new SqlSafePersistenceManager(JDBCUtils.getConnection());
        } catch (SQLException e) {
            logger.error("Error during sql persistence manager creation!");
        }
    }

    @Override
    public Repository<View> getViewRepositoryManager() {
        return new ViewSqlRepository(persistenceManager);
    }

    @Override
    public Repository<Field> getFieldRepositoryManager() {
        return new FieldSqlRepository(persistenceManager);
    }

    @Override
    public Repository<Game> getGameRepositoryManager() {
        return new GameSqlRepository(persistenceManager);
    }
}
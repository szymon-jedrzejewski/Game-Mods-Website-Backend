package com.gmw.services.mod.impl;

import com.gmw.coverters.ModConverter;
import com.gmw.mod.tos.ExistingModTO;
import com.gmw.model.Mod;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.mod.DBModReadService;

import java.util.List;

public class DBModReadServiceImpl extends DBService implements DBModReadService {
    public DBModReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public List<ExistingModTO> findAllMods() throws ResourceNotFoundException {
        Repository<Mod> repository = getRepositoryManager().getModRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("mods");
        querySpec.setClazz(Mod.class);

        return ServiceUtils.find(repository, new ModConverter(), querySpec);
    }

    @Override
    public List<ExistingModTO> findModsByGameId(Long gameId) throws ResourceNotFoundException {
        Repository<Mod> repository = getRepositoryManager().getModRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("mods");
        querySpec.setClazz(Mod.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("game_id", Operator.EQUAL_TO, gameId));

        return ServiceUtils.find(repository, new ModConverter(), querySpec);
    }
}

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
        querySpec.setTableName(new Mod().getTableName());
        querySpec.setClazz(Mod.class);

        return ServiceUtils.find(repository, new ModConverter(), querySpec);
    }

    @Override
    public List<ExistingModTO> findModsByGameId(Long gameId) throws ResourceNotFoundException {
        Repository<Mod> repository = getRepositoryManager().getModRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new Mod().getTableName());
        querySpec.setClazz(Mod.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("game_id", Operator.EQUAL_TO, List.of(gameId)));

        return ServiceUtils.find(repository, new ModConverter(), querySpec);
    }

    @Override
    public ExistingModTO findModById(Long modId) throws ResourceNotFoundException {
        Repository<Mod> repository = getRepositoryManager().getModRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new Mod().getTableName());
        querySpec.setClazz(Mod.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, List.of(modId)));

        return ServiceUtils.find(repository, new ModConverter(), querySpec).get(0);
    }

    @Override
    public List<ExistingModTO> findModsByIds(List<Long> ids) throws ResourceNotFoundException {
        Repository<Mod> repository = getRepositoryManager().getModRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new Mod().getTableName());
        querySpec.setClazz(Mod.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.IN,
                ids.stream().map(id -> (Object) id).toList()));

        return ServiceUtils.find(repository, new ModConverter(), querySpec);
    }
}

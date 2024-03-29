package com.gmw.services.view.impl;

import com.gmw.coverters.ViewConverter;
import com.gmw.model.View;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.view.DBViewReadService;
import com.gmw.view.tos.ExistingViewTO;

import java.util.List;

public class DBViewReadServiceImpl extends DBService implements DBViewReadService {

    public DBViewReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public ExistingViewTO obtainViewById(Long viewId) throws ResourceNotFoundException {
        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepository();
        QuerySpec querySpec = new QuerySpec();
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, List.of(viewId)));
        querySpec.setClazz(View.class);
        querySpec.setTableName(new View().getTableName());

        return ServiceUtils.find(viewRepositoryManager, new ViewConverter(), querySpec).get(0);
    }

    @Override
    public ExistingViewTO obtainViewByGameId(Long gameId) throws ResourceNotFoundException {
        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepository();
        QuerySpec querySpec = new QuerySpec();
        querySpec.append(QueryOperator.WHERE, new SearchCondition("game_id", Operator.EQUAL_TO, List.of(gameId)));
        querySpec.setClazz(View.class);
        querySpec.setTableName(new View().getTableName());

        return ServiceUtils.find(viewRepositoryManager, new ViewConverter(), querySpec).get(0);
    }
}

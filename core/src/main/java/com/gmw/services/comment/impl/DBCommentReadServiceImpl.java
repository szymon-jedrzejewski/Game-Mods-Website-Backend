package com.gmw.services.comment.impl;

import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.coverters.CommentConverter;
import com.gmw.model.Comment;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.ServiceUtils;
import com.gmw.services.comment.DBCommentReadService;
import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public class DBCommentReadServiceImpl extends DBService implements DBCommentReadService {
    public DBCommentReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public List<ExistingCommentTO> obtainCommentsByModId(Long modId) throws ResourceNotFoundException {
        Repository<Comment> repository = getRepositoryManager().getCommentRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new Comment().getTableName());
        querySpec.setClazz(Comment.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("mod_id", Operator.EQUAL_TO, List.of(modId)));

        return ServiceUtils.find(repository, new CommentConverter(), querySpec);
    }

    @Override
    public Long obtainUserIdByCommentId(Long id) throws ResourceNotFoundException {
        Repository<Comment> repository = getRepositoryManager().getCommentRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new Comment().getTableName());
        querySpec.setClazz(Comment.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, List.of(id)));

        return ServiceUtils.find(repository, new CommentConverter(), querySpec).get(0).getId();
    }
}

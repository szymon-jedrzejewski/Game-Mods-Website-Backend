package com.gmw.services.mod;

import com.gmw.mod.tos.ExistingModTO;
import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DBModReadService {
    List<ExistingModTO> findAllMods() throws ResourceNotFoundException;
    List<ExistingModTO> findModsByGameId(Long gameId) throws ResourceNotFoundException;
    ExistingModTO findModById(Long modId) throws ResourceNotFoundException;
    List<ExistingModTO> findModsByIds(List<Long> ids) throws ResourceNotFoundException;
}

package com.gmw.api.rest.activity.category;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class DeleteCategoryActivity extends Activity<Void> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Long id;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotDeletedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (RoleChecker.isAdmin(serviceManager, userId)) {
                serviceManager.getDbCategoryService().deleteCategory(id);
                status = HttpStatus.OK;
            } else {
                throw new PermissionDeniedException();
            }
        } catch (Exception e) {
            LOGGER.error("Cannot delete category with id: " + id);
            throw new ResourceNotDeletedException(e);
        }
        return null;
    }
}

package com.gmw.api.rest.activity.rating;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class DeleteRatingActivity extends Activity<Void> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Long id;

    @Override
    protected Void realExecute() throws ResourceNotDeletedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()){
            serviceManager.getDbRatingService().deleteRating(id);
            status = HttpStatus.OK;
        } catch (Exception e) {
            LOGGER.error("Cannot delete rating with id: " + id);
            throw new ResourceNotDeletedException(e);
        }
        return null;
    }
}

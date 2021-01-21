package crud.service.abstraction;

import crud.model.Driver;
import crud.service.abstraction.src.Service;
import java.util.Optional;

public interface DriverService extends Service<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}

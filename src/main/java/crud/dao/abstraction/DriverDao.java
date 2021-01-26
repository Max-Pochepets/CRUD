package crud.dao.abstraction;

import crud.dao.abstraction.src.Dao;
import crud.model.Driver;
import java.util.Optional;

public interface DriverDao extends Dao<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}

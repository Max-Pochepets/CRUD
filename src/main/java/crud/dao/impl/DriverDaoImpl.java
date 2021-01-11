package crud.dao.impl;

import crud.dao.abstraction.DriverDao;
import crud.lib.DaoImpl;
import crud.model.Driver;
import java.util.Map;
import java.util.Optional;

@DaoImpl
public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        return null;
    }

    @Override
    public Optional<Driver> get(Long id) {
        return Optional.empty();
    }

    @Override
    public Map<Long, Driver> getAll() {
        return null;
    }

    @Override
    public Driver update(Driver driver) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}

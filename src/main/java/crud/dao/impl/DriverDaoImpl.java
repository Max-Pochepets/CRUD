package crud.dao.impl;

import crud.dao.abstraction.DriverDao;
import crud.lib.DaoImpl;
import crud.model.Driver;
import crud.storage.Storage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DaoImpl
public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        return Storage.addDriver(driver);
    }

    @Override
    public Optional<Driver> get(Long id) {
        return Optional.ofNullable(Storage.drivers.get(id));
    }

    @Override
    public List<Driver> getAll() {
        return new ArrayList<>(Storage.drivers.values());
    }

    @Override
    public Driver update(Driver driver) {
        Long desiredId = driver.getId();
        Driver oldDriver = get(desiredId).get();
        Storage.drivers.put(oldDriver.getId(), driver);
        return get(desiredId).get();
    }

    @Override
    public boolean delete(Long id) {
        return Storage.drivers.remove(id) != null;
    }
}

package crud.service.impl;

import crud.dao.abstraction.DriverDao;
import crud.lib.Inject;
import crud.lib.ServiceImpl;
import crud.model.Driver;
import crud.service.abstraction.DriverService;
import java.util.List;
import java.util.Optional;

@ServiceImpl
public class DriverServiceImpl implements DriverService {
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver create(Driver driver) {
        return driverDao.create(driver);
    }

    @Override
    public Driver get(Long id) {
        if (driverDao.get(id).isPresent()) {
            return driverDao.get(id).get();
        }
        return null;
    }

    @Override
    public List<Driver> getAll() {
        return driverDao.getAll();
    }

    @Override
    public Driver update(Driver driver) {
        return driverDao.update(driver);
    }

    @Override
    public boolean delete(Long id) {
        return driverDao.delete(id);
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        return driverDao.findByLogin(login);
    }
}

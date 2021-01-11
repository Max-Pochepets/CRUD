package crud.dao.impl;

import crud.dao.abstraction.CarDao;
import crud.lib.DaoImpl;
import crud.model.Car;
import java.util.Map;
import java.util.Optional;

@DaoImpl
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car element) {
        return null;
    }

    @Override
    public Optional<Car> get(Long id) {
        return Optional.empty();
    }

    @Override
    public Map<Long, Car> getAll() {
        return null;
    }

    @Override
    public Car update(Car element) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}

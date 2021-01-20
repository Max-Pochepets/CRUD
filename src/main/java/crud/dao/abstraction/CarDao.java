package crud.dao.abstraction;

import crud.dao.abstraction.src.Dao;
import crud.model.Car;
import java.util.List;

public interface CarDao extends Dao<Car, Long> {
    List<Car> getAllByDriver(Long driverId);
}

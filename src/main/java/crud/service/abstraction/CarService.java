package crud.service.abstraction;

import crud.model.Car;
import crud.model.Driver;
import crud.service.abstraction.src.Service;
import java.util.List;

public interface CarService extends Service<Car> {
    void addDriverToCar(Driver driver, Car car);

    void removeDriverFromCar(Driver driver, Car car);

    List<Car> getAllByDriver(Long driverId);
}

package crud.dao.impl;

import crud.dao.abstraction.CarDao;
import crud.lib.DaoImpl;
import crud.model.Car;
import crud.model.Manufacturer;
import crud.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DaoImpl
public class CarDaoImpl implements CarDao {
    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return Storage.cars.values().stream()
                .filter(c -> c.getDrivers().stream()
                        .anyMatch(d -> d.getId().equals(driverId)))
                .collect(Collectors.toList());
    }

    @Override
    public Car create(Car car) {
        return Storage.addCar(car);
    }

    @Override
    public Optional<Car> get(Long id) {
        return Optional.ofNullable(Storage.cars.get(id));
    }

    @Override
    public List<Car> getAll() {
        return new ArrayList<>(Storage.cars.values());
    }

    @Override
    public Car update(Car car) {
        Long desiredId = car.getId();
        Car oldCar = get(desiredId).get();
        Storage.cars.put(oldCar.getId(), car);
        return get(desiredId).get();
    }

    @Override
    public boolean delete(Long id) {
        return Storage.cars.remove(id) != null;
    }
}

package crud.dao.impl;

import crud.dao.abstraction.ManufacturerDao;
import crud.lib.DaoImpl;
import crud.model.Manufacturer;
import crud.storage.Storage;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@DaoImpl
public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return Storage.addManufacturer(manufacturer);
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Storage.manufacturers.stream()
                .filter(element -> Objects.equals(element.getId(), id))
                .findFirst();
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        Manufacturer oldManufacturer = get(manufacturer.getId()).get();
        Storage.manufacturers.set(Storage.manufacturers.indexOf(oldManufacturer), manufacturer);
        return oldManufacturer;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturers.removeIf(element -> element.getId().equals(id));
    }
}

package crud.dao.impl;

import crud.dao.abstraction.ManufacturerDao;
import crud.lib.DaoImpl;
import crud.model.Manufacturer;
import crud.storage.Storage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DaoImpl
public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return Storage.addManufacturer(manufacturer);
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Optional.ofNullable(Storage.manufacturers.get(id));
    }

    @Override
    public List<Manufacturer> getAll() {
        return new ArrayList<>(Storage.manufacturers.values());
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        Long desiredId = manufacturer.getId();
        Manufacturer oldManufacturer = get(desiredId).get();
        Storage.manufacturers.put(oldManufacturer.getId(), manufacturer);
        return get(desiredId).get();
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturers.remove(id) != null;
    }
}

package crud.dao.impl;

import crud.dao.abstraction.ManufacturerDao;
import crud.lib.DaoImpl;
import crud.model.Manufacturer;
import crud.storage.Storage;
import java.util.List;
import java.util.Optional;

@DaoImpl
public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return Storage.addManufacturer(manufacturer);
    }

    @Override
    public Optional<Manufacturer> get(long id) {
        return Optional.ofNullable(Storage.manufacturersStorage.get((int) id));
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturersStorage;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        Storage.manufacturersStorage.set((int) manufacturer.getId(), manufacturer);
        return manufacturer;
    }

    @Override
    public boolean delete(long id) {
        return Storage.manufacturersStorage.remove(Storage.manufacturersStorage.get((int) id));
    }
}

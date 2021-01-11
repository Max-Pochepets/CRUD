package crud.dao.impl;

import crud.dao.abstraction.ManufacturerDao;
import crud.lib.DaoImpl;
import crud.model.Manufacturer;
import crud.storage.Storage;
import java.util.Map;
import java.util.Optional;

@DaoImpl
public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        if (Storage.manufacturersStorage.containsValue(manufacturer)) {
            return manufacturer;
        }
        Storage.addManufacturer(manufacturer);
        return Storage.manufacturersStorage.get(manufacturer.getId());
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Optional.of(Storage.manufacturersStorage.get(id));
    }

    @Override
    public Map<Long, Manufacturer> getAll() {
        return Storage.manufacturersStorage;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        if (Storage.manufacturersStorage.containsKey(manufacturer.getId())) {
            Storage.manufacturersStorage.put(manufacturer.getId(), manufacturer);
        }
        return create(manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturersStorage.remove(id, Storage.manufacturersStorage.get(id));
    }
}

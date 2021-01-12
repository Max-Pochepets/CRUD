package crud.storage;

import crud.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Manufacturer> manufacturersStorage = new ArrayList<>();
    private static long manufacturerId = 0L;

    public static Manufacturer addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(manufacturerId++);
        manufacturersStorage.add(manufacturer);
        return manufacturer;
    }
}

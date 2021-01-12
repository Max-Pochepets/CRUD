package crud.storage;

import crud.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    private static Long manufacturerId = 0L;

    public static Manufacturer addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufacturerId);
        manufacturers.add(manufacturer);
        return manufacturer;
    }
}

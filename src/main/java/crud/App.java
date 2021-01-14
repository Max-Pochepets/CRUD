package crud;

import crud.lib.Injector;
import crud.model.Manufacturer;
import crud.service.abstraction.ManufacturerService;
import crud.util.ConnectionUtil;

public class App {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private static final Manufacturer MANUFACTURER = new Manufacturer("Tesla", "USA");
    private static final Manufacturer MANUFACTURER_2 = new Manufacturer("Audi", "Germany");
    private static final Manufacturer MANUFACTURER_3 = new Manufacturer("Mercedes", "Germany");
    private static final String MANUFACTURERS = "manufacturers";

    public static void main(String[] args) {
        ConnectionUtil.clearTable(MANUFACTURERS);
        ManufacturerService manufacturerService
                = (ManufacturerService) INJECTOR.getInstance(ManufacturerService.class);
        manufacturerService.create(MANUFACTURER);
        manufacturerService.create(MANUFACTURER_2);
        manufacturerService.create(MANUFACTURER_3);
        System.out.println(manufacturerService.getAll());
        System.out.println(manufacturerService.get(2L));
        MANUFACTURER.setName("Ford");
        manufacturerService.update(MANUFACTURER);
        manufacturerService.delete(2L);
        System.out.println(manufacturerService.getAll());
    }
}

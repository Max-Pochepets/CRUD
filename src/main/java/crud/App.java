package crud;

import crud.lib.Injector;
import crud.model.Manufacturer;
import crud.service.abstraction.ManufacturerService;

public class App {
    private static final Injector injector = Injector.getInstance("crud");

    public static void main(String[] args) {
        ManufacturerService manufacturerService
                = (ManufacturerService) injector.getInstance(ManufacturerService.class);
        System.out.println("Representing whole list: " + manufacturerService.getAll());
        Manufacturer manufacturer = new Manufacturer("Tesla", "USA");
        System.out.println("Manufacturer was created: " + manufacturerService.create(manufacturer));
        System.out.println("Representing single element with ID 1: " + manufacturerService.get(1L));
        Manufacturer manufacturer1 = new Manufacturer("Audi", "Germany");
        manufacturer1.setId(1L);
        System.out.println("New manufacturer: " + manufacturer1);
        manufacturerService.update(manufacturer1);
        System.out.println("After update: "
                + manufacturerService.getAll());
        manufacturerService.delete(1L);
        System.out.println("After delete on ID 1: " + manufacturerService.getAll());
    }
}

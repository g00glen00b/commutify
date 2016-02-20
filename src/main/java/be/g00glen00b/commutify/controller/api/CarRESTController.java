package be.g00glen00b.commutify.controller.api;

import be.g00glen00b.commutify.dto.CarCollectionDTO;
import be.g00glen00b.commutify.dto.StringCollectionDTO;
import be.g00glen00b.commutify.service.impl.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarRESTController {
    @Autowired
    private CarService service;

    @RequestMapping("/manufacturers")
    public StringCollectionDTO findManufacturers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "10") int limit) {
        return service.findManufacturers(search, offset, limit);
    }

    @RequestMapping("/models")
    public StringCollectionDTO findModels(
            @RequestParam String manufacturer,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "10") int limit) {
        return service.findModels(manufacturer, search, offset, limit);
    }

    @RequestMapping("/types")
    public CarCollectionDTO findTypes(
            @RequestParam String manufacturer,
            @RequestParam String model,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "10") int limit) {
        return service.findTypes(manufacturer, model, search, offset, limit);
    }
}

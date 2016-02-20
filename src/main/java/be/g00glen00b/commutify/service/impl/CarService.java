package be.g00glen00b.commutify.service.impl;

import be.g00glen00b.commutify.dto.CarCollectionDTO;
import be.g00glen00b.commutify.dto.StringCollectionDTO;

public interface CarService {
    StringCollectionDTO findManufacturers(String name, int offset, int limit);
    StringCollectionDTO findModels(String manufacturer, String search, int offset, int limit);

    CarCollectionDTO findTypes(String manufacturer, String model, String search, int offset, int limit);
}

package be.g00glen00b.commutify.service.impl;

import be.g00glen00b.commutify.dto.CarCollectionDTO;
import be.g00glen00b.commutify.dto.CarDTO;
import be.g00glen00b.commutify.dto.StringCollectionDTO;
import be.g00glen00b.commutify.entity.CarManufacturer;
import be.g00glen00b.commutify.entity.CarModel;
import be.g00glen00b.commutify.entity.CarType;
import be.g00glen00b.commutify.repository.CarManufacturerRepository;
import be.g00glen00b.commutify.repository.CarModelRepository;
import be.g00glen00b.commutify.repository.CarTypeRepository;
import be.g00glen00b.commutify.repository.OffsetPageRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private static final String WILDCARD = "%";
    @Autowired
    private CarManufacturerRepository manufacturerRepository;
    @Autowired
    private CarModelRepository modelRepository;
    @Autowired
    private CarTypeRepository typeRepository;

    @Override
    public StringCollectionDTO findManufacturers(String search, int offset, int limit) {
        final String pattern = StringUtils.join(WILDCARD, StringUtils.lowerCase(search), WILDCARD);
        final OffsetPageRequest page = new OffsetPageRequest(offset, limit);
        final Page<CarManufacturer> entities = manufacturerRepository.findByDistinctManufacturerLike(pattern, page);
        return new StringCollectionDTO.Builder()
            .offset(offset)
            .limit(limit)
            .totalResults(entities.getTotalElements())
            .results(entities.getContent().stream().map(CarManufacturer::getManufacturer).collect(Collectors.toList()))
            .build();
    }

    @Override
    public StringCollectionDTO findModels(String manufacturer, String search, int offset, int limit) {
        final String pattern = StringUtils.join(WILDCARD, StringUtils.lowerCase(search), WILDCARD);
        final OffsetPageRequest page = new OffsetPageRequest(offset, limit);
        final String lowerManufacturer = StringUtils.lowerCase(manufacturer);
        final Page<CarModel> entities = modelRepository.findByManufacturerAndModelLikeDistinct(lowerManufacturer, pattern, page);
        return new StringCollectionDTO.Builder()
            .offset(offset)
            .limit(limit)
            .totalResults(entities.getTotalElements())
            .results(entities.getContent().stream().map(CarModel::getModel).collect(Collectors.toList()))
            .build();
    }

    @Override
    public CarCollectionDTO findTypes(String manufacturer, String model, String search, int offset, int limit) {
        final String pattern = StringUtils.join(WILDCARD, StringUtils.lowerCase(search), WILDCARD);
        final String lowerManufactuer = StringUtils.lowerCase(manufacturer);
        final String lowerModel = StringUtils.lowerCase(model);
        final OffsetPageRequest page = new OffsetPageRequest(offset, limit);
        final Page<CarType> entities = typeRepository.findByManufacturerModelAndTypeLike(lowerManufactuer, lowerModel, pattern, page);
        return new CarCollectionDTO.Builder()
            .offset(offset)
            .limit(limit)
            .totalResults(entities.getTotalElements())
            .results(entities.getContent().stream()
                .map(entity -> new CarDTO.Builder()
                    .manufacturer(entity.getModel().getManufacturer().getManufacturer())
                    .model(entity.getModel().getModel())
                    .type(entity.getType())
                    .emission(entity.getEmission())
                    .build())
                .collect(Collectors.toList()))
            .build();
    }
}

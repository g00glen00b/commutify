package be.g00glen00b.commutify.repository;

import be.g00glen00b.commutify.entity.CarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType, String> {
    @Query("SELECT DISTINCT t FROM CarType t " +
            "INNER JOIN t.model m " +
            "INNER JOIN m.manufacturer ma " +
            "WHERE LOWER(t.type) LIKE ?3 " +
            "AND LOWER(ma.manufacturer) LIKE ?1 " +
            "AND LOWER(m.model) LIKE ?2 " +
            "ORDER BY t.type")
    Page<CarType> findByManufacturerModelAndTypeLike(String manufacturer, String model, String search, Pageable page);
}

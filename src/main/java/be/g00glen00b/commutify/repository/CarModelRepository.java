package be.g00glen00b.commutify.repository;

import be.g00glen00b.commutify.entity.CarModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, String> {
    @Query("SELECT DISTINCT m FROM CarModel m " +
            "INNER JOIN m.manufacturer " +
            "WHERE LOWER(m.manufacturer.manufacturer) = ?1 " +
            "AND LOWER(m.model) LIKE ?2 " +
            "ORDER BY m.model")
    Page<CarModel> findByManufacturerAndModelLikeDistinct(String manufacturer, String pattern, Pageable page);
}

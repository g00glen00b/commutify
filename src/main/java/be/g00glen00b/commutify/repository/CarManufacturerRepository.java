package be.g00glen00b.commutify.repository;

import be.g00glen00b.commutify.entity.CarManufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarManufacturerRepository extends JpaRepository<CarManufacturer, String> {

    @Query("SELECT DISTINCT m FROM CarManufacturer m WHERE LOWER(m.manufacturer) LIKE ?1 ORDER BY m.manufacturer")
    Page<CarManufacturer> findByDistinctManufacturerLike(String pattern, Pageable page);
}

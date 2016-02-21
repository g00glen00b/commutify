package be.g00glen00b.commutify.repository;

import be.g00glen00b.commutify.entity.CommutifyProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CommutifyProfileRepository extends JpaRepository<CommutifyProfile, Long> {
    @Query("SELECT p FROM CommutifyProfile p " +
            "LEFT JOIN FETCH p.entries e " +
            "LEFT JOIN FETCH e.type " +
            "WHERE p.email=?1")
    CommutifyProfile findByEmail(String email);

    @Override
    @Query("SELECT p FROM CommutifyProfile p " +
            "LEFT JOIN FETCH p.entries e " +
            "LEFT JOIN FETCH e.type " +
            "WHERE p.id=?1")
    CommutifyProfile findOne(Long id);

    @Query(value = "SELECT p FROM CommutifyProfile p " +
        "LEFT JOIN FETCH p.entries e " +
        "LEFT JOIN FETCH e.type " +
        "ORDER BY p.saved DESC", countQuery = "SELECT COUNT(p) FROM CommutifyProfile p")
    Page<CommutifyProfile> findAllOrderedBySavings(Pageable page);

    @Query("SELECT SUM(p.saved) FROM CommutifyProfile p")
    BigDecimal getTotalSavings();
}

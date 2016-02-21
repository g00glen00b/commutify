package be.g00glen00b.commutify.repository;

import be.g00glen00b.commutify.entity.CommutifyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}

package be.g00glen00b.commutify.repository;

import be.g00glen00b.commutify.entity.CommutifyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommutifyProfileRepository extends JpaRepository<CommutifyProfile, Long> {
    CommutifyProfile findByEmail(String email);
}

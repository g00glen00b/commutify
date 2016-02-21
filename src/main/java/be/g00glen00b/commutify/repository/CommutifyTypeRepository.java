package be.g00glen00b.commutify.repository;

import be.g00glen00b.commutify.entity.CommutifyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommutifyTypeRepository extends JpaRepository<CommutifyType, Long> {
}

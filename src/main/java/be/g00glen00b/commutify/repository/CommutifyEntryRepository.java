package be.g00glen00b.commutify.repository;

import be.g00glen00b.commutify.entity.CommutifyEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommutifyEntryRepository extends JpaRepository<CommutifyEntry, Long> {
}

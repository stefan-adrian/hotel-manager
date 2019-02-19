package fii.hotel.manager.repository;

import fii.hotel.manager.model.SpaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaTypeRepository extends JpaRepository<SpaType, Long> {
}

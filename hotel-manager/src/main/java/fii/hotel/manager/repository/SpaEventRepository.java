package fii.hotel.manager.repository;

import fii.hotel.manager.model.SpaEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaEventRepository extends JpaRepository<SpaEvent, Long> {
}

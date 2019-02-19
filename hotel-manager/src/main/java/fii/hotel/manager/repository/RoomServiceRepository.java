package fii.hotel.manager.repository;

import fii.hotel.manager.model.RoomService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomServiceRepository extends JpaRepository<RoomService, Long> {
}

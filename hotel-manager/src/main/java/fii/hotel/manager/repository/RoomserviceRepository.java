package fii.hotel.manager.repository;

import fii.hotel.manager.model.Roomservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomserviceRepository extends JpaRepository<Roomservice, Long> {
}

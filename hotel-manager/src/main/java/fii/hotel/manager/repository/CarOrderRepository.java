package fii.hotel.manager.repository;

import fii.hotel.manager.model.CarOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOrderRepository extends JpaRepository<CarOrder, Long> {
}

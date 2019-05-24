package fii.hotel.manager.repository;

import fii.hotel.manager.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Transactional
    @Query("SELECT r FROM Room r LEFT JOIN FETCH r.bookings b LEFT JOIN FETCH r.category c " +
            " WHERE :id = r.id")
    Optional<Room> findByIdFetchBookings(@Param("id") Long id);
}

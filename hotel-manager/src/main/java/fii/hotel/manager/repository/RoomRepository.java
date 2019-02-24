package fii.hotel.manager.repository;

import fii.hotel.manager.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r LEFT JOIN FETCH r.bookings b " +
            " WHERE :id = r.id")
    Optional<Room> findByIdFetchBookings(@Param("id") Long id);
}

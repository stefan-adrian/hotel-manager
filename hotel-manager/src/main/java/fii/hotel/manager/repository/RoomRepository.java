package fii.hotel.manager.repository;

import fii.hotel.manager.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Transactional
    @Query("SELECT r FROM Room r LEFT JOIN FETCH r.bookings b LEFT JOIN FETCH r.category c " +
            " WHERE :id = r.id")
    Optional<Room> findByIdFetchBookings(@Param("id") Long id);

    @Transactional
    @Query("SELECT r FROM Room r LEFT JOIN FETCH r.bookings b LEFT JOIN FETCH r.category c " +
            " ORDER BY r.category")
    Set<Room> getRoomsFetchingBookingsCategory();

    @Transactional
    @Query("SELECT r FROM Room r LEFT JOIN FETCH r.bookings b LEFT JOIN FETCH r.category c " +
            " WHERE c.name=:roomCategory")
    Set<Room> getRoomsByCategoryAvailableBetweenDates(@Param("roomCategory") String roomCategory);
}

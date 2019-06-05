package fii.hotel.manager.repository;

import fii.hotel.manager.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Transactional
    @Query("SELECT b FROM Booking b LEFT JOIN FETCH b.carOrders co LEFT JOIN FETCH b.spaEvents se " +
            " LEFT JOIN FETCH b.roomservices rs LEFT JOIN FETCH rs.aliments WHERE :id = b.id")
    Optional<Booking> findByIdFetchCarOrders(@Param("id") Long id);

    @Query("SELECT count(b) FROM Booking b WHERE b.bookingTime >= :givenDate")
    Integer getNumberOfBookingsAfterDate(@Param("givenDate") LocalDateTime givenDate);

    @Transactional
    @Query("SELECT b FROM Booking b JOIN b.customer c WHERE c.email= :email")
    List<Booking> getBookingsByCustomerEmail(@Param("email") String email);

    @Transactional
    @Query("SELECT b FROM Booking b JOIN b.customer c LEFT JOIN FETCH b.room" +
            " LEFT JOIN FETCH b.carOrders co LEFT JOIN FETCH b.spaEvents se" +
            " LEFT JOIN FETCH b.roomservices rs LEFT JOIN FETCH rs.aliments" +
            " WHERE c.email= :email ORDER BY b.fromTime DESC ")
    List<Booking> getBookingsFetchingRoomByCustomerEmail(@Param("email") String email);
}

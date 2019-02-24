package fii.hotel.manager.repository;

import fii.hotel.manager.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b LEFT JOIN FETCH b.carOrders co LEFT JOIN FETCH b.spaEvents se " +
            " WHERE :id = b.id")
    Optional<Booking> findByIdFetchCarOrders(@Param("id") Long id);
}

package fii.hotel.manager.repository;

import fii.hotel.manager.model.Roomservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface RoomserviceRepository extends JpaRepository<Roomservice, Long> {

    @Transactional
    @Query("SELECT rs FROM Roomservice rs JOIN FETCH rs.aliments a JOIN FETCH rs.booking b " +
            "JOIN FETCH b.customer c JOIN FETCH b.room WHERE c.email= :email ORDER BY rs.timeOfOrder DESC")
    Set<Roomservice> getRoomserviceByCustomerEmail(@Param("email") String email);
}

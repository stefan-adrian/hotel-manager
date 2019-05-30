package fii.hotel.manager.repository;

import fii.hotel.manager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Transactional
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.rooms r LEFT JOIN FETCH r.bookings b ")
    Set<Category> findAllCategoriesFetchingRoomsFetchingBookings();
}

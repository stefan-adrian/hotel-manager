package fii.hotel.manager.repository;

import fii.hotel.manager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Transactional
    Optional<Customer> findByEmail(String email);
}

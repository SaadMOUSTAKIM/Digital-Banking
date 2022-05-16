package ma.enset.moustakimebankingback.repositories;

import ma.enset.moustakimebankingback.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

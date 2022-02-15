package pl.bpiotrowski.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bpiotrowski.crm.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByName(String name);

}

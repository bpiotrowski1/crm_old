package pl.bpiotrowski.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bpiotrowski.crm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByName(String name);

}

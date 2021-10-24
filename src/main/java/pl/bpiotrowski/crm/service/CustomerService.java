package pl.bpiotrowski.crm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bpiotrowski.crm.dto.CustomerDto;
import pl.bpiotrowski.crm.entity.Customer;
import pl.bpiotrowski.crm.exception.CustomerAlreadyExistsException;
import pl.bpiotrowski.crm.repository.CustomerRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void createCustomer(CustomerDto customerDto) {
        if (customerRepository.findByName(customerDto.getName()).isPresent())
            throw new CustomerAlreadyExistsException(customerDto.getName());

        customerRepository.save(mapCustomerDtoToEntity(customerDto));
    }

    private String getDateTimeNow() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private Customer mapCustomerDtoToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setName(customerDto.getName());
        customer.setField1(customerDto.getField1());
        customer.setField2(customerDto.getField2());
        customer.setField3(customerDto.getField3());
        customer.setField4(customerDto.getField4());
        customer.setField5(customerDto.getField5());
        customer.setField6(customerDto.getField6());
        customer.setField7(customerDto.getField7());
        customer.setCheck1(customerDto.isCheck1());
        customer.setCheck2(customerDto.isCheck2());
        customer.setCheck3(customerDto.isCheck3());
        customer.setDescription(customerDto.getDescription());
        customer.setLastUpdate(getDateTimeNow());

        return customer;
    }

}

package pl.bpiotrowski.crm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bpiotrowski.crm.dto.CustomerDto;
import pl.bpiotrowski.crm.entity.Customer;
import pl.bpiotrowski.crm.exception.CustomerAlreadyExistsException;
import pl.bpiotrowski.crm.repository.CustomerRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customersList = customerRepository.findAll();
        List<CustomerDto> result = new ArrayList<>();

        for(Customer customer : customersList) {
            result.add(mapCustomerEntityToDto(customer));
        }

        return result;
    }

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

    private CustomerDto mapCustomerEntityToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setName(customer.getName());
        customerDto.setField1(customer.getField1());
        customerDto.setField2(customer.getField2());
        customerDto.setField3(customer.getField3());
        customerDto.setField4(customer.getField4());
        customerDto.setField5(customer.getField5());
        customerDto.setField6(customer.getField6());
        customerDto.setField7(customer.getField7());
        customerDto.setCheck1(customer.isCheck1());
        customerDto.setCheck2(customer.isCheck2());
        customerDto.setCheck3(customer.isCheck3());
        customerDto.setLastUpdate(customer.getLastUpdate());

        return customerDto;
    }
}

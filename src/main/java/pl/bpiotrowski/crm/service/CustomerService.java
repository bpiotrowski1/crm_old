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

    public CustomerDto findById(Long id) {
        return mapCustomerEntityToDto(customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer " + id + " not found")));
    }

    public void createCustomer(CustomerDto customerDto) {
        if (customerRepository.findByName(customerDto.getName()) != null)
            throw new CustomerAlreadyExistsException(customerDto.getName());

        customerRepository.save(mapCustomerDtoToEntity(customerDto));
    }

    public void update(CustomerDto customerDto) throws Exception {
        Customer updatedCustomer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new RuntimeException("Customer " + customerDto.getId() + " not found"));

        Customer checkNameCustomer = customerRepository.findByName(customerDto.getName());
        if (checkNameCustomer != null && customerDto.getName().equals(checkNameCustomer.getName())
                && !customerDto.getName().equals(updatedCustomer.getName())) {
            throw new CustomerAlreadyExistsException(customerDto.getName());
        }

        customerRepository.save(setFieldsFromDto(updatedCustomer, customerDto));
    }

    private String getDateTimeNow() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private Customer mapCustomerDtoToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        return setFieldsFromDto(customer, customerDto);
    }

    private Customer setFieldsFromDto(Customer entity, CustomerDto dto) {
        entity.setName(dto.getName());
        entity.setField1(dto.getField1());
        entity.setField2(dto.getField2());
        entity.setField3(dto.getField3());
        entity.setField4(dto.getField4());
        entity.setField5(dto.getField5());
        entity.setField6(dto.getField6());
        entity.setField7(dto.getField7());
        entity.setCheck1(dto.isCheck1());
        entity.setCheck2(dto.isCheck2());
        entity.setCheck3(dto.isCheck3());
        entity.setDescription(dto.getDescription());
        entity.setLastUpdate(getDateTimeNow());
        return entity;
    }

    private CustomerDto mapCustomerEntityToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
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

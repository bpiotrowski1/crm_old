package pl.bpiotrowski.crm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bpiotrowski.crm.dto.CustomerDto;
import pl.bpiotrowski.crm.entity.Customer;
import pl.bpiotrowski.crm.exception.CustomerAlreadyExistsException;
import pl.bpiotrowski.crm.exception.CustomerNotFoundException;
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
                .orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    public CustomerDto findByName(String name) {
        return mapCustomerEntityToDto(customerRepository.findByName(name)
                .orElseThrow(() -> new CustomerNotFoundException(name)));
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        if (customerRepository.findByName(customerDto.getName()).isPresent())
            throw new CustomerAlreadyExistsException(customerDto.getName());

        return mapCustomerEntityToDto(customerRepository.save(mapCustomerDtoToEntity(customerDto)));
    }

    public void update(CustomerDto customerDto) throws Exception {
        Customer updatedCustomer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new CustomerNotFoundException(customerDto.getId()));

        Customer checkNameCustomer = customerRepository.findByName(customerDto.getName())
                .orElseThrow(() -> new CustomerNotFoundException(customerDto.getName()));
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
        CustomerDto dto = new CustomerDto();

        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setField1(customer.getField1());
        dto.setField2(customer.getField2());
        dto.setField3(customer.getField3());
        dto.setField4(customer.getField4());
        dto.setField5(customer.getField5());
        dto.setField6(customer.getField6());
        dto.setField7(customer.getField7());
        dto.setCheck1(customer.isCheck1());
        dto.setCheck2(customer.isCheck2());
        dto.setCheck3(customer.isCheck3());
        dto.setLastUpdate(customer.getLastUpdate());

        return dto;
    }
}

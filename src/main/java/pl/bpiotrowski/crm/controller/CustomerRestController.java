package pl.bpiotrowski.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bpiotrowski.crm.dto.CustomerDto;
import pl.bpiotrowski.crm.service.CustomerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer/api")
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("/edit/{id}")
    public CustomerDto getCustomerToEdit(@PathVariable Long id) {
        return customerService.findById(id);
    }

}

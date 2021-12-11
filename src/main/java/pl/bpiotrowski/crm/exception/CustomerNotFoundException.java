package pl.bpiotrowski.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Customer not found.")
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String name) {
        super("Customer: " + name + " - not found");
    }

    public CustomerNotFoundException(Long id) {
        super("Customer: " + id + " - not found");
    }

}

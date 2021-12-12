package pl.bpiotrowski.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Customer already exists.")
public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String name) {
        super("Customer: " + name + " - already exists");
    }

}

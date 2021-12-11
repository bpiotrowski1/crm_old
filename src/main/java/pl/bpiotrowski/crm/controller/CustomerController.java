package pl.bpiotrowski.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bpiotrowski.crm.dto.CustomerDto;
import pl.bpiotrowski.crm.service.CustomerService;
import pl.bpiotrowski.crm.service.SettingsService;

import javax.validation.Valid;

@RequestMapping
@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final SettingsService settingsService;

    @GetMapping("/")
    public String getCustomers(Model model) {
        model.addAttribute("customersList", customerService.getAllCustomers());
        model.addAttribute("settings", settingsService.getSettings());
        return "customers";
    }

    @GetMapping("/customer/add")
    public String getCustomerAddForm(Model model) {
        model.addAttribute("customerForm", new CustomerDto());
        model.addAttribute("settings", settingsService.getSettings());
        return "addCustomer";
    }

    @PostMapping("/customer/add")
    public String saveCustomer(@Valid @ModelAttribute CustomerDto customerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.getErrorCount() > 1) {      //1 - id null
            model.addAttribute("customerForm", customerDto);
            for (int i = 0; i < bindingResult.getErrorCount(); i++) {
                if (!bindingResult.getFieldErrors().get(i).getField().equals("id")) {
                    model.addAttribute(bindingResult.getFieldErrors().get(i).getField() + "Error", 1);
                }
            }
            return "addCustomer";
        }
        customerService.createCustomer(customerDto);
        return "redirect:/";
    }

}

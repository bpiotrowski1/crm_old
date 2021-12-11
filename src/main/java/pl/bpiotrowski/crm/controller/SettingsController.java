package pl.bpiotrowski.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bpiotrowski.crm.dto.SettingsDto;
import pl.bpiotrowski.crm.service.SettingsService;

import javax.validation.Valid;

@RequestMapping
@Controller
@RequiredArgsConstructor
public class SettingsController {

    private final SettingsService settingsService;

    @GetMapping("/settings")
    public String getSettingsForm(Model model) {
        model.addAttribute("settingsForm", settingsService.getSettings());
        return "settings";
    }

    @PostMapping("/settings")
    public String saveCustomer(@Valid @ModelAttribute SettingsDto settingsDto, BindingResult bindingResult, Model model) {
        if (bindingResult.getErrorCount() > 1) {      //1 - id null
            model.addAttribute("settingsForm", settingsDto);
            for (int i = 0; i < bindingResult.getErrorCount(); i++) {
                if (!bindingResult.getFieldErrors().get(i).getField().equals("id")) {
                    model.addAttribute(bindingResult.getFieldErrors().get(i).getField() + "Error", 1);
                }
            }
            return "settings";
        }
        settingsService.saveSettings(settingsDto);
        return "redirect:/";
    }

}

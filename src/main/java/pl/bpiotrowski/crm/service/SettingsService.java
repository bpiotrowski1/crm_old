package pl.bpiotrowski.crm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bpiotrowski.crm.dto.SettingsDto;
import pl.bpiotrowski.crm.entity.Settings;
import pl.bpiotrowski.crm.repository.SettingsRepository;

@Service
@RequiredArgsConstructor
public class SettingsService {

    private final SettingsRepository settingsRepository;

    public void createCustomer(SettingsDto settingsDto) {
        settingsRepository.save(mapSettingsDtoToEntity(settingsDto));
    }

    private Settings mapSettingsDtoToEntity(SettingsDto settingsDto) {
        Settings settings = new Settings();

        settings.setField1(settingsDto.getField1());
        settings.setField2(settingsDto.getField2());
        settings.setField3(settingsDto.getField3());
        settings.setField4(settingsDto.getField4());
        settings.setField5(settingsDto.getField5());
        settings.setField6(settingsDto.getField6());
        settings.setField7(settingsDto.getField7());
        settings.setCheck1(settingsDto.getCheck1());
        settings.setCheck2(settingsDto.getCheck2());
        settings.setCheck3(settingsDto.getCheck3());
        return settings;
    }

}

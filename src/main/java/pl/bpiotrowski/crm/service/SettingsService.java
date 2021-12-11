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

    public SettingsDto getSettings() {
        Settings settings = settingsRepository.findFirstByOrderByIdDesc();
        return mapSettingsEntityToDto(settings == null ? new Settings() : settings);
    }

    private SettingsDto mapSettingsEntityToDto(Settings entity) {
        SettingsDto dto = new SettingsDto();

        dto.setField1(entity.getField1());
        dto.setField2(entity.getField2());
        dto.setField3(entity.getField3());
        dto.setField4(entity.getField4());
        dto.setField5(entity.getField5());
        dto.setField6(entity.getField6());
        dto.setField7(entity.getField7());
        dto.setCheck1(entity.getCheck1());
        dto.setCheck2(entity.getCheck2());
        dto.setCheck3(entity.getCheck3());

        return dto;
    }

    private Settings mapSettingsDtoToEntity(SettingsDto dto) {
        Settings settings = new Settings();

        settings.setField1(dto.getField1());
        settings.setField2(dto.getField2());
        settings.setField3(dto.getField3());
        settings.setField4(dto.getField4());
        settings.setField5(dto.getField5());
        settings.setField6(dto.getField6());
        settings.setField7(dto.getField7());
        settings.setCheck1(dto.getCheck1());
        settings.setCheck2(dto.getCheck2());
        settings.setCheck3(dto.getCheck3());

        return settings;
    }

}

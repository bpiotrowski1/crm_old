package pl.bpiotrowski.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bpiotrowski.crm.entity.Settings;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
}

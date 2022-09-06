package org.globaltest.configuration.repository;

import org.globaltest.configuration.entity.Config;
import org.globaltest.configuration.entity.ConfigDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config,Long> {
    ConfigDto getBySerialNumber(String serialNumber);
}

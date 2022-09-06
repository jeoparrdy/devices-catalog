package org.globaltest.configuration.service;

import lombok.RequiredArgsConstructor;
import org.globaltest.configuration.entity.Config;
import org.globaltest.configuration.entity.ConfigDto;
import org.globaltest.configuration.repository.ConfigRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigService {
    private final ConfigRepository configRepository;

    public void saveConfig(ConfigDto configDto){
        Config config = Config.builder()
                .ip(configDto.ip())
                .serialNumber(configDto.serialNumber())
                .netmask(configDto.netmask())
                .build();
        configRepository.save(config);
    }

    public ConfigDto getConfigBySerialNumber(String serialNumber){
        return configRepository.getBySerialNumber(serialNumber);
    }

    public List<Config> getAll() {
        return configRepository.findAll();
    }
}

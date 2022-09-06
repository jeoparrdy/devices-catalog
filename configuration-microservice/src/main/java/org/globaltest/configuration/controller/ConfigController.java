package org.globaltest.configuration.controller;

import lombok.RequiredArgsConstructor;
import org.globaltest.configuration.entity.Config;
import org.globaltest.configuration.entity.ConfigDto;
import org.globaltest.configuration.service.ConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/configurations")
@RequiredArgsConstructor
public class ConfigController {
    private final ConfigService configService;
    @PostMapping("/add")
    public void saveConfig(@RequestBody ConfigDto configDto){
        configService.saveConfig(configDto);
    }
    @GetMapping("/{serialNumber}")
    public ConfigDto getConfigBySerialNumber(@PathVariable("serialNumber") String serialNumber){
        return configService.getConfigBySerialNumber(serialNumber);
    }

    @GetMapping("/all")
    public List<Config> getAllConfigurations(){
        return configService.getAll();
    }

}

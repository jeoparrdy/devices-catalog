package org.globaltest.registry.service;

import lombok.RequiredArgsConstructor;
import org.globaltest.registry.entity.ConfigDto;
import org.globaltest.registry.entity.Device;
import org.globaltest.registry.entity.DeviceDto;
import org.globaltest.registry.exception.ConfigNotFoundException;
import org.globaltest.registry.repository.DeviceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final RestTemplate restTemplate;
    private final DeviceRepository deviceRepository;

    public void saveNewDevice(DeviceDto deviceDto) {
        ConfigDto configDto = getConfigBySerialNumber(deviceDto.serialNumber());
        Device device = Device.builder()
                .vendor(deviceDto.vendor())
                .model(deviceDto.model())
                .serialNumber(deviceDto.serialNumber())
                .mac(deviceDto.mac())
                .ip(configDto.ip())//getting from conf microservice
                .netmask(configDto.netmask())//getting from conf microservice
                .build();

        deviceRepository.save(device);
    }

    public ConfigDto getConfigBySerialNumber(String serialNumber) {
        var configDto = Optional.ofNullable(
                restTemplate.getForObject("http://localhost:8081/api/v1/configurations/{serialNumber}",
                        ConfigDto.class,serialNumber))
                .orElseThrow(() ->new ConfigNotFoundException(
                        String.format("No such configuration. Please add configuration for the device with s/n: %s",
                                serialNumber)));
        return configDto;
    }

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    public Device getById(Long deviceId) {
        return deviceRepository.findById(deviceId).get();
    }

    public List<Device> getByVendorAndModel(String vendor, String model) {
        return deviceRepository.getDevicesByVendorAndModel(vendor,model);
    }
}

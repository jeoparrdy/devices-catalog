package org.globaltest.registry.controller;

import lombok.RequiredArgsConstructor;
import org.globaltest.registry.entity.Device;
import org.globaltest.registry.entity.DeviceDto;
import org.globaltest.registry.service.DeviceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    @PostMapping("/add")
    public void registerDevice(@RequestBody DeviceDto deviceDto){

        deviceService.saveNewDevice(deviceDto);
    }

    @GetMapping("/{serialNumber}")
    public String checkForConfig(@PathVariable("serialNumber") String serialNumber){
        return deviceService.getConfigBySerialNumber(serialNumber).ip();
    }

    @GetMapping("/all")
    public List<Device> getAllDevices(){
        return deviceService.getAll();
    }

    @GetMapping("/get/{deviceId}")
    public Device getDeviceById(@PathVariable("deviceId") Long deviceId){
        return deviceService.getById(deviceId);
    }

    @GetMapping("/filter/{vendor}/{model}")
    public List<Device> getAllByVendorAndModel(@PathVariable String vendor, @PathVariable String model){
        return deviceService.getByVendorAndModel(vendor,model);
    }
}

package org.globaltest.registry.repository;

import org.globaltest.registry.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    List<Device> getDevicesByVendorAndModel(String vendor, String model);
}

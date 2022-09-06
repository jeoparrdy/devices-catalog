package org.globaltest.registry.entity;

public record DeviceDto(
        String vendor,
        String model,
        String serialNumber,
        String mac,
        String ip,
        String netmask
) {
}

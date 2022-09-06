package org.globaltest.registry.entity;

public record ConfigDto(
        String ip,
        String netmask,
        String serialNumber
) {
}

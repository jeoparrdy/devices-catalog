package org.globaltest.configuration.entity;

public record ConfigDto(
        String ip,
        String netmask,
        String serialNumber
) {
}

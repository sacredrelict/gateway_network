package com.omikheev.testapp.gateways.network.builder;

import com.omikheev.testapp.gateways.network.dto.request.CreateDeviceDto;
import com.omikheev.testapp.gateways.network.dto.request.UpdateDeviceDto;
import com.omikheev.testapp.gateways.network.entity.DeviceEntity;
import com.omikheev.testapp.gateways.network.enums.DeviceStatus;

import java.time.LocalDateTime;

/**
 * Util class for Device builders
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
public final class DeviceBuilder {

    private DeviceBuilder() {

    }

    public static CreateDeviceDto buildCreateDeviceDto() {
        return new CreateDeviceDto(
                11112222,
                "Test vendor",
                LocalDateTime.now(),
                DeviceStatus.ONLINE
        );
    }

    public static UpdateDeviceDto buildUpdateDeviceDto() {
        return new UpdateDeviceDto(
                11112222,
                "Test vendor",
                DeviceStatus.ONLINE
        );
    }

    public static DeviceEntity buildDeviceEntity() {
        return new DeviceEntity(
                1L,
                11112222,
                "Test vendor",
                LocalDateTime.now(),
                DeviceStatus.ONLINE,
                null
        );
    }

    public static DeviceEntity buildDeviceEntityWithGateway() {
        return new DeviceEntity(
                1L,
                11112222,
                "Test vendor",
                LocalDateTime.now(),
                DeviceStatus.ONLINE,
                GatewayBuilder.buildGatewayEntity()
        );
    }
}

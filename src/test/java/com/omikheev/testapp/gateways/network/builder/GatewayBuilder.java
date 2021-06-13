package com.omikheev.testapp.gateways.network.builder;

import com.omikheev.testapp.gateways.network.dto.request.CreateGatewayDto;
import com.omikheev.testapp.gateways.network.dto.request.UpdateGatewayDto;
import com.omikheev.testapp.gateways.network.entity.DeviceEntity;
import com.omikheev.testapp.gateways.network.entity.GatewayEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
public final class GatewayBuilder {

    private GatewayBuilder() {

    }

    public static CreateGatewayDto buildCreateGatewayDto() {
        return new CreateGatewayDto(
                "100812345678300",
                "Master 001",
                "192.168.0.1"
        );
    }

    public static UpdateGatewayDto buildUpdateGatewayDto() {
        return new UpdateGatewayDto(
                "100812345678300",
                "Master 001",
                "192.168.0.1"
        );
    }

    public static GatewayEntity buildGatewayEntity() {
        return new GatewayEntity(
                1L,
                "100812345678300",
                "Master 001",
                "192.168.0.1",
                Collections.emptyList()
        );
    }

    public static GatewayEntity buildGatewayEntityWithDevices(int devicesCount) {
        List<DeviceEntity> devices = new ArrayList<>();
        for (int i = 0; i < devicesCount; i++) {
            devices.add(DeviceBuilder.buildDeviceEntity());
        }

        return new GatewayEntity(
                1L,
                "100812345678300",
                "Master 001",
                "192.168.0.1",
                devices
        );
    }
}

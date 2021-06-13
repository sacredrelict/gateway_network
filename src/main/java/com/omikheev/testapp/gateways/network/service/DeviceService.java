package com.omikheev.testapp.gateways.network.service;

import com.omikheev.testapp.gateways.network.dto.DeviceDto;
import com.omikheev.testapp.gateways.network.dto.request.CreateDeviceDto;
import com.omikheev.testapp.gateways.network.dto.request.UpdateDeviceDto;

import java.util.Collection;

/**
 * Base interface for Device service layer
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
public interface DeviceService {

    /**
     * Get all devices
     *
     * @return collection of devices
     */
    Collection<DeviceDto> getAllDevices();

    /**
     * Get device by id
     *
     * @param id           device id
     * @return             {@link DeviceDto}
     * @throws Exception   possible exception
     */
    DeviceDto getDevice(Long id) throws Exception;

    /**
     * Create device
     *
     * @param deviceDto    {@link CreateDeviceDto}
     * @return             created device in dto {@link DeviceDto}
     */
    DeviceDto createDevice(CreateDeviceDto deviceDto);

    /**
     * Update device
     *
     * @param id           device id
     * @param deviceDto    {@link UpdateDeviceDto}
     * @return             updated device in dto {@link DeviceDto}
     */
    DeviceDto updateDevice(Long id, UpdateDeviceDto deviceDto);

    /**
     * Add device to gateway
     *
     * @param deviceId     device id
     * @param gatewayId    gateway id
     * @return             updated device in dto {@link DeviceDto}
     * @throws Exception   possible exception
     */
    DeviceDto addDeviceToGateway(Long deviceId, Long gatewayId) throws Exception;

    /**
     * Remove device from gateway
     *
     * @param deviceId     device id
     * @return             {@link DeviceDto}
     * @throws Exception   possible exception
     */
    DeviceDto removeDeviceFromGateway(Long deviceId) throws Exception;
}

package com.omikheev.testapp.gateways.network.service;

import com.omikheev.testapp.gateways.network.builder.DeviceBuilder;
import com.omikheev.testapp.gateways.network.builder.GatewayBuilder;
import com.omikheev.testapp.gateways.network.configuration.AppProperties;
import com.omikheev.testapp.gateways.network.dto.DeviceDto;
import com.omikheev.testapp.gateways.network.entity.DeviceEntity;
import com.omikheev.testapp.gateways.network.entity.GatewayEntity;
import com.omikheev.testapp.gateways.network.exception.DeviceModifyException;
import com.omikheev.testapp.gateways.network.repository.DeviceRepository;
import com.omikheev.testapp.gateways.network.service.impl.DeviceServiceImpl;
import com.omikheev.testapp.gateways.network.service.impl.GatewayServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link DeviceService}
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    private static final int MAX_PERIPHERAL_DEVICES = 10;

    private DeviceRepository deviceRepository = Mockito.mock(DeviceRepository.class);
    private GatewayServiceImpl gatewayService = Mockito.mock(GatewayServiceImpl.class);
    private AppProperties appProperties = Mockito.mock(AppProperties.class);
    private DeviceServiceImpl deviceService;

    @BeforeEach
    void init() {
        ModelMapper modelMapper = new ModelMapper();
        deviceService = new DeviceServiceImpl(deviceRepository, gatewayService, modelMapper, appProperties);
    }

    @Test
    void testGetAllDevices() {
        when(deviceRepository.findAll()).thenReturn(Collections.singletonList(DeviceBuilder.buildDeviceEntity()));
        Collection<DeviceDto> devices = deviceService.getAllDevices();

        assertNotNull(devices);
        assertTrue(devices.size() > 0);
    }

    @Test
    void testGetDevice() throws Exception {
        when(deviceRepository.findById(any())).thenReturn(Optional.of(DeviceBuilder.buildDeviceEntity()));
        DeviceDto deviceDto = deviceService.getDevice(1L);

        assertNotNull(deviceDto);
        assertEquals(11112222, deviceDto.getUuid());
    }

    @Test
    void testCreateDevice() {
        DeviceEntity deviceEntity = DeviceBuilder.buildDeviceEntity();
        deviceEntity.setId(null);
        when(deviceRepository.save(any())).thenReturn(deviceEntity);
        DeviceDto deviceDto = deviceService.createDevice(DeviceBuilder.buildCreateDeviceDto());

        assertNotNull(deviceDto);
        assertEquals(11112222, deviceDto.getUuid());
    }

    @Test
    void testUpdateDevice() {
        DeviceEntity deviceEntity = DeviceBuilder.buildDeviceEntity();
        when(deviceRepository.findById(any())).thenReturn(Optional.of(deviceEntity));
        when(deviceRepository.save(any())).thenReturn(deviceEntity);
        DeviceDto deviceDto = deviceService.updateDevice(1L, DeviceBuilder.buildUpdateDeviceDto());

        verify(deviceRepository).save(deviceEntity);
        assertNotNull(deviceDto);
        assertEquals(11112222, deviceDto.getUuid());
    }

    @Test
    void testAddDeviceToGateway() throws Exception {
        DeviceEntity deviceEntity = DeviceBuilder.buildDeviceEntity();
        GatewayEntity gatewayEntity = GatewayBuilder.buildGatewayEntity();
        when(appProperties.getMaxPeripheralDevices()).thenReturn(MAX_PERIPHERAL_DEVICES);
        when(deviceRepository.findById(any())).thenReturn(Optional.of(deviceEntity));
        when(gatewayService.getGatewayById(anyLong())).thenReturn(gatewayEntity);
        when(deviceRepository.save(any())).thenReturn(deviceEntity);
        DeviceDto deviceDto = deviceService.addDeviceToGateway(1L, 1L);

        verify(deviceRepository).save(deviceEntity);
        assertNotNull(deviceDto);
        assertNotNull(deviceDto.getGateway());
        assertEquals(gatewayEntity.getId(), deviceDto.getGateway());
    }

    @Test
    void testAddDeviceToGateway_whenMaxDevicesReached() throws Exception {
        DeviceEntity deviceEntity = DeviceBuilder.buildDeviceEntity();
        GatewayEntity gatewayEntity = GatewayBuilder.buildGatewayEntityWithDevices(MAX_PERIPHERAL_DEVICES);
        when(appProperties.getMaxPeripheralDevices()).thenReturn(MAX_PERIPHERAL_DEVICES);
        when(deviceRepository.findById(any())).thenReturn(Optional.of(deviceEntity));
        when(gatewayService.getGatewayById(anyLong())).thenReturn(gatewayEntity);
        when(deviceRepository.save(any())).thenReturn(deviceEntity);

        assertThrows(
                DeviceModifyException.class,
                () -> deviceService.addDeviceToGateway(1L, 1L),
                "Device cannot be added to gateway " + 1L + ", because maximum devices for gateway is reached"
        );
    }

    @Test
    void testRemoveDeviceFromGateway() throws Exception {
        DeviceEntity deviceEntity = DeviceBuilder.buildDeviceEntityWithGateway();
        when(deviceRepository.findById(any())).thenReturn(Optional.of(deviceEntity));
        when(deviceRepository.save(any())).thenReturn(deviceEntity);
        DeviceDto deviceDto = deviceService.removeDeviceFromGateway(1L);

        verify(deviceRepository).save(deviceEntity);
        assertNotNull(deviceDto);
        assertNull(deviceDto.getGateway());
    }

    @Test
    void testRemoveDeviceFromGateway_whenDeviceWithoutGateway() throws Exception {
        DeviceEntity deviceEntity = DeviceBuilder.buildDeviceEntity();
        when(deviceRepository.findById(any())).thenReturn(Optional.of(deviceEntity));
        when(deviceRepository.save(any())).thenReturn(deviceEntity);

        assertThrows(
                DeviceModifyException.class,
                () -> deviceService.removeDeviceFromGateway(1L),
                "Device with id " + 1L + " does not belong to any gateway."
        );
    }
}

package com.omikheev.testapp.gateways.network.service.impl;

import com.omikheev.testapp.gateways.network.configuration.AppProperties;
import com.omikheev.testapp.gateways.network.dto.DeviceDto;
import com.omikheev.testapp.gateways.network.dto.request.CreateDeviceDto;
import com.omikheev.testapp.gateways.network.dto.request.UpdateDeviceDto;
import com.omikheev.testapp.gateways.network.entity.DeviceEntity;
import com.omikheev.testapp.gateways.network.entity.GatewayEntity;
import com.omikheev.testapp.gateways.network.exception.DeviceModifyException;
import com.omikheev.testapp.gateways.network.exception.NotFoundException;
import com.omikheev.testapp.gateways.network.exception.RepositoryException;
import com.omikheev.testapp.gateways.network.repository.DeviceRepository;
import com.omikheev.testapp.gateways.network.service.DeviceService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Device service layer implementation
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    private static final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);

    private final DeviceRepository deviceRepository;
    private final GatewayServiceImpl gatewayService;
    private final ModelMapper modelMapper;
    private final AppProperties appProperties;

    public DeviceServiceImpl(DeviceRepository deviceRepository, GatewayServiceImpl gatewayService, ModelMapper modelMapper, AppProperties appProperties) {
        this.deviceRepository = requireNonNull(deviceRepository, "deviceRepository");
        this.gatewayService = requireNonNull(gatewayService, "gatewayService");
        this.modelMapper = requireNonNull(modelMapper, "modelMapper");
        this.appProperties = requireNonNull(appProperties, "appProperties");
    }

    @Override
    public Collection<DeviceDto> getAllDevices() {
        List<DeviceEntity> deviceEntities = deviceRepository.findAll();
        return deviceEntities
                .stream()
                .map(device -> modelMapper.map(device, DeviceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DeviceDto getDevice(Long id) throws Exception {
        return modelMapper.map(getDeviceById(id), DeviceDto.class);
    }

    @Override
    public DeviceDto createDevice(CreateDeviceDto deviceDto) {
        deviceDto.setCreatedAt(LocalDateTime.now());
        try {
            DeviceEntity deviceEntity = deviceRepository.save(modelMapper.map(deviceDto, DeviceEntity.class));
            return modelMapper.map(deviceEntity, DeviceDto.class);
        } catch (Exception ex) {
            log.error("Error during saving entity", ex);
            throw new RepositoryException("Error during saving device into database. Details: " + ex.getMessage());
        }
    }

    @Override
    public DeviceDto updateDevice(Long id, UpdateDeviceDto deviceDto) {
        DeviceEntity entity = getDeviceById(id);
        entity.setId(id);
        entity.setUuid(deviceDto.getUuid());
        entity.setVendor(deviceDto.getVendor());
        entity.setStatus(deviceDto.getStatus());
        try {
            entity = deviceRepository.save(entity);
            return modelMapper.map(entity, DeviceDto.class);
        } catch (Exception ex) {
            log.error("Error during saving entity", ex);
            throw new RepositoryException("Error during saving device into database. Details: " + ex.getMessage());
        }
    }

    @Override
    public DeviceDto addDeviceToGateway(Long deviceId, Long gatewayId) throws Exception {
        DeviceEntity deviceEntity = getDeviceById(deviceId);
        GatewayEntity gatewayEntity = gatewayService.getGatewayById(gatewayId);

        if (deviceEntity.getGateway() != null) {
            throw new DeviceModifyException("Device with id " + deviceId + " already have gateway. Please, remove old gateway, before setting new gateway.");
        }
        if (gatewayEntity.getDevices().size() >= appProperties.getMaxPeripheralDevices()) {
            throw new DeviceModifyException("Device cannot be added to gateway " + gatewayId + ", because maximum devices for gateway is reached");
        }

        deviceEntity.setGateway(gatewayEntity);
        deviceEntity = deviceRepository.save(deviceEntity);
        return modelMapper.map(deviceEntity, DeviceDto.class);
    }

    @Override
    public DeviceDto removeDeviceFromGateway(Long deviceId) throws Exception {
        DeviceEntity deviceEntity = getDeviceById(deviceId);

        if (deviceEntity.getGateway() == null) {
            throw new DeviceModifyException("Device with id " + deviceId + " does not belong to any gateway.");
        }

        deviceEntity.setGateway(null);
        deviceEntity = deviceRepository.save(deviceEntity);
        return modelMapper.map(deviceEntity, DeviceDto.class);
    }

    public DeviceEntity getDeviceById(Long deviceId) {
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findById(deviceId);
        if (deviceEntityOptional.isEmpty()) {
            throw new NotFoundException("Device with id " + deviceId + " not found");
        }
        return deviceEntityOptional.get();
    }
}

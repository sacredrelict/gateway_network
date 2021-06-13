package com.omikheev.testapp.gateways.network.service.impl;

import com.omikheev.testapp.gateways.network.dto.GatewayDto;
import com.omikheev.testapp.gateways.network.dto.request.CreateGatewayDto;
import com.omikheev.testapp.gateways.network.dto.request.UpdateGatewayDto;
import com.omikheev.testapp.gateways.network.entity.GatewayEntity;
import com.omikheev.testapp.gateways.network.exception.NotFoundException;
import com.omikheev.testapp.gateways.network.exception.RepositoryException;
import com.omikheev.testapp.gateways.network.repository.GatewayRepository;
import com.omikheev.testapp.gateways.network.service.GatewayService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * Gateway service layer implementation
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Service
public class GatewayServiceImpl implements GatewayService {

    private static final Logger log = LoggerFactory.getLogger(GatewayServiceImpl.class);

    private final GatewayRepository gatewayRepository;
    private final ModelMapper modelMapper;

    public GatewayServiceImpl(GatewayRepository gatewayRepository, ModelMapper modelMapper) {
        this.gatewayRepository = requireNonNull(gatewayRepository, "gatewayRepository");
        this.modelMapper = requireNonNull(modelMapper, "modelMapper");
    }

    @Override
    public Collection<GatewayDto> getAllGateways() {
        List<GatewayEntity> gatewayEntities = gatewayRepository.findAll();

        List<GatewayDto> gatewayDtos = new ArrayList<>();
        for (GatewayEntity entity : gatewayEntities) {
            gatewayDtos.add(modelMapper.map(entity, GatewayDto.class));
        }

        return gatewayDtos;
//        return gatewayEntities
//                .stream()
//                .map(gateway -> modelMapper.map(gateway, GatewayDto.class))
//                .collect(Collectors.toList());
    }

    @Override
    public GatewayDto getGateway(Long id) throws Exception {
        return modelMapper.map(getGatewayById(id), GatewayDto.class);
    }

    @Override
    public GatewayDto createGateway(CreateGatewayDto gatewayDto) {
        try {
            GatewayEntity gatewayEntity = gatewayRepository.save(modelMapper.map(gatewayDto, GatewayEntity.class));
            return modelMapper.map(gatewayEntity, GatewayDto.class);
        } catch (Exception ex) {
            log.error("Error during saving entity", ex);
            throw new RepositoryException("Error during saving gateway into database. Details: " + ex.getMessage());
        }
    }

    @Override
    public GatewayDto updateGateway(Long id, UpdateGatewayDto gatewayDto) {
        GatewayEntity entity = getGatewayById(id);
        entity.setId(id);
        entity.setName(gatewayDto.getName());
        entity.setSerialNumber(gatewayDto.getSerialNumber());
        entity.setIpAddress(gatewayDto.getIpAddress());
        try {
            entity = gatewayRepository.save(entity);
            return modelMapper.map(entity, GatewayDto.class);
        } catch (Exception ex) {
            log.error("Error during saving entity", ex);
            throw new RepositoryException("Error during saving gateway into database. Details: " + ex.getMessage());
        }
    }

    public GatewayEntity getGatewayById(Long gatewayId) {
        Optional<GatewayEntity> gatewayEntityOptional = gatewayRepository.findById(gatewayId);
        if (gatewayEntityOptional.isEmpty()) {
            throw new NotFoundException("Gateway with id " + gatewayId + " not found");
        }
        return gatewayEntityOptional.get();
    }
}

package com.omikheev.testapp.gateways.network.service;

import com.omikheev.testapp.gateways.network.builder.GatewayBuilder;
import com.omikheev.testapp.gateways.network.dto.GatewayDto;
import com.omikheev.testapp.gateways.network.entity.GatewayEntity;
import com.omikheev.testapp.gateways.network.repository.GatewayRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link GatewayService}
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@ExtendWith(MockitoExtension.class)
class GatewayServiceTest {

    private GatewayRepository gatewayRepository = Mockito.mock(GatewayRepository.class);
    private GatewayServiceImpl gatewayService;

    @BeforeEach
    void init() {
        ModelMapper modelMapper = new ModelMapper();
        gatewayService = new GatewayServiceImpl(gatewayRepository, modelMapper);
    }

    @Test
    void testGetAllGateways() {
        when(gatewayRepository.findAll()).thenReturn(Collections.singletonList(GatewayBuilder.buildGatewayEntity()));
        Collection<GatewayDto> gateways = gatewayService.getAllGateways();

        assertNotNull(gateways);
        assertTrue(gateways.size() > 0);
    }

    @Test
    void testGetGateway() throws Exception {
        when(gatewayRepository.findById(any())).thenReturn(Optional.of(GatewayBuilder.buildGatewayEntity()));
        GatewayDto gatewayDto = gatewayService.getGateway(1L);

        assertNotNull(gatewayDto);
        assertEquals("192.168.0.1", gatewayDto.getIpAddress());
    }

    @Test
    void testCreateGateway() {
        GatewayEntity gatewayEntity = GatewayBuilder.buildGatewayEntity();
        gatewayEntity.setId(null);
        when(gatewayRepository.save(any())).thenReturn(gatewayEntity);
        GatewayDto gatewayDto = gatewayService.createGateway(GatewayBuilder.buildCreateGatewayDto());

        verify(gatewayRepository).save(gatewayEntity);
        assertNotNull(gatewayDto);
        assertEquals("192.168.0.1", gatewayDto.getIpAddress());
    }

    @Test
    void testUpdateGateway() {
        GatewayEntity gatewayEntity = GatewayBuilder.buildGatewayEntity();
        when(gatewayRepository.findById(any())).thenReturn(Optional.of(gatewayEntity));
        when(gatewayRepository.save(any())).thenReturn(gatewayEntity);
        GatewayDto gatewayDto = gatewayService.updateGateway(1L, GatewayBuilder.buildUpdateGatewayDto());

        verify(gatewayRepository).save(gatewayEntity);
        assertNotNull(gatewayDto);
        assertEquals("192.168.0.1", gatewayDto.getIpAddress());
    }
}

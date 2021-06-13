package com.omikheev.testapp.gateways.network.service;

import com.omikheev.testapp.gateways.network.dto.GatewayDto;
import com.omikheev.testapp.gateways.network.dto.request.CreateGatewayDto;
import com.omikheev.testapp.gateways.network.dto.request.UpdateGatewayDto;

import java.util.Collection;

/**
 * Base interface for Gateway service layer
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
public interface GatewayService {

    /**
     * Get all gateways
     *
     * @return collection of gateways
     */
    Collection<GatewayDto> getAllGateways();

    /**
     * Get gateway by id
     *
     * @param id           gateway id
     * @return             {@link GatewayDto}
     * @throws Exception   possible exception
     */
    GatewayDto getGateway(Long id) throws Exception;

    /**
     * Create gateway
     *
     * @param gatewayDto   {@link CreateGatewayDto}
     * @return             created gateway in dto {@link GatewayDto}
     */
    GatewayDto createGateway(CreateGatewayDto gatewayDto);

    /**
     * Update gateway
     *
     * @param id           gateway id
     * @param gatewayDto   {@link UpdateGatewayDto}
     * @return             updated gateway in dto {@link GatewayDto}
     */
    GatewayDto updateGateway(Long id, UpdateGatewayDto gatewayDto);
}

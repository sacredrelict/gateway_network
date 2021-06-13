package com.omikheev.testapp.gateways.network.controller;

import com.omikheev.testapp.gateways.network.dto.GatewayDto;
import com.omikheev.testapp.gateways.network.dto.request.CreateGatewayDto;
import com.omikheev.testapp.gateways.network.dto.request.UpdateGatewayDto;
import com.omikheev.testapp.gateways.network.service.GatewayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.HttpURLConnection;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

/**
 * Controller for gateways
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@RestController
@RequestMapping(path = "/api/v1/gateway")
@Api(value = "gateway", description = "Operations with gateways")
public class GatewayController {

    private final GatewayService gatewayService;

    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = requireNonNull(gatewayService, "gatewayService");
    }

    /**
     * @return collection of gateways   {@link GatewayDto}
     */
    @ApiOperation("Get all gateways")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK", responseContainer = "List", response = GatewayDto.class)
    })
    @GetMapping
    public Collection<GatewayDto> getAllGateways() {
        return gatewayService.getAllGateways();
    }

    /**
     * @param id             Gateway id
     * @return gateway dto   {@link GatewayDto}
     */
    @ApiOperation("Get gateway by id")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK", response = GatewayDto.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Gateway not found")
    })
    @GetMapping("/{id}")
    public GatewayDto getGateway(@PathVariable("id") Long id) throws Exception {
        return gatewayService.getGateway(id);
    }

    /**
     * @param dto                   {@link CreateGatewayDto} request dto
     * @return created entity dto   {@link GatewayDto}
     */
    @ApiOperation("Create new gateway")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "OK", response = GatewayDto.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request")
    })
    @PostMapping
    public GatewayDto createGateway(@RequestBody @Valid @NotNull CreateGatewayDto dto) {
        return gatewayService.createGateway(dto);
    }

    /**
     * @param id                    Gateway id
     * @param dto                   {@link UpdateGatewayDto} request dto
     * @return updated entity dto   {@link GatewayDto}
     */
    @ApiOperation("Update gateway")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_ACCEPTED, message = "OK", response = GatewayDto.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Gateway not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request")
    })
    @PutMapping("/{id}")
    public GatewayDto updateGateway(@PathVariable("id") Long id,
                                    @RequestBody @Valid @NotNull UpdateGatewayDto dto) {
        return gatewayService.updateGateway(id, dto);
    }
}

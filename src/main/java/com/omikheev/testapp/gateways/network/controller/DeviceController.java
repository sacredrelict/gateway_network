package com.omikheev.testapp.gateways.network.controller;

import com.omikheev.testapp.gateways.network.dto.DeviceDto;
import com.omikheev.testapp.gateways.network.dto.request.CreateDeviceDto;
import com.omikheev.testapp.gateways.network.dto.request.UpdateDeviceDto;
import com.omikheev.testapp.gateways.network.service.DeviceService;
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
 * Controller for devices
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@RestController
@RequestMapping(path = "/api/v1/device")
@Api(value = "device", description = "Operations with devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = requireNonNull(deviceService, "deviceService");
    }

    /**
     * @return collection of devices   {@link DeviceDto}
     */
    @ApiOperation("Get all devices")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK", responseContainer = "List", response = DeviceDto.class)
    })
    @GetMapping
    public Collection<DeviceDto> getAllDevices() {
        return deviceService.getAllDevices();
    }

    /**
     * @param id             Device id
     * @return device dto    {@link DeviceDto}
     */
    @ApiOperation("Get device by id")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK", response = DeviceDto.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Device not found")
    })
    @GetMapping("/{id}")
    public DeviceDto getDevice(@PathVariable("id") Long id) throws Exception {
        return deviceService.getDevice(id);
    }

    /**
     * @param dto                   {@link CreateDeviceDto} request dto
     * @return created entity dto   {@link DeviceDto}
     */
    @ApiOperation("Create new device")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "OK", response = DeviceDto.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request")
    })
    @PostMapping
    public DeviceDto createDevice(@RequestBody @Valid @NotNull CreateDeviceDto dto) {
        return deviceService.createDevice(dto);
    }

    /**
     * @param id                    Device id
     * @param dto                   {@link UpdateDeviceDto} request dto
     * @return updated entity dto   {@link DeviceDto}
     */
    @ApiOperation("Update device")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_ACCEPTED, message = "OK", response = DeviceDto.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Device not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request")
    })
    @PutMapping("/{id}")
    public DeviceDto updateGateway(@PathVariable("id") Long id,
                                    @RequestBody @Valid @NotNull UpdateDeviceDto dto) throws Exception {
        return deviceService.updateDevice(id, dto);
    }

    /**
     * @param deviceId              Device id
     * @param gatewayId             Gateway id
     * @return updated entity dto   {@link DeviceDto}
     */
    @ApiOperation("Add device to gateway")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_ACCEPTED, message = "OK", response = DeviceDto.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Device not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "Error during adding device to gateway")
    })
    @PutMapping("/{deviceId}/gateway/{gatewayId}")
    public DeviceDto addDeviceToGateway(@PathVariable("deviceId") Long deviceId,
                                        @PathVariable("gatewayId") Long gatewayId) throws Exception {
        return deviceService.addDeviceToGateway(deviceId, gatewayId);
    }

    /**
     * @param deviceId              Device id
     * @return updated entity dto   {@link DeviceDto}
     */
    @ApiOperation("Remove device from gateway")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_ACCEPTED, message = "OK", response = DeviceDto.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Device not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "Error during removing device from gateway")
    })
    @PutMapping("/{deviceId}/gateway")
    public DeviceDto removeDeviceFromGateway(@PathVariable("deviceId") Long deviceId) throws Exception {
        return deviceService.removeDeviceFromGateway(deviceId);
    }
}

package com.omikheev.testapp.gateways.network.dto.request;

import com.omikheev.testapp.gateways.network.enums.DeviceStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO representation for update Device operation
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Getter
@AllArgsConstructor
@ApiModel
public final class UpdateDeviceDto {

    @ApiModelProperty(value = "Unique UUID", example = "11112222", position = 10)
    private Integer uuid;

    @ApiModelProperty(value = "Device vendor", example = "Test vendor", position = 20)
    private String vendor;

    @ApiModelProperty(value = "Device status", example = "ONLINE", position = 30)
    private DeviceStatus status;
}

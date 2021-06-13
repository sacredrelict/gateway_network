package com.omikheev.testapp.gateways.network.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * DTO representation for update Gateway operation.
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Getter
@AllArgsConstructor
@ApiModel
public final class UpdateGatewayDto {

    @ApiModelProperty(value = "Serial number", example = "100812345678300", position = 10)
    @NotNull
    private String serialNumber;

    @ApiModelProperty(value = "Gateway name", example = "Master 001", position = 20)
    @NotNull
    private String name;

    @ApiModelProperty(value = "Gateway ipv4 address", example = "192.168.0.1", position = 30)
    @NotNull
    @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")
    private String ipAddress;
}

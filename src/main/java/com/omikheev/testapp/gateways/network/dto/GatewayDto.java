package com.omikheev.testapp.gateways.network.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO representation for Gateway entity
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class GatewayDto {

    @ApiModelProperty(value = "Gateway ID", position = 10)
    private Long id;

    @ApiModelProperty(value = "Serial number", position = 20)
    private String serialNumber;

    @ApiModelProperty(value = "Gateway name", position = 30)
    private String name;

    @ApiModelProperty(value = "Gateway ipv4 address", position = 40)
    private String ipAddress;

    @ApiModelProperty(value = "List of peripheral devices", position = 50)
    private List<DeviceDto> devices;

    @Override
    public String toString() {
        return "GatewayDto{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", devices.size=" + devices.size() +
                '}';
    }
}

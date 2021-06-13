package com.omikheev.testapp.gateways.network.dto;

import com.omikheev.testapp.gateways.network.enums.DeviceStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO representation for Device entity
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Data
@ApiModel
public class DeviceDto {

    @ApiModelProperty(value = "Device ID", position = 10)
    private Long id;

    @ApiModelProperty(value = "Unique UUID", position = 20)
    private Integer uuid;

    @ApiModelProperty(value = "Device vendor", position = 30)
    private String vendor;

    @ApiModelProperty(value = "Created time", position = 40)
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "Device status", position = 50)
    private DeviceStatus status;

    @ApiModelProperty(value = "Device gateway", position = 60)
    private Long gateway;

    /**
     * Sets gateway id.
     * This method needed to avoid recursion during the mapping.
     *
     * @param gateway   {@link GatewayDto}
     */
    public void setGateway(GatewayDto gateway) {
        this.gateway = gateway.getId();
    }
}

package com.omikheev.testapp.gateways.network.dto.request;

import com.omikheev.testapp.gateways.network.enums.DeviceStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * DTO representation for Create Device operation
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public final class CreateDeviceDto {

    @ApiModelProperty(value = "Unique UUID", example = "11112222", position = 10)
    @NotNull
    private Integer uuid;

    @ApiModelProperty(value = "Device vendor", example = "Test vendor", position = 20)
    private String vendor;

    @ApiModelProperty(value = "Created time", example = "2021-06-13T16:06:42.016Z", position = 30)
    @Setter
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "Device status", example = "ONLINE", position = 40)
    private DeviceStatus status;
}

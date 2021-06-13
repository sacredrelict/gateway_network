package com.omikheev.testapp.gateways.network.entity;

import com.omikheev.testapp.gateways.network.enums.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity for Device object
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Entity
@Table(name = "device")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private Integer uuid;

    @Column
    private String vendor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gateway_id")
    private GatewayEntity gateway;
}

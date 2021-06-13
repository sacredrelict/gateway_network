package com.omikheev.testapp.gateways.network.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity for Gateway object
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Entity
@Table(name = "gateway")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @Column
    private String name;

    @Column(name = "ip_address")
    private String ipAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="gateway", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<DeviceEntity> devices = new ArrayList<>();
}

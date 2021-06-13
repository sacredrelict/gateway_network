package com.omikheev.testapp.gateways.network.repository;

import com.omikheev.testapp.gateways.network.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Base repository interface for Device entity
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
}

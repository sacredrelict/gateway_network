package com.omikheev.testapp.gateways.network.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Application properties
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Configuration
@ConfigurationProperties(prefix = "app.properties")
@Getter
@Setter
public class AppProperties {

    private int maxPeripheralDevices;
}

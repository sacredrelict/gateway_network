package com.omikheev.testapp.gateways.network;

import com.omikheev.testapp.gateways.network.dto.DeviceDto;
import com.omikheev.testapp.gateways.network.entity.DeviceEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

/**
 * Entry class for Spring Boot application
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@SpringBootApplication
public class GatewaysNetworkApplication {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        // In case we don't want recursive mapping
        mapper.createTypeMap(DeviceEntity.class, DeviceDto.class).addMappings(mapping -> mapping.map(DeviceEntity::getGateway, DeviceDto::setGateway));

        return mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewaysNetworkApplication.class, args);
    }
}

package com.omikheev.testapp.gateways.network.exception;

/**
 * Custom exception for Device modify operations
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
public class DeviceModifyException extends RuntimeException {

    public DeviceModifyException(String msg) {
        super(msg);
    }
}

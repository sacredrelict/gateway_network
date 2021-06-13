package com.omikheev.testapp.gateways.network.exception;

/**
 * Custom exception for cases, when some resource not found
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg);
    }
}

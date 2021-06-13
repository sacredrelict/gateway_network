package com.omikheev.testapp.gateways.network.exception;

/**
 * Custom exception for repository operations
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
public class RepositoryException extends RuntimeException {

    public RepositoryException(String msg) {
        super(msg);
    }
}

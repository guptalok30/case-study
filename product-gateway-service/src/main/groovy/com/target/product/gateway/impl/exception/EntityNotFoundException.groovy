package com.target.product.gateway.impl.exception

class EntityNotFoundException extends RuntimeException {
    EntityNotFoundException(final String msg) {
        super(msg)
    }
}

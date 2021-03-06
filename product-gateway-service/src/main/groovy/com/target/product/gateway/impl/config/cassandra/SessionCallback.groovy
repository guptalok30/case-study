package com.target.product.gateway.impl.config.cassandra

import com.datastax.driver.core.Session

interface SessionCallback<T> {
    T doInSession(Session s)
}

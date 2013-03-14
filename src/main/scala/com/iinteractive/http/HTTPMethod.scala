package com.iinteractive.http

object HTTPMethod extends Enumeration {
    type HTTPMethod = Value

    val OPTIONS = Value("OPTIONS")
    val GET     = Value("GET")
    val HEAD    = Value("HEAD")
    val POST    = Value("POST")
    val PUT     = Value("PUT")
    val DELETE  = Value("DELETE")
    val PATCH   = Value("PATCH")
    val TRACE   = Value("TRACE")
    val CONNECT = Value("CONNECT")

    def apply(x: String) = withName(x)
}

/**
 * See Also:
 *
 * - https://github.com/netty/netty/blob/master/codec-http/src/main/java/io/netty/handler/codec/http/HttpMethod.java
 * - http://java.net/projects/jax-rs-spec/sources/git/content/src/jax-rs-api/src/main/java/javax/ws/rs/HttpMethod.java
 */
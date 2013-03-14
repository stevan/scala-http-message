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
}

/**
 * See Also:
 *
 * - https://github.com/netty/netty/blob/master/codec-http/src/main/java/io/netty/handler/codec/http/HttpMethod.java
 */
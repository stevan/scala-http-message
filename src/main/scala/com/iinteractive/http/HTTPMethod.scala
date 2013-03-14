package com.iinteractive.http


sealed abstract class HTTPMethod

object HTTPMethod {
    case object OPTIONS extends HTTPMethod
    case object GET     extends HTTPMethod
    case object HEAD    extends HTTPMethod
    case object POST    extends HTTPMethod
    case object PUT     extends HTTPMethod
    case object DELETE  extends HTTPMethod
    case object PATCH   extends HTTPMethod
    case object TRACE   extends HTTPMethod
    case object CONNECT extends HTTPMethod

    def withName(n: String): HTTPMethod = n.toUpperCase match {
        case "OPTIONS" => OPTIONS
        case "GET"     => GET
        case "HEAD"    => HEAD
        case "POST"    => POST
        case "PUT"     => PUT
        case "DELETE"  => DELETE
        case "PATCH"   => PATCH
        case "TRACE"   => TRACE
        case "CONNECT" => CONNECT
        case _         => throw new HTTP.Errors.InvalidHTTPMethod(n)
    }
}

/**
 * See Also:
 *
 * Other Implementations
 * - https://github.com/netty/netty/blob/master/codec-http/src/main/java/io/netty/handler/codec/http/HttpMethod.java
 * - http://java.net/projects/jax-rs-spec/sources/git/content/src/jax-rs-api/src/main/java/javax/ws/rs/HttpMethod.java
 */
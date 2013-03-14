package com.iinteractive.http


sealed abstract class HTTPMethod(private val name: String) {
    def getName = name

    override def toString = getName
}

object HTTPMethod {
    case object OPTIONS extends HTTPMethod("OPTIONS")
    case object GET     extends HTTPMethod("GET")
    case object HEAD    extends HTTPMethod("HEAD")
    case object POST    extends HTTPMethod("POST")
    case object PUT     extends HTTPMethod("PUT")
    case object DELETE  extends HTTPMethod("DELETE")
    case object PATCH   extends HTTPMethod("PATCH")
    case object TRACE   extends HTTPMethod("TRACE")
    case object CONNECT extends HTTPMethod("CONNECT")

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
    }
}

/**
 * See Also:
 *
 * Other Implementations
 * - https://github.com/netty/netty/blob/master/codec-http/src/main/java/io/netty/handler/codec/http/HttpMethod.java
 * - http://java.net/projects/jax-rs-spec/sources/git/content/src/jax-rs-api/src/main/java/javax/ws/rs/HttpMethod.java
 */
package com.iinteractive.http

sealed abstract class HTTPVersion(major: Int, minor: Int) {
    def majorVersion = major
    def minorVersion = minor
    override def toString = "HTTP/" + majorVersion + "." + minorVersion
}

object HTTPVersion {
    case object HTTP_0_9 extends HTTPVersion(0, 9)
    case object HTTP_1_0 extends HTTPVersion(1, 0)
    case object HTTP_1_1 extends HTTPVersion(1, 1)

    def apply (major: Int, minor: Int): HTTPVersion = (major, minor) match {
        case (0, 9) => HTTP_0_9
        case (1, 0) => HTTP_1_0
        case (1, 1) => HTTP_1_1
        case _      => throw new HTTP.Errors.InvalidHTTPVersion(major + "." + minor)
    }
}

/**
 * See Also:
 *
 * Other implementations:
 * - http://hc.apache.org/httpcomponents-core-ga/httpcore/xref/org/apache/http/HttpVersion.html
 * - https://github.com/netty/netty/blob/master/codec-http/src/main/java/io/netty/handler/codec/http/HttpVersion.java
 */

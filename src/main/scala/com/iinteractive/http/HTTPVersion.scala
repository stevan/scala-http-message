package com.iinteractive.http

object HTTPVersion extends Enumeration {
    type HTTPVersion = HTTPVersionValue

    case class HTTPVersionValue(major: Int, minor: Int) extends Val(nextId) {
        def protocolName = "HTTP"
        def majorVersion = major
        def minorVersion = minor
        override def toString = protocolName + "/" + majorVersion + "." + minorVersion
    }

    val HTTP_0_9 = HTTPVersionValue(0, 9)
    val HTTP_1_0 = HTTPVersionValue(1, 0)
    val HTTP_1_1 = HTTPVersionValue(1, 1)

    // NOTE:
    // not sure I actually need this or care about it
    // it will require adding the following line at
    // the top of this file:
    //    import scala.language.implicitConversions
    // but lets wait until we actually need or care.
    // - SL
    //implicit def valueToVersion(v: Value): HTTPVersionValue = v.asInstanceOf[HTTPVersionValue]
}

/**
 * See Also:
 *
 * Enumeration.Val extension:
 * - http://downgra.de/2010/02/11/playing-with-scala-enumeration/
 *
 * Other implementations:
 * - http://hc.apache.org/httpcomponents-core-ga/httpcore/xref/org/apache/http/HttpVersion.html
 * - https://github.com/netty/netty/blob/master/codec-http/src/main/java/io/netty/handler/codec/http/HttpVersion.java
 */

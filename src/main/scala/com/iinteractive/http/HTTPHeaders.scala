package com.iinteractive.http

import scala.collection.mutable.LinkedHashMap

import com.iinteractive.http.headers._

sealed abstract class HTTPHeader(val name: String) {
    def value: String
    override def toString = name + ": " + value
}

object HTTPHeader {
    def unapply(h: HTTPHeader): Option[(String, String)] = Some(h.name -> h.value)

    case class Accept         (val value: String) extends HTTPHeader("Accept")
    case class AcceptCharset  (val value: String) extends HTTPHeader("Accept-Charset")
    case class AcceptEncoding (val value: String) extends HTTPHeader("Accept-Encoding")
    case class AcceptLanguage (val value: String) extends HTTPHeader("Accept-Language")
    case class AcceptRanges   (val value: String) extends HTTPHeader("Accept-Ranges")
    case class AcceptPatch    (val value: String) extends HTTPHeader("Accept-Patch")

    // Cross-Origin Resource Sharing headers - http://www.w3.org/TR/cors/
    case class AccessControlAllowCredentials (val value: String) extends HTTPHeader("Access-Control-Allow-Credentials")
    case class AccessControlAllowHeaders     (val value: String) extends HTTPHeader("Access-Control-Allow-Headers")
    case class AccessControlAllowMethods     (val value: String) extends HTTPHeader("Access-Control-Allow-Methods")
    case class AccessControlAllowOrigin      (val value: String) extends HTTPHeader("Access-Control-Allow-Origin")
    case class AccessControlExposeHeaders    (val value: String) extends HTTPHeader("Access-Control-Expose-Headers")
    case class AccessControlMaxAge           (val value: String) extends HTTPHeader("Access-Control-Max-Age")
    case class AccessControlRequestHeaders   (val value: String) extends HTTPHeader("Access-Control-Request-Headers")
    case class AccessControlRequestMethod    (val value: String) extends HTTPHeader("Access-Control-Request-Method")

    case class Age           (val value: String) extends HTTPHeader("Age")
    case class Allow         (val value: String) extends HTTPHeader("Allow")
    case class Authorization (val value: String) extends HTTPHeader("Authorization")
    case class CacheControl  (val value: String) extends HTTPHeader("Cache-Control")
    case class Connection    (val value: String) extends HTTPHeader("Connection")

    case class ContentBase             (val value: String) extends HTTPHeader("Content-Base")
    case class ContentDisposition      (val value: String) extends HTTPHeader("Content-Disposition")
    case class ContentEncoding         (val value: String) extends HTTPHeader("Content-Encoding")
    case class ContentLanguage         (val value: String) extends HTTPHeader("Content-Language")
    case class ContentLength           (val value: String) extends HTTPHeader("Content-Length")
    case class ContentLocation         (val value: String) extends HTTPHeader("Content-Location")
    case class ContentTransferEncoding (val value: String) extends HTTPHeader("Content-Transfer-Encoding")
    case class ContentMD5              (val value: String) extends HTTPHeader("Content-MD5")
    case class ContentRange            (val value: String) extends HTTPHeader("Content-Range")
    case class ContentType             (val value: String) extends HTTPHeader("Content-Type")

    case class Cookie (val value: String) extends HTTPHeader("Cookie")

    case class Date (val date: HTTPDate) extends HTTPHeader("Date") {
        def value = date.format(HTTPDate.formats("Response-Header"))
    }

    case class ETag    (val value: String) extends HTTPHeader("ETag")
    case class Expect  (val value: String) extends HTTPHeader("Expect")
    case class Expires (val value: String) extends HTTPHeader("Expires")
    case class From    (val value: String) extends HTTPHeader("From")
    case class Host    (val value: String) extends HTTPHeader("Host")
 
    case class IfMatch           (val value: String) extends HTTPHeader("If-Match")
    case class IfModifiedSince   (val value: String) extends HTTPHeader("If-Modified-Since")
    case class IfNoneMatch       (val value: String) extends HTTPHeader("If-None-Match")
    case class IfRange           (val value: String) extends HTTPHeader("If-Range")
    case class IfUnmodifiedSince (val value: String) extends HTTPHeader("If-Unmodified-Since")

    case class LastModified (val value: String) extends HTTPHeader("LastModified")
    case class Location     (val value: String) extends HTTPHeader("Location")
    case class MaxForwards  (val value: String) extends HTTPHeader("Max-Forwards")
    case class Origin       (val value: String) extends HTTPHeader("Origin")
    case class Pragma       (val value: String) extends HTTPHeader("Pragma")

    case class ProxyAuthenticate  (val value: String) extends HTTPHeader("Proxy-Authenticate")
    case class ProxyAuthorization (val value: String) extends HTTPHeader("Proxy-Authorization")

    case class Range      (val value: String) extends HTTPHeader("Range")
    case class Referer    (val value: String) extends HTTPHeader("Referer")
    case class RetryAfter (val value: String) extends HTTPHeader("RetryAfter")

    case class Server (val value: String) extends HTTPHeader("Server")

    case class SetCookie  (val value: String) extends HTTPHeader("Set-Cookie")
    case class SetCookie2 (val value: String) extends HTTPHeader("Set-Cookie2")

    case class TE               (val value: String) extends HTTPHeader("TE")
    case class Trailer          (val value: String) extends HTTPHeader("Trailer")
    case class TransferEncoding (val value: String) extends HTTPHeader("Transfer-Encoding")
    case class Upgrade          (val value: String) extends HTTPHeader("Upgrade")
    case class UserAgent        (val value: String) extends HTTPHeader("User-Agent")
    case class Vary             (val value: String) extends HTTPHeader("Vary")
    case class Via              (val value: String) extends HTTPHeader("Via")
    case class Warning          (val value: String) extends HTTPHeader("Warning")

    case class WWWAuthenticate (val value: String) extends HTTPHeader("WWW-Authenticate")

    object WebSockets {
        // http://tools.ietf.org/html/rfc6455
        case class SecWebSocketKey        (val value: String) extends HTTPHeader("Sec-WebSocket-Key")
        case class SecWebSocketExtensions (val value: String) extends HTTPHeader("Sec-WebSocket-Extensions")
        case class SecWebSocketAccept     (val value: String) extends HTTPHeader("Sec-WebSocket-Accept")
        case class SecWebSocketProtocol   (val value: String) extends HTTPHeader("Sec-WebSocket-Protocol")
        case class SecWebSocketVersion    (val value: String) extends HTTPHeader("Sec-WebSocket-Version")
    }

    object WebDAV {
        // http://tools.ietf.org/html/rfc4918
        case class Dav         (val value: String) extends HTTPHeader("Dav")         
        case class Depth       (val value: String) extends HTTPHeader("Depth")       
        case class Destination (val value: String) extends HTTPHeader("Destination") 
        case class If          (val value: String) extends HTTPHeader("If")          
        case class LockToken   (val value: String) extends HTTPHeader("Lock-Token") 
        case class Overwrite   (val value: String) extends HTTPHeader("Overwrite") 
        case class Timeout     (val value: String) extends HTTPHeader("Timeout") 

        // http://www.ietf.org/rfc/rfc2518
        case class StatusURI   (val value: String) extends HTTPHeader("Status-URI") 
    }
}

class HTTPHeaders (headers: List[HTTPHeader]) {

}

/**
 * Reference:
 * - http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html
 *
 * Lots of this is shamelessly stolen from:
 * - https://github.com/spray/spray/blob/master/spray-http/src/main/scala/spray/http/HttpHeader.scala
 *
 * Which was influenced by this:
 * - https://github.com/jdegoes/blueeyes/blob/master/core/src/main/scala/blueeyes/core/http/HttpHeader.scala
 *
 * See Also:
 *
 * - https://github.com/twitter/finagle/blob/master/finagle-http/src/main/scala/com/twitter/finagle/http/HeaderMap.scala
 * - https://github.com/spray/spray/blob/master/spray-http/src/main/scala/spray/http/HttpHeader.scala
 * - https://github.com/netty/netty/blob/master/codec-http/src/main/java/io/netty/handler/codec/http/HttpHeaders.java
 * - http://hc.apache.org/httpcomponents-core-ga/httpcore/clover/org/apache/http/HttpHeaders.html
 * - http://hc.apache.org/httpcomponents-core-ga/httpcore/xref/org/apache/http/Header.html
 * - http://java.net/projects/jax-rs-spec/sources/git/content/src/jax-rs-api/src/main/java/javax/ws/rs/core/HttpHeaders.java
 * - https://code.google.com/p/google-api-java-client/source/browse/google-api-client/src/main/java/com/google/api/client/http/HttpHeaders.java?spec=svn1075be069f8e914e9bde90cbf0b0bccfc6986398&name=1.8&r=293c6f883b157d50adb0fbcefcf1bbfc1bac583f
 * - http://code.google.com/p/guava-libraries/source/browse/guava/src/com/google/common/net/HttpHeaders.java?r=83c496dd2363e839b582d73dd41f4d869abd3f7e
 * - http://code.google.com/p/google-http-java-client/source/browse/google-http-client/src/main/java/com/google/api/client/http/HttpHeaders.java?r=0c25b46785381261f7093dc945581de8ec32f7ea
 */
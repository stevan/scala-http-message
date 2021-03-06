package com.iinteractive.http

import com.github.theon.uri.Uri

import scala.collection.mutable.LinkedHashMap

import com.iinteractive.http.headers._

sealed abstract class HTTPHeader(val name: String) {
    def value: String
    override def toString = name + ": " + value
}

object HTTPHeader {
    def unapply(h: HTTPHeader): Option[(String, String)] = Some(h.name -> h.value)

    /**
     * SEE ALSO 
     * - http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html
     * - http://en.wikipedia.org/wiki/List_of_HTTP_header_fields
     *
     * Range Requests:
     * - http://www.greenbytes.de/tech/webdav/draft-ietf-httpbis-p5-range-latest.html
     * 
     * Authorization: 
     * - http://www.ietf.org/rfc/rfc2617.txt
     *
     * Content-MD5
     * - http://www.ietf.org/rfc/rfc1864.txt
     *
     * Accept-Patch
     * - http://tools.ietf.org/html/rfc5789#section-3.1
     *
     * Content-Transfer-Encoding
     * - http://www.w3.org/Protocols/rfc1341/5_Content-Transfer-Encoding.html
     */

    case class Accept         (val list: MediaTypeList)  extends HTTPHeader("Accept")          { def value = list.toString }
    case class AcceptCharset  (val list: PriorityList)   extends HTTPHeader("Accept-Charset")  { def value = list.toString }
    case class AcceptEncoding (val list: PriorityList)   extends HTTPHeader("Accept-Encoding") { def value = list.toString }
    case class AcceptLanguage (val list: PriorityList)   extends HTTPHeader("Accept-Language") { def value = list.toString }
    case class AcceptRanges   (val values: String*)      extends HTTPHeader("Accept-Ranges")   { def value = values.mkString(", ") }
    case class AcceptPatch    (val list: MediaTypeList)  extends HTTPHeader("Accept-Patch")    { def value = list.toString }
    case class Age            (val deltaSeconds: Int)    extends HTTPHeader("Age")             { def value = deltaSeconds.toString }
    case class Allow          (val methods: HTTPMethod*) extends HTTPHeader("Allow")           { def value = methods.mkString(", ") }

    // TODO - make an Authorization header type
    case class Authorization (val value: String) extends HTTPHeader("Authorization")

    case class CacheControl (val directives: CacheDirective*) extends HTTPHeader("Cache-Control") { def value = directives.mkString(", ") }
    case class Connection   (val values: String*)             extends HTTPHeader("Connection")    { def value = values.mkString(", ") }
    case class ContentBase  (val url: Uri)                    extends HTTPHeader("Content-Base")  { def value = url.toString }

    case class ContentDisposition (val dispositionType: ContentDispositionType) extends HTTPHeader("Content-Disposition") { 
        def value = dispositionType.toString   
    }

    case class ContentEncoding         (val values: String*) extends HTTPHeader("Content-Encoding") { def value = values.mkString(", ") }
    case class ContentLanguage         (val values: String*) extends HTTPHeader("Content-Language") { def value = values.mkString(", ") }
    case class ContentLength           (val length: Int)     extends HTTPHeader("Content-Length")   { def value = length.toString }
    case class ContentLocation         (val uri: Uri)        extends HTTPHeader("Content-Location") { def value = uri.toString }
    case class ContentTransferEncoding (val value: String)   extends HTTPHeader("Content-Transfer-Encoding")
    case class ContentMD5              (val value: String)   extends HTTPHeader("Content-MD5")

    // TODO - write a ContentRange header - SL 
    case class ContentRange            (val value: String) extends HTTPHeader("Content-Range")

    case class ContentType (val mediaType: MediaType) extends HTTPHeader("Content-Type") { def value = mediaType.toString }

    // TODO - write a Cookie Parser - SL
    case class Cookie (val value: String) extends HTTPHeader("Cookie")

    case class Date    (val date: HTTPDate)                 extends HTTPHeader("Date") { def value = date.toString }
    case class ETag    (val entityTag: EntityTag)           extends HTTPHeader("ETag") { def value = entityTag.toString }
    case class Expect  (val value: String = "100-continue") extends HTTPHeader("Expect") // wrong I know, but I can't find other examples
    case class Expires (val date: HTTPDate)                 extends HTTPHeader("Expires") { def value = date.toString }
    case class From    (val value: String)                  extends HTTPHeader("From") // TODO - email address validation (maybe)
    case class Host    (val value: String)                  extends HTTPHeader("Host") // TODO - add some validaiton
 
    case class IfMatch (val entityTags: EntityTag*) extends HTTPHeader("If-Match") { 
        def value = if (entityTags.isEmpty) "*" else entityTags.mkString(", ") 
    }

    case class IfNoneMatch (val entityTags: EntityTag*) extends HTTPHeader("If-None-Match") { 
        def value = if (entityTags.isEmpty) "*" else entityTags.mkString(", ") 
    }

    case class IfRange (val entityTag: Option[EntityTag], val date: Option[HTTPDate]) extends HTTPHeader("If-Range") { 
        def value = (entityTag, date) match {
            case (Some(e), None) => e.toString
            case (None, Some(d)) => d.toString
            case _               => throw new HTTP.Errors.InvalidHTTPHeader("If-Range")
        }
    }

    case class IfModifiedSince   (val date: HTTPDate) extends HTTPHeader("If-Modified-Since")   {  def value = date.toString }
    case class IfUnmodifiedSince (val date: HTTPDate) extends HTTPHeader("If-Unmodified-Since") { def value = date.toString }
    case class LastModified      (val date: HTTPDate) extends HTTPHeader("LastModified")        { def value = date.toString }

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

    object CORS {
        // Cross-Origin Resource Sharing headers - http://www.w3.org/TR/cors/
        case class AccessControlAllowCredentials (val value: String) extends HTTPHeader("Access-Control-Allow-Credentials")
        case class AccessControlAllowHeaders     (val value: String) extends HTTPHeader("Access-Control-Allow-Headers")
        case class AccessControlAllowMethods     (val value: String) extends HTTPHeader("Access-Control-Allow-Methods")
        case class AccessControlAllowOrigin      (val value: String) extends HTTPHeader("Access-Control-Allow-Origin")
        case class AccessControlExposeHeaders    (val value: String) extends HTTPHeader("Access-Control-Expose-Headers")
        case class AccessControlMaxAge           (val value: String) extends HTTPHeader("Access-Control-Max-Age")
        case class AccessControlRequestHeaders   (val value: String) extends HTTPHeader("Access-Control-Request-Headers")
        case class AccessControlRequestMethod    (val value: String) extends HTTPHeader("Access-Control-Request-Method")        
    }

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
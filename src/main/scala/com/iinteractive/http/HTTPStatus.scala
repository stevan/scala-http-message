package com.iinteractive.http

object HTTPStatus extends Enumeration {
    type HTTPStatus = Value

    val CONTINUE                        = Value(100, "Continue")
    val SWITCHING_PROTOCOLS             = Value(101, "Switching Protocols")
    val PROCESSING                      = Value(102, "Processing")                      // RFC 2518 (WebDAV)
    val OK                              = Value(200, "OK")
    val CREATED                         = Value(201, "Created")
    val ACCEPTED                        = Value(202, "Accepted")
    val NON_AUTHORITATIVE_INFORMATION   = Value(203, "Non-Authoritative Information")
    val NO_CONTENT                      = Value(204, "No Content")
    val RESET_CONTENT                   = Value(205, "Reset Content")
    val PARTIAL_CONTENT                 = Value(206, "Partial Content")
    val MULTI_STATUS                    = Value(207, "Multi-Status")                    // RFC 2518 (WebDAV)
    val ALREADY_REPORTED                = Value(208, "Already Reported")                // RFC 5842
    val IM_USED                         = Value(226, "IM Used")
    val MULTIPLE_CHOICES                = Value(300, "Multiple Choices")
    val MOVED_PERMANENTLY               = Value(301, "Moved Permanently")
    val MOVED_TEMPORARILY               = Value(302, "Moved Temporarily")
    val SEE_OTHER                       = Value(303, "See Other")
    val NOT_MODIFIED                    = Value(304, "Not Modified")
    val USE_PROXY                       = Value(305, "Use Proxy")
    val TEMPORARY_REDIRECT              = Value(307, "Temporary Redirect")
    val BAD_REQUEST                     = Value(400, "Bad Request")
    val UNAUTHORIZED                    = Value(401, "Unauthorized")
    val PAYMENT_REQUIRED                = Value(402, "Payment Required")
    val FORBIDDEN                       = Value(403, "Forbidden")
    val NOT_FOUND                       = Value(404, "Not Found")
    val METHOD_NOT_ALLOWED              = Value(405, "Method Not Allowed")
    val NOT_ACCEPTABLE                  = Value(406, "Not Acceptable")
    val PROXY_AUTHENTICATION_REQUIRED   = Value(407, "Proxy Authentication Required")
    val REQUEST_TIMEOUT                 = Value(408, "Request Timeout")
    val CONFLICT                        = Value(409, "Conflict")
    val GONE                            = Value(410, "Gone")
    val LENGTH_REQUIRED                 = Value(411, "Length Required")
    val PRECONDITION_FAILED             = Value(412, "Precondition Failed")
    val REQUEST_ENTITY_TOO_LARGE        = Value(413, "Request Entity Too Large")
    val REQUEST_URI_TOO_LONG            = Value(414, "Request-URI Too Large")
    val UNSUPPORTED_MEDIA_TYPE          = Value(415, "Unsupported Media Type")
    val REQUESTED_RANGE_NOT_SATISFIABLE = Value(416, "Request Range Not Satisfiable")
    val EXPECTATION_FAILED              = Value(417, "Expectation Failed")
    val IM_A_TEAPOT                     = Value(418, "I'm a teapot")                    // RFC 2324
    val INSUFFICIENT_SPACE_ON_RESOURCE  = Value(419, "Insufficient Space on Resource.") // RFC 2518 (WebDAV)
    val METHOD_FAILURE                  = Value(420, "Method Failure")                  // RFC 2518 (WebDAV)
    val DESTINATION_LOCKED              = Value(421, "Destination Locked")              // RFC 2518 (WebDAV)
    val UNPROCESSABLE_ENTITY            = Value(422, "Unprocessable Entity")            // RFC 2518 (WebDAV)
    val LOCKED                          = Value(423, "Locked")                          // RFC 2518 (WebDAV)
    val FAILED_DEPENDENCY               = Value(424, "Failed Dependency")               // RFC 2518 (WebDAV)
    val NO_CODE                         = Value(425, "No code")                         // WebDAV Advanced Collections
    val UPGRADE_REQUIRED                = Value(426, "Upgrade Required")                // RFC 2817
    val PRECONDITION_REQUIRED           = Value(428, "Precondition Required")
    val TOO_MANY_REQUESTS               = Value(429, "Too Many Requests")
    val REQUEST_HEADER_FIELDS_TO_LARGE  = Value(431, "Request Header Fields Too Large")
    val RETRY_WITH                      = Value(449, "Retry with")                      // unofficial Microsoft
    val INTERNAL_SERVER_ERROR           = Value(500, "Internal Server Error")
    val NOT_IMPLEMENTED                 = Value(501, "Not Implemented")
    val BAD_GATEWAY                     = Value(502, "Bad Gateway")
    val SERVICE_UNAVAILABLE             = Value(503, "Service Unavailable")
    val GATEWAY_TIMEOUT                 = Value(504, "Gateway Timeout")
    val HTTP_VERSION_NOT_SUPPORTED      = Value(505, "HTTP Version Not Supported")
    val VARIANT_ALSO_NEGOTIATES         = Value(506, "Variant Also Negotiates")         // RFC 2295
    val INSUFFICIENT_STORAGE            = Value(507, "Insufficient Storage")            // RFC 2518 (WebDAV)
    val LOOP_DETECTED                   = Value(508, "Loop Detected")
    val BANDWIDTH_LIMIT_EXCEEDED        = Value(509, "Bandwidth Limit Exceeded")        // unofficial
    val NOT_EXTENDED                    = Value(510, "Not Extended")                    // RFC 2774
    val NETWORK_AUTHENTICATION_REQUIRED = Value(511, "Network Authentication Required")

    def isInfo        (s: HTTPStatus): Boolean = s.id >= 100 && s.id < 200
    def isSuccess     (s: HTTPStatus): Boolean = s.id >= 200 && s.id < 300
    def isRedirect    (s: HTTPStatus): Boolean = s.id >= 300 && s.id < 400
    def isError       (s: HTTPStatus): Boolean = s.id >= 400 && s.id < 600
    def isClientError (s: HTTPStatus): Boolean = s.id >= 400 && s.id < 500
    def isServerError (s: HTTPStatus): Boolean = s.id >= 500 && s.id < 600

}


/**
 * See Also
 *
 * The following fail to implement the complete set:
 * - http://docs.oracle.com/javaee/6/api/javax/ws/rs/core/Response.Status.html
 * - http://hc.apache.org/httpclient-3.x/apidocs/org/apache/commons/httpclient/HttpStatus.html
 * - http://static.springsource.org/spring/docs/3.0.x/api/org/springframework/http/HttpStatus.html
 * - http://docs.oracle.com/javase/1.4.2/docs/api/java/net/HttpURLConnection.html
 * - http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletResponse.html
 * - http://hc.apache.org/httpcomponents-core-ga/httpcore/xref/org/apache/http/HttpStatus.html
 *
 */



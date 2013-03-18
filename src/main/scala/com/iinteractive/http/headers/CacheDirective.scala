package com.iinteractive.http.headers

/**
 * - http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.9
 */

class CacheDirective (val directive: String, val parameter: Option[String] = None) {
    override def toString = directive + (if (parameter.isDefined) "=\"" + parameter.get + "\"" else "")
}

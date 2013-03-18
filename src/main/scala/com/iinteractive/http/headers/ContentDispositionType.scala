package com.iinteractive.http.headers

/**
 * - http://www.w3.org/Protocols/rfc2616/rfc2616-sec19.html#sec19.5.1
 * - http://tools.ietf.org/html/rfc6266
 *     - http://tools.ietf.org/html/rfc6266#section-4.1
 */

class ContentDispositionType (val dispositionType: String) extends WithParams {
    override def toString = (dispositionType + (if (paramsAreEmpty) "" else "; " + paramsToString))
}
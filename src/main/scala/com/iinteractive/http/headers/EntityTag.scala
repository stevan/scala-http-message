package com.iinteractive.http.headers

/**
 * - http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.11
 */

class EntityTag (val tag: String, val isWeak: Boolean = false) {
    override def toString = ((if (isWeak) "W/" else "") + "\"" + tag + "\"")
}

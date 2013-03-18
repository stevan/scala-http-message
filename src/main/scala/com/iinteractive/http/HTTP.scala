package com.iinteractive.http

object HTTP {

    object Errors {
        class InvalidHTTPMethod  (msg: String) extends Exception(msg)
        class InvalidHTTPVersion (msg: String) extends Exception(msg)
        class InvalidHTTPDate    (msg: String) extends Exception(msg)
        class InvalidHTTPHeader  (msg: String) extends Exception(msg)
    }

}
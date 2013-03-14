package com.iinteractive.http

object HTTP {

    object Errors {
        class InvalidHTTPMethod  (msg: String) extends Exception(msg)
        class InvalidHTTPVersion (msg: String) extends Exception(msg)
    }

}
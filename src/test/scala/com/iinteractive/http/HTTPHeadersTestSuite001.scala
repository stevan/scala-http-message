package com.iinteractive.http

import com.iinteractive.test._
import com.iinteractive.http.headers._

import java.util.Date

class HTTPHeadersTestSuite001 extends TestMore {

    val a = new HTTPHeader.Accept("Foo")
    diag(a.value)

    val d = new HTTPHeader.Date(new HTTPDate(new Date()))
    diag(d.value)

    pass("... placeholder")

}
package com.iinteractive.http

import com.iinteractive.test._

class HTTPMethodTestSuite001 extends TestMore {

    is(HTTPMethod.GET.toString, "GET", "... got the expected value")
    is(HTTPMethod.withName("GET"), HTTPMethod.GET, "... got the expected value")

}
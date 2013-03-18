package com.iinteractive.http

import com.iinteractive.test._
import com.iinteractive.http.headers._
import com.iinteractive.http.HTTPMethod._

class HTTPHeadersTestSuite001 extends TestMore {

    subtest ("Age header") {
        val h = new HTTPHeader.Age(200)
        is(h.toString, "Age: 200", "... got the value we expected")
    }

    subtest ("Allow header") {
        val h = new HTTPHeader.Allow(GET, POST, HEAD)
        is(h.toString, "Allow: GET, POST, HEAD", "... got the value we expected")
    }

}
package com.iinteractive.http

import com.iinteractive.test._

class HTTPVersionTestSuite001 extends TestMore {

    is(HTTPVersion.HTTP_0_9.toString, "HTTP/0.9", "... got the expected value")
    is(HTTPVersion.HTTP_0_9.majorVersion, 0, "... got the expected value")
    is(HTTPVersion.HTTP_0_9.minorVersion, 9, "... got the expected value")

    is(HTTPVersion.HTTP_1_0.toString, "HTTP/1.0", "... got the expected value")
    is(HTTPVersion.HTTP_1_0.majorVersion, 1, "... got the expected value")
    is(HTTPVersion.HTTP_1_0.minorVersion, 0, "... got the expected value")

    is(HTTPVersion.HTTP_1_1.toString, "HTTP/1.1", "... got the expected value")
    is(HTTPVersion.HTTP_1_1.majorVersion, 1, "... got the expected value")
    is(HTTPVersion.HTTP_1_1.minorVersion, 1, "... got the expected value")

    is(HTTPVersion(0, 9), HTTPVersion.HTTP_0_9, "... values matched")
    is(HTTPVersion(1, 0), HTTPVersion.HTTP_1_0, "... values matched")
    is(HTTPVersion(1, 1), HTTPVersion.HTTP_1_1, "... values matched")

}
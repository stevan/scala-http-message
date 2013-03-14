package com.iinteractive.http

import com.iinteractive.test._

class HTTPStatusTestSuite001 extends TestMore {

    is(HTTPStatus(200).toString, "OK", "... got the expected value")
    is(HTTPStatus(200), HTTPStatus.OK, "... got the expected value")

}
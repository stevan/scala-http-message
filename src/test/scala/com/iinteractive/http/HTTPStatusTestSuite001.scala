package com.iinteractive.http

import com.iinteractive.test._

class HTTPStatusTestSuite001 extends TestMore {

    is(HTTPStatus(200).toString, "OK", "... got the expected value")
    is(HTTPStatus(200), HTTPStatus.OK, "... got the expected value")

    ok(HTTPStatus.isInfo(HTTPStatus.CONTINUE),                     "... 100 is Info")
    ok(HTTPStatus.isSuccess(HTTPStatus.OK),                        "... 200 is Success")
    ok(HTTPStatus.isSuccess(HTTPStatus.NO_CONTENT),                "... 204 is Success")
    ok(HTTPStatus.isRedirect(HTTPStatus.MULTIPLE_CHOICES),         "... 300 is Redirect")
    ok(HTTPStatus.isError(HTTPStatus.NOT_FOUND),                   "... 400 is Error")
    ok(HTTPStatus.isError(HTTPStatus.INTERNAL_SERVER_ERROR),       "... 500 is Server Error")
    ok(HTTPStatus.isClientError(HTTPStatus.NOT_FOUND),             "... 400 is Client Error")
    ok(HTTPStatus.isServerError(HTTPStatus.INTERNAL_SERVER_ERROR), "... 500 is Server Error")

}
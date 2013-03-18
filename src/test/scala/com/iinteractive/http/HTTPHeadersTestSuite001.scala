package com.iinteractive.http

import com.iinteractive.test._

import com.iinteractive.http.headers._
import com.github.theon.uri.Uri

class HTTPHeadersTestSuite001 extends TestMore {

    subtest ("Accept header") {
        val h = new HTTPHeader.Accept(new MediaTypeList(new MediaType("text", "html"), new MediaType("text", "xml", Some(0.7))))
        is(h.toString, "Accept: text/html, text/xml; q=0.7", "... got the value we expected")
    }

    subtest("Accept-Charset header") {
        val h = new HTTPHeader.AcceptCharset(new PriorityList(new PriorityItem("UTF-8"), new PriorityItem("US-ASCII", Some(0.2))))
        is(h.toString, "Accept-Charset: UTF-8, US-ASCII; q=0.2", "... got the value we expected")

        val h2 = new HTTPHeader.AcceptCharset(new PriorityList(new PriorityItem("*")))
        is(h2.toString, "Accept-Charset: *", "... got the value we expected")

        val h3 = new HTTPHeader.AcceptCharset(new PriorityList())
        is(h3.toString, "Accept-Charset: ", "... got the value we expected")
    }

    subtest("Accept-Encoding header") {
        val h = new HTTPHeader.AcceptEncoding(new PriorityList(new PriorityItem("gzip"), new PriorityItem("identity", Some(0.2))))
        is(h.toString, "Accept-Encoding: gzip, identity; q=0.2", "... got the value we expected")

        val h2 = new HTTPHeader.AcceptEncoding(new PriorityList(new PriorityItem("*")))
        is(h2.toString, "Accept-Encoding: *", "... got the value we expected")

        val h3 = new HTTPHeader.AcceptEncoding(new PriorityList())
        is(h3.toString, "Accept-Encoding: ", "... got the value we expected")
    }

    subtest("Accept-Language header") {
        val h = new HTTPHeader.AcceptLanguage(new PriorityList(new PriorityItem("en-US"), new PriorityItem("de", Some(0.2))))
        is(h.toString, "Accept-Language: en-US, de; q=0.2", "... got the value we expected")

        val h2 = new HTTPHeader.AcceptLanguage(new PriorityList(new PriorityItem("*")))
        is(h2.toString, "Accept-Language: *", "... got the value we expected")

        val h3 = new HTTPHeader.AcceptLanguage(new PriorityList())
        is(h3.toString, "Accept-Language: ", "... got the value we expected")
    }

    subtest ("AcceptRanges header") {
        val h = new HTTPHeader.AcceptRanges("bytes")
        is(h.toString, "Accept-Ranges: bytes", "... got the value we expected")

        val h2 = new HTTPHeader.AcceptRanges("bytes", "inches")
        is(h2.toString, "Accept-Ranges: bytes, inches", "... got the value we expected")
    }

    subtest ("Accept-Patch header") {
        val h = new HTTPHeader.AcceptPatch(new MediaTypeList(new MediaType("text", "html"), new MediaType("text", "xml", Some(0.7))))
        is(h.toString, "Accept-Patch: text/html, text/xml; q=0.7", "... got the value we expected")
    }

    subtest ("Age header") {
        val h = new HTTPHeader.Age(200)
        is(h.toString, "Age: 200", "... got the value we expected")
    }

    subtest ("Allow header") {
        val h = new HTTPHeader.Allow(GET, POST, HEAD)
        is(h.toString, "Allow: GET, POST, HEAD", "... got the value we expected")
    }

    subtest ("Authorization header") {
        val h = new HTTPHeader.Authorization("TODO")
        is(h.toString, "Authorization: TODO", "... got the value we expected")
    }

}







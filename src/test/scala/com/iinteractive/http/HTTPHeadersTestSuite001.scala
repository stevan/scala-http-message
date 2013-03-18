package com.iinteractive.http

import com.iinteractive.test._

import com.iinteractive.http.headers._
import com.iinteractive.http.HTTPMethod._
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

    subtest ("CacheControl header") {
        val h = new HTTPHeader.CacheControl(new CacheDirective("no-cache"))
        is(h.toString, "Cache-Control: no-cache", "... got the value we expected")

        val h2 = new HTTPHeader.CacheControl(new CacheDirective("no-transform"), new CacheDirective("max-age", Some("10")))
        is(h2.toString, "Cache-Control: no-transform, max-age=\"10\"", "... got the value we expected")
    }

    subtest ("Connection header") {
        val h = new HTTPHeader.Connection("close")
        is(h.toString, "Connection: close", "... got the value we expected")

        val h2 = new HTTPHeader.Connection("close", "open")
        is(h2.toString, "Connection: close, open", "... got the value we expected")
    }

    subtest ("ContentBase header") {
        val h = new HTTPHeader.ContentBase( Uri.parseUri("http://example.org") )
        is(h.toString, "Content-Base: http://example.org", "... got the value we expected")
    }    

    subtest ("Content-Disposition header") {
        val cd = new ContentDispositionType("Attachment")
        cd.addParam("filename" -> "example.html")

        val h = new HTTPHeader.ContentDisposition(cd)
        is(h.toString, "Content-Disposition: Attachment; filename=\"example.html\"", "... got the value we expected")

        val cd2 = new ContentDispositionType("inline")
        cd2.addParam("filename" -> "EURO rates")
        cd2.addParam("filename*" -> "utf-8''%e2%82%ac%20rates")

        val h2 = new HTTPHeader.ContentDisposition(cd2)
        is(h2.toString, "Content-Disposition: inline; filename=\"EURO rates\"; filename*=\"utf-8''%e2%82%ac%20rates\"", "... got the value we expected")
    }

    subtest ("ContentEncoding header") {
        val h = new HTTPHeader.ContentEncoding("gzip")
        is(h.toString, "Content-Encoding: gzip", "... got the value we expected")

        val h2 = new HTTPHeader.ContentEncoding("gzip", "deflate")
        is(h2.toString, "Content-Encoding: gzip, deflate", "... got the value we expected")
    }

    subtest ("ContentLanguage header") {
        val h = new HTTPHeader.ContentLanguage("en-US")
        is(h.toString, "Content-Language: en-US", "... got the value we expected")

        val h2 = new HTTPHeader.ContentLanguage("en-US", "de")
        is(h2.toString, "Content-Language: en-US, de", "... got the value we expected")
    }

    subtest ("ContentLength header") {
        val h = new HTTPHeader.ContentLength(1234)
        is(h.toString, "Content-Length: 1234", "... got the value we expected")
    }

    subtest ("ContentLocation header") {
        val h = new HTTPHeader.ContentLocation( Uri.parseUri("http://example.org") )
        is(h.toString, "Content-Location: http://example.org", "... got the value we expected")

        val h2 = new HTTPHeader.ContentLocation( Uri.parseUri("/some/where") )
        is(h2.toString, "Content-Location: /some/where", "... got the value we expected")
    }

    subtest ("ContentTransferEncoding header") {
        val h = new HTTPHeader.ContentTransferEncoding( "base64" )
        is(h.toString, "Content-Transfer-Encoding: base64", "... got the value we expected")
    }

    subtest ("ContentMD5 header") {
        val h = new HTTPHeader.ContentMD5("Q2hlY2sgSW50ZWdyaXR5IQ==")
        is(h.toString, "Content-MD5: Q2hlY2sgSW50ZWdyaXR5IQ==", "... got the value we expected")
    }

    subtest ("ContentRange header") {
        val h = new HTTPHeader.ContentRange("TODO")
        is(h.toString, "Content-Range: TODO", "... got the value we expected")
    }

    subtest ("ContentType header") {
        val h = new HTTPHeader.ContentType(new MediaType("text", "html", Some(0.3)))
        is(h.toString, "Content-Type: text/html; q=0.3", "... got the value we expected")
    }

    subtest ("Cookie header") {
        val h = new HTTPHeader.Cookie("TODO")
        is(h.toString, "Cookie: TODO", "... got the value we expected")
    }

}







package com.iinteractive.http

import com.iinteractive.test._

import com.iinteractive.http.headers._

class HTTPHeadersTestSuite003 extends TestMore {

    subtest ("Date header") {
        val h = new HTTPHeader.Date(HTTPDate("Sat, 16 Mar 2013 16:14:50 GMT"))
        is(h.toString, "Date: Sat, 16 Mar 2013 16:14:50 +00:00", "... got the value we expected")
    }

    subtest ("ETag header") {
        val h = new HTTPHeader.ETag(new EntityTag("686897696a7c876b7e"))
        is(h.toString, "ETag: \"686897696a7c876b7e\"", "... got the value we expected")

        val h2 = new HTTPHeader.ETag(new EntityTag("686897696a7c876b7e", true))
        is(h2.toString, "ETag: W/\"686897696a7c876b7e\"", "... got the value we expected")
    }

    subtest ("Expect header") {
        val h = new HTTPHeader.Expect("200-ok")
        is(h.toString, "Expect: 200-ok", "... got the value we expected")

        val h2 = new HTTPHeader.Expect()
        is(h2.toString, "Expect: 100-continue", "... got the value we expected")
    }

    subtest ("Expires header") {
        val h = new HTTPHeader.Expires(HTTPDate("Sat, 16 Mar 2013 16:14:50 GMT"))
        is(h.toString, "Expires: Sat, 16 Mar 2013 16:14:50 +00:00", "... got the value we expected")
    }

    subtest ("From header") {
        val h = new HTTPHeader.From("john@example.org")
        is(h.toString, "From: john@example.org", "... got the value we expected")
    }

    subtest ("Host header") {
        val h = new HTTPHeader.Host("www.example.org")
        is(h.toString, "Host: www.example.org", "... got the value we expected")

        val h2 = new HTTPHeader.Host("www.example.org:8080")
        is(h2.toString, "Host: www.example.org:8080", "... got the value we expected")        
    }

    subtest ("IfMatch header") {
        val h = new HTTPHeader.IfMatch(new EntityTag("686897696a7c876b7e"))
        is(h.toString, "If-Match: \"686897696a7c876b7e\"", "... got the value we expected")

        val h2 = new HTTPHeader.IfMatch(new EntityTag("686897696a7c876b7e", true))
        is(h2.toString, "If-Match: W/\"686897696a7c876b7e\"", "... got the value we expected")

        val h3 = new HTTPHeader.IfMatch()
        is(h3.toString, "If-Match: *", "... got the value we expected")
    }   

    subtest ("IfNoneMatch header") {
        val h = new HTTPHeader.IfNoneMatch(new EntityTag("686897696a7c876b7e"))
        is(h.toString, "If-None-Match: \"686897696a7c876b7e\"", "... got the value we expected")

        val h2 = new HTTPHeader.IfNoneMatch(new EntityTag("686897696a7c876b7e", true))
        is(h2.toString, "If-None-Match: W/\"686897696a7c876b7e\"", "... got the value we expected")

        val h3 = new HTTPHeader.IfNoneMatch()
        is(h3.toString, "If-None-Match: *", "... got the value we expected")
    } 

    subtest ("IfRange header") {
        val h = new HTTPHeader.IfRange(Some(new EntityTag("686897696a7c876b7e")), None) 
        is(h.toString, "If-Range: \"686897696a7c876b7e\"", "... got the value we expected")

        val h2 = new HTTPHeader.IfRange(Some(new EntityTag("686897696a7c876b7e", true)), None)
        is(h2.toString, "If-Range: W/\"686897696a7c876b7e\"", "... got the value we expected")

        val h3 = new HTTPHeader.IfRange(None, Some(HTTPDate("Sat, 16 Mar 2013 16:14:50 GMT"))) 
        is(h3.toString, "If-Range: Sat, 16 Mar 2013 16:14:50 +00:00", "... got the value we expected")        
    }

    subtest ("IfModifiedSince header") {
        val h = new HTTPHeader.IfModifiedSince(HTTPDate("Sat, 16 Mar 2013 16:14:50 GMT"))
        is(h.toString, "If-Modified-Since: Sat, 16 Mar 2013 16:14:50 +00:00", "... got the value we expected")
    }

    subtest ("IfUnmodifiedSince header") {
        val h = new HTTPHeader.IfUnmodifiedSince(HTTPDate("Sat, 16 Mar 2013 16:14:50 GMT"))
        is(h.toString, "If-Unmodified-Since: Sat, 16 Mar 2013 16:14:50 +00:00", "... got the value we expected")
    }

    subtest ("LastModified header") {
        val h = new HTTPHeader.LastModified(HTTPDate("Sat, 16 Mar 2013 16:14:50 GMT"))
        is(h.toString, "LastModified: Sat, 16 Mar 2013 16:14:50 +00:00", "... got the value we expected")
    }

}







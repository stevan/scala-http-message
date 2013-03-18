package com.iinteractive.http.headers

import com.iinteractive.test._

class MediaTypeListTestSuite001 extends TestMore {

    subtest("constructor add") {
        val pl = new MediaTypeList(
            new MediaType("text", "html"),
            new MediaType("text", "xml", Some(0.5)),
            new MediaType("image", "jpeg"),
            new MediaType("audio", "basic", Some(0.2))
        )

        is(pl.toString, "image/jpeg, text/xml; q=0.5, text/html, audio/basic; q=0.2", "... got the output we expected")
    }

    subtest("manually add") {
        val pl = new MediaTypeList()

        pl.add("text", "html")
        pl.add("text", "xml", 0.5)
        pl.add("image", "jpeg")
        pl.add("audio", "basic", 0.2)

        is(pl.toString, "image/jpeg, text/xml; q=0.5, text/html, audio/basic; q=0.2", "... got the output we expected")
    }

    subtest("order preserved") {
        val pl = new MediaTypeList(
            new MediaType("text", "html"),
            new MediaType("image", "jpeg"),
            new MediaType("audio", "basic", Some(0.2)),
            new MediaType("text", "xml", Some(0.5))
        )

        is(pl.toString, "image/jpeg, text/html, audio/basic; q=0.2, text/xml; q=0.5", "... got the output we expected")
    }

    subtest("ordering test 1") {
        val pl = new MediaTypeList(
            new MediaType("audio", "*", Some(0.2)),
            new MediaType("audio", "basic")
        )

        val i = pl.iterator
        is(i.next().toString, "audio/basic", "... got the value we expected")
        is(i.next().toString, "audio/*; q=0.2", "... got the value we expected")
    }

    subtest("ordering test 2") {
        val pl = new MediaTypeList(
            new MediaType("text", "plain", Some(0.5)),
            new MediaType("text", "html"),
            new MediaType("text", "x-dvi", Some(0.8)),
            new MediaType("text", "x-c")
        )

        val i = pl.iterator
        is(i.next().toString, "text/html", "... got the value we expected")
        is(i.next().toString, "text/x-c", "... got the value we expected")
        is(i.next().toString, "text/x-dvi; q=0.8", "... got the value we expected")
        is(i.next().toString, "text/plain; q=0.5", "... got the value we expected")
    }

    subtest("ordering test 3") {
        val mt = new MediaType("text", "html")
        mt.addParam("level", "1")

        val pl = new MediaTypeList(
            new MediaType("text", "*"),
            new MediaType("text", "html"),
            mt, 
            new MediaType("*", "*")
        )

        val i = pl.iterator
        is(i.next().toString, "text/html; level=\"1\"", "... got the value we expected")
        is(i.next().toString, "text/html", "... got the value we expected")
        is(i.next().toString, "text/*", "... got the value we expected")
        is(i.next().toString, "*/*", "... got the value we expected")
    }

    subtest("ordering test 4") {
        val mt = new MediaType("text", "html")
        mt.addParam("charset", "iso8859-1")

        val pl = new MediaTypeList(
            mt,
            new MediaType("application", "xml")
        )

        val i = pl.iterator
        is(i.next().toString, "text/html; charset=\"iso8859-1\"", "... got the value we expected")
        is(i.next().toString, "application/xml", "... got the value we expected")
    }

    subtest("ordering test 5") {

        val pl = new MediaTypeList(
            new MediaType("application", "xml", Some(0.7)),
            new MediaType("text", "html"),
            new MediaType("*", "*")
        )

        val i = pl.iterator
        is(i.next().toString, "text/html", "... got the value we expected")
        is(i.next().toString, "*/*", "... got the value we expected")
        is(i.next().toString, "application/xml; q=0.7", "... got the value we expected")
    }

    subtest("ordering test 6") {

        val mt = new MediaType("application", "json")
        mt.addParam("v", "3")
        mt.addParam("foo", "bar")

        val mt2 = new MediaType("application", "json")
        mt2.addParam("v", "2")

        val pl = new MediaTypeList(mt, mt2)

        val i = pl.iterator
        is(i.next().toString, "application/json; v=\"3\"; foo=\"bar\"", "... got the value we expected")
        is(i.next().toString, "application/json; v=\"2\"", "... got the value we expected")
    }


}
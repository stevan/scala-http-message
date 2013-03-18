package com.iinteractive.http.headers

import com.iinteractive.test._

class PriorityListTestSuite001 extends TestMore {

    subtest("constructor add") {
        val pl = new PriorityList(
            new PriorityItem("utf8"),
            new PriorityItem("utf16", Some(0.5)),
            new PriorityItem("latin-1"),
            new PriorityItem("ascii", Some(0.2))
        )

        val i = pl.iterator
        
        is(i.next().toString, "utf8", "... got the top priority")
        is(i.next().toString, "latin-1", "... got the next priority")
        is(i.next().toString, "utf16; q=0.5", "... got the next priority")
        is(i.next().toString, "ascii; q=0.2", "... got the next priority")

        is(pl.toString, "utf8, utf16; q=0.5, latin-1, ascii; q=0.2", "... got the output we expected")
    }

    subtest("manually add") {
        val pl = new PriorityList()

        pl.add("utf8")
        pl.add("utf16", 0.5)
        pl.add("latin-1")
        pl.add("ascii", 0.2)

        val i = pl.iterator
        
        is(i.next().toString, "utf8", "... got the top priority")
        is(i.next().toString, "latin-1", "... got the next priority")
        is(i.next().toString, "utf16; q=0.5", "... got the next priority")
        is(i.next().toString, "ascii; q=0.2", "... got the next priority")

        is(pl.toString, "utf8, utf16; q=0.5, latin-1, ascii; q=0.2", "... got the output we expected")
    }

    subtest("order preserved") {
        val pl = new PriorityList(
            new PriorityItem("utf8"),
            new PriorityItem("latin-1"),
            new PriorityItem("ascii", Some(0.2)),
            new PriorityItem("utf16", Some(0.5))
        )

        is(pl.toString, "utf8, latin-1, ascii; q=0.2, utf16; q=0.5", "... got the output we expected")
    }

}
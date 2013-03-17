package com.iinteractive.http.headers

import com.iinteractive.test._

class PriorityListTestSuite001 extends TestMore {

    val pl = new PriorityList()

    pl.add("utf8")
    pl.add("utf16", 0.5)
    pl.add("latin-1")
    pl.add("ascii", 0.2)

    val i = pl.iterator
    
    is(i.next().value, "utf8", "... got the top priority")
    is(i.next().value, "latin-1", "... got the next priority")
    is(i.next().value, "utf16", "... got the next priority")
    is(i.next().value, "ascii", "... got the next priority")

    is(pl.toString, "utf8; q=1.0, utf16; q=0.5, latin-1; q=1.0, ascii; q=0.2", "... got the output we expected")

}
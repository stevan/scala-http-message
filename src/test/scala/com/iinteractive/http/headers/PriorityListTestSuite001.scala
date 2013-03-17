package com.iinteractive.http.headers

import com.iinteractive.test._

class PriorityListTestSuite001 extends TestMore {

    val pl = new PriorityList()

    pl.add(new MediaType("audio", "mp3"))
    pl.add(new MediaType("text", "html", 0.5))
    pl.add(new MediaType("text", "plain"))
    pl.add(new MediaType("text", "xml", 0.2))

    val i = pl.iterator
    
    is(i.next().toString, "audio/mp3", "... got the top priority")
    is(i.next().toString, "text/plain", "... got the next priority")
    is(i.next().toString, "text/html", "... got the next priority")
    is(i.next().toString, "text/xml", "... got the next priority")

}
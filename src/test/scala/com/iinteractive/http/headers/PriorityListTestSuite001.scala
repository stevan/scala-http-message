package com.iinteractive.http.headers

import com.iinteractive.test._

class PriorityListTestSuite001 extends TestMore {

    val pl = new PriorityList()

    pl.add(new MediaType("text", "html", 0.5))
    pl.add(new MediaType("text", "plain"))
    pl.add(new MediaType("text", "xml", 0.2))

    val i = pl.iterator
    while (i.hasNext) {
        diag(i.next().toString)
    }

    pass("... placeholder")

}
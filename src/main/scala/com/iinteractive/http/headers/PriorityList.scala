package com.iinteractive.http.headers

import scala.collection.mutable.PriorityQueue

class PriorityItem (val value: String) extends WithQuality {

    def this(v: String, q: Option[Double]) = {
        this(v)
        quality = q
    }

    override def toString = value + (if (hasQuality) ("; " + qualityToString) else "")
}

class PriorityList {

    def this (i: PriorityItem*) = {
        this()
        queue ++= i
    }

    object QualityOrdering extends Ordering[PriorityItem] {
        def compare(x: PriorityItem, y: PriorityItem) = x.getQuality.compare(y.getQuality)
    }

    private val queue: PriorityQueue[PriorityItem] = new PriorityQueue[PriorityItem]()(QualityOrdering)

    def add(i: PriorityItem)      = queue += i
    def add(v: String)            = queue += new PriorityItem(v)
    def add(v: String, q: Double) = queue += new PriorityItem(v, Some(q))

    def iterator = queue.clone().dequeueAll.iterator

    override def toString = queue.mkString(", ")

}
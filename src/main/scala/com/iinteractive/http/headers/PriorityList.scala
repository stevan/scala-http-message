package com.iinteractive.http.headers

import scala.collection.mutable.PriorityQueue

class PriorityItem (val value: String) extends WithQuality {

    def this(v: String, q: Double) = {
        this(v)
        quality = q
    }

    override def toString = value + "; q=" + quality 
}

class PriorityList {

    def this (i: PriorityItem*) = {
        this()
        queue ++= i
    }

    object QualityOrding extends Ordering[PriorityItem] {
        def compare(x: PriorityItem, y: PriorityItem) = x.quality.compare(y.quality)
    }

    private val queue: PriorityQueue[PriorityItem] = new PriorityQueue[PriorityItem]()(QualityOrding)

    def add(i: PriorityItem)      = queue += i
    def add(v: String)            = queue += new PriorityItem(v)
    def add(v: String, q: Double) = queue += new PriorityItem(v, q)

    def iterator = queue.clone().dequeueAll.iterator

    override def toString = queue.mkString(", ")

}
package com.iinteractive.http.headers

import scala.collection.mutable.PriorityQueue

class PriorityList {

    object QualityOrding extends Ordering[WithQuality] {
        def compare(x: WithQuality, y: WithQuality) = x.quality.compare(y.quality)
    }

    private val queue: PriorityQueue[WithQuality] = new PriorityQueue[WithQuality]()(QualityOrding)

    def add(i: WithQuality) = queue += i

    def iterator = queue.clone().dequeueAll.iterator

}
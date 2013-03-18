package com.iinteractive.http.headers

import scala.collection.mutable.PriorityQueue

/**
 * SEE ALSO - http://tools.ietf.org/html/rfc2616#section-3.7
 */

class MediaType(val major: String, val minor: String) extends WithParams with WithQuality {

    def this (major: String, minor: String, q: Option[Double]) = {
        this(major, minor)
        quality = q
    }

    private val fullType = major + "/" + minor

    def matchesAll: Boolean = this.fullType == "*/*" && paramsAreEmpty

    def equals       (other: MediaType): Boolean = other.fullType == this.fullType && paramsMatch(other.params)
    def exactMatch   (other: MediaType): Boolean = typeMatches(other) && paramsMatch(other.params)
    def looseMatch   (other: MediaType): Boolean = typeMatches(other) && paramsAreSubset(other.params)
    def typeMatches  (other: MediaType): Boolean = {
        if ((other.fullType == "*")   ||
            (other.fullType == "*/*") ||
            (other.fullType == this.fullType)) {
            true
        } else {
            (other.major == this.major) && (other.minor == "*")
        }
    }

    override def toString = (fullType + 
        (if (hasQuality) ("; " + qualityToString) else "") + 
        (if (hasQuality) paramsToString else (if (paramsAreEmpty) "" else "; " + paramsToString))) 
}

class MediaTypeList {

    def this (i: MediaType*) = {
        this()
        queue ++= i
    }

    object MediaTypeOrdering extends Ordering[MediaType] {
        /**
         * From RFC-2616 sec14
         * Media ranges can be overridden by more specific
         * media ranges or specific media types. If more
         * than one media range applies to a given type,
         * the most specific reference has precedence.
         */
        def compare(x: MediaType, y: MediaType) = 
            if (x.getQuality == y.getQuality) {
                if (x.matchesAll) -1 else 
                    if (y.matchesAll) 1 else 
                        if (x.minor == "*") -1 else 
                            if (y.minor == "*") 1 else 
                                if (x.paramsAreEmpty) -1 else 
                                    if (y.paramsAreEmpty) 1 else 0
            } else {
                x.getQuality.compare(y.getQuality)
            }
    }

    private val queue: PriorityQueue[MediaType] = new PriorityQueue[MediaType]()(MediaTypeOrdering)

    def add(i: MediaType)                            = queue += i
    def add(major: String, minor: String)            = queue += new MediaType(major, minor)
    def add(major: String, minor: String, q: Double) = queue += new MediaType(major, minor, Some(q))

    def iterator = queue.clone().dequeueAll.iterator

    override def toString = queue.mkString(", ")

}
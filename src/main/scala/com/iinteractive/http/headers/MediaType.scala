package com.iinteractive.http.headers

class MediaType(val major: String, val minor: String) extends WithParams with WithQuality {

    def this (major: String, minor: String, quality: Double) = {
        this(major, minor)
        this.quality = quality
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

    override def toString = fullType
}
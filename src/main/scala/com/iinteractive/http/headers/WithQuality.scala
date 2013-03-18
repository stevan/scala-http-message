package com.iinteractive.http.headers

trait WithQuality {
    var quality: Option[Double] = None

    def hasQuality: Boolean = quality.isDefined
    def getQuality: Double  = quality.getOrElse(1.0)

    def qualityToString = if (hasQuality) "q=" + quality.get else ""
}
package com.iinteractive.http.headers

import java.util.Locale

import org.joda.time.{DateTime, DateTimeZone}
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

import scala.collection.immutable.Map

import com.iinteractive.http.HTTP

class HTTPDate (
        private val date: DateTime = new DateTime()
    ) {
    
    def second     = date.getSecondOfMinute
    def minute     = date.getMinuteOfHour
    def hour       = date.getHourOfDay
    def dayOfMonth = date.getDayOfMonth
    def dayOfWeek  = date.getDayOfWeek
    def month      = date.getMonthOfYear
    def year       = date.getYear
    
    def format(f: DateTimeFormatter) = f.print(date)

    override def toString = date.toString()
}

object HTTPDate {

    val formats = Map(
        "RFC-1123"       -> DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss ZZ").withLocale(Locale.US).withZone(DateTimeZone.forID("Etc/GMT")),
        "RFC-1036"       -> DateTimeFormat.forPattern("EEEE, dd-MMM-yy HH:mm:ss ZZ").withLocale(Locale.US).withZone(DateTimeZone.forID("Etc/GMT")),
        "ANSI-C-asctime" -> DateTimeFormat.forPattern("EEE MMM  d HH:mm:ss yyyy").withLocale(Locale.US).withZone(DateTimeZone.forID("Etc/GMT"))
    )

    def apply(d: String): HTTPDate = {
        val date = if (d.endsWith("GMT")) d.replace("GMT", "+00:00") else d
        try {
            return new HTTPDate(new DateTime(date, DateTimeZone.forID("Etc/GMT")))
        } catch {
            case e:IllegalArgumentException => {
                val i = formats.valuesIterator
                while (i.hasNext) {
                    val p = i.next()
                    try {
                        return new HTTPDate(p.parseDateTime(date))
                    } catch {
                        case _: Throwable => {} // ignore this and try the next format
                    }
                }
            }
        }
        throw new HTTP.Errors.InvalidHTTPDate(date)
    }
}

/**
 * SEE ALSO:
 * - http://svn.ettrema.com/svn/milton/tags/milton-1.3b/milton-api/src/main/java/com/bradmcevoy/http/DateUtils.java
 * - https://svn.apache.org/repos/asf/commons/dormant/feedparser/trunk/src/java/org/apache/commons/feedparser/tools/ISO8601DateParser.java
 */




package com.iinteractive.http.headers

import java.util.{Date, Calendar, TimeZone, Locale}
import java.text.SimpleDateFormat

import scala.collection.immutable.Map

import com.iinteractive.http.HTTP

class HTTPDate (private val date: Date) {

    private lazy val calendar = initCalendar 

    private def initCalendar: Calendar = {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.US)
        calendar.setTime(date)
        calendar
    }

    def second     = calendar.get(Calendar.SECOND)
    def minute     = calendar.get(Calendar.MINUTE)
    def hour       = calendar.get(Calendar.HOUR_OF_DAY)
    def dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    def dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK)
    def month      = calendar.get(Calendar.MONTH)
    def year       = calendar.get(Calendar.YEAR)
    def epoch      = date.getTime()

    def format(pattern: String) = {
        val formatter = new SimpleDateFormat(pattern, Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        formatter.format(date);
    }

    override def toString = date.toString()
}

object HTTPDate {

    val formats = Map(
        "WebDAV"          -> "yyyy-MM-ddTHH:mm:ssZ",
        "Response-Header" -> "E, dd MMM yyyy HH:mm:ss z",
        "RFC-1123"        -> "EEE, dd MMM yyyy HH:mm:ss zzz",
        "RFC-1036"        -> "EEEE, dd-MMM-yy HH:mm:ss zzz",
        "ANSI-C-asctime"  -> "EEE MMM d HH:mm:ss yyyy",
        "ANSI-C-asctime2" -> "EEE MMM yyyy HH:mm:ss zzz"
    )

    private lazy val dateParser: SimpleDateFormat = initDateParser

    private def initDateParser: SimpleDateFormat = {
        val calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0);
        val parser = new SimpleDateFormat();
        parser.setTimeZone(TimeZone.getTimeZone("GMT"));
        parser.set2DigitYearStart(calendar.getTime()); 
        parser
    }

    def apply(d: String): HTTPDate = {
        val i = formats.valuesIterator
        while (i.hasNext) {
            dateParser.applyPattern(i.next())
            try {
                 new HTTPDate(dateParser.parse(d))
            } catch {
                // ignore this and try 
                // the next format
                case _: Throwable => {}
            }
        }
        throw new HTTP.Errors.InvalidHTTPDate (d)
    }
}


/**
 * NOTES:
 * - java.util.Date sucks and is deprecated, look into using jodatime instead
 *
 * SEE ALSO:
 * - http://svn.ettrema.com/svn/milton/tags/milton-1.3b/milton-api/src/main/java/com/bradmcevoy/http/DateUtils.java
 */
package com.iinteractive.http.headers

import com.iinteractive.test._

class HTTPDateTestSuite001 extends TestMore {

    val ISO8601 = HTTPDate("2004-06-14T19:20:30+00:00")

    is(ISO8601.second, 30, "... got the right seconds")
    is(ISO8601.minute, 20, "... got the right minutes")
    is(ISO8601.hour, 19, "... got the right hours")
    is(ISO8601.month, 6, "... got the right month")
    is(ISO8601.year, 2004, "... got the right year")
    is(ISO8601.dayOfMonth, 14, "... got the right day or month")

    val ISO8601_2 = HTTPDate("2004-06-14T19:20:30GMT")

    is(ISO8601_2.second, 30, "... got the right seconds")
    is(ISO8601_2.minute, 20, "... got the right minutes")
    is(ISO8601_2.hour, 19, "... got the right hours")
    is(ISO8601_2.month, 6, "... got the right month")
    is(ISO8601_2.year, 2004, "... got the right year")
    is(ISO8601_2.dayOfMonth, 14, "... got the right day or month")
    
    val RFC_1123 = HTTPDate("Sat, 16 Mar 2013 16:14:50 +00:00")

    is(RFC_1123.second, 50, "... got the right seconds")
    is(RFC_1123.minute, 14, "... got the right minutes")
    is(RFC_1123.hour, 16, "... got the right hours")
    is(RFC_1123.month, 3, "... got the right month")
    is(RFC_1123.year, 2013, "... got the right year")
    is(RFC_1123.dayOfMonth, 16, "... got the right day or month")

    val RFC_1123_2 = HTTPDate("Sat, 16 Mar 2013 16:14:50 GMT")

    is(RFC_1123_2.second, 50, "... got the right seconds")
    is(RFC_1123_2.minute, 14, "... got the right minutes")
    is(RFC_1123_2.hour, 16, "... got the right hours")
    is(RFC_1123_2.month, 3, "... got the right month")
    is(RFC_1123_2.year, 2013, "... got the right year")
    is(RFC_1123_2.dayOfMonth, 16, "... got the right day or month")

    val ANSI_C_asctime = HTTPDate("Thu Feb  3 00:00:00 1994")

    is(ANSI_C_asctime.second, 0, "... got the right seconds")
    is(ANSI_C_asctime.minute, 0, "... got the right minutes")
    is(ANSI_C_asctime.hour, 0, "... got the right hours")
    is(ANSI_C_asctime.month, 2, "... got the right month")
    is(ANSI_C_asctime.year, 1994, "... got the right year")
    is(ANSI_C_asctime.dayOfMonth, 3, "... got the right day or month")

    val RFC_1036 = HTTPDate("Sunday, 06-Nov-94 08:49:37 +00:00")      

    is(RFC_1036.second, 37, "... got the right seconds")
    is(RFC_1036.minute, 49, "... got the right minutes")
    is(RFC_1036.hour, 8, "... got the right hours")
    is(RFC_1036.month, 11, "... got the right month")
    is(RFC_1036.year, 1994, "... got the right year")
    is(RFC_1036.dayOfMonth, 6, "... got the right day or month")

    val RFC_1036_2 = HTTPDate("Sunday, 06-Nov-94 08:49:37 GMT")      

    is(RFC_1036_2.second, 37, "... got the right seconds")
    is(RFC_1036_2.minute, 49, "... got the right minutes")
    is(RFC_1036_2.hour, 8, "... got the right hours")
    is(RFC_1036_2.month, 11, "... got the right month")
    is(RFC_1036_2.year, 1994, "... got the right year")
    is(RFC_1036_2.dayOfMonth, 6, "... got the right day or month")

}
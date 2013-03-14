# HTTPMessage

This is a scala library for the parsing and creation of HTTP messages. It
is based heavily on the [HTTP::Message](https://metacpan.org/release/HTTP-Message)
library for Perl.

## Why?

While there are a number of really nice HTTP libraries out there for both
Java and Scala, they all are part of a larger project. This means that
either they are a heavier dependency or they are specifically focused on
one task (HTTP clients or HTTP servers, for instance). The goal of this
library is to provide a task agnostic means of dealing with HTTP.

## See Also

The closest I have seen to what I am looking for is the [org.apache.http](http://hc.apache.org/httpcomponents-core-ga/httpcore/xref/index.html)
package, but even that deals with things like socket connections, etc. which
is more then what I am looking for.


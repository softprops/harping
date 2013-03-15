package harping

object Default {
  val version = "1.1"
}

case class Log(creator: Creator,
               pages: Iterable[Page],
               entries: Iterable[Entry],
               browser: Option[Browser] = None,
               version: String = Default.version,
               comment: Option[String] = None)

case class Creator(name: String,
                   version: String,
                   comment: Option[String] = None)

case class Browser(name: String,
                   version: String,
                   comment: Option[String] = None)

case class Page(id: String,
                title: String,
                startedDateTime: String,
                pageTiming: PageTimings, 
                comment: Option[String] = None)

case class PageTimings(onContentLoaded: Int,
                       onLoad: Int,
                       comment: Option[String] = None)

case class Entry(startedDateTime: String,
                 time: Int,
                 /*request: Request,
                 response: Response,
                 cache: Cache,
                 timings: Timings,*/
                 serverIPAddress: Option[String] = None,
                 connection: Option[String] = None,
                 pageRef: Option[String] = None,
                 comment: Option[String] = None)

case class Request(method: String,
                   url: String,
                   httpVersion: String,
                   cookies: Iterable[Cookie],
                   headers: Iterable[Header],
                   queryString: Iterable[QueryParam],
                   headersSize: Int,
                   bodySize: Int,
                   postData: Option[PostData] = None,
                   comment: Option[String] = None)

case class Response(status: Int,
                    statusText: String,
                    httpVersion: String,
                    cookies: Iterable[Cookie],
                    headers: Iterable[Header],
                    content: Content,
                    redirectURL: String,
                    headersSize: Int,
                    bodySize: Int,
                    comment: Option[String] = None)

case class Cookie(name: String,
                  value: String,
                  path: Option[String] = None,
                  secure: Option[Boolean] = None,
                  domain: Option[String] = None,
                  expires: Option[String] = None,
                  httpOnly: Option[Boolean] = None,
                  comment: Option[String] = None)

case class Header(name: String, value: String, comment: Option[String] = None)

case class QueryParam(name: String, value: String, comment: Option[String] = None)

case class PostData(mime: String,
                    params: Iterable[PostParam],
                    text: String,
                    comment: Option[String] = None)

case class PostParam(name: String,
                     value: Option[String] = None,
                     fileName: Option[String] = None,
                     contentType: Option[String] = None,
                     comment: Option[String] = None)

case class Content(size: Int,
                   mime: String,
                   text: Option[String] = None,
                   encoding: Option[String] = None,
                   compression: Option[Int] = None,
                   comment: Option[String] = None)

case class Cache(beforeRequest: Option[CacheInfo],
                 afterRequest: Option[CacheInfo],
                 comment: Option[String] = None)

case class CacheInfo(lastAccess: String,
                     etag: String,
                     hitCount: Int,
                     expires: Option[String] = None,
                     comment: Option[String] = None)

case class Timings(send: Int,
                   waiting: Int,
                   receive: Int,
                   blocked: Option[Int] = None,
                   dns: Option[Int] = None,
                   connect: Option[Int] = None,
                   ssl: Option[Int] = None,
                   comment: Option[String] = None)

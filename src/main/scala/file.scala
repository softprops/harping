package harping

object File {
  object Reader {
    def apply(path: String): Option[String] =
      path match {
        case http if (http.startsWith("http")) =>
          remote(http)
        case fs =>
          local(fs)
      }    
    private def remote(uri: String) = {
      import dispatch._
      Some(Http(url(uri) > as.String)())
    }
    private def local(path: String) = None
  }

  // ideally, this should be `pipeable`
  // meaning we should be able to create
  // and instance of this and pipe it
  // a stream of har entries
  // rather than creating a whole tree in memory
  // then serializing it in bulk.
  // I'm not sure how that would be possible 
  // using json4s. think about it.
  object Writer {
    // def appender(...)
  }
}

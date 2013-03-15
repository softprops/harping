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
}

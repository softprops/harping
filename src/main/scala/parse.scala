package harping

object Parse {
  import org.json4s._
  import org.json4s.native.JsonMethods._
  import org.json4s.JsonDSL._

  // todo if ast parsing becomes a problem
  // let https://github.com/json4s/json4s#low-level-pull-parser-api 
  // be a solution

  def apply(in: String): Either[String, Log] = {
    val json = parse(in)
    (for {
      JObject(fields)                <- json
      ("log", JObject(log))          <- fields
      ("creator", JObject(creatorf)) <- log
    } yield {

      val creator = for {
        ("name", JString(name))       <- creatorf
        ("version", JString(version)) <- creatorf
      } yield Creator(name, version)

      val browser = for {
        ("browser", JObject(browser)) <- log
        ("name", JString(name))       <- browser
        ("version", JString(version)) <- browser
      } yield Browser(name, version)

      val version = for {
        ("version", JString(version)) <- log
      } yield version

      val ps = for {
        ("pages", JArray(pgs)) <- log
      } yield pgs

      val es = for {
        ("entries", JArray(ents)) <- log        
      } yield ents

      creator.headOption.map( ctr =>
        Log(ctr,
            ps.headOption.map(pages).getOrElse(Nil),
            es.headOption.map(entries).getOrElse(Nil),
            browser.headOption,
            version.headOption.getOrElse(Default.version))
       )
    }).flatten.headOption.toRight("failed to parse har")
  }

  def entries(entries: List[JValue]) =
    for {
      JObject(entry) <- entries
      ("startedDateTime", JString(started)) <- entry
      ("time", JInt(time)) <- entry
    } yield Entry(started,
                  time.toInt)

  def pages(pages: List[JValue]) =
    for {
      JObject(page)             <- pages
      ("id", JString(id))       <- page
      ("title", JString(title)) <- page
      ("startedDateTime", JString(started)) <- page
      ("pageTimings", JObject(pageTimings)) <- page        
    } yield Page(id, title, started, timings(pageTimings)(0))

  def timings(timings: List[JField]) =
    for {
      ("onContentLoad", JInt(cl)) <- timings
      ("onLoad", JInt(ol))        <- timings
    } yield PageTimings(cl.toInt, ol.toInt)
}

package harping

object Parse {
  import org.json4s._
  import org.json4s.native.JsonMethods._
  import org.json4s.JsonDSL._

  // todo if ast parsing becomes a problem
  // let https://github.com/json4s/json4s#low-level-pull-parser-api 
  // be a solution

  def apply(in: String) = {
    val json = parse(in)
    for {
      JObject(fields)               <- json
      ("log", JObject(log))         <- fields
      ("creator", JObject(creator)) <- log
    } yield {
      for {
        ("name", JString(name))       <- creator
        ("version", JString(version)) <- creator
      } yield Creator(name, version)
    }
  }
}

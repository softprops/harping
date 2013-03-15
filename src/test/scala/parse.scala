package harping

import org.scalatest.FunSpec
import dispatch._

class ParseSpec extends FunSpec {

  def espn = url("http://demo.ajaxperformance.com/har/espn.har")

  def time[T](label: String)(blk: => T): T = {
    val b = System.currentTimeMillis
    val r = blk
    println("%s time %sms" format(label, System.currentTimeMillis - b))
    r
  }

  describe("parse") {
    it ("should parse a har file") {
      val str = time("fetch")(Http(espn > as.String)())
      val log = time("parse")(Parse(str))
      println("out %s" format log)
    }
  }
}

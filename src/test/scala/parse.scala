package harping

import org.scalatest.FunSpec
import dispatch._

class ParseSpec extends FunSpec {

  def espn = "http://demo.ajaxperformance.com/har/espn.har"

  def time[T](label: String)(blk: => T): T = {
    val b = System.currentTimeMillis
    val r = blk
    println("%s time %sms" format(label, System.currentTimeMillis - b))
    r
  }

  describe("parse") {
    it ("should parse a har file") {
      val str = time("remote read")(File.Reader(espn))
      str.map { in =>
        val log = time("parse")(Parse(in))
        println("out %s" format log)
      }.getOrElse("empty har contents")
    }
  }
}

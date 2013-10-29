package api

import akka.pattern.AskSupport
import akka.util.Timeout
import scala.concurrent.duration._

trait AsyncSupport extends AskSupport {
  implicit val timeout = Timeout(30 seconds)
}

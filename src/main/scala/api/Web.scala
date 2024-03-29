package api

import akka.io.IO
import spray.can.Http
import core._

trait Web { this: Api with CoreActors with Core =>

  IO(Http)(system) ! Http.Bind(rootService, "0.0.0.0", port = 8080)

}

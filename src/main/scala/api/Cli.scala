package api

import core._
import model.messages._

object Cli extends App with BootedCore with CoreActors {

  val master = system.actorSelection("/user/master")

  master ! Start(args(0))

}

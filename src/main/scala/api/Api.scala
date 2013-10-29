package api

import spray.routing.RouteConcatenation
import akka.actor.Props
import core.{ CoreActors, Core }

trait Api extends RouteConcatenation {
  this: CoreActors with Core =>

  private implicit val _ = system.dispatcher

  val routes =
    new TweetStreamService(system.actorSelection("/user/master")).route //~
  //      new StatusStreamService(system).route

  val rootService = system.actorOf(Props(new services(routes)), "rootService")
}

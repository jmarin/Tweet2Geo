package api

import akka.actor.Actor
import spray.routing.{ Route, HttpService }

class services(route: Route) extends Actor with HttpService {

  implicit def actorRefFactory = context

  def receive = runRoute(route)

}

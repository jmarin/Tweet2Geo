package core

import akka.actor.{ Props, Actor, ActorLogging }

class Tweet2GeoActor extends Actor with ActorLogging {

  override def preStart() {
    log.debug("Actor started at: " + self.path)
  }

  def receive = {
    case _ => ???
  }
}

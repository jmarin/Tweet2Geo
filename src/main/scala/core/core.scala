package core

import akka.actor.{ Props, ActorSystem }
import akka.util.Timeout

trait Core {
  implicit def system: ActorSystem

  implicit val timeout = Timeout(5000)
}

trait BootedCore extends Core {
  override implicit lazy val system = ActorSystem("tweet2geo-system")

  sys.addShutdownHook(system.shutdown())
}

trait CoreActors { this: Core =>
  val masterActor = system.actorOf(Props[MasterActor], "master")

}

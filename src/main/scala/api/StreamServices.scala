package api

import akka.actor.{ ActorSystem, Props }
import spray.routing.Directives
import spray.json.DefaultJsonProtocol
import spray.httpx.SprayJsonSupport
import scala.concurrent.ExecutionContext

class StatusStreamService(system: ActorSystem)(implicit ec: ExecutionContext) extends Directives with DefaultJsonProtocol with SprayJsonSupport with AsyncSupport {

  val route = {
    path("stream") {
      get { request =>
        system.actorOf(Props(new StatusStream(request.responder)))
      }
    } ~
      path("geostream") {
        get { request =>
          system.actorOf(Props(new TweetGeoStream(request.responder)))
        }
      }
  }

}

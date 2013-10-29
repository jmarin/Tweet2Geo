package api

import spray.routing.Directives
import spray.json.DefaultJsonProtocol
import spray.httpx.SprayJsonSupport
import spray.http.{ ChunkedResponseStart, ChunkedMessageEnd, MessageChunk }
import spray.can.Http
import akka.actor.{ ActorSelection, Actor, ActorRef, ActorLogging }
import scala.concurrent.ExecutionContext
import model.messages.{ Start, Stop, Tweet }
import twitter4j._

class TweetStreamService(masterActor: ActorSelection)(implicit ec: ExecutionContext) extends Directives with DefaultJsonProtocol with SprayJsonSupport with AsyncSupport {

  val route = {
    get {
      path("status") {
        complete {
          "OK"
        }
      }
    } ~
      post {
        path("start") {
          parameters('searchTerm.as[String]) {
            searchTerm =>
              complete {
                masterActor ! Start(searchTerm)
                s"Twitter Stream started with search: $searchTerm"
              }
          }
        } ~
          path("stop") {
            complete {
              masterActor ! Stop
              "Twitter Stream stopped"
            }
          }
      }
  }

}

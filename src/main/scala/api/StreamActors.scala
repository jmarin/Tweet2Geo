package api

import akka.actor.{ Actor, ActorRef, ActorLogging }
import spray.http.{ MessageChunk, ChunkedMessageEnd, ChunkedResponseStart }
import spray.can.Http
import spray.http.{ HttpResponse, HttpEntity }
import spray.http.ContentType
import spray.http.MediaTypes._
import spray.http.HttpCharsets
import spray.http.HttpCharsets.`UTF-8`
import model.messages.Tweet

class StatusStream(responder: ActorRef) extends Actor with ActorLogging {

  private val header = (1 to 1024).map(_ => "\uFEFF").mkString("")

  responder ! ChunkedResponseStart(
    HttpResponse(entity = HttpEntity(ContentType(`text/plain`, HttpCharsets.`UTF-8`), header)))

  override def preStart = {
    context.system.eventStream.subscribe(context.self, classOf[Tweet])
  }

  def receive = {
    case Tweet(None) =>
      responder ! ChunkedMessageEnd()
      context.stop(self)

    case Tweet(status) =>
      responder ! MessageChunk(status.get.toString)

    case ev: Http.ConnectionClosed =>
      log.debug("Connection closed")
      context.stop(self)

  }

}

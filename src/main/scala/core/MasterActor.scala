package core

import akka.actor.Props
import model.messages.{ Start, Stop }
import java.io.File
import model.messages.{ Start, Stop, Tweet }
import twitter4j._

class MasterActor extends Tweet2GeoActor {

  val twitterStream = TwitterStreamer.getStream(new File("src/main/resources/twitter-config.conf"))

  override def receive = {
    case Start(searchTerm) =>
      log.debug("Master actor started")
      twitterStream.addListener(statusListener)
      val params: Array[String] = Array(searchTerm)
      twitterStream.filter(new FilterQuery().track(params))
      context.actorOf(Props[StatusPrintActor], "statusPrint")

    case Stop =>
      log.debug("Master actor stopped")
      twitterStream.cleanUp
      twitterStream.shutdown
      context stop context.actorFor("/user/master/statusPrint")
  }

  def statusListener = new StatusListener() {
    def onStatus(status: Status) {
      context.system.eventStream.publish(Tweet(Some(status)))
    }
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) { ex.printStackTrace }
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }
}

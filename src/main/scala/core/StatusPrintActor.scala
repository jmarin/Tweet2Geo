package core

import akka.actor.Actor
import twitter4j.Status
import model.messages.Tweet

class StatusPrintActor extends Actor {

  override def preStart = {
    context.system.eventStream.subscribe(context.self, classOf[Tweet])
  }

  def receive = {
    case tweet: Tweet => println(tweet.status.get.getText)

  }

}

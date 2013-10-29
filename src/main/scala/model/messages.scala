package model

import twitter4j.Status

object messages {
  case class Start(searchTerm: String)
  case class Stop()

  case class Tweet(status: Option[Status])

}
